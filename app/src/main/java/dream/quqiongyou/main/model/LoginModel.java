package dream.quqiongyou.main.model;

import dream.quqiongyou.Bean.QuUser;

/**
 * Created by SomeOneInTheWorld on 2016/10/3.
 */
public interface LoginModel {
    void checkQuUserByModel(QuUser user, LoginModelImpl.CallBackByLoginModel listener);
}
