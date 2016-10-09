package dream.quqiongyou.fuckticket.model;

import java.util.List;

import dream.quqiongyou.bean.PostBean;

/**
 * Created by SomeOneInTheWorld on 2016/10/7.
 */
public interface FuckticketModel {
    interface CallBackByFuckticketModel{
        void loadSuccess(List<PostBean>topList,List<PostBean>normalList);
        void loadFail(String message);
    }
    void loadPostBeanList(CallBackByFuckticketModel listener);
}
