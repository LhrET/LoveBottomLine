package net.web.lblpack.push.bean.card;

import com.google.gson.annotations.Expose;
import net.web.lblpack.push.bean.db.UserChallenge;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class UserChallengeCard {

    @Expose
    private String id;

    @Expose
    private int daySum;
    @Expose
    private String description;
    @Expose
    private String guanka;
    @Expose
    private String Url;

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

    @Expose
    private LocalDateTime createAt = LocalDateTime.now();


    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }


    @Expose
    private String sendId;

    @Expose
    private String receiveId;

    public UserChallengeCard(UserChallenge userChallenge) {
        this.id = userChallenge.getId();
        this.daySum = userChallenge.getDaySum();
        this.createAt = userChallenge.getCreateAt();
        this.sendId = userChallenge.getSendId();
        this.receiveId = userChallenge.getReceiveId();
        this.Url = userChallenge.getUrl();
        this.description = userChallenge.getDescription();
        this.guanka = userChallenge.getGuanka();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public int getDaySum() {
        return daySum;
    }

    public void setDaySum(int daySum) {
        this.daySum = daySum;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
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
}
