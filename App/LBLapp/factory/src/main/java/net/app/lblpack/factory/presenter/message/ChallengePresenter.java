package net.app.lblpack.factory.presenter.message;

import net.app.lblpack.factory.Factory;
import net.app.lblpack.factory.data.helper.ChallengeHelp;
import net.app.lblpack.factory.model.db.Challenge;
import net.app.lblpack.factory.presenter.BasePresenter;
import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;

public class ChallengePresenter extends BasePresenter<ChallengeContract.View>
        implements ChallengeContract.Presenter{
    private Challenge challenge;
    public ChallengePresenter(ChallengeContract.View view) {
        super(view);
    }

    @Override
    public Challenge getChallenge() {
        return challenge;
    }
    @Override
    public void start() {
        super.start();

        // 个人界面用户数据优先从网络拉取
        Factory.runOnAsync(new Runnable() {
            @Override
            public void run() {
                ChallengeContract.View view = getView();
                if (view != null) {
                    Challenge user = ChallengeHelp.searchFirstOfNet();
                    onLoaded(user);
                }
            }
        });

    }

    /**
     * 进行界面的设置
     *
     * @param love 用户信息
     */
    private void onLoaded(final Challenge love) {
        this.challenge = love;

        // 切换到Ui线程
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                final ChallengeContract.View view = getView();
                if (view == null)
                    return;
                view.onLoadDone(love);
            }
        });
    }
}
