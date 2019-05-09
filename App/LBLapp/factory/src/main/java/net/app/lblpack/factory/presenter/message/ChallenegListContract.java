package net.app.lblpack.factory.presenter.message;

import net.app.lblpack.factory.presenter.BaseContract;

public interface ChallenegListContract {
    interface Presenter extends BaseContract.Presenter {
    }

    // 都在基类完成了
    interface View extends BaseContract.View<ChallenegListContract.Presenter> {
        void getSuccessed();
    }

}
