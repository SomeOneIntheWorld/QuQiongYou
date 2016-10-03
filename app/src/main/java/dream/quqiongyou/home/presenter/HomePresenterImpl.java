package dream.quqiongyou.home.presenter;

import java.util.List;

import dream.quqiongyou.bean.HomeItemBean;
import dream.quqiongyou.home.model.HomeModel;
import dream.quqiongyou.home.model.HomeModelImpl;
import dream.quqiongyou.home.view.HomeView;

/**
 * Created by SomeOneInTheWorld on 2016/10/3.
 */
public class HomePresenterImpl implements HomePresenter,HomeModelImpl.CallBackByHomeModel {
    private HomeModel homeModel;
    private HomeView homeView;

    public HomePresenterImpl(HomeView homeView){
        this.homeModel = new HomeModelImpl();
        this.homeView = homeView;
    }

    @Override
    public void loadTrips() {
        homeView.showProgressBar();
        homeModel.loadTripsInModel(this);
    }

    @Override
    public void loadTripsSuccess(List<HomeItemBean> datas) {
        homeView.hideProgressBar();
        homeView.loadTripsSuccess(datas);
    }

    @Override
    public void loadTripFail(String errorMessage) {
        homeView.hideProgressBar();
        homeView.loadTripFail(errorMessage);
    }
}
