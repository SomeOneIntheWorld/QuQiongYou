package dream.quqiongyou.register.view;

/**
 * Created by SomeOneInTheWorld on 2016/10/17.
 */
public interface RegisterView {
    void showProgressbar();
    void hideProgressbar();
    void onSuccessRequestForCode(String code);
    void onFailRequestForCode(String message);
    void onSuccessRegister();
    void onFailRegister();
}
