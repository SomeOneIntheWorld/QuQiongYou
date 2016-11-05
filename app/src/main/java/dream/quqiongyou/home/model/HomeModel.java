package dream.quqiongyou.home.model;

import java.util.List;

import dream.quqiongyou.bean.HomeItemBean;
import dream.quqiongyou.bean.TopInfo;

/**
 * Created by SomeOneInTheWorld on 2016/10/3.
 */
public interface HomeModel {
    interface CallbackByHomeModel{
        void loadSuccess(List<HomeItemBean>homeItemBeanList,List<TopInfo>topInfoList);
        void loadFail(List<?>item,int tag,String message);
        void loadNoMoreData();
    }
    void loadHomeInfoInModel(int page,CallbackByHomeModel listener);
}
