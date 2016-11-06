package dream.quqiongyou.main.model;

import com.google.gson.Gson;

import java.util.LinkedHashMap;

import dream.quqiongyou.bean.QuUser;
import dream.quqiongyou.bean.Response;
import dream.quqiongyou.common.Constants;
import dream.quqiongyou.service.OkService;
import dream.quqiongyou.utils.RxUtils;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by SomeOneInTheWorld on 2016/10/3.
 */
public class LoginModelImpl implements LoginModel {
    private final String TAG = "LoginTest";
    @Override
    public void checkQuUserByModel(final String account, String password, final CallBackByLoginModel listener) {
        OkService.LoginService loginService = RxUtils.createService(OkService.LoginService.class);
        Gson gson = new Gson();
        LinkedHashMap<String,String> paramsMap = new LinkedHashMap<>();
        paramsMap.put("account",account);
        paramsMap.put("password",password);
        String strEntity = gson.toJson(paramsMap);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),strEntity);
        loginService.getUserMessage(requestBody)
                .compose(RxUtils.<Response<QuUser>>normalSchedulers())
                .subscribe(
                        response -> {
                        if(response.code == Constants.LOGIN_SUCCESS){
                            response.data.setAccount(account);
                            listener.onSuccess(response.data);
                        }else if(response.code == Constants.LOGIN_ERROR_PASSWORD){
                            listener.onFail("密码错误");
                        }else if(response.code == Constants.LOGIN_USRE_NOT_EXIST){
                            listener.onFail("该用户不存在");
                        }
                    },
                        e -> {
                        listener.onFail(e.getMessage());
                    }
                );
    }
}
