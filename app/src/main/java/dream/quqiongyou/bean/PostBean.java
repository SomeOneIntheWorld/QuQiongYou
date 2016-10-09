package dream.quqiongyou.bean;

import java.util.List;

/**
 * Created by SomeOneInTheWorld on 2016/10/7.
 */
public class PostBean {
    private String title;
    private String subtitle;
    private QuUser poster;
    private String time;
    private int seenum;
    private int commentnum;
    private boolean isgreat;
    private List<String>imglist;

    public PostBean(String title,String subtitle){
        this.title = title;
        this.subtitle = subtitle;
        this.poster = QuUser.getCurrentUser();
    }

    public void setSubtitle(String subtitle){
        this.subtitle = subtitle;
    }
    public String getSubtitle(){
        return subtitle;
    }

    public void setPoster(QuUser poster){
        this.poster = poster;
    }
    public QuUser getPoster(){
        return poster;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;
    }

    public void setSeenum(int seenum){
        this.seenum = seenum;
    }
    public int getSeenum(){
        return seenum;
    }

    public void setCommentnum(int commentnum){
        this.commentnum = commentnum;
    }
    public int getCommentnum(){
        return commentnum;
    }

    public void setIsgreat(boolean isgreat){
        this.isgreat = isgreat;
    }
    public boolean getIsgreat(){
        return isgreat;
    }

    public void setTime(String time){
        this.time = time;
    }
    public String getTime(){
        return time;
    }

    public void setImglist(List<String>imglist){
        this.imglist = imglist;
    }
    public List<String> getImglist(){
        return imglist;
    }
}
