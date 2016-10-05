package dream.quqiongyou.origination.model;

import dream.quqiongyou.bean.OriginationDetailBean;

/**
 * Created by SomeOneInTheWorld on 2016/10/4.
 */
public interface OriginationModel {
    interface CallBackByOriginationModel{
        void uploadResult(boolean isSuccess);
    }

    void uploadOrigination(OriginationDetailBean originationDetailBean, OriginationModelImpl.CallBackByOriginationModel callback);
}
