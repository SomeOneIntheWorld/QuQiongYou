package dream.quqiongyou.community.model;

import java.util.ArrayList;
import java.util.List;

import dream.quqiongyou.R;
import dream.quqiongyou.bean.TopicBean;

/**
 * Created by SomeOneInTheWorld on 2016/10/4.
 */
public class CommunityModelImpl implements CommunityModel{

    @Override
    public void loadSomethingInModel(CallBackByCommunityModel callBackByCommunityModel) {
        List<TopicBean>communityDatas = new ArrayList<>();


            TopicBean topicBean1 = new TopicBean();
            topicBean1.setTitle("逃票攻略");
            topicBean1.setImgId(R.mipmap.fuckticket);
            topicBean1.setTopicnum("10");
            communityDatas.add(topicBean1);

        TopicBean topicBean2 = new TopicBean();
        topicBean2.setTitle("骑行");
        topicBean2.setImgId(R.mipmap.riding);
        topicBean2.setTopicnum("20");
        communityDatas.add(topicBean2);



        List<TopicBean>guessDatas = new ArrayList<>();

            TopicBean topicBean = new TopicBean();
            topicBean.setTitle("野外漂流");
            topicBean.setImgId(R.mipmap.outdoor);
            topicBean.setTopicnum("30");
            guessDatas.add(topicBean);

        callBackByCommunityModel.loadSuccess(communityDatas,guessDatas);
    }
}
