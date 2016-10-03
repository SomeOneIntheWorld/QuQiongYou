package dream.quqiongyou.home.model;

import java.util.ArrayList;
import java.util.List;

import dream.quqiongyou.bean.HomeItemBean;

/**
 * Created by SomeOneInTheWorld on 2016/10/3.
 */
public class HomeModelImpl implements HomeModel {
    @Override
    public void loadTripsInModel(CallBackByHomeModel listener) {
        List<HomeItemBean>datas = new ArrayList<>();
        for(int i=0;i<10;i++){
            HomeItemBean data = new HomeItemBean();
            data.setImageurl(null);
            data.setTitle("这是测试");
            data.setLefttime("10");
            datas.add(data);
        }
        listener.loadTripsSuccess(datas);
    }

    public interface CallBackByHomeModel{
        void loadTripsSuccess(List<HomeItemBean>datas);
        void loadTripFail(String errorMessage);
    }
}
