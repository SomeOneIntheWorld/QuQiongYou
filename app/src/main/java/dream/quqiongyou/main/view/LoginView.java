package dream.quqiongyou.main.view;

/**
 * Created by SomeOneInTheWorld on 2016/10/3.
 */
public interface LoginView {
    void showProgress();
    void hideProgress();
    void loginResult(boolean isSuccess);
}
