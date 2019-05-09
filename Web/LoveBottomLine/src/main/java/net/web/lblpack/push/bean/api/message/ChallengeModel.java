package net.web.lblpack.push.bean.api.message;

import com.google.common.base.Strings;
import com.google.gson.annotations.Expose;
import net.web.lblpack.push.bean.db.Challenge;
import net.web.lblpack.push.utils.TextUtil;

import java.time.LocalDateTime;

public class ChallengeModel {
    @Expose
    private String originId;
    @Expose
    private String targetId;
    @Expose
    private boolean startFlag;
    @Expose
    private boolean finishFlag;
    @Expose
    private int dayNum;
    @Expose
    private LocalDateTime createAt;

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

    public int getDayNum() {
        return dayNum;
    }

    public void setDayNum(int dayNum) {
        this.dayNum = dayNum;
    }

    public Challenge updateChallenge(Challenge love) {
        love.setDayNum(dayNum);
        love.setStartFlag(startFlag);
        love.setFinishFlag(finishFlag);
        return love;

    }
    public Challenge updateChallengeC(Challenge love) {
        love.setStartFlag(startFlag);
        love.setFinishFlag(finishFlag);
        love.setDayNum(dayNum);
        love.setCreateAt(createAt);
        return love;

    }
    public static boolean check(ChallengeModel model) {
        return model != null
                && (!Strings.isNullOrEmpty(model.targetId) ||
                !Strings.isNullOrEmpty(model.originId));
    }
}
