package dream.quqiongyou.bean;

import java.io.Serializable;

/**
 * Created by SomeOneInTheWorld on 2016/10/4.
 */
public class TopicBean implements Serializable{
    private String imgurl;
    private String title;
    private String topicnum;
    private int imgId;

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
