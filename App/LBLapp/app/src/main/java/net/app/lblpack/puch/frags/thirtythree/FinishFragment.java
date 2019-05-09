package net.app.lblpack.puch.frags.thirtythree;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import net.app.lblpack.common.app.PresenterFragment;
import net.app.lblpack.factory.model.db.UserChallenge;
import net.app.lblpack.factory.presenter.message.UserChallengePresenter;
import net.app.lblpack.factory.presenter.message.UserChallengetContract;
import net.app.lblpack.puch.R;

import butterknife.BindView;

public class FinishFragment extends PresenterFragment<UserChallengetContract.Presenter>
implements UserChallengetContract.View{
    @BindView(R.id.day_text)
    TextView day_txt;
    @BindView(R.id.task_text)
    TextView task_text;
    @BindView(R.id.said_text)
    TextView said_text;
    @BindView(R.id.picture_imageView)
    ImageView picture_imageView;
    private int dayNum;
    @Override
    protected void initArgs(Bundle bundle) {
        super.initArgs(bundle);
        dayNum = bundle.getInt("dayNum");

    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        mPresenter.setDayNum(dayNum);
        mPresenter.start();
    }

    @Override
    protected void initData() {
        super.initData();
        if(dayNum==33){
            ImageView img = new ImageView(getActivity());
            img.setImageResource(R.drawable.bg_register);
            new AlertDialog.Builder(getActivity())
                    .setTitle("恭喜完成恩爱33关！！！")
                    .setMessage("点击历史记录查看33天历史，我们会为您保存长图的哦~")
                    .setView(img)
                    .setPositiveButton("确定", null)
                    .show();
            Toast.makeText(getActivity(),"长图已保存成功,请查看相册", Toast.LENGTH_SHORT).show();
        }
    }

    @Override

    protected int getContentLayoutId() {
        return R.layout.fragment_finish;
    }

    @Override
    protected UserChallengetContract.Presenter initPresenter() {
        return new UserChallengePresenter(this);
    }

    @Override
    public void onLoadDone(UserChallenge userChallenge) {
        task_text.setText(userChallenge.getGuanka());
        day_txt.setText("第"+dayNum+"关");
        said_text.setText(userChallenge.getDescription());
        Glide.with(this)
                .load(userChallenge.getUrl())
                .asBitmap()
                .centerCrop()
                .into(picture_imageView);
    }

    @Override
    public void updateSucceed() {

    }
}
