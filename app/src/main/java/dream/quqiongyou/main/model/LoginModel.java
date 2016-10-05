package dream.quqiongyou.main.model;

import dream.quqiongyou.bean.QuUser;

/**
 * Created by SomeOneInTheWorld on 2016/10/3.
 */
public interface LoginModel {
    interface CallBackByLoginModel{
        void onSuccess();
        void onFail();
    }

    void checkQuUserByModel(QuUser user, LoginModelImpl.CallBackByLoginModel listener);
}
