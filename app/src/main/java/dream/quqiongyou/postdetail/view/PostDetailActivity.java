package dream.quqiongyou.postdetail.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import dream.quqiongyou.bean.CommentBean;
import dream.quqiongyou.bean.PostBean;
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
        postDetailAdapter = new PostDetailAdapter(this);
        postDetailAdapter.setCommentBeanList(commentBeanList);
        postDetailAdapter.setPostBean(postBean);
        postdetailRV.setLayoutManager(new LinearLayoutManager(this));
        postdetailRV.setItemAnimator(new DefaultItemAnimator());
        postdetailRV.setAdapter(postDetailAdapter);

        presenter = new PostDetailPresenterImpl(this);
        presenter.loadComments(postBean);
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showComments(List<CommentBean> commentBeanList) {
        this.commentBeanList.clear();
        this.commentBeanList.addAll(commentBeanList);
        this.postDetailAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage(String message) {

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
