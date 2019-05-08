package net.app.lblpack.factory.data.message;

import net.app.lblpack.factory.model.card.UserChallengeCard;

public interface UserChallengeCenter {
    void dispatch(UserChallengeCard... cards);
}
