package dream.quqiongyou.tripdetail.model;

import dream.quqiongyou.bean.Response;
import dream.quqiongyou.bean.TripDetail;
import dream.quqiongyou.service.OkService;
import dream.quqiongyou.utils.RxUtils;

/**
 * Created by SaladJack on 2016/11/6.
 */
public class TripDetailModelImpl implements TripDetailModel {
    @Override
    public void loadTripDetail(String tripId, CallBackTripDetailModel callBackTripDetailModel) {
        OkService.TripDetailService service = RxUtils.createService(OkService.TripDetailService.class);
        service.getTripDetailData(tripId)
                .compose(RxUtils.<Response<TripDetail>>normalSchedulers())
                .subscribe(tripDetailResponse -> callBackTripDetailModel.loadSuccess(tripDetailResponse.data),
                            throwable -> callBackTripDetailModel.loadFail(throwable.getMessage()));
    }
}
