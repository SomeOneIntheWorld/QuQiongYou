package dream.quqiongyou.community.model;

import java.util.List;

import dream.quqiongyou.bean.TopInfo;
import dream.quqiongyou.bean.TopicBean;

/**
 * Created by SomeOneInTheWorld on 2016/10/4.
 */
public interface CommunityModel {
    interface CallBackByCommunityModel{
        void loadSuccess(List<TopicBean> communityDatas, List<TopicBean>guessDatas, List<TopInfo>topInfoList);
        void loadFail(String errorMessage);
    }
    void loadSomethingInModel(CommunityModelImpl.CallBackByCommunityModel listener);
}