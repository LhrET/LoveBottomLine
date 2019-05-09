package net.app.lblpack.puch.frags.main;


import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.app.lblpack.common.app.Fragment;
import net.app.lblpack.common.app.PresenterFragment;
import net.app.lblpack.common.widget.PortraitView;
import net.app.lblpack.factory.data.helper.UserHelper;
import net.app.lblpack.factory.model.db.Love;
import net.app.lblpack.factory.model.db.User;
import net.app.lblpack.factory.persistence.Account;
import net.app.lblpack.factory.presenter.message.LoveContract;
import net.app.lblpack.factory.presenter.message.LovePresenter;
import net.app.lblpack.puch.R;
import net.app.lblpack.puch.activities.PersonalActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActiveFragment extends PresenterFragment<LoveContract.Presenter>
        implements LoveContract.View  {
    @BindView(R.id.im_portrait1)
    PortraitView portraitView1;
    @BindView(R.id.im_portrait2)
    PortraitView portraitView2;
    @BindView(R.id.txt_fenshu)
    TextView textView_fenshu;
    @BindView(R.id.txt_active_hidden)
    TextView textView_hidden;

    private Love love;
    public ActiveFragment() {
        // Required empty public constructor
    }

    @OnClick(R.id.im_portrait1)
    public void onClickProtrait1(){
        PersonalActivity.show(getContext(), Account.getUserId());
    }
    @OnClick(R.id.im_portrait2)
    public void onClickProtrait2(){
        PersonalActivity.show(getContext(), love.getTargetId());
    }


    @Override
    protected void onFirstInit() {
        super.onFirstInit();
        mPresenter.start();
    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            mPresenter.start();
        }
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_active;
    }

    @Override
    protected LoveContract.Presenter initPresenter() {
        return new LovePresenter(this);
    }

    @Override
    public void onLoadDone(Love love) {
        if(love == null){
            textView_hidden.setVisibility(View.VISIBLE);
        }else {
            this.love = love;
            User user = UserHelper.findFromLocal(love.getTargetId());
            portraitView2.setup(Glide.with(this), user);
            portraitView1.setup(Glide.with(this), Account.getUser());
            textView_fenshu.setText(Integer.toString(love.getLove_num()));
        }

    }
}
