package dream.quqiongyou.register.model;

import dream.quqiongyou.bean.QuUser;
import dream.quqiongyou.bean.Response;
import dream.quqiongyou.common.Constants;
import dream.quqiongyou.service.OkService;
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
        OkService.IdentifyingCodeGetService service = retrofit.create(OkService.IdentifyingCodeGetService.class);
        QuUser user = new QuUser();
        user.setPhone(phone);
        service.getIdentifyingCode(user)
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
}
