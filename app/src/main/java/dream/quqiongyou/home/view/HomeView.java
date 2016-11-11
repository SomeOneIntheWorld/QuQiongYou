package dream.quqiongyou.home.view;

import java.util.List;

import dream.quqiongyou.bean.HomeItemBean;
import dream.quqiongyou.bean.TopInfo;

/**
 * Created by SomeOneInTheWorld on 2016/10/3.
 */
public interface HomeView {
    void loadTripActivitiesSuccess(List<HomeItemBean>datas);
    void loadTopBannerSuccess(List<TopInfo>topInfoList);
    void loadDataFail(int tag,String message);
    void loadNoMoreData();
    void showProgressBar();
    void hideProgressBar();
}
