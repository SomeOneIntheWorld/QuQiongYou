package dream.quqiongyou.home.model;

import java.util.ArrayList;
import java.util.List;

import dream.quqiongyou.bean.HomeItemBean;
import dream.quqiongyou.bean.Response;
import dream.quqiongyou.bean.TopInfo;
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
            listener.loadFail(null,Constants.HOME_TAG_ALL_FAIL,"page should be legal");
            return;
        }

        OkService.MainDataService mainDataService = RxUtils.createService(OkService.MainDataService.class);
        mainDataService.getMainData(page)
                .compose(RxUtils.<Response<List<HomeItemBean>>>normalSchedulers())
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
                            List<TopInfo>topInfos = new ArrayList<>();
                            topInfos.add(new TopInfo());
                            topInfos.add(new TopInfo());
                            topInfos.add(new TopInfo());
                            listener.loadSuccess(data,topInfos);
                        },
                        error -> {
                            LogUtils.d(TAG,"onError is " + error.getMessage());
                            listener.loadFail(null,Constants.HOME_TAG_ALL_FAIL,error.getMessage());
                        }
                );
    }
}
