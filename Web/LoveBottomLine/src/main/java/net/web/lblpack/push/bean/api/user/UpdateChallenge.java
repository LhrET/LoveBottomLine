package net.web.lblpack.push.bean.api.user;

import com.google.common.base.Strings;
import com.google.gson.annotations.Expose;
import net.web.lblpack.push.bean.db.User;
import net.web.lblpack.push.bean.db.UserChallenge;
import net.web.lblpack.push.factory.ChallengeFactory;
import net.web.lblpack.push.factory.UserFactory;

import java.time.LocalDateTime;

public class UpdateChallenge {


    @Expose
    private boolean startFlag;
    @Expose
    private boolean finishFlag;
    @Expose
    private int daySum;


    @Expose
    private String sendId;

    @Expose
    private String receiveId;

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

    public UserChallenge updateToUserChallenge(UserChallenge userChallenge) {
        userChallenge.setStartFlag(startFlag);
        userChallenge.setFinishFlag(finishFlag);
        if (daySum != 0)
            userChallenge.setDaySum(daySum);

        return userChallenge;
    }

    public static boolean check(UpdateChallenge model) {
        // Model 不允许为null，
        // 并且只需要具有一个及其以上的参数即可
        return model != null
                && (!Strings.isNullOrEmpty(model.receiveId) ||
                !Strings.isNullOrEmpty(model.sendId) ||
                model.daySum != 0);
    }

}
