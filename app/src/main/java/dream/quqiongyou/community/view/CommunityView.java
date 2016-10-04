package dream.quqiongyou.community.view;

import java.util.List;

import dream.quqiongyou.bean.CommunityItemBean;

/**
 * Created by SomeOneInTheWorld on 2016/10/4.
 */
public interface CommunityView {
    void loadSomethingSuccess(List<CommunityItemBean> communityDatas,List<CommunityItemBean>guessDatas);
    void loadSomethingFail(String errorMessage);
    void showProgress();
    void hideProgress();
}
