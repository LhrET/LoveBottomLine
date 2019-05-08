package net.app.lblpack.factory.model.api.message;

import net.app.lblpack.factory.model.db.UserChallenge;

public class UpdateChallenge {
    private int daySum;
    private String description;

    private String Url;
    private String guanka;
    private String sendId;
    private String receiveId;

    public UpdateChallenge(UserChallenge userChallenge){
            this.sendId = userChallenge.getSendId();
            this.receiveId = userChallenge.getReceiveId();
            this.daySum = userChallenge.getDaySum();
            this.description = userChallenge.getDescription();
            this.Url = userChallenge.getUrl();
            this.guanka = userChallenge.getGuanka();
    }

}
