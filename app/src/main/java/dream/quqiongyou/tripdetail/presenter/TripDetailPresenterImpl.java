package dream.quqiongyou.tripdetail.presenter;


import dream.quqiongyou.bean.TripDetail;
import dream.quqiongyou.tripdetail.model.TripDetailModel;
import dream.quqiongyou.tripdetail.model.TripDetailModelImpl;
import dream.quqiongyou.tripdetail.view.TripDetailView;

/**
 * Created by SaladJack on 2016/11/6.
 */

public class TripDetailPresenterImpl implements TripDetailPresenter, TripDetailModel.CallBackTripDetailModel {
    private TripDetailView view;
    private TripDetailModel model;

    public TripDetailPresenterImpl(TripDetailView view) {
        this.view = view;
        this.model = new TripDetailModelImpl();
    }

    @Override
    public void loadTripDetail(String tripId) {
        view.showProgressBar();
        model.loadTripDetail(tripId,this);
    }

    @Override
    public void loadSuccess(TripDetail tripDetail) {
        view.hideProgressBar();
        view.showTripDetail(tripDetail);
    }

    @Override
    public void loadFail(String message) {
        view.hideProgressBar();
        view.showErrorMessage(message);
    }
}
