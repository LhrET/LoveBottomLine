package net.web.lblpack.push.bean.card;

import com.google.gson.annotations.Expose;
import net.web.lblpack.push.bean.db.UserChallenge;

import java.time.LocalDateTime;

public class UserChallengeCard {

    @Expose
    private String id;

    @Expose
    private boolean startFlag;
    @Expose
    private boolean finishFlag;
    @Expose
    private int daySum;

    @Expose
    private LocalDateTime createAt = LocalDateTime.now();


    @Expose
    private String sendId;

    @Expose
    private String receiveId;

    public UserChallengeCard(UserChallenge userChallenge) {
        this.id = userChallenge.getId();
        this.startFlag = userChallenge.isStartFlag();
        this.finishFlag = userChallenge.isFinishFlag();
        this.daySum = userChallenge.getDaySum();
        this.createAt = userChallenge.getCreateAt();
        this.sendId = userChallenge.getSendId();
        this.receiveId = userChallenge.getReceiveId();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isStartFlag() {
        return startFlag;
    }

    public void setStartFlag(boolean startFlag) {
        this.startFlag = startFlag;
    }

    public boolean isFinishFlag() {
        return finishFlag;
    }

    public void setFinishFlag(boolean finishFlag) {
        this.finishFlag = finishFlag;
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
