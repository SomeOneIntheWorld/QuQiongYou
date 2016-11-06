package dream.quqiongyou.fuckticket.model;

import java.util.ArrayList;
import java.util.List;

import dream.quqiongyou.bean.PostBean;
import dream.quqiongyou.bean.Response;
import dream.quqiongyou.bean.TopicBean;
import dream.quqiongyou.service.OkService;
import dream.quqiongyou.utils.RxUtils;
import rx.functions.Action1;

import static dream.quqiongyou.utils.RxUtils.normalSchedulers;

/**
 * Created by SomeOneInTheWorld on 2016/10/8.
 */
public class FuckticketModelImpl implements FuckticketModel {
    @Override
    public void loadPostBeanList(TopicBean topicBean, int page,final CallBackByFuckticketModel listener) {
        final List<PostBean>topList = new ArrayList<>();
        final List<PostBean>normalList = new ArrayList<>();

        for(int i=0;i<5;i++){
            PostBean postBean = new PostBean("这是第 " + i + " 个置顶帖");
            topList.add(postBean);
        }


        OkService.CommunitySecondService service = RxUtils.createService(OkService.CommunitySecondService.class);
        service.getCommunitySecondData(3,page)
                .compose(RxUtils.<Response<List<PostBean>>>normalSchedulers())
                .subscribe(listResponse ->{
                        normalList.addAll(listResponse.data);
                        listener.loadSuccess(topList,normalList);
                        },
                    throwable -> listener.loadFail(throwable.getMessage())
                );

    }
}
