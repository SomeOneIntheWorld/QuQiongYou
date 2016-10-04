package dream.quqiongyou.common;

import java.util.List;

/**
 * Created by SomeOneInTheWorld on 2016/10/4.
 */
public interface CallBackByModel<T> {
    void loadSuccess(List<T>results);
    void loadFail(String errorMessage);
}
