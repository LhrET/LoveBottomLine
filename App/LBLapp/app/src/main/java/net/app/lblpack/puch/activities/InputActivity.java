package net.app.lblpack.puch.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.EditText;

import com.bumptech.glide.Glide;
import com.yalantis.ucrop.UCrop;

import net.app.lblpack.common.app.Application;
import net.app.lblpack.common.app.PresenterToolbarActivity;
import net.app.lblpack.common.widget.InputImageView;
import net.app.lblpack.factory.model.db.UserChallenge;
import net.app.lblpack.factory.presenter.message.UserChallengePresenter;
import net.app.lblpack.factory.presenter.message.UserChallengetContract;
import net.app.lblpack.puch.R;
import net.app.lblpack.puch.frags.media.GalleryFragment;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

public class InputActivity extends PresenterToolbarActivity<UserChallengetContract.Presenter> implements
        UserChallengetContract.View{
    @BindView(R.id.text)
    EditText editText;
    @BindView(R.id.image_input_view)
    InputImageView imageButton;

    private String mPortraitPath;
    @OnClick(R.id.image_input_view)
    public void onClickImage(){
        new GalleryFragment()
                .setListener(new GalleryFragment.OnSelectedListener() {
                    @Override
                    public void onSelectedImage(String path) {
                        UCrop.Options options = new UCrop.Options();
                        // 设置图片处理的格式JPEG
                        options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
                        // 设置压缩后的图片精度
                        options.setCompressionQuality(96);

                        // 得到头像的缓存地址
                        File dPath = Application.getPortraitTmpFile();

                        // 发起剪切
                        UCrop.of(Uri.fromFile(new File(path)), Uri.fromFile(dPath))
                                .withAspectRatio(1, 1) // 1比1比例
                                .withMaxResultSize(520, 520) // 返回最大的尺寸
                                .withOptions(options) // 相关参数
                                .start(InputActivity.this);
                    }
                })
                // show 的时候建议使用getChildFragmentManager，
                // tag GalleryFragment class 名
                .show(getSupportFragmentManager(), GalleryFragment.class.getName());
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_input;
    }
    @OnClick(R.id.btn_submit)
    public void onClickInput(){
        mPresenter.update(mPortraitPath,editText.getText().toString(),getIntent().getIntExtra("dayNum",0));
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            // 通过UCrop得到对应的Uri
            final Uri resultUri = UCrop.getOutput(data);
            if (resultUri != null) {
                loadImage(resultUri);
            }
        } else if (resultCode == UCrop.RESULT_ERROR) {
            Application.showToast(R.string.data_rsp_error_unknown);
        }
    }
    private void loadImage(Uri uri) {
        // 得到头像地址
        mPortraitPath = uri.getPath();

        Glide.with(this)
                .load(uri)
                .asBitmap()
                .centerCrop()
                .into(imageButton);
    }

    @Override
    protected UserChallengetContract.Presenter initPresenter() {
        return new UserChallengePresenter(this);
    }

    @Override
    public void onLoadDone(UserChallenge love) {

    }

    @Override
    public void updateSucceed() {
        Intent intent = new Intent(InputActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
