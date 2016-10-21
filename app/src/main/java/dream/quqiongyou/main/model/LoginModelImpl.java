package dream.quqiongyou.main.model;

import com.google.gson.Gson;

import java.util.LinkedHashMap;

import dream.quqiongyou.bean.QuUser;
import dream.quqiongyou.bean.Response;
import dream.quqiongyou.common.Constants;
import dream.quqiongyou.service.OkService;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by SomeOneInTheWorld on 2016/10/3.
 */
public class LoginModelImpl implements LoginModel {
    private final String TAG = "LoginTest";
    @Override
    public void checkQuUserByModel(final String account, String password, final CallBackByLoginModel listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        OkService.LoginService loginService = retrofit.create(OkService.LoginService.class);
        Gson gson = new Gson();
        LinkedHashMap<String,String> paramsMap = new LinkedHashMap<>();
        paramsMap.put("account",account);
        paramsMap.put("password",password);
        String strEntity = gson.toJson(paramsMap);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),strEntity);
        loginService.getUserMessage(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<QuUser>>(){
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onNext(Response<QuUser> response) {
                        if(response.code == Constants.LOGIN_SUCCESS){
                            response.data.setAccount(account);
                            listener.onSuccess(response.data);
                        }else if(response.code == Constants.LOGIN_ERROR_PASSWORD){
                            listener.onFail("密码错误");
                        }else if(response.code == Constants.LOGIN_USRE_NOT_EXIST){
                            listener.onFail("该用户不存在");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFail(e.getMessage());
                    }
                });
    }
}
