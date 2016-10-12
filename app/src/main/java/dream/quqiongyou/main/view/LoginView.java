package dream.quqiongyou.main.view;

import dream.quqiongyou.bean.QuUser;

/**
 * Created by SomeOneInTheWorld on 2016/10/3.
 */
public interface LoginView {
    void showProgress();
    void hideProgress();
    void loginSuccess(QuUser user);
    void loginFail(String message);
}
