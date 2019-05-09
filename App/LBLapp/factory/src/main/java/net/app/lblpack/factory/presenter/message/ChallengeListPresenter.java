package net.app.lblpack.factory.presenter.message;

import net.app.lblpack.factory.Factory;
import net.app.lblpack.factory.data.helper.UserChallengeHelper;
import net.app.lblpack.factory.presenter.BasePresenter;
import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;

public class ChallengeListPresenter extends BasePresenter<ChallenegListContract.View>
        implements ChallenegListContract.Presenter{
    public ChallengeListPresenter(ChallenegListContract.View view) {
        super(view);
    }
    @Override
    public void start() {
        super.start();

        // 个人界面用户数据优先从网络拉取
        Factory.runOnAsync(new Runnable() {
            @Override
            public void run() {
                ChallenegListContract.View view = getView();
                if (view != null) {
                    UserChallengeHelper.refreshUCContacts();
                    onLoaded();
                }
            }
        });

    }
    private void onLoaded() {

        // 切换到Ui线程
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                final ChallenegListContract.View view = getView();
                if (view == null)
                    return;
                view.getSuccessed();
            }
        });
    }

}
