package net.app.lblpack.puch;

import android.support.design.widget.BottomNavigationView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;

import net.app.lblpack.common.app.Activity;
import net.app.lblpack.common.app.Fragment;
import net.app.lblpack.common.wiget.Portrait;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends Activity{
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



    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initWidget() {
        super.initWidget();

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

    }

}
