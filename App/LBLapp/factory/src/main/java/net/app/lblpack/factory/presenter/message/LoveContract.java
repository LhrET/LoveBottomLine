package net.app.lblpack.factory.presenter.message;

import net.app.lblpack.factory.model.db.Love;
import net.app.lblpack.factory.presenter.BaseContract;

public interface LoveContract {
    interface Presenter extends BaseContract.Presenter {
        // 获取Love信息

        Love getLove();
    }

    interface View extends BaseContract.View<LoveContract.Presenter> {

        // 加载数据完成
        void onLoadDone(Love love);

    }
}
