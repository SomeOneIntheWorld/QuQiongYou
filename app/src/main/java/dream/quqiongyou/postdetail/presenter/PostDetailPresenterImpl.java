package dream.quqiongyou.postdetail.presenter;

import java.util.List;

import dream.quqiongyou.bean.PostBean;
import dream.quqiongyou.postdetail.model.PostDetailModel;
import dream.quqiongyou.postdetail.model.PostDetailModelImpl;
import dream.quqiongyou.postdetail.view.PostDetailView;

/**
 * Created by SomeOneInTheWorld on 2016/10/10.
 */
public class PostDetailPresenterImpl implements PostDetailPresenter,PostDetailModel.CallBackByPostDetailModel{
    private PostDetailView view;
    private PostDetailModel model;

    public PostDetailPresenterImpl(PostDetailView view){
        this.view = view;
        this.model = new PostDetailModelImpl();
    }

    @Override
    public void loadComments(String id,int page) {
        view.showProgressBar();
        model.loadComments(id,page,this);
    }

    @Override
    public void loadSuccess(List<PostBean> commentBeanList) {
        view.hideProgressBar();
        view.showComments(commentBeanList);
    }

    @Override
    public void loadFail(String message) {
        view.hideProgressBar();
        view.showErrorMessage(message);
    }
}
