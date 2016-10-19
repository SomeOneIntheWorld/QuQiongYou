package dream.quqiongyou.register.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dream.quqiongyou.R;
import dream.quqiongyou.register.presenter.RegisterPresenter;
import dream.quqiongyou.register.presenter.RegisterPresenterImpl;

/**
 * Created by SomeOneInTheWorld on 2016/10/3.
 */
public class RegisterActivity extends AppCompatActivity implements RegisterView{
    @BindView(R.id.register_identifying_code_et)EditText identifyCodeET;
    @BindView(R.id.register_identifying_code_tv)Button identifyCodeTV;
    @BindView(R.id.register_password)EditText passwordET;
    @BindView(R.id.register_phone)EditText phoneET;
    @BindView(R.id.register_register)Button registerButton;

    private Unbinder unbinder;
    private String identifyingCode = null;
    private RegisterPresenter presenter;

    public static void startRegisterActivity(Context context){
        Intent intent = new Intent(context,RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        unbinder = ButterKnife.bind(this);
        presenter = new RegisterPresenterImpl(this);
    }

    @OnClick({R.id.register_identifying_code_tv,R.id.register_register})
    void onClick(View view){
        switch(view.getId()){
            case R.id.register_identifying_code_tv:
                String phone = phoneET.getText().toString();
                Toast.makeText(this,"你按了",Toast.LENGTH_SHORT).show();
                if(TextUtils.isEmpty(phone)){
                    Toast.makeText(this,"电话号码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                presenter.requestIdentifyingCode(phoneET.getText().toString());
                break;
            case R.id.register_register:
                Toast.makeText(this,"你带呢了",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressbar() {

    }

    @Override
    public void onSuccessRequestForCode(String code) {
        identifyingCode = code;
        phoneET.setText(code);
    }

    @Override
    public void onFailRequestForCode(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unbinder.unbind();
    }
}
