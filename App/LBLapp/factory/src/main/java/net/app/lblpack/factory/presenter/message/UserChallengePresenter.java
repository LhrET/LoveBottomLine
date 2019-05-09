package net.app.lblpack.factory.presenter.message;

import android.text.TextUtils;

import net.app.lblpack.factory.Factory;
import net.app.lblpack.factory.R;
import net.app.lblpack.factory.data.DataSource;
import net.app.lblpack.factory.data.helper.UserChallengeHelper;
import net.app.lblpack.factory.model.api.message.UpdateChallenge;
import net.app.lblpack.factory.model.card.UserChallengeCard;
import net.app.lblpack.factory.model.db.UserChallenge;
import net.app.lblpack.factory.net.UploadHelper;
import net.app.lblpack.factory.presenter.BasePresenter;
import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;

public class UserChallengePresenter extends BasePresenter<UserChallengetContract.View>
        implements UserChallengetContract.Presenter, DataSource.Callback<UserChallengeCard>{
    private UserChallenge userChallenge;
    public UserChallengePresenter(UserChallengetContract.View view) {
        super(view);
    }

    private int dayNum = -1;

    public int getDayNum() {
        return dayNum;
    }

    public void setDayNum(int dayNum) {
        this.dayNum = dayNum;
    }

    @Override
    public void start() {
        super.start();

        // 个人界面用户数据优先从网络拉取
        Factory.runOnAsync(new Runnable() {
            @Override
            public void run() {
                UserChallengetContract.View view = getView();
                if (view != null) {
                    UserChallenge user = UserChallengeHelper.searchFirstOfNet(dayNum);
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
    private void onLoaded(final UserChallenge love) {
        this.userChallenge = love;

        // 切换到Ui线程
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                final UserChallengetContract.View view = getView();
                if (view == null)
                    return;
                view.onLoadDone(love);
            }
        });
    }

    @Override
    public UserChallenge getUserChallenge() {
        return userChallenge;
    }

    @Override
    public void update(final String photoFilePath, final String desc, final int dayNum) {
        start();

        final UserChallengetContract.View view = getView();

            if (TextUtils.isEmpty(photoFilePath) || TextUtils.isEmpty(desc)) {
                view.showError(R.string.data_account_update_invalid_parameter);
            } else {
                // 上传头像
                Factory.runOnAsync(new Runnable() {
                    @Override
                    public void run() {
                        String url = UploadHelper.uploadImage(photoFilePath);
                        if (TextUtils.isEmpty(url)) {
                            // 上传失败
                            view.showError(R.string.data_upload_error);
                        } else {
                            // 构建Model
                            UserChallenge userChallenge = UserChallengeHelper.findFromNet(dayNum);
                            UpdateChallenge model = new UpdateChallenge(userChallenge);
                            model.setUrl(url);

                            model.setDescription(desc);

                            // 进行网络请求，上传
                            UserChallengeHelper.update(model, UserChallengePresenter.this);
                        }
                    }
                });
            }
        }



    @Override
    public void onDataNotAvailable(int strRes) {

    }

    @Override
    public void onDataLoaded(UserChallengeCard userChallengeCard) {
        final UserChallengetContract.View view = getView();
        if (view == null)
            return;
        // 强制执行在主线程中
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                view.updateSucceed();
            }
        });
    }
}
