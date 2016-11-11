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
import android.widget.TextView;


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
import dream.quqiongyou.bean.TopicBean;
import dream.quqiongyou.fuckticket.presenter.FuckticketPresenter;
import dream.quqiongyou.fuckticket.presenter.FuckticketPresenterImpl;
import dream.quqiongyou.post.view.PostActivity;
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
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;


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
        toolbarTitle.setText(topicBean.getTitle());
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
                PostDetailActivity.startPostDetailActivity(FuckticketActivity.this, postBean.getId());
            }
        });
        final RecyclerViewLoadMoreListener listener = new RecyclerViewLoadMoreListener(manager) {
            @Override
            public void onLoadMore(int currentPage) {
                presenter.loadPostsByPresenter(topicBean,currentPage);
            }
        };
        fuckticketRC.addOnScrollListener(listener);
        fuckticketSRL.setOnRefreshListener(() -> {
            listener.setPreviousTotal(0);
            presenter.loadPostsByPresenter(topicBean,1);
        });

        presenter = new FuckticketPresenterImpl(this);
        presenter.loadPostsByPresenter(topicBean,1);
    }

    @Override
    public void showProgressBar() {
        fuckticketAdapter.setStatusOfProgressBar(false);
    }

    @Override
    public void hideProgressBar() {
        fuckticketAdapter.setStatusOfProgressBar(true);
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


    @OnClick({R.id.back, R.id.search,R.id.post})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.search:
                break;
            case R.id.post:
                PostActivity.startPostActivity(this);
        }
    }
}
