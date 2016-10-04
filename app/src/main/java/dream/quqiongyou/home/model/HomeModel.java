package dream.quqiongyou.home.model;

import dream.quqiongyou.bean.HomeItemBean;
import dream.quqiongyou.bean.TopInfo;
import dream.quqiongyou.common.CallBackByModel;
import dream.quqiongyou.home.presenter.HomePresenterImpl;

/**
 * Created by SomeOneInTheWorld on 2016/10/3.
 */
public interface HomeModel {
    void loadTripsInModel(CallBackByModel<HomeItemBean> listener);
    void loadTopinfoInModel(CallBackByModel<TopInfo> listener);

}
