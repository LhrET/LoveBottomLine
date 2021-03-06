package net.app.lblpack.factory.data.message;

import net.app.lblpack.factory.model.card.MessageCard;

/**
 * 消息中心，进行消息卡片的消费
 *
 * @version 1.0.0
 */
public interface MessageCenter {
    void dispatch(MessageCard... cards);
}
