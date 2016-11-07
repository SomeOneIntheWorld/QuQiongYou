package dream.quqiongyou.postdetail.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dream.quqiongyou.bean.CommentBean;
import dream.quqiongyou.bean.PostBean;
import dream.quqiongyou.bean.QuUser;
import dream.quqiongyou.bean.Response;
import dream.quqiongyou.service.OkService;
import dream.quqiongyou.utils.RxUtils;

/**
 * Created by SomeOneInTheWorld on 2016/10/10.
 */
public class PostDetailModelImpl implements PostDetailModel {
    @Override
    public void loadComments(PostBean postBean,CallBackByPostDetailModel callBackByPostDetailModel) {
        List<CommentBean>commentBeanList = new ArrayList<>();
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
        callBackByPostDetailModel.loadSuccess(commentBeanList);
    }
}
