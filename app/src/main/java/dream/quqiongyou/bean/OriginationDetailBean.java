package dream.quqiongyou.bean;

import java.util.List;

/**
 * Created by SomeOneInTheWorld on 2016/10/4.
 */
public class OriginationDetailBean {
    private String topic;
    private String time;
    private String location;
    private int price;
    private String phone;
    private List<String> imglist;

    public void setTopic(String topic){
        this.topic = topic;
    }
    public String getTopic(){
        return topic;
    }

    public void setTime(String time){
        this.time = time;
    }
    public String getTime(){
        return time;
    }

    public void setLocation(String location){
        this.location = location;
    }
    public String getLocation(){
        return location;
    }

    public void setPrice(int price){
        this.price = price;
    }
    public int getPrice(){
        return price;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }
    public String getPhone(){
        return phone;
    }

    public void setImglist(List<String>imgList){
        this.imglist = imgList;
    }
}

