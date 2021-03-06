package net.app.lblpack.puch.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import net.app.lblpack.common.app.PresenterToolbarActivity;
import net.app.lblpack.common.widget.PortraitView;
import net.app.lblpack.factory.data.DataSource;
import net.app.lblpack.factory.data.helper.LoveHelper;
import net.app.lblpack.factory.model.api.message.LoveModel;
import net.app.lblpack.factory.model.card.LoveCard;
import net.app.lblpack.factory.model.db.User;
import net.app.lblpack.factory.persistence.Account;
import net.app.lblpack.factory.presenter.contact.PersonalContract;
import net.app.lblpack.factory.presenter.contact.PersonalPresenter;
import net.app.lblpack.puch.R;
import net.qiujuer.genius.res.Resource;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonalActivity extends PresenterToolbarActivity<PersonalContract.Presenter>
        implements PersonalContract.View , DataSource.Callback<LoveCard> {
    private static final String BOUND_KEY_ID = "BOUND_KEY_ID";
    private String userId;

    @BindView(R.id.im_header)
    ImageView mHeader;
    @BindView(R.id.im_portrait)
    PortraitView mPortrait;
    @BindView(R.id.txt_name)
    TextView mName;
    @BindView(R.id.txt_desc)
    TextView mDesc;
    @BindView(R.id.txt_follows)
    TextView mFollows;
    @BindView(R.id.txt_following)
    TextView mFollowing;
    @BindView(R.id.btn_say_hello)
    Button mSayHello;
    @BindView(R.id.im_sex)
    ImageView mSex;


    // 关注
    private MenuItem mFollowItem;
    private boolean mIsFollowUser = false;
    private boolean mIsLoveUser = false;
    private boolean mIsHaveLUser = false;
    public static void show(Context context, String userId) {
        Intent intent = new Intent(context, PersonalActivity.class);
        intent.putExtra(BOUND_KEY_ID, userId);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_personal;
    }

    @Override
    protected boolean initArgs(Bundle bundle) {
        userId = bundle.getString(BOUND_KEY_ID);
        return !TextUtils.isEmpty(userId);
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        setTitle("");
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.personal, menu);
        mFollowItem = menu.findItem(R.id.action_follow);
        changeFollowItemStatus();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_follow) {
            if(!mIsHaveLUser&&!mIsLoveUser){
                LoveModel loveModel = new LoveModel(Account.getUserId(),userId,60);
                LoveHelper.create(loveModel,this);
            }else if(mIsHaveLUser){
                Toast.makeText(this,"TA已经有侣伴啦！",Toast.LENGTH_SHORT).show();
            }else {
                if(Account.getUser().getSex()==1){
                    Toast.makeText(this,"你已经有侣伴啦！请不要做渣男！！",Toast.LENGTH_SHORT).show();
                }
                if(Account.getUser().getSex()==2){
                    Toast.makeText(this,"小哥哥随多，请不要贪心哦！",Toast.LENGTH_SHORT).show();
                }

            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_say_hello)
    void onSayHelloClick() {
        // 发起聊天的点击
        User user = mPresenter.getUserPersonal();
        if (user == null)
            return;
        MessageActivity.show(this, user);
    }


    /**
     * 更改关注菜单状态
     */
    private void changeFollowItemStatus() {
        if (mFollowItem == null)
            return;

        // 根据状态设置颜色
        Drawable drawable = mIsLoveUser ? getResources()
                .getDrawable(R.drawable.ic_favorite) :
                getResources().getDrawable(R.drawable.ic_favorite_border);
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, Resource.Color.RED);
        mFollowItem.setIcon(drawable);


    }

    @Override
    public String getUserId() {
        return userId;
    }


    @Override
    public void onLoadDone(User user) {
        if (user == null)
            return;
        boolean isMan = user.getSex()==1 ? true :false;
                Drawable drawable2 = getResources().getDrawable(isMan ?
                R.drawable.ic_sex_man : R.drawable.ic_sex_woman);
        mSex.setImageDrawable(drawable2);
        // 设置背景的层级，切换颜色
        mSex.getBackground().setLevel(isMan ? 0 : 1);
        mPortrait.setup(Glide.with(this), user);
        mName.setText(user.getName());
        mDesc.setText(user.getDesc());
        mFollows.setText(String.format(getString(R.string.label_follows), Integer.toString(user.getFollows())));
        mFollowing.setText(String.format(getString(R.string.label_following), Integer.toString(user.getFollowing())));
        hideLoading();
    }

    @Override
    public void allowSayHello(boolean isAllow) {
        mSayHello.setVisibility(isAllow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setFollowStatus(boolean isFollow) {
        mIsFollowUser = isFollow;
        changeFollowItemStatus();
    }

    @Override
    public void setLoveStatus(boolean isLove) {
        mIsLoveUser = isLove;
    }

    @Override
    public void setHLoveStatus(boolean isLove) {
        mIsHaveLUser = isLove;
    }

    @Override
    protected PersonalContract.Presenter initPresenter() {
        return new PersonalPresenter(this);
    }

    @Override
    public void onDataLoaded(LoveCard loveCard) {
        mIsLoveUser = true;
        mIsHaveLUser = true;
        changeFollowItemStatus();
    }

    @Override
    public void onDataNotAvailable(int strRes) {

    }
}
