package net.web.lblpack.push.bean.api.user;

import com.google.common.base.Strings;
import com.google.gson.annotations.Expose;
import net.web.lblpack.push.bean.db.User;
import net.web.lblpack.push.bean.db.UserChallenge;
import net.web.lblpack.push.factory.ChallengeFactory;
import net.web.lblpack.push.factory.UserFactory;
import net.web.lblpack.push.utils.TextUtil;

import java.time.LocalDateTime;

public class UpdateChallenge {


    @Expose
    private int daySum;
    @Expose
    private String description;

    @Expose
    private String Url;
    @Expose
    private String guanka;
    @Expose
    private String sendId;

    @Expose
    private String receiveId;


    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
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

        if (daySum != 0)
            userChallenge.setDaySum(daySum);
        if (!Strings.isNullOrEmpty(Url))
            userChallenge.setUrl(Url);
        if (!Strings.isNullOrEmpty(description))
            userChallenge.setDescription(description);
        if (!Strings.isNullOrEmpty(guanka))
            userChallenge.setGuanka(guanka);
        return userChallenge;
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

    public static boolean check(UpdateChallenge model) {
        // Model 不允许为null，
        // 并且只需要具有一个及其以上的参数即可
        return model != null
                && (!Strings.isNullOrEmpty(model.receiveId) ||
                !Strings.isNullOrEmpty(model.sendId) ||
                model.daySum != 0);
    }

}
