package dream.quqiongyou.home.model;

import java.util.ArrayList;
import java.util.List;

import dream.quqiongyou.bean.HomeItemBean;
import dream.quqiongyou.common.Constants;
import dream.quqiongyou.service.OkService;
import dream.quqiongyou.utils.LogUtils;
import dream.quqiongyou.utils.RxUtils;

/**
 * Created by SomeOneInTheWorld on 2016/10/3.
 */
public class HomeModelImpl implements HomeModel {
    private final static String TAG = "HOMEMODELIMPL_TEST";
    private List<HomeItemBean>data = new ArrayList<>();
    private int maxPage = 1;

    @Override
    public void loadHomeInfoInModel(final int page,final CallbackByHomeModel listener) {
        LogUtils.d(TAG,"page = " + page);
        if(page < 1 || page > maxPage){
            listener.loadFail(Constants.HOME_TAG_HOME_ITEM_FAIL,"page should be legal");
            return;
        }

        OkService.MainDataService mainDataService = RxUtils.createService(OkService.MainDataService.class);
        mainDataService.getMainData(page)
                .compose(RxUtils.normalSchedulers())
                .subscribe(
                        listResponse -> {
                            if(listResponse.data == null || listResponse.data.size() == 0){
                                listener.loadNoMoreData();
                                return;
                            }
                            maxPage = Integer.parseInt(listResponse.message);

                            if(page == 1){
                                data.clear();
                            }
                            data.addAll(listResponse.data);
                            listener.loadSuccessTripActivities(data);
                        },
                        error -> {
                            LogUtils.d(TAG,"onError is " + error.getMessage());
                            listener.loadFail(Constants.HOME_TAG_HOME_ITEM_FAIL,error.getMessage());
                        }
                );
    }

    @Override
    public void loadTopBannerInModel(CallbackByHomeModel listener) {
        OkService.BannerService service = RxUtils.createService(OkService.BannerService.class);
        service.getBanner()
                .compose(RxUtils.normalSchedulers())
                .subscribe(listResponse -> {
                    if(listResponse.code == 200){
                        listener.loadSuccessTopBanners(listResponse.data);
                    }else{
                        listener.loadFail(Constants.HOME_TAG_TOP_INFO_FAIL,listResponse.message);
                    }
                },error -> {
                    listener.loadFail(Constants.HOME_TAG_TOP_INFO_FAIL,error.getMessage());
                });
    }
}
