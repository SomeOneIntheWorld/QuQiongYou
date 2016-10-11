package dream.quqiongyou.postdetail.view;

import java.util.List;

import dream.quqiongyou.bean.CommentBean;

/**
 * Created by SomeOneInTheWorld on 2016/10/10.
 */
public interface PostDetailView{
    void showProgressBar();
    void hideProgressBar();
    void showComments(List<CommentBean>commentBeanList);
    void showErrorMessage(String message);
}
