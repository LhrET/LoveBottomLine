package net.app.lblpack.factory.data.message;

import net.app.lblpack.factory.model.card.ChallengeCard;

public interface ChallengeCenter {
    void dispatch(ChallengeCard... cards);
}
