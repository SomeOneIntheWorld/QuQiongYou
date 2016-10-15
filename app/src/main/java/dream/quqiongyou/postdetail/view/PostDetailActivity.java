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
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dream.quqiongyou.R;
import dream.quqiongyou.adapter.PostDetailAdapter;
import dream.quqiongyou.adapter.RecyclerViewLoadMoreListener;
import dream.quqiongyou.bean.CommentBean;
import dream.quqiongyou.bean.PostBean;
import dream.quqiongyou.bean.QuUser;
import dream.quqiongyou.postdetail.presenter.PostDetailPresenter;
import dream.quqiongyou.postdetail.presenter.PostDetailPresenterImpl;

/**
 * Created by SomeOneInTheWorld on 2016/10/10.
 */
public class PostDetailActivity extends AppCompatActivity implements PostDetailView {
    private Unbinder unbinder;
    private PostDetailAdapter postDetailAdapter;
    private PostBean postBean;
    private PostDetailPresenter presenter;
    private List<CommentBean> commentBeanList = new ArrayList<>();
    private final static String POST_BEAN = "postBean";
    @BindView(R.id.postdetail_recycler)
    RecyclerView postdetailRV;
    @BindView(R.id.postdetail_refresh)
    SwipeRefreshLayout refreshLayout;

    public static void startPostDetailActivity(Context context, PostBean postBean) {
        Intent intent = new Intent(context, PostDetailActivity.class);
        intent.putExtra(POST_BEAN, postBean);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postdetail);
        unbinder = ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null) {
            postBean = (PostBean) intent.getSerializableExtra(POST_BEAN);
        }

        LinearLayoutManager manager = new LinearLayoutManager(this);
        RecyclerViewLoadMoreListener listener = new RecyclerViewLoadMoreListener(manager) {
            @Override
            public void onLoadMore(int currentPage) {
                loadMoreDatas();
            }
        };
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadComments(postBean);
            }
        });
        postDetailAdapter = new PostDetailAdapter(this);
        postDetailAdapter.setCommentBeanList(commentBeanList);
        postDetailAdapter.setPostBean(postBean);
        postdetailRV.setLayoutManager(manager);
        postdetailRV.setItemAnimator(new DefaultItemAnimator());
        postdetailRV.setAdapter(postDetailAdapter);
        postdetailRV.addOnScrollListener(listener);

        presenter = new PostDetailPresenterImpl(this);
        presenter.loadComments(postBean);
    }

    //just for test,please remove it later;
    void loadMoreDatas(){
        for(int i=0;i<10;i++){
            CommentBean commentBean = new CommentBean();
            commentBean.setAnswertime(new Date(24));
            QuUser user = new QuUser("123456","password");
            user.setLevel(2);
            user.setNickname("第 " + i + "个用户");
            user.setHeadingimg(null);
            commentBean.setAnsweruser(user);
            commentBean.setComment("这是第 " + i + " 个用户在回答");
            commentBean.setCommentnum(i*100);
            commentBean.setGoodjobnum(i*10);
            commentBean.setSource("来自iphone " + i);
            commentBeanList.add(commentBean);
        }
        postDetailAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showComments(List<CommentBean> commentBeanList) {
        if(refreshLayout.isRefreshing()){
            refreshLayout.setRefreshing(false);
        }
        this.commentBeanList.clear();
        this.commentBeanList.addAll(commentBeanList);
        this.postDetailAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage(String message) {
        if(refreshLayout.isRefreshing()){
            refreshLayout.setRefreshing(false);
        }
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
