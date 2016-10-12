package dream.quqiongyou.common;

import rx.functions.Func1;

/**
 * Created by SomeOneInTheWorld on 2016/10/11.
 */
public class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

    @Override
    public T call(HttpResult<T> httpResult) {
        if (httpResult.getResultCode() != 666) {
            throw new RuntimeException(String.valueOf(httpResult.getResultCode()));
        }
        return httpResult.getData();
    }
}
