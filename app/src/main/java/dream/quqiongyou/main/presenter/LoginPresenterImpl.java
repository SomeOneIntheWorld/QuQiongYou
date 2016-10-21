package dream.quqiongyou.main.presenter;

import dream.quqiongyou.bean.QuUser;
import dream.quqiongyou.main.model.LoginModel;
import dream.quqiongyou.main.model.LoginModelImpl;
import dream.quqiongyou.main.view.LoginView;
import dream.quqiongyou.utils.LogUtils;

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
    public void onFail(String message) {
        LogUtils.d("LoginTest","onFail");
        loginView.hideProgress();
        loginView.loginFail(message);
    }

    @Override
    public void onSuccess(QuUser user) {
        LogUtils.d("LoginTest","onSuccess and the user name = " + user.getNickname() + " " + user.getLevel());
        loginView.hideProgress();
        loginView.loginSuccess(user);
    }

    @Override
    public void checkUser(String account,String password) {
        loginView.showProgress();
        loginModel.checkQuUserByModel(account,password, this);
    }
}
