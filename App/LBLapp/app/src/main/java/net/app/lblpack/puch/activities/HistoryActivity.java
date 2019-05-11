package net.app.lblpack.puch.activities;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;

import net.app.lblpack.common.app.Activity;
import net.app.lblpack.puch.R;
import net.app.lblpack.puch.frags.thirtythree.HistoryFragment;

import butterknife.BindView;

public class HistoryActivity extends Activity {

    @BindView(R.id.appbar)
    View mLayAppbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected void initWidget() {
        super.initWidget();
    }

    @Override
    protected void initData() {
        super.initData();
        HistoryFragment historyFragment = new HistoryFragment();
        Glide.with(this)
                .load(R.drawable.default_banner_head)
                .centerCrop()
                .into(new ViewTarget<View, GlideDrawable>(mLayAppbar) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        this.view.setBackground(resource.getCurrent());
                    }
                });
        getSupportFragmentManager().beginTransaction()
                .add(R.id.lay_container, historyFragment)
                .commit();



    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_history;
    }

}
