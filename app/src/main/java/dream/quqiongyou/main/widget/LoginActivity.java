package dream.quqiongyou.main.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dream.quqiongyou.Bean.QuUser;
import dream.quqiongyou.R;
import dream.quqiongyou.main.presenter.LoginPresenter;
import dream.quqiongyou.main.presenter.LoginPresenterImpl;
import dream.quqiongyou.main.view.LoginView;
import dream.quqiongyou.register.widget.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements LoginView{
    @BindView(R.id.account)
    EditText mAccountET;
    @BindView(R.id.pwd)
    EditText mPwdET;

    private Unbinder unbinder;
    private LoginPresenter loginPresenter;

    private String account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        unbinder = ButterKnife.bind(this);
        loginPresenter = new LoginPresenterImpl(this);
    }

    @OnClick(R.id.login)
    void onClickLogin(){
        account = mAccountET.getText() == null ? "" : mAccountET.getText().toString();
        String password = mPwdET.getText() == null ? "" : mPwdET.getText().toString();
        QuUser quUser = new QuUser(account,password);
        loginPresenter.checkUser(quUser);
    }

    @OnClick(R.id.register)
    void onClickRegister(){
        RegisterActivity.startRegisterActivity(this);
    }

    @OnClick(R.id.textView)
    void onClickForgetPSD(){

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void loginResult(boolean isSuccess) {
        isSuccess = true;
        if(isSuccess){
            MainActivity.startMainActivity(this,account);
        }else{
            Toast.makeText(this,"账号或密码错误！",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unbinder.unbind();
    }
}
