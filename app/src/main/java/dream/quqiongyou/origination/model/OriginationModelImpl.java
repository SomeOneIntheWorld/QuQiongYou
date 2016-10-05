package dream.quqiongyou.origination.model;

import dream.quqiongyou.bean.OriginationDetailBean;

/**
 * Created by SomeOneInTheWorld on 2016/10/4.
 */
public class OriginationModelImpl implements OriginationModel {

    @Override
    public void uploadOrigination(OriginationDetailBean originationDetailBean,CallBackByOriginationModel callback) {
        callback.uploadResult(true);
    }
}
