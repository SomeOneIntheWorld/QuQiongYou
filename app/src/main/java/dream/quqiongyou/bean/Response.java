package dream.quqiongyou.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by SomeOneInTheWorld on 2016/10/12.
 */
public class Response<T> extends BaseBean implements Serializable {

    /**
     * 服务器的返回代码
     */
    @SerializedName("code")
    public int code;

    /**
     * 服务器的返回消息
     */
    @SerializedName("message")
    public String message;

    @SerializedName("data")
    public T data;
}