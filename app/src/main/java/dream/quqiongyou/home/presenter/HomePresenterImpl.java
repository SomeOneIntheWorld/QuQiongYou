package dream.quqiongyou.home.presenter;

import java.util.List;

import dream.quqiongyou.bean.HomeItemBean;
import dream.quqiongyou.bean.TopInfo;
import dream.quqiongyou.home.model.HomeModel;
import dream.quqiongyou.home.model.HomeModelImpl;
import dream.quqiongyou.home.view.HomeView;

/**
 * Created by SomeOneInTheWorld on 2016/10/3.
 */
public class HomePresenterImpl implements HomePresenter,HomeModel.CallbackByHomeModel{
    private HomeModel homeModel;
    private HomeView homeView;

    public HomePresenterImpl(HomeView homeView){
        this.homeModel = new HomeModelImpl();
        this.homeView = homeView;
    }

    @Override
    public void loadHomeInfoByPresenter(int page) {
        homeView.showProgressBar();
        homeModel.loadHomeInfoInModel(page,this);
    }

    @Override
    public void loadSuccess(List<HomeItemBean> homeItemBeanList, List<TopInfo> topInfoList) {
        homeView.hideProgressBar();
        homeView.loadHomeInfoSuccess(homeItemBeanList, topInfoList);
    }

    @Override
    public void loadFail(List<?> item, int tag, String message) {
        homeView.hideProgressBar();
        homeView.loadHomeInfoFail(item, tag, message);
    }

    @Override
    public void loadNoMoreData() {
        homeView.hideProgressBar();
        homeView.loadNoMoreData();
    }
}


