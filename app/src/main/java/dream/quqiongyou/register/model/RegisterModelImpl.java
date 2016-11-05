package dream.quqiongyou.register.model;

import com.google.gson.Gson;

import java.util.LinkedHashMap;

import dream.quqiongyou.bean.Response;
import dream.quqiongyou.service.OkService;
import dream.quqiongyou.utils.RxUtils;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Subscriber;

/**
 * Created by SomeOneInTheWorld on 2016/10/17.
 */
public class RegisterModelImpl implements RegisterModel{
    @Override
    public void requestIdentifyingCode(String phone, final OnCallBackByRegisterModel listener) {
        OkService.IdentifyingCodeGetService identifyingCodeGetService = RxUtils.createService(OkService.IdentifyingCodeGetService.class);
        Gson gson = new Gson();
        LinkedHashMap<String,String> paramsMap = new LinkedHashMap<>();
        paramsMap.put("phone",phone);
        String strEntity = gson.toJson(paramsMap);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),strEntity);
        identifyingCodeGetService.getIdentifyingCode(requestBody)
                .compose(RxUtils.<Response<String>>normalSchedulers())
                .subscribe(new Subscriber<Response<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFail(e.getMessage());
                    }

                    @Override
                    public void onNext(Response<String> stringResponse) {
                        if("true".equalsIgnoreCase(stringResponse.message)){
                            listener.onSuccess(stringResponse.data);
                        }else if("false".equalsIgnoreCase(stringResponse.message)){
                            listener.onFail("发送短信失败");
                        }else{
                            listener.onFail("该号码已经存在");
                        }
                    }
                });
    }

    @Override
    public void register(String phone, String identifyingCode, String password, final OnCallBackByRegisterModel listener) {
        OkService.RegisterService service = RxUtils.createService(OkService.RegisterService.class);
        Gson gson = new Gson();
        LinkedHashMap<String,String> paramsMap = new LinkedHashMap<>();
        paramsMap.put("account",phone);
        paramsMap.put("password",password);
        String strEntity = gson.toJson(paramsMap);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),strEntity);
        service.getRegisterResult(requestBody)
                .compose(RxUtils.<Response<String>>normalSchedulers())
                .subscribe(new Subscriber<Response<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFail(e.getMessage());
                    }

                    @Override
                    public void onNext(Response<String> stringResponse) {
                        if(stringResponse.code == 102){
                            listener.onSuccessRegister();
                        }else{
                            listener.onFailRegister();
                        }
                    }
                });
    }
}
