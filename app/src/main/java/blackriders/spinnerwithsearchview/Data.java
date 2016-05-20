package blackriders.spinnerwithsearchview;

/**
 * Created by Sanwal Singh on 20/5/16.
 */
public class Data {

    String id,visitedDate,name,status,comment,sentmessage,dealer_id,followdate,createDate,
            userid,username,FollowupStatus,telephone;

    public Data(String id, String visitedDate, String name, String status, String comment,
                String sentmessage, String dealer_id, String followdate, String createDate,
                String userid, String username, String followupStatus, String telephone) {
        this.id = id;
        this.visitedDate = visitedDate;
        this.name = name;
        this.status = status;
        this.comment = comment;
        this.sentmessage = sentmessage;
        this.dealer_id = dealer_id;
        this.followdate = followdate;
        this.createDate = createDate;
        this.userid = userid;
        this.username = username;
        FollowupStatus = followupStatus;
        this.telephone = telephone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVisitedDate() {
        return visitedDate;
    }

    public void setVisitedDate(String visitedDate) {
        this.visitedDate = visitedDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSentmessage() {
        return sentmessage;
    }

    public void setSentmessage(String sentmessage) {
        this.sentmessage = sentmessage;
    }

    public String getDealer_id() {
        return dealer_id;
    }

    public void setDealer_id(String dealer_id) {
        this.dealer_id = dealer_id;
    }

    public String getFollowdate() {
        return followdate;
    }

    public void setFollowdate(String followdate) {
        this.followdate = followdate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFollowupStatus() {
        return FollowupStatus;
    }

    public void setFollowupStatus(String followupStatus) {
        FollowupStatus = followupStatus;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
