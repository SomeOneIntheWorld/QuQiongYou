package dream.quqiongyou.register.presenter;

/**
 * Created by SomeOneInTheWorld on 2016/10/17.
 */
public interface RegisterPresenter {
    void requestIdentifyingCode(String phone);
    void register(String phone,String identifyingCode,String password);
}
