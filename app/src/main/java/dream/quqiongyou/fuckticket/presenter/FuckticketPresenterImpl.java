package dream.quqiongyou.fuckticket.presenter;

import java.util.List;

import dream.quqiongyou.bean.PostBean;
import dream.quqiongyou.fuckticket.model.FuckticketModel;
import dream.quqiongyou.fuckticket.model.FuckticketModelImpl;
import dream.quqiongyou.fuckticket.view.FuckticketView;

/**
 * Created by SomeOneInTheWorld on 2016/10/8.
 */
public class FuckticketPresenterImpl implements FuckticketPresenter,FuckticketModel.CallBackByFuckticketModel{
    private FuckticketModel model;
    private FuckticketView view;

    public FuckticketPresenterImpl(FuckticketView view){
        this.view = view;
        model = new FuckticketModelImpl();
    }

    @Override
    public void loadPostsByPresenter() {
        view.showProgressBar();
        model.loadPostBeanList(this);
    }

    @Override
    public void loadSuccess(List<PostBean> topList, List<PostBean> normalList) {
        view.hideProgressBar();
        view.showPostBeanList(topList,normalList);
    }

    @Override
    public void loadFail(String errorMessage) {
        view.hideProgressBar();
        view.showErrorMessage(errorMessage);
    }
}
