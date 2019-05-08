package net.web.lblpack.push.bean.card;

import com.google.gson.annotations.Expose;
import net.web.lblpack.push.bean.db.Challenge;

import java.time.LocalDateTime;

public class ChallengeCard {
    @Expose
    private String id;
    @Expose
    private String originId;
    @Expose
    private String targetId;
    @Expose
    private boolean startFlag;
    @Expose
    private boolean finishFlag;
    @Expose
    private LocalDateTime createAt;// 创建时间

    public ChallengeCard(Challenge challenge){
        this.id = challenge.getId();
        this.originId = challenge.getOriginId();
        this.targetId = challenge.getTargetId();
        this.finishFlag = challenge.isFinishFlag();
        this.startFlag = challenge.isStartFlag();
        this.createAt = challenge.getCreateAt();
    }

    public String getId() {
        return id;
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

    public boolean isFinishFlag() {
        return finishFlag;
    }

    public void setFinishFlag(boolean finishFlag) {
        this.finishFlag = finishFlag;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}
