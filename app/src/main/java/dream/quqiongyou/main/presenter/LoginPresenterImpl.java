package dream.quqiongyou.main.presenter;

import dream.quqiongyou.bean.QuUser;
import dream.quqiongyou.main.model.LoginModel;
import dream.quqiongyou.main.model.LoginModelImpl;
import dream.quqiongyou.main.view.LoginView;

/**
 * Created by SomeOneInTheWorld on 2016/10/3.
 */
public class LoginPresenterImpl implements LoginPresenter,LoginModel.CallBackByLoginModel{
    private LoginModel loginModel;
    private LoginView loginView;

    public LoginPresenterImpl(LoginView loginView){
        this.loginModel = new LoginModelImpl();
        this.loginView = loginView;
    }

    @Override
    public void onSuccess() {
        loginView.hideProgress();
        loginView.loginResult(true);
    }

    @Override
    public void onFail() {
        loginView.hideProgress();
        loginView.loginResult(false);
    }

    @Override
    public void checkUser(QuUser user) {
        loginView.showProgress();
        loginModel.checkQuUserByModel(user, this);
    }
}
