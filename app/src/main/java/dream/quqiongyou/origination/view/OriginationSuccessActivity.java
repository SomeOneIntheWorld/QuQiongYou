package dream.quqiongyou.origination.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dream.quqiongyou.R;
import dream.quqiongyou.bean.OriginationDetailBean;
import dream.quqiongyou.bean.QuUser;
import dream.quqiongyou.main.widget.MainActivity;

/**
 * Created by SomeOneInTheWorld on 2016/10/16.
 */
public class OriginationSuccessActivity extends AppCompatActivity{
    private static final String DETAIL_BEAN = "detail_bean";
    private Unbinder unbinder;
    @BindView(R.id.origination_success_activity)TextView activityNameTV;

    public static void startOriginationSuccessActivity(Context context, OriginationDetailBean bean) {
        Intent intent = new Intent(context, OriginationSuccessActivity.class);
        intent.putExtra(DETAIL_BEAN, bean);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_origination_success);

        unbinder = ButterKnife.bind(this);
        Intent intent = getIntent();
        if(intent != null){
            OriginationDetailBean originationDetailBean = (OriginationDetailBean)intent.getSerializableExtra(DETAIL_BEAN);
            if(originationDetailBean != null){
                activityNameTV.setText(originationDetailBean.getTopic() == null ? "lala" : originationDetailBean.getTopic());
            }
        }
    }

    @OnClick({R.id.origination_success_share,R.id.origination_success_explore})
    void onClick(View view){
        switch(view.getId()){
            case R.id.origination_success_share:
                Toast.makeText(this,"分享",Toast.LENGTH_SHORT).show();
                break;
            case R.id.origination_success_explore:
                Toast.makeText(this,"探索",Toast.LENGTH_SHORT).show();
                MainActivity.startMainActivity(this, QuUser.getCurrentUser());
                break;
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unbinder.unbind();
    }
}
