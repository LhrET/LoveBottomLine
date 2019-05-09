package net.app.lblpack.puch.frags.thirtythree;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import net.app.lblpack.common.app.PresenterFragment;
import net.app.lblpack.factory.data.DataSource;
import net.app.lblpack.factory.data.helper.UserChallengeHelper;
import net.app.lblpack.factory.model.api.message.UpdateChallenge;
import net.app.lblpack.factory.model.card.UserChallengeCard;
import net.app.lblpack.factory.model.db.UserChallenge;
import net.app.lblpack.factory.presenter.message.UserChallengePresenter;
import net.app.lblpack.factory.presenter.message.UserChallengetContract;
import net.app.lblpack.puch.R;
import net.app.lblpack.puch.activities.InputActivity;

import java.util.Date;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

public class StartFragment extends PresenterFragment<UserChallengetContract.Presenter>
        implements UserChallengetContract.View , DataSource.Callback<UserChallengeCard> {
    private int dayNum;
    private String task;
    @BindView(R.id.day)
    TextView txt_day;
    @BindView(R.id.task)
    TextView txt_task;

    @OnClick(R.id.btn_input)
    public void onClickButton(){
        Intent intent = new Intent(getActivity(), InputActivity.class);
        intent.putExtra("dayNum",dayNum);
        startActivity(intent);
        getActivity().finish();
    }
    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_start;
    }

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
    protected UserChallengetContract.Presenter initPresenter() {
        return new UserChallengePresenter(this);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(isVisibleToUser){
            getFragmentManager().beginTransaction().remove(this);
        }
    }

    @Override
    public void onLoadDone(UserChallenge userChallenge) {

        if(userChallenge==null){
            task = getResources()
                    .getStringArray(R.array.task_array)[(new Random(new Date().getTime())).nextInt(53)];
            userChallenge = new UserChallenge();
            userChallenge.setUrl("");
            userChallenge.setDaySum(dayNum);
            userChallenge.setGuanka(task);
            userChallenge.setDescription("未完成");
            UpdateChallenge updateChallenge = new UpdateChallenge(userChallenge);
            UserChallengeHelper.create(updateChallenge,this);
        }else {
            task = userChallenge.getGuanka();
        }
        txt_day.setText("第"+dayNum+"关");
        txt_task.setText(task);
    }

    @Override
    public void updateSucceed() {

    }

    @Override
    public void onDataLoaded(UserChallengeCard userChallenge) {

    }

    @Override
    public void onDataNotAvailable(int strRes) {

    }
}
