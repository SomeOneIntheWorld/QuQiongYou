package dream.quqiongyou.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by SomeOneInTheWorld on 2016/10/4.
 */
public class TopicBean implements Serializable{
    @SerializedName("image") private String imgurl;
    @SerializedName("name") private String title;
    @SerializedName("number") private String topicnum;
    @SerializedName("leibie")private int type;
    private int imgId;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setImgurl(String imgurl){
        this.imgurl = imgurl;
    }
    public String getImgurl(){
        return imgurl;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;
    }

    public void setTopicnum(String topicnum){
        this.topicnum = topicnum;
    }
    public String getTopicnum(){
        return topicnum;
    }

    public int getImgId() {
        return imgId;
    }
    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
