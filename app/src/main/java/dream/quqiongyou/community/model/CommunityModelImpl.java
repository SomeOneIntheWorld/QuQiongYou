package dream.quqiongyou.community.model;

import java.util.ArrayList;
import java.util.List;

import dream.quqiongyou.bean.TopicBean;

/**
 * Created by SomeOneInTheWorld on 2016/10/4.
 */
public class CommunityModelImpl implements CommunityModel{

    @Override
    public void loadSomethingInModel(CallBackByCommunityModel callBackByCommunityModel) {
        List<TopicBean>communityDatas = new ArrayList<>();
        for(int i=0;i<10;i++){
            TopicBean topicBean = new TopicBean();
            topicBean.setTitle("这是上边");
            topicBean.setImgurl(null);
            topicBean.setTopicnum("2333");
            communityDatas.add(topicBean);
        }

        List<TopicBean>guessDatas = new ArrayList<>();
        for(int i=0;i<10;i++){
            TopicBean topicBean = new TopicBean();
            topicBean.setTitle("这是下边");
            topicBean.setImgurl(null);
            topicBean.setTopicnum("6666");
            guessDatas.add(topicBean);
        }
        callBackByCommunityModel.loadSuccess(communityDatas,guessDatas);
    }
}
