package net.app.lblpack.puch.activities;

import android.content.Context;
import android.content.Intent;

import net.app.lblpack.common.app.Activity;
import net.app.lblpack.common.app.Fragment;
import net.app.lblpack.puch.R;
import net.app.lblpack.puch.frags.account.UpdateInfoFragment;

public class AccountActivity extends Activity {

    private Fragment mCurFragment;

    public static void show(Context context){
        context.startActivity(new Intent(context,AccountActivity.class));
    }
    @Override
    protected void initWidget() {
        super.initWidget();

        mCurFragment = new UpdateInfoFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.lay_container,new UpdateInfoFragment())
                .commit();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_account;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCurFragment.onActivityResult(requestCode,resultCode,data);

    }
}
