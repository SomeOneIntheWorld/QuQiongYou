package dream.quqiongyou.bean;

import java.util.Date;

/**
 * Created by SomeOneInTheWorld on 2016/10/10.
 */
public class CommentBean {
    private QuUser answeruser;
    private String comment;
    private Date answertime;
    private String source;
    private int commentnum;
    private int goodjobnum;
    private String phone;

    public void setAnsweruser(QuUser answeruser){
        this.answeruser = answeruser;
    }
    public QuUser getAnsweruser(){
        return answeruser;
    }
    public void setComment(String comment){
        this.comment = comment;
    }
    public String getComment(){
        return comment;
    }
    public void setAnswertime(Date answertime){
        this.answertime = answertime;
    }
    public Date getAnswertime(){
        return answertime;
    }
    public void setSource(String source){
        this.source = source;
    }
    public String getSource(){
        return source;
    }
    public void setCommentnum(int commentnum){ this.commentnum = commentnum; }
    public int getCommentnum(){return commentnum;}
    public void setGoodjobnum(int goodjobnum){this.goodjobnum = goodjobnum;}
    public int getGoodjobnum(){return goodjobnum;}
}
