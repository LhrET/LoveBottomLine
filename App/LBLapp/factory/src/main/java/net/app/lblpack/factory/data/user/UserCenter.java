package net.app.lblpack.factory.data.user;

import net.app.lblpack.factory.model.card.UserCard;

/**
 * 用户中心的基本定义
 *
 * @version 1.0.0
 */
public interface UserCenter {
    // 分发处理一堆用户卡片的信息，并更新到数据库
    void dispatch(UserCard... cards);
}
