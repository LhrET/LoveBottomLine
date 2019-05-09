package net.app.lblpack.factory.presenter.message;

import net.app.lblpack.factory.model.db.Challenge;
import net.app.lblpack.factory.presenter.BaseContract;

public interface ChallengeContract {
    interface Presenter extends BaseContract.Presenter {
        // 获取Love信息

        Challenge getChallenge();
    }

    interface View extends BaseContract.View<ChallengeContract.Presenter> {

        // 加载数据完成
        void onLoadDone(Challenge love);

    }
}
