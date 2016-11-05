package dream.quqiongyou.bean;

/**
 * Created by SomeOneInTheWorld on 2016/10/3.
 */
public class HomeItemBean {
    private String imageurl;
    private String acname;
    private String  begintime;
    private String endtime;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImageurl(String imageurl){
        this.imageurl = imageurl;
    }
    public String getImageurl(){
        return imageurl;
    }

    public void setAcname(String acname){
        this.acname = acname;
    }
    public String getAcname(){
        return acname;
    }

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
}
