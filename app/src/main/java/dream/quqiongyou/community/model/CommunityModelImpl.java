package dream.quqiongyou.community.model;

import java.util.ArrayList;
import java.util.List;

import dream.quqiongyou.bean.Response;
import dream.quqiongyou.bean.TopInfo;
import dream.quqiongyou.bean.TopicBean;
import dream.quqiongyou.service.OkService;
import dream.quqiongyou.utils.RxUtils;
import rx.Observable;

/**
 * Created by SomeOneInTheWorld on 2016/10/4.
 */
public class CommunityModelImpl implements CommunityModel{

    @Override
    public void loadSomethingInModel(final CallBackByCommunityModel callBackByCommunityModel) {
        OkService.CommunityMainService service = RxUtils.createService(OkService.CommunityMainService.class);
        Observable.zip(service.getCommunityMainData(),
                        service.getCommunityBannerData(),
                        (topicBeanListResponse,topInfoListResponse) ->new TopicBeanAndTopInfo(topicBeanListResponse,topInfoListResponse))
                .compose(RxUtils.<TopicBeanAndTopInfo>normalSchedulers())
                .subscribe(topicBeanAndTopInfo -> callBackByCommunityModel.loadSuccess(
                        topicBeanAndTopInfo.topicBeanListResponse.data,
                        topicBeanAndTopInfo.topicBeanListResponse.data,
                        topicBeanAndTopInfo.topInfoListResponse.data),
                        throwable -> callBackByCommunityModel.loadFail(throwable.getMessage()));
        /*
                service.getCommunityMainData()
                .compose(RxUtils.<Response<List<TopicBean>>>normalSchedulers())
                .subscribe(listResponse -> {
                    List<TopInfo>topInfos = new ArrayList<>();
                    topInfos.add(new TopInfo());
                    topInfos.add(new TopInfo());
                    topInfos.add(new TopInfo());

                    callBackByCommunityModel.loadSuccess(listResponse.data,listResponse.data,topInfos);
                }, throwable -> {
                    callBackByCommunityModel.loadFail(throwable.getMessage());
                });
                */
    }

    class TopicBeanAndTopInfo{
        public Response<List<TopicBean>> topicBeanListResponse;
        public Response<List<TopInfo>> topInfoListResponse;

        public TopicBeanAndTopInfo(Response<List<TopicBean>> topicBeanListResponse, Response<List<TopInfo>> topInfoListResponse) {
            this.topicBeanListResponse = topicBeanListResponse;
            this.topInfoListResponse = topInfoListResponse;
        }
    }
}
