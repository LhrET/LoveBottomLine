package net.app.lblpack.factory.model.api.message;

import java.util.Date;

public class ChallengeModel {
    private String originId;
    private String targetId;
    private boolean startFlag;
    private boolean finishFlag;
    private int dayNum;
    private Date createAt;
    public ChallengeModel(String originId,String targetId,boolean startFlag,boolean finishFlag,int dayNum){
        this.originId = originId;
        this.targetId = targetId;
        this.startFlag = startFlag;
        this.finishFlag = finishFlag;
        this.dayNum = dayNum;
    }
    public ChallengeModel(String originId,String targetId,boolean startFlag,boolean finishFlag,Date createAt,int dauNum){
        this.originId = originId;
        this.targetId = targetId;
        this.startFlag = startFlag;
        this.finishFlag = finishFlag;
        this.createAt = createAt;
        this.dayNum = dauNum;
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

    public boolean isFinishFlag() {
        return finishFlag;
    }

    public void setFinishFlag(boolean finishFlag) {
        this.finishFlag = finishFlag;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public int getDayNum() {
        return dayNum;
    }

    public void setDayNum(int dayNum) {
        this.dayNum = dayNum;
    }
}
