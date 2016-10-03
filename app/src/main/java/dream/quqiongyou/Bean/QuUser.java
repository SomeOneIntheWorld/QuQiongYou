package dream.quqiongyou.Bean;

/**
 * Created by SomeOneInTheWorld on 2016/10/3.
 */
public class QuUser {
    private String password;
    private String account;

    public QuUser(){}

    public QuUser(String account,String password){
        this.account = account;
        this.password = password;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }

    public void setAccount(String account){
        this.account = account;
    }
    public String getAccount(){
        return account;
    }
}
