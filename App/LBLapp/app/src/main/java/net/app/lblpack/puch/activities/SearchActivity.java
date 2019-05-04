package net.app.lblpack.puch.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import net.app.lblpack.common.app.Fragment;
import net.app.lblpack.common.app.ToolbarActivity;
import net.app.lblpack.puch.R;
import net.app.lblpack.puch.frags.account.SearchUserFragment;

public class SearchActivity extends ToolbarActivity {
    private static final String EXTRA_TYPE = "EXTRA_TYPE";
    public static final int TYPE_USER = 1; // 搜索人

    // 具体需要显示的类型
    private int type;
    private SearchFragment mSearchFragment;

    /**
     * 显示搜索界面
     *
     * @param context 上下文
     * @param type    显示的类型，用户还是群
     */
    public static void show(Context context, int type) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(EXTRA_TYPE, type);
        context.startActivity(intent);
    }


    @Override
    protected boolean initArgs(Bundle bundle) {
        type = bundle.getInt(EXTRA_TYPE);
        // 是搜索人或者搜索群
        return type == TYPE_USER;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        // 显示对应的Fragment
        Fragment fragment;
        SearchUserFragment searchUserFragment = new SearchUserFragment();
        fragment = searchUserFragment;
        mSearchFragment = searchUserFragment;

        getSupportFragmentManager().beginTransaction()
                .add(R.id.lay_container, fragment)
                .commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 初始化菜单
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);

        // 找到搜索菜单
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        if (searchView != null) {
            // 拿到一个搜索管理器
            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

            // 添加搜索监听
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    // 当点击了提交按钮的时候
                    search(query);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    return false;
                }

            });


        }

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 搜索的发起点
     *
     * @param query 搜索的文字
     */
    private void search(String query) {
        if (mSearchFragment == null)
            return;
        mSearchFragment.search(query);
    }

    /**
     * 搜索的Fragment必须继承的接口
     */
    public interface SearchFragment {
        void search(String content);
    }
}

