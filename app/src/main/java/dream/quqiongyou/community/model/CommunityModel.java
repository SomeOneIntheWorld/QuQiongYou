package dream.quqiongyou.community.model;

import java.util.List;

import dream.quqiongyou.bean.CommunityItemBean;

/**
 * Created by SomeOneInTheWorld on 2016/10/4.
 */
public interface CommunityModel {
    interface CallBackByCommunityModel{
        void loadSuccess(List<CommunityItemBean> communityDatas, List<CommunityItemBean>guessDatas);
        void loadFail(String errorMessage);
    }
    void loadSomethingInModel(CommunityModelImpl.CallBackByCommunityModel listener);
}
