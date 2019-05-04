package net.app.lblpack.factory.data.message;

import net.app.lblpack.factory.data.DbDataSource;
import net.app.lblpack.factory.model.db.Message;

/**
 * 消息的数据源定义，他的实现是：MessageRepository, MessageGroupRepository
 * 关注的对象是Message表
 *
 * @version 1.0.0
 */
public interface MessageDataSource extends DbDataSource<Message> {
}
