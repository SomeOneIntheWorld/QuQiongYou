package dream.quqiongyou.main.model;

import dream.quqiongyou.bean.QuUser;
import dream.quqiongyou.bean.Response;
import dream.quqiongyou.common.Constants;
import dream.quqiongyou.service.LoginService;
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
    public void checkQuUserByModel(QuUser user, final CallBackByLoginModel listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        LoginService loginService = retrofit.create(LoginService.class);
        loginService.getUserMessage(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<QuUser>>(){
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onNext(Response<QuUser> resposne) {
                        if(resposne.code == Constants.LOGIN_SUCCESS){
                            listener.onSuccess(resposne.data);
                        }else if(resposne.code == Constants.LOGIN_ERROR_PASSWORD){
                            listener.onFail("密码错误");
                        }else if(resposne.code == Constants.LOGIN_USRE_NOT_EXIST){
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
