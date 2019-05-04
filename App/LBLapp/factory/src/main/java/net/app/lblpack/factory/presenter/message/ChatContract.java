package net.app.lblpack.factory.presenter.message;

import net.app.lblpack.factory.model.db.Message;
import net.app.lblpack.factory.model.db.User;
import net.app.lblpack.factory.presenter.BaseContract;

/**
 * 聊天契约
 *
 * @version 1.0.0
 */
public interface ChatContract {
    interface Presenter extends BaseContract.Presenter {
        // 发送文字
        void pushText(String content);

        // 发送语音
        void pushAudio(String path, long time);

        // 发送图片
        void pushImages(String[] paths);

        // 重新发送一个消息，返回是否调度成功
        boolean rePush(Message message);
    }

    // 界面的基类
    interface View<InitModel> extends BaseContract.RecyclerView<Presenter, Message> {
        // 初始化的Model
        void onInit(InitModel model);
    }

    // 人聊天的界面
    interface UserView extends View<User> {

    }

}
