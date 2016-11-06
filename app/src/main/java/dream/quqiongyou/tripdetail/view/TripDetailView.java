package dream.quqiongyou.tripdetail.view;

import dream.quqiongyou.bean.TripDetail;

/**
 * Created by SaladJack on 2016/11/6.
 */
public interface TripDetailView {
    void showProgressBar();
    void hideProgressBar();
    void showTripDetail(TripDetail tripDetail);
    void showErrorMessage(String message);
}
