package dream.quqiongyou.origination.presenter;

import dream.quqiongyou.bean.OriginationDetailBean;
import dream.quqiongyou.origination.model.OriginationModel;
import dream.quqiongyou.origination.model.OriginationModelImpl;
import dream.quqiongyou.origination.view.OriginationView;

/**
 * Created by SomeOneInTheWorld on 2016/10/4.
 */
public class OriginationPresenterImpl implements OriginationPresenter,OriginationModel.CallBackByOriginationModel {
    private OriginationModel model;
    private OriginationView view;

    public OriginationPresenterImpl(OriginationView view){
        this.view = view;
        model = new OriginationModelImpl();
    }

    @Override
    public void createOrigination(OriginationDetailBean originationDetailBean) {
        view.showProgressBar();
        model.uploadOrigination(originationDetailBean,this);
    }

    @Override
    public void uploadResult(boolean isSuccess) {
        view.hideProgressBar();
        view.uploadResult(isSuccess);
    }
}
