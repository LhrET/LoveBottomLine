package net.app.lblpack.factory.model.card;

import net.app.lblpack.factory.model.db.Challenge;

import java.util.Date;

public class ChallengeCard {
    private String id;
    private String originId;
    private String targetId;
    private boolean startFlag;
    private boolean finishFlag;
    private int dayNum;

    private Date createAt;// 创建时间

    private transient Challenge challenge;

    public String getId() {
        return id;
    }
    public Challenge build() {
        if (challenge == null) {
            Challenge love = new Challenge();
            love.setId(id);
            love.setOriginId(originId);
            love.setTargetId(targetId);
            love.setStartFlag(startFlag);
            love.setFinishFlag(finishFlag);
            love.setCreateAt(createAt);
            love.setDayNum(dayNum);
            this.challenge = love;
        }
        return challenge;
    }

    public boolean isFinishFlag() {
        return finishFlag;
    }

    public void setFinishFlag(boolean finishFlag) {
        this.finishFlag = finishFlag;
    }

    public int getDayNum() {
        return dayNum;
    }

    public void setDayNum(int dayNum) {
        this.dayNum = dayNum;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public boolean isStartFlag() {
        return startFlag;
    }

    public void setStartFlag(boolean startFlag) {
        this.startFlag = startFlag;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }
}
