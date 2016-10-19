package dream.quqiongyou.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by SomeOneInTheWorld on 2016/10/4.
 */
public class OriginationDetailBean implements Serializable{
    private String topic;
    private String starttime;
    private String endtime;
    private String location;
    private String price;
    private String phone;
    private int maxpeoplenum;
    private boolean needName;
    private boolean needPhone;
    private boolean needIntroduce;
    private List<String> imglist;

    public void setTopic(String topic){
        this.topic = topic;
    }
    public String getTopic(){
        return topic;
    }

    public void setStarttime(String starttime){
        this.starttime = starttime;
    }
    public String getStarttime(){
        return starttime;
    }

    public void setEndtime(String endtime){
        this.endtime = endtime;
    }
    public String getEndtime(){
        return endtime;
    }

    public void setLocation(String location){
        this.location = location;
    }
    public String getLocation(){
        return location;
    }

    public void setPrice(String price){
        this.price = price;
    }
    public String getPrice(){
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
    public List<String>getImglist(){
        return this.imglist;
    }

    public void setMaxpeoplenum(int maxpeoplenum){
        this.maxpeoplenum = maxpeoplenum;
    }
    public int getMaxpeoplenum(){
        return maxpeoplenum;
    }

    public void setNeedName(boolean needName){
        this.needName = needName;
    }
    public boolean getNeedName(){
        return needName;
    }

    public void setNeedPhone(boolean needPhone){
        this.needPhone = needPhone;
    }
    public boolean getNeedPhone(){
        return needPhone;
    }

    public void setNeedIntroduce(boolean needIntroduce){
        this.needIntroduce = needIntroduce;
    }
    public boolean getNeedIntroduce(){
        return needIntroduce;
    }
}

