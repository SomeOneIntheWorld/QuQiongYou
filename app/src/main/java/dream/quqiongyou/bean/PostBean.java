package dream.quqiongyou.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by SomeOneInTheWorld on 2016/10/7.
 */
public class PostBean implements Serializable {

    /**
     * id : 3
     * author : author5
     * level : 2
     * h_image : image5
     * time : 0000-00-00 00:00:00
     * content : test_content
     * abstract : test_abstract
     * t_image : test_image
     */

    private String id;
    private String author;
    private String level;
    private String h_image;
    private String time;
    @SerializedName("content")
    private String title;
    @SerializedName("abstract")
    private String subtitle;
    private String t_image;
    private int floor;
    /**
     * 以下变量在后台接口中还没定义
     */
    private int commentNum = 10;
    private int seenNum = 10;
    private boolean isGreat = true;
    private int goodJobNum = 10;
    private String phone = "来自iphone";
    public PostBean() {
    }

    public PostBean(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getH_image() {
        return h_image;
    }

    public void setH_image(String h_image) {
        this.h_image = h_image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getT_image() {
        return t_image;
    }

    public void setT_image(String t_image) {
        this.t_image = t_image;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getSeenNum() {
        return seenNum;
    }

    public void setSeenNum(int seenNum) {
        this.seenNum = seenNum;
    }

    public boolean isGreat() {
        return isGreat;
    }

    public void setGreat(boolean great) {
        isGreat = great;
    }

    public int getGoodJobNum() {
        return goodJobNum;
    }

    public void setGoodJobNum(int goodJobNum) {
        this.goodJobNum = goodJobNum;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}



