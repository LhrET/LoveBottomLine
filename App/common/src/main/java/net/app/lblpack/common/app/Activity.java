package net.app.lblpack.common.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import butterknife.ButterKnife;

public abstract class Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化窗口
        initWidows();
        if(initArgs(getIntent().getExtras())){
            getContentLayoutId();
            initWidget();;
            initData();
        }else{
            finish();
        }



    }
    protected  void initWidows(){

    }

    /**
     * 初始化相关参数
     * @param bundle
     * @return
     */
    protected boolean initArgs(Bundle bundle){

        return true;
    }
    /**
     * 当前资源文件Id
     * @return
     */
    protected abstract int getContentLayoutId();

    /**
     * 初始化控件
     */
    protected void initWidget(){
        ButterKnife.bind(this);
    }
    /*
    初始化数据
     */
    protected void initData(){

    }

    @Override
    public boolean onSupportNavigateUp() {
        //当点击界面导航返回时，Finish当前界面
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {

        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        //判断是否拦截
        if(fragmentList!=null&&fragmentList.size()>0){
            for (Fragment fragment :fragmentList){
                if(fragment instanceof net.app.lblpack.common.app.Fragment){
                    if(((net.app.lblpack.common.app.Fragment) fragment).onBackPressed()){
                        return;
                    }
                }
            }
        }
        super.onBackPressed();
        finish();
    }


}
