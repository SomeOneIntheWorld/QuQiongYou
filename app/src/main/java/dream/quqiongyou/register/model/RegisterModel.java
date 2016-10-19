package dream.quqiongyou.register.model;

/**
 * Created by SomeOneInTheWorld on 2016/10/17.
 */
public interface RegisterModel {
    interface OnCallBackByRegisterModel{
        void onSuccess(String identifyingCode);
        void onFail(String message);
    }
    void requestIdentifyingCode(String phone,OnCallBackByRegisterModel listener);
}
