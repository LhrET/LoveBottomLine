package net.app.lblpack.factory.model.api.message;

import net.app.lblpack.factory.model.db.UserChallenge;

public class UpdateChallenge {
    private int daySum;
    private String description;

    private String Url;
    private String guanka;
    private String sendId;
    private String receiveId;

    public int getDaySum() {
        return daySum;
    }

    public void setDaySum(int daySum) {
        this.daySum = daySum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getGuanka() {
        return guanka;
    }

    public void setGuanka(String guanka) {
        this.guanka = guanka;
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

    public UpdateChallenge(UserChallenge userChallenge){
            this.sendId = userChallenge.getSendId();
            this.receiveId = userChallenge.getReceiveId();
            this.daySum = userChallenge.getDaySum();
            this.description = userChallenge.getDescription();
            this.Url = userChallenge.getUrl();
            this.guanka = userChallenge.getGuanka();
    }

}
