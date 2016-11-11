package dream.quqiongyou.postdetail.model;

import java.util.List;

import dream.quqiongyou.bean.PostBean;

/**
 * Created by SomeOneInTheWorld on 2016/10/10.
 */
public interface PostDetailModel {
    interface CallBackByPostDetailModel{
        void loadSuccess(List<PostBean>commentBeanList);
        void loadFail(String message);
    }
    void loadComments(String id,int page,CallBackByPostDetailModel callback);
}
