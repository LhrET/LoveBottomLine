package net.app.lblpack.factory.presenter.contact;

import net.app.lblpack.factory.model.db.User;
import net.app.lblpack.factory.presenter.BaseContract;

/**
 * @version 1.0.0
 */
public interface PersonalContract {
    interface Presenter extends BaseContract.Presenter {
        // 获取用户信息
        User getUserPersonal();
    }

    interface View extends BaseContract.View<Presenter> {
        String getUserId();

        // 加载数据完成
        void onLoadDone(User user);

        // 是否发起聊天
        void allowSayHello(boolean isAllow);

        // 设置关注状态
        void setFollowStatus(boolean isFollow);

        void setLoveStatus(boolean isLove);
        void setHLoveStatus(boolean isLove);

    }
}
