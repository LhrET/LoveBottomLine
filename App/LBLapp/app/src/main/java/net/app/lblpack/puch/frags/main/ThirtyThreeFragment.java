package net.app.lblpack.puch.frags.main;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import net.app.lblpack.common.app.Fragment;
import net.app.lblpack.common.app.PresenterFragment;
import net.app.lblpack.factory.data.DataSource;
import net.app.lblpack.factory.data.helper.ChallengeHelp;
import net.app.lblpack.factory.model.api.message.ChallengeModel;
import net.app.lblpack.factory.model.card.ChallengeCard;
import net.app.lblpack.factory.model.db.Challenge;
import net.app.lblpack.factory.persistence.Account;
import net.app.lblpack.factory.presenter.message.ChallengeContract;
import net.app.lblpack.factory.presenter.message.ChallengePresenter;
import net.app.lblpack.puch.R;
import net.app.lblpack.puch.frags.thirtythree.FinishFragment;
import net.app.lblpack.puch.frags.thirtythree.StartFragment;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThirtyThreeFragment extends PresenterFragment<ChallengeContract.Presenter>
        implements ChallengeContract.View  , DataSource.Callback<ChallengeCard> {
    private Challenge challenge;
    @BindView(R.id.start)
    ImageButton start_image;
    @OnClick(R.id.start)
    public void onClickStart(){
        if(Account.isLove()){
            challenge.setOriginId(Account.getUserId());
            challenge.setStartFlag(true);
            challenge.setFinishFlag(false);
            challenge.setDayNum(1);
            challenge.setCreateAt(new Date());

            ChallengeModel challengeModel = new ChallengeModel(challenge.getOriginId(),
                    null,challenge.isStartFlag(),challenge.isFinishFlag(),
                    challenge.getCreateAt(),challenge.getDayNum());
            ChallengeHelp.create(challengeModel,this);
            Bundle bundle = new Bundle();
            bundle.putInt("dayNum",challenge.getDayNum());
            StartFragment startFragment = new StartFragment();
            startFragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.lay_container,startFragment)
            .commit();
        }else {
            Toast.makeText(getActivity(),"请先绑定情侣！",Toast.LENGTH_SHORT).show();
        }



    }
    public ThirtyThreeFragment() {
        // Required empty public constructor
    }



    @Override
    protected void onFirstInit() {
        super.onFirstInit();
        mPresenter.start();
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_thirty_three;
    }

    @Override
    protected ChallengeContract.Presenter initPresenter() {
        return new ChallengePresenter(this);
    }

    @Override
    public void onLoadDone(Challenge challenge) {
        if(challenge ==null||challenge.getDayNum()>33){
            challenge = new Challenge();
            challenge.setStartFlag(false);
            challenge.setFinishFlag(false);
            challenge.setDayNum(1);
            challenge.setCreateAt(new Date());
        }
        int isNext = isNetxDay(challenge.getCreateAt());
        if(challenge.isStartFlag()&&isNext>1){
            challenge.setDayNum(isNext);
            if(challenge.isFinishFlag()){
                challenge.setFinishFlag(false);
            }else {
                Toast.makeText(getActivity(),"昨天任务未完成！",Toast.LENGTH_SHORT).show();
            }
        }
        if(challenge.isStartFlag()){
            Bundle bundle = new Bundle();
            bundle.putInt("dayNum",challenge.getDayNum());

            if(challenge.isFinishFlag()){
                FinishFragment finishFragment = new FinishFragment();
                finishFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.lay_container, finishFragment)
                .commit();
            }else {
                StartFragment startFragment = new StartFragment();
                startFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.lay_container, startFragment)
                .commit();
            }
        }else {

            this.challenge = challenge;

        }
    }


    public int isNetxDay(Date date){
        int start = (int) (date.getTime()/(1000*3600*24));
        int now = (int) ((new Date()).getTime()/(1000*3600*24));
        return now - start+1;
    }


    @Override
    public void onDataNotAvailable(int strRes) {

    }

    @Override
    public void onDataLoaded(ChallengeCard challengeCard) {

    }
}
