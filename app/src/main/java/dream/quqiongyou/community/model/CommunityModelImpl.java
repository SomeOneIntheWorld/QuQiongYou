package dream.quqiongyou.community.model;

import java.util.ArrayList;
import java.util.List;

import dream.quqiongyou.bean.Response;
import dream.quqiongyou.bean.TopInfo;
import dream.quqiongyou.bean.TopicBean;
import dream.quqiongyou.service.OkService;
import dream.quqiongyou.utils.RxUtils;

/**
 * Created by SomeOneInTheWorld on 2016/10/4.
 */
public class CommunityModelImpl implements CommunityModel{

    @Override
    public void loadSomethingInModel(final CallBackByCommunityModel callBackByCommunityModel) {
        OkService.CommunityMainService service = RxUtils.createService(OkService.CommunityMainService.class);
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
    }
}
