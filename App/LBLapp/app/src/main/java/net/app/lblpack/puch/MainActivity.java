package net.app.lblpack.puch;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;

import net.app.lblpack.common.app.Activity;
import net.app.lblpack.common.wiget.Portrait;
import net.app.lblpack.puch.activities.AccountActivity;
import net.app.lblpack.puch.frags.main.ActiveFragment;
import net.app.lblpack.puch.frags.main.ContactFragment;
import net.app.lblpack.puch.frags.main.ThirtyThreeFragment;
import net.app.lblpack.puch.helper.NavHelper;
import net.qiujuer.genius.ui.Ui;
import net.qiujuer.genius.ui.widget.FloatActionButton;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends Activity implements BottomNavigationView.OnNavigationItemSelectedListener,
        NavHelper.OnTabChangedListener<Integer> {
    @BindView(R.id.appbar)
    View mLayAppBar;
    @BindView(R.id.im_portrait)
    Portrait mPortrait;
    @BindView(R.id.txt_title)
    TextView mTextView;
    @BindView(R.id.lay_container)
    FrameLayout mFrameLayout;
    @BindView(R.id.navigation)
    BottomNavigationView mNavigation;
    @BindView(R.id.btn_action)
    FloatActionButton mAction;
    private NavHelper<Integer> navHelper;


    @Override
    protected void initData() {
        super.initData();
        Menu menu = mNavigation.getMenu();
        menu.performIdentifierAction(R.id.action_home,0);
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        //添加底部按钮的监听
        navHelper = new NavHelper<>(this, R.id.lay_container,
                getSupportFragmentManager(),this);
        navHelper.add(R.id.action_home,new NavHelper.Tab<>(ActiveFragment.class,R.string.title_home))
                .add(R.id.action_group,new NavHelper.Tab<>(ThirtyThreeFragment.class,R.string.action_group))
                .add(R.id.action_contact,new NavHelper.Tab<>(ContactFragment.class,R.string.title_contact));
        mNavigation.setOnNavigationItemSelectedListener(this);

        //this = MainActivity
        Glide.with(this)
                .load(R.drawable.bg_src_morning)
                .centerCrop().into(new ViewTarget<View, GlideDrawable>(mLayAppBar) {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                //this = new ViewTarget
                this.view.setBackground(resource.getCurrent());
            }
        });
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }
    @OnClick(R.id.im_search)
    void onSearchMenuClick(){

    }
    @OnClick(R.id.btn_action)
    void onActionClick(){
        AccountActivity.show(this);
    }
    boolean isFirst = true;
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        return navHelper.performClickMenu(menuItem.getItemId());
    }

    /**
     * 处理后回调
     * @param newTab
     * @param oldTab
     */
    @Override
    public void onTabChanged(NavHelper.Tab<Integer> newTab, NavHelper.Tab<Integer> oldTab) {
        //从额外字段中取出资源Id
        mTextView.setText(newTab.extra);

        //对浮动按钮进行隐藏与显示的动画
        float transY = 0;
        float rotation = 0;
        if(Objects.equals(newTab.extra,R.string.title_home)){
            transY = Ui.dipToPx(getResources(),76);
        }else{
            if(Objects.equals(newTab.extra,R.string.title_contact)){
                mAction.setImageResource(R.drawable.ic_contact_add);
                rotation = -360;
            }else {
                mAction.setImageResource(R.drawable.ic_group_add);
                rotation = 360;
            }
        }
        // 旋转， Y轴位移，弹性差值器，时间
        mAction.animate()
                .rotation(rotation)
                .translationY(transY)
                .setInterpolator(new AnticipateOvershootInterpolator(1))
                .setDuration(480)
                .start();;


    }
}
