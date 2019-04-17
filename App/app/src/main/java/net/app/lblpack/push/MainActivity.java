package net.app.lblpack.push;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import net.app.lblpack.common.Common;
import net.app.lblpack.common.app.Activity;

import butterknife.BindView;

public class MainActivity extends Activity {
    @BindView(R.id.txt_test)
    TextView mTestView;
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mTestView.setText("test hello.");
    }
}
