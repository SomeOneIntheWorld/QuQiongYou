package dream.quqiongyou.community.view;

import java.util.List;

import dream.quqiongyou.bean.TopInfo;
import dream.quqiongyou.bean.TopicBean;

/**
 * Created by SomeOneInTheWorld on 2016/10/4.
 */
public interface CommunityView {
    void loadSomethingSuccess(List<TopicBean> communityDatas, List<TopicBean>guessDatas, List<TopInfo>topInfoList);
    void loadSomethingFail(String errorMessage);
    void showProgress();
    void hideProgress();
}