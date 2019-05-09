package net.app.lblpack.factory.presenter.message;

import net.app.lblpack.factory.model.db.UserChallenge;
import net.app.lblpack.factory.presenter.BaseContract;

public interface UserChallengetContract {
    interface Presenter extends BaseContract.Presenter {
        // 获取Love信息
        void setDayNum(int dayNum);
        UserChallenge getUserChallenge();
        void update(String photoFilePath, String desc,int dayNum);
    }

    interface View extends BaseContract.View<UserChallengetContract.Presenter> {

        // 加载数据完成
        void onLoadDone(UserChallenge love);
        void updateSucceed();

    }
}
