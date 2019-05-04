package net.app.lblpack.puch.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import net.app.lblpack.common.app.Activity;
import net.app.lblpack.common.app.Fragment;
import net.app.lblpack.factory.model.Author;
import net.app.lblpack.factory.model.db.Session;
import net.app.lblpack.puch.R;
import net.app.lblpack.puch.frags.message.ChatUserFragment;

public class MessageActivity extends Activity {
    // 接收者Id，可以是群，也可以是人的Id
    public static final String KEY_RECEIVER_ID = "KEY_RECEIVER_ID";

    private String mReceiverId;

    /**
     * 通过Session发起聊天
     *
     * @param context 上下文
     * @param session Session
     */
    public static void show(Context context, Session session) {
        if (session == null || context == null || TextUtils.isEmpty(session.getId()))
            return;
        Intent intent = new Intent(context, MessageActivity.class);
        intent.putExtra(KEY_RECEIVER_ID, session.getId());
        context.startActivity(intent);
    }

    /**
     * 显示人的聊天界面
     *
     * @param context 上下文
     * @param author  人的信息
     */
    public static void show(Context context, Author author) {
        if (author == null || context == null || TextUtils.isEmpty(author.getId()))
            return;
        Intent intent = new Intent(context, MessageActivity.class);
        intent.putExtra(KEY_RECEIVER_ID, author.getId());
        context.startActivity(intent);
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_message;
    }


    @Override
    protected boolean initArgs(Bundle bundle) {
        mReceiverId = bundle.getString(KEY_RECEIVER_ID);
        return !TextUtils.isEmpty(mReceiverId);
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        setTitle("");
        Fragment fragment;
        fragment = new ChatUserFragment();

        // 从Activity传递参数到Fragment中去
        Bundle bundle = new Bundle();
        bundle.putString(KEY_RECEIVER_ID, mReceiverId);
        fragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.lay_container, fragment)
                .commit();
    }
}
