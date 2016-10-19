package dream.quqiongyou.bean;

import java.io.Serializable;

/**
 * Created by SomeOneInTheWorld on 2016/10/3.
 */
public class QuUser implements Serializable{
    //we need a method to get the current QuUser,because it is important.
    private String password;
    private String account;
    private String nickname;
    private String headingimg;
    private String phone;
    private int level;

    private static QuUser instance = null;

    public QuUser(){}

    public QuUser(String account,String password){
        this.account = account;
        this.password = password;
    }

    public static void setCurrentUser(QuUser currentUser){
        instance = currentUser;
    }

    public static QuUser getCurrentUser(){
        return instance;
    }

    //for register
    public QuUser(String account,String password,String nickname,String headingimg){
        this.account = account;
        this.password = password;
        this.nickname = nickname;
        this.headingimg = headingimg;
        this.level = 1;
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

    public void setLevel(int level){
        this.level = level;
    }
    public int getLevel(){
        return level;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public String getNickname(){
        return nickname;
    }

    public void setHeadingimg(String headingimg){
        this.headingimg = headingimg;
    }
    public String getHeadingimg(){
        return headingimg;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }
    public String getPhone(){
        return phone;
    }
}
