package dream.quqiongyou.home.view;

import java.util.List;

import dream.quqiongyou.bean.HomeItemBean;

/**
 * Created by SomeOneInTheWorld on 2016/10/3.
 */
public interface HomeView {
    void loadTripsSuccess(List<HomeItemBean>datas);
    void loadTripFail(String errorMessage);
    void showProgressBar();
    void hideProgressBar();
}
