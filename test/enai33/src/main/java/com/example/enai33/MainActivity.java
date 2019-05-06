package com.example.enai33;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enai33.Method.FileMethod;
import com.example.enai33.bean.User;
import com.example.enai33.ui.main.BlackFragment;
import com.example.enai33.ui.main.FinishFragment;
import com.example.enai33.ui.main.HistoryFragment;
import com.example.enai33.ui.main.IndexFragment;
import com.example.enai33.ui.main.MainFragment;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private User user;

    public MainActivity(){
        this.user = new User();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState != null) {
            savedInstanceState.clear();
        }
        final User u = this.user;

        //获取提交的返回信息,跳转到finish界面
        int id = getIntent().getIntExtra("id",0);
        if(id == 1){
            u.setStartflag(true);
            u.setFinishflag(true);
            u.setDayNum(getIntent().getIntExtra("u_dayNum",1));
            u.setStartDate(getIntent().getIntExtra("u_startDay",1));
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment, FinishFragment.newInstance(u))
                    .addToBackStack(null)
                    .commit();
        }

        Button enai = findViewById(R.id.button1);
        Button text = findViewById(R.id.button2);

        enai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //根据当天日期，更改内容
                if(u.isStartflag() && u.isNextDay(new Date())) {
                    u.changeDayNum(new Date());
                    if(u.isFinishflag()){
                        u.setFinishflag(false);
                    } else {
                        Toast.makeText(MainActivity.this,"昨天的任务未完成哦，今天继续加油呀！",Toast.LENGTH_SHORT).show();
                        FileMethod.writeLineFile("info.txt","未完成...");
                    }
                }
                if(u.getDayNum() > 33){
                    u.init();
                    //提示完成33关任务
                    ImageView img = new ImageView(MainActivity.this);
                    img.setImageResource(R.drawable.wancheng);
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("恭喜完成恩爱33关！！！")
                            .setView(img)
                            .setPositiveButton("确定", null)
                            .show();
                }
                if(u.isStartflag()) {
                    //开启33关
                    if(u.isFinishflag()){
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment, FinishFragment.newInstance(u))
                                .commit();
                    }else{
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment, MainFragment.newInstance(u))
                                .commit();
                    }
                } else {
                    //未开启33关

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment, IndexFragment.newInstance(u))
                            .commit();
                }
            }
        });

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment, new HistoryFragment())
                        .commit();
            }
        });
    }


}
