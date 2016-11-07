package dream.quqiongyou.tripdetail.model;

import dream.quqiongyou.bean.TripDetail;
import dream.quqiongyou.tripdetail.presenter.TripDetailPresenter;

/**
 * Created by SaladJack on 2016/11/6.
 */

public interface TripDetailModel {
    interface CallBackTripDetailModel{
        void loadSuccess(TripDetail tripDetail);
        void loadFail(String message);
    }
    void loadTripDetail(String tripId, CallBackTripDetailModel callBackTripDetailModel);
}
