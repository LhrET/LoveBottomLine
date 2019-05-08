package net.app.lblpack.common.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.bumptech.glide.RequestManager;

import net.app.lblpack.common.R;

public class InputImageView extends android.support.v7.widget.AppCompatImageView {

    public InputImageView(Context context) {
        super(context);
    }

    public InputImageView(Context context,AttributeSet attrs) {
        super(context, attrs);
    }

    public InputImageView(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setup(RequestManager manager, String url) {
        setup(manager, R.drawable.default_portrait, url);
    }


    public void setup(RequestManager manager, int resourceId, String url) {
        if (url == null)
            url = "";
        manager.load(url)
                .placeholder(resourceId)
                .centerCrop()
                .dontAnimate() // CircleImageView 控件中不能使用渐变动画，会导致显示延迟
                .into(this);

    }
}
