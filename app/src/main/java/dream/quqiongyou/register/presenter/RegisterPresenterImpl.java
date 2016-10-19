package dream.quqiongyou.register.presenter;

import dream.quqiongyou.register.model.RegisterModel;
import dream.quqiongyou.register.model.RegisterModelImpl;
import dream.quqiongyou.register.view.RegisterView;

/**
 * Created by SomeOneInTheWorld on 2016/10/17.
 */
public class RegisterPresenterImpl implements RegisterPresenter,RegisterModel.OnCallBackByRegisterModel{
    private RegisterView view;
    private RegisterModel model;

    public RegisterPresenterImpl(RegisterView view){
        this.view = view;
        this.model = new RegisterModelImpl();
    }

    @Override
    public void requestIdentifyingCode(String phone) {
        view.showProgressbar();
        model.requestIdentifyingCode(phone,this);
    }

    @Override
    public void onSuccess(String code) {
        view.hideProgressbar();
        view.onSuccessRequestForCode(code);
    }

    @Override
    public void onFail(String message) {
        view.hideProgressbar();
        view.onFailRequestForCode(message);
    }
}
