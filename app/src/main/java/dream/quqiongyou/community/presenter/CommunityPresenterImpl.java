package dream.quqiongyou.community.presenter;

import java.util.List;

import dream.quqiongyou.bean.CommunityItemBean;
import dream.quqiongyou.community.model.CommunityModel;
import dream.quqiongyou.community.model.CommunityModelImpl;
import dream.quqiongyou.community.view.CommunityView;

/**
 * Created by SomeOneInTheWorld on 2016/10/4.
 */
public class CommunityPresenterImpl implements CommunityPresenter,CommunityModel.CallBackByCommunityModel {
    private CommunityView view;
    private CommunityModel model;

    public CommunityPresenterImpl(CommunityView view){
        this.view = view;
        model = new CommunityModelImpl();
    }

    @Override
    public void loadSomethingInCommunity() {
        view.showProgress();
        model.loadSomethingInModel(this);
    }

    @Override
    public void loadSuccess(List<CommunityItemBean> communityDatas, List<CommunityItemBean> guessDatas) {
        view.loadSomethingSuccess(communityDatas,guessDatas);
    }

    @Override
    public void loadFail(String errorMessage) {
        view.loadSomethingFail(errorMessage);
    }
}
