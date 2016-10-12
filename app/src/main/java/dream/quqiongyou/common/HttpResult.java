package dream.quqiongyou.common;

/**
 * Created by SomeOneInTheWorld on 2016/10/11.
 */
public class HttpResult<T> {
    private int resultCode;
    private String resultMessage;

    private T data;

    public void setResultCode(int resultCode){
        this.resultCode = resultCode;
    }
    public int getResultCode(){
        return resultCode;
    }
    public void setResultMessage(String resultMessage){
        this.resultMessage = resultMessage;
    }
    public String getResultMessage(){
        return resultMessage;
    }
    public void setData(T data){
        this.data = data;
    }
    public T getData(){
        return data;
    }
}