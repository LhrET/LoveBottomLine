package net.app.lblpack.factory.presenter.message;

import net.app.lblpack.factory.Factory;
import net.app.lblpack.factory.data.helper.LoveHelper;
import net.app.lblpack.factory.model.db.Love;
import net.app.lblpack.factory.presenter.BasePresenter;
import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;

public class LovePresenter extends BasePresenter<LoveContract.View>
        implements LoveContract.Presenter {
    private Love love;
    public LovePresenter(LoveContract.View view) {
        super(view);
    }
    @Override
    public void start() {
        super.start();

        // 个人界面用户数据优先从网络拉取
        Factory.runOnAsync(new Runnable() {
            @Override
            public void run() {
                LoveContract.View view = getView();
                if (view != null) {
                    Love user = LoveHelper.findFromNet();
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
    private void onLoaded(final Love love) {
        this.love = love;

        // 切换到Ui线程
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                final LoveContract.View view = getView();
                if (view == null)
                    return;
                view.onLoadDone(love);
            }
        });
    }

    @Override
    public Love getLove() {
        return love;
    }
}
