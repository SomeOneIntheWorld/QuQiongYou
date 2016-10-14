package dream.quqiongyou.bean;

import java.io.Serializable;

/**
 * Created by SomeOneInTheWorld on 2016/10/12.
 */
public class Response<T> extends BaseBean implements Serializable {

    /**
     * 服务器的返回代码
     */
    public int code;

    /**
     * 服务器的返回消息
     */
    public String message;

    public T data;
}