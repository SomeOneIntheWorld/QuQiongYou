package dream.quqiongyou.fuckticket.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dream.quqiongyou.R;
import dream.quqiongyou.adapter.FuckticketAdapter;
import dream.quqiongyou.adapter.OnItemTouchListener;
import dream.quqiongyou.bean.PostBean;

/**
 * Created by SomeOneInTheWorld on 2016/10/8.
 */
public class FuckticketActivity extends AppCompatActivity implements FuckticketView{
    private Unbinder unbinder;
    private FuckticketAdapter fuckticketAdapter;
    @BindView(R.id.fuckticket_recycler)RecyclerView fuckticketRC;

    private List<PostBean>topList = new ArrayList<>();
    private List<PostBean>normalList = new ArrayList<>();

    public static void startFuckticketActivity(Context context) {
        Intent intent = new Intent(context,FuckticketActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuckticket);
        unbinder = ButterKnife.bind(this);

        fuckticketAdapter = new FuckticketAdapter(this);
        fuckticketRC.setAdapter(fuckticketAdapter);
        fuckticketRC.addOnItemTouchListener(new OnItemTouchListener(fuckticketRC) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
            }
        });
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showPostBeanList(List<PostBean> topList, List<PostBean> normalList) {
        this.topList.clear();
        this.topList.addAll(topList);
        this.normalList.clear();
        this.normalList.addAll(normalList);
        this.fuckticketAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unbinder.unbind();
    }
}
