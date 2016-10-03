package dream.quqiongyou.register.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import dream.quqiongyou.R;

/**
 * Created by SomeOneInTheWorld on 2016/10/3.
 */
public class RegisterActivity extends AppCompatActivity {

    public static void startRegisterActivity(Context context){
        Intent intent = new Intent(context,RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
}
