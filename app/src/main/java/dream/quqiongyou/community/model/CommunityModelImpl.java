package dream.quqiongyou.community.model;

import java.util.ArrayList;
import java.util.List;

import dream.quqiongyou.bean.CommunityItemBean;

/**
 * Created by SomeOneInTheWorld on 2016/10/4.
 */
public class CommunityModelImpl implements CommunityModel{

    @Override
    public void loadSomethingInModel(CallBackByCommunityModel callBackByCommunityModel) {
        List<CommunityItemBean>communityDatas = new ArrayList<>();
        for(int i=0;i<10;i++){
            CommunityItemBean communityItemBean = new CommunityItemBean();
            communityItemBean.setTitle("这是上边");
            communityItemBean.setImgurl(null);
            communityItemBean.setTopicnum("2333");
            communityDatas.add(communityItemBean);
        }

        List<CommunityItemBean>guessDatas = new ArrayList<>();
        for(int i=0;i<10;i++){
            CommunityItemBean communityItemBean = new CommunityItemBean();
            communityItemBean.setTitle("这是下边");
            communityItemBean.setImgurl(null);
            communityItemBean.setTopicnum("6666");
            guessDatas.add(communityItemBean);
        }
        callBackByCommunityModel.loadSuccess(communityDatas,guessDatas);
    }
}
