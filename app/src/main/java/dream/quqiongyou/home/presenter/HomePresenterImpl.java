package dream.quqiongyou.home.presenter;

import java.util.ArrayList;
import java.util.List;

import dream.quqiongyou.bean.HomeItemBean;
import dream.quqiongyou.bean.TopInfo;
import dream.quqiongyou.common.CallBackByModel;
import dream.quqiongyou.home.model.HomeModel;
import dream.quqiongyou.home.model.HomeModelImpl;
import dream.quqiongyou.home.view.HomeView;

/**
 * Created by SomeOneInTheWorld on 2016/10/3.
 */
public class HomePresenterImpl implements HomePresenter{
    private HomeModel homeModel;
    private HomeView homeView;
    private CallBackByModel<TopInfo> topInfoListener;
    private CallBackByModel<HomeItemBean> tripListener;

    public HomePresenterImpl(HomeView homeView){
        this.homeModel = new HomeModelImpl();
        this.homeView = homeView;
        topInfoListener = new TopInfoListener();
        tripListener = new TripListener();
    }

    @Override
    public void loadTrips() {
        homeView.showProgressBar();
        homeModel.loadTripsInModel(tripListener);
    }

    @Override
    public void loadTopInfo() {
        homeView.showProgressBar();
        homeModel.loadTopinfoInModel(topInfoListener);
    }


    class TopInfoListener implements CallBackByModel<TopInfo> {

        @Override
        public void loadSuccess(List<TopInfo> results) {
            homeView.hideProgressBar();
            homeView.loadTopInfoSuccess(results);
        }

        @Override
        public void loadFail(String errorMessage) {

        }
    }
    class TripListener implements CallBackByModel<HomeItemBean>{

        @Override
        public void loadSuccess(List<HomeItemBean> results) {
            homeView.hideProgressBar();
            homeView.loadTripsSuccess(results);
        }

        @Override
        public void loadFail(String errorMessage) {
            homeView.hideProgressBar();
            homeView.loadTripFail(errorMessage);
        }
    }
}


