package dream.quqiongyou.home.model;

import dream.quqiongyou.bean.HomeItemBean;
import dream.quqiongyou.common.CallBackByModel;

/**
 * Created by SomeOneInTheWorld on 2016/10/3.
 */
public interface HomeModel {
    void loadTripsInModel(CallBackByModel<HomeItemBean> listener);
}
