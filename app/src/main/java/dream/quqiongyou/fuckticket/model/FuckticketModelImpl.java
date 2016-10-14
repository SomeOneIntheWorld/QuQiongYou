package dream.quqiongyou.fuckticket.model;

import java.util.ArrayList;
import java.util.List;

import dream.quqiongyou.bean.PostBean;
import dream.quqiongyou.bean.QuUser;
import dream.quqiongyou.bean.TopicBean;

/**
 * Created by SomeOneInTheWorld on 2016/10/8.
 */
public class FuckticketModelImpl implements FuckticketModel {
    @Override
    public void loadPostBeanList(TopicBean topicBean,CallBackByFuckticketModel listener) {
        List<PostBean>topList = new ArrayList<>();
        List<PostBean>normalList = new ArrayList<>();

        for(int i=0;i<5;i++){
            PostBean postBean = new PostBean("这是第 " + i + " 个置顶帖","这是副标题");
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

            topList.add(postBean);
        }
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
        listener.loadSuccess(topList,normalList);
    }
}
