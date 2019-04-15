package net.app.lblpack.common.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class Fragment extends android.support.v4.app.Fragment {
    protected View mRoot;
    protected Unbinder mRootUnBinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //初始化参数
        initArgs(getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mRoot == null) {
            int LayId = getContentLayoutId();
            // 初始化当前的跟布局，但是不在创建时就添加到container里
            View view = inflater.inflate(LayId, container, false);
            initWidget(view);
            mRoot = view;
        }else {
            if(mRoot.getParent()!=null){
                //不为空，就移除当前控件
                ((ViewGroup)mRoot.getParent()).removeView(mRoot);
            }
        }


        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //当View创建完成之后初始化数据
        initData();
    }

    protected abstract int getContentLayoutId();


    protected void initWidget(View view){
        mRootUnBinder = ButterKnife.bind(this,view);
    }
    protected void initData(){

    }
    /**
     * 初始化相关参数
     * @param bundle
     * @return
     */
    protected void initArgs(Bundle bundle){

    }

    /**
     * 返回按键触发时调用
     * @return 返回TRUE代表已处理，Activity不用自己Finish
     */
    public boolean onBackPressed(){
        return false;
    }
}
