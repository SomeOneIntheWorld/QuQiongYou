package dream.quqiongyou.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SaladJack on 2016/11/6.
 */

public class TripDetail {

    /**
     * id : 1
     * acname : test
     * renshu : 100
     * image : wu
     * begintime : 2016-10-14 00:00:00
     * endtime : 2016-10-22 00:00:00
     * liulantimes : 100
     * position : 中国
     * yuan : 1000
     */

    private String id;
    @SerializedName("acname")
    private String name;
    @SerializedName("renshu")
    private String peopleNum;
    private String image;
    private String begintime;
    private String endtime;
    @SerializedName("liulantimes")
    private String seenNum;
    @SerializedName("position")
    private String location;
    @SerializedName("yuan")
    private String consumeNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(String peopleNum) {
        this.peopleNum = peopleNum;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getSeenNum() {
        return seenNum;
    }

    public void setSeenNum(String seenNum) {
        this.seenNum = seenNum;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getConsumeNum() {
        return consumeNum;
    }

    public void setConsumeNum(String consumeNum) {
        this.consumeNum = consumeNum;
    }
}
