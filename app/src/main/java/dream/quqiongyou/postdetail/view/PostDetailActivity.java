package dream.quqiongyou.postdetail.view;

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
import dream.quqiongyou.adapter.PostDetailAdapter;
import dream.quqiongyou.adapter.RecyclerViewLoadMoreListener;
import dream.quqiongyou.bean.PostBean;
import dream.quqiongyou.postdetail.presenter.PostDetailPresenter;
import dream.quqiongyou.postdetail.presenter.PostDetailPresenterImpl;
import dream.quqiongyou.utils.LogUtils;

/**
 * Created by SomeOneInTheWorld on 2016/10/10.
 */
public class PostDetailActivity extends AppCompatActivity implements PostDetailView {
    private Unbinder unbinder;
    private PostDetailAdapter postDetailAdapter;
    private String postId;
    private int postPage = 1;
    private PostDetailPresenter presenter;
    private List<PostBean> postBeanList = new ArrayList<>();
    private final static String POST_ID = "postID";
    @BindView(R.id.postdetail_recycler) RecyclerView postdetailRV;
    @BindView(R.id.postdetail_refresh) SwipeRefreshLayout refreshLayout;

    private static final String TAG = "POSTTEST";

    public static void startPostDetailActivity(Context context, String id) {
        Intent intent = new Intent(context, PostDetailActivity.class);
        intent.putExtra(POST_ID, id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postdetail);
        unbinder = ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null) {
            postId = intent.getStringExtra(POST_ID);
        }

        LinearLayoutManager manager = new LinearLayoutManager(this);
        RecyclerViewLoadMoreListener listener = new RecyclerViewLoadMoreListener(manager) {
            @Override
            public void onLoadMore(int currentPage) {
                presenter.loadComments(postId,currentPage);
            }
        };
        refreshLayout.setOnRefreshListener(() -> presenter.loadComments(postId,postPage));
        postDetailAdapter = new PostDetailAdapter(this);
        postDetailAdapter.setCommentBeanList(postBeanList);
        postdetailRV.setLayoutManager(manager);
        postdetailRV.setItemAnimator(new DefaultItemAnimator());
        postdetailRV.setAdapter(postDetailAdapter);
        postdetailRV.addOnScrollListener(listener);

        presenter = new PostDetailPresenterImpl(this);
        presenter.loadComments(postId,postPage);
    }

    //just for test,please remove it later;
//    void loadMoreDatas(){
//        for(int i=0;i<10;i++){
//            CommentBean commentBean = new CommentBean();
//            commentBean.setAnswertime(new Date(24));
//            QuUser user = new QuUser("123456","password");
//            user.setLevel(2);
//            user.setNickname("第 " + i + "个用户");
//            user.setHeadingimg(null);
//            commentBean.setAnsweruser(user);
//            commentBean.setComment("这是第 " + i + " 个用户在回答");
//            commentBean.setCommentnum(i*100);
//            commentBean.setGoodjobnum(i*10);
//            commentBean.setSource("来自iphone " + i);
//            postBeanList.add(commentBean);
//        }
//        postDetailAdapter.notifyDataSetChanged();
//    }

    @Override
    public void showProgressBar() {
        postDetailAdapter.setStatusOfProgressBar(false);
    }

    @Override
    public void hideProgressBar() {
        postDetailAdapter.setStatusOfProgressBar(true);
    }

    @Override
    public void showComments(List<PostBean> postBeanList) {
        if(refreshLayout.isRefreshing()){
            refreshLayout.setRefreshing(false);
        }
        this.postBeanList.clear();
        this.postBeanList.addAll(postBeanList);
        this.postDetailAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage(String message) {
        if(refreshLayout.isRefreshing()){
            refreshLayout.setRefreshing(false);
        }
        LogUtils.d(TAG,"message is " + message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.back)
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
        }
    }
}
