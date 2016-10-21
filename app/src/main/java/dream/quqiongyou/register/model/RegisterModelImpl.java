package dream.quqiongyou.register.model;

import com.google.gson.Gson;

import java.util.LinkedHashMap;

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
 * Created by SomeOneInTheWorld on 2016/10/17.
 */
public class RegisterModelImpl implements RegisterModel{
    @Override
    public void requestIdentifyingCode(String phone, final OnCallBackByRegisterModel listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        OkService.IdentifyingCodeGetService identifyingCodeGetService = retrofit.create(OkService.IdentifyingCodeGetService.class);
        Gson gson = new Gson();
        LinkedHashMap<String,String> paramsMap = new LinkedHashMap<>();
        paramsMap.put("phone",phone);
        String strEntity = gson.toJson(paramsMap);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),strEntity);
        identifyingCodeGetService.getIdentifyingCode(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        OkService.RegisterService service = retrofit.create(OkService.RegisterService.class);
        Gson gson = new Gson();
        LinkedHashMap<String,String> paramsMap = new LinkedHashMap<>();
        paramsMap.put("account",phone);
        paramsMap.put("password",password);
        String strEntity = gson.toJson(paramsMap);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),strEntity);
        service.getRegisterResult(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
