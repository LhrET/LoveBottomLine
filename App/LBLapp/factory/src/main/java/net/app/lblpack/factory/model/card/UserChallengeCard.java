package net.app.lblpack.factory.model.card;

import net.app.lblpack.factory.model.db.UserChallenge;

import java.util.Date;

public class UserChallengeCard {
    private String id;
    private String sendId;
    private String receiveId;
    String Url;
    String description;
    String guanka;
    private int daySum;
    private Date createAt;// 创建时间

    private  transient  UserChallenge userChallenge;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    public String getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(String receiveId) {
        this.receiveId = receiveId;
    }

    public UserChallenge getUserChallenge() {
        return userChallenge;
    }

    public void setUserChallenge(UserChallenge userChallenge) {
        this.userChallenge = userChallenge;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGuanka() {
        return guanka;
    }

    public void setGuanka(String guanka) {
        this.guanka = guanka;
    }

    public int getDaySum() {
        return daySum;
    }

    public void setDaySum(int daySum) {
        this.daySum = daySum;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
    public UserChallenge build() {
        if (userChallenge == null) {
            UserChallenge challenge = new UserChallenge();
            challenge.setId(id);
            challenge.setSendId(sendId);
            challenge.setReceiveId(receiveId);
            challenge.setDaySum(daySum);
            challenge.setCreateAt(createAt);
            challenge.setDescription(description);
            challenge.setUrl(Url);
            challenge.setGuanka(guanka);
            this.userChallenge = challenge;
        }
        return userChallenge;
    }
}
