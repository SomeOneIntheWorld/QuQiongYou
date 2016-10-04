package dream.quqiongyou.home.model;

import java.util.ArrayList;
import java.util.List;

import dream.quqiongyou.bean.HomeItemBean;
import dream.quqiongyou.common.CallBackByModel;

/**
 * Created by SomeOneInTheWorld on 2016/10/3.
 */
public class HomeModelImpl implements HomeModel {
    @Override
    public void loadTripsInModel(CallBackByModel<HomeItemBean> listener) {
        List<HomeItemBean>datas = new ArrayList<>();
        for(int i=0;i<10;i++){
            HomeItemBean data = new HomeItemBean();
            data.setImageurl(null);
            data.setTitle("这是测试");
            data.setLefttime("10");
            datas.add(data);
        }
        listener.loadSuccess(datas);
    }
}
