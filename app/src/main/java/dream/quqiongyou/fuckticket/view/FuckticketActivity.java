package dream.quqiongyou.fuckticket.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dream.quqiongyou.R;
import dream.quqiongyou.adapter.FuckticketAdapter;
import dream.quqiongyou.adapter.OnItemTouchListener;
import dream.quqiongyou.adapter.RecyclerViewLoadMoreListener;
import dream.quqiongyou.bean.PostBean;
import dream.quqiongyou.bean.QuUser;
import dream.quqiongyou.bean.TopicBean;
import dream.quqiongyou.fuckticket.presenter.FuckticketPresenter;
import dream.quqiongyou.fuckticket.presenter.FuckticketPresenterImpl;
import dream.quqiongyou.postdetail.view.PostDetailActivity;
import dream.quqiongyou.utils.LogUtils;

/**
 * Created by SomeOneInTheWorld on 2016/10/8.
 */
public class FuckticketActivity extends AppCompatActivity implements FuckticketView {
    private Unbinder unbinder;
    private FuckticketAdapter fuckticketAdapter;
    @BindView(R.id.fuckticket_recycler)
    RecyclerView fuckticketRC;
    @BindView(R.id.fuckticket_refresh_layout)
    SwipeRefreshLayout fuckticketSRL;

    private final String TAG = "FUCKACTEST";

    private List<PostBean> topList = new ArrayList<>();
    private List<PostBean> normalList = new ArrayList<>();
    private FuckticketPresenter presenter;
    private TopicBean topicBean;
    private final static String TOPIC = "topicbean";

    public static void startFuckticketActivity(Context context, TopicBean topicBean) {
        Intent intent = new Intent(context, FuckticketActivity.class);
        intent.putExtra(TOPIC, topicBean);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuckticket);
        unbinder = ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null) {
            topicBean = (TopicBean) intent.getSerializableExtra(TOPIC);
            LogUtils.d(TAG, "topicBean is not null");
        }
        LinearLayoutManager manager = new LinearLayoutManager(this);
        fuckticketRC.setLayoutManager(manager);
        fuckticketRC.setItemAnimator(new DefaultItemAnimator());
        fuckticketAdapter = new FuckticketAdapter(this);
        fuckticketAdapter.setTopAndNormalLists(topList, normalList);
        fuckticketRC.setAdapter(fuckticketAdapter);
        fuckticketRC.addOnItemTouchListener(new OnItemTouchListener(fuckticketRC) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                int position = vh.getAdapterPosition();
                PostBean postBean = null;
                LogUtils.d(TAG, "the position is " + position);
                if (position >= 0 && position < topList.size()) {
                    postBean = topList.get(position);
                } else if (position >= topList.size() && position < topList.size() + normalList.size()) {
                    postBean = normalList.get(position - topList.size());
                }
                PostDetailActivity.startPostDetailActivity(FuckticketActivity.this, postBean);
            }
        });
        final RecyclerViewLoadMoreListener listener = new RecyclerViewLoadMoreListener(manager) {
            @Override
            public void onLoadMore(int currentPage) {
                loadMoreData();
            }
        };
        fuckticketRC.addOnScrollListener(listener);
        fuckticketSRL.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listener.setPreviousTotal(0);
                presenter.loadPostsByPresenter(topicBean);
            }
        });

        presenter = new FuckticketPresenterImpl(this);
        presenter.loadPostsByPresenter(topicBean);
    }

    //the method is just for testing.please remove it later.
    void loadMoreData(){
        for(int i=0;i<10;i++){
            PostBean postBean = new PostBean("这是第 " + i + " 个普通帖","这是副标题");
            postBean.setSource("iphone " + i);
            postBean.setGoodjobnum(i*10);
            postBean.setCommentnum(i*100);
            postBean.setImglist(null);
            postBean.setIsgreat(true);
            postBean.setSeenum(i*100);

            QuUser user = new QuUser("123456","password");
            user.setLevel(2);
            user.setNickname("第 " + i + "个用户");
            user.setHeadingimg(null);
            postBean.setPoster(user);
            normalList.add(postBean);
        }
        try{
            new Thread().sleep(2000);
        }catch(Exception e){
            e.printStackTrace();
        }
        fuckticketAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showPostBeanList(List<PostBean> topList, List<PostBean> normalList) {
        if(fuckticketSRL.isRefreshing()){
            fuckticketSRL.setRefreshing(false);
        }
        this.topList.clear();
        this.topList.addAll(topList);
        this.normalList.clear();
        this.normalList.addAll(normalList);
        this.fuckticketAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage(String message) {
        if(fuckticketSRL.isRefreshing()){
            fuckticketSRL.setRefreshing(false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    @OnClick({R.id.back, R.id.search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.search:
                break;
        }
    }
}
