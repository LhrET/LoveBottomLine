package net.app.lblpack.factory.data.message;

import net.app.lblpack.factory.model.card.LoveCard;

public interface LoveCenter {
    void dispatch(LoveCard... cards);
}
