package net.app.lblpack.factory.presenter.user;

import android.text.TextUtils;
import android.util.Log;

import net.app.lblpack.factory.Factory;
import net.app.lblpack.factory.R;
import net.app.lblpack.factory.data.DataSource;
import net.app.lblpack.factory.data.helper.UserHelper;
import net.app.lblpack.factory.model.api.user.UserUpdateModel;
import net.app.lblpack.factory.model.card.UserCard;
import net.app.lblpack.factory.model.db.User;
import net.app.lblpack.factory.net.UploadHelper;
import net.app.lblpack.factory.presenter.BasePresenter;
import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;

/**
 * @version 1.0.0
 */
public class UpdateInfoPresenter extends BasePresenter<UpdateInfoContract.View>
        implements UpdateInfoContract.Presenter, DataSource.Callback<UserCard> {
    public UpdateInfoPresenter(UpdateInfoContract.View view) {
        super(view);
    }

    @Override
    public void update(final String photoFilePath, final String desc, final boolean isMan) {
        start();

        final UpdateInfoContract.View view = getView();

        if (TextUtils.isEmpty(photoFilePath) || TextUtils.isEmpty(desc)) {
            view.showError(R.string.data_account_update_invalid_parameter);
        } else {
            // 上传头像
            Factory.runOnAsync(new Runnable() {
                @Override
                public void run() {
                    String url = UploadHelper.uploadPortrait(photoFilePath);
                    if (TextUtils.isEmpty(url)) {
                        // 上传失败
                        view.showError(R.string.data_upload_error);
                    } else {
                        // 构建Model
                        UserUpdateModel model = new UserUpdateModel("", url, desc,
                                isMan ? User.SEX_MAN : User.SEX_WOMAN);

                        // 进行网络请求，上传
                        Log.e("TAG",model.toString());
                        UserHelper.update(model, UpdateInfoPresenter.this);
                    }
                }
            });
        }
    }

    @Override
    public void onDataLoaded(UserCard userCard) {
        final UpdateInfoContract.View view = getView();
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

    @Override
    public void onDataNotAvailable(final int strRes) {
        final UpdateInfoContract.View view = getView();
        if (view == null)
            return;
        // 强制执行在主线程中
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                view.showError(strRes);
            }
        });
    }
}
