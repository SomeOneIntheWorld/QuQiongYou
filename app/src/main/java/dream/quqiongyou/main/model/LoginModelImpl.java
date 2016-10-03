package dream.quqiongyou.main.model;

import dream.quqiongyou.Bean.QuUser;

/**
 * Created by SomeOneInTheWorld on 2016/10/3.
 */
public class LoginModelImpl implements LoginModel {
    @Override
    public void checkQuUserByModel(QuUser user,CallBackByLoginModel listener) {
        listener.onSuccess();
    }

    public interface CallBackByLoginModel{
        void onSuccess();
        void onFail();
    }
}
