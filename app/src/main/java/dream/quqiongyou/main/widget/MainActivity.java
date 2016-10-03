package dream.quqiongyou.main.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import dream.quqiongyou.R;

/**
 * Created by SomeOneInTheWorld on 2016/10/3.
 */
public class MainActivity extends AppCompatActivity {
    private final static String ACCOUNT = "account";

    public static void startMainActivity(Context context, String account){
        Intent intent = new Intent(context,MainActivity.class);
        intent.putExtra(ACCOUNT,account);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
