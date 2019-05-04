package com.example.enai33;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enai33.bean.User;
import com.example.enai33.ui.main.FinishFragment;
import com.example.enai33.ui.main.IndexFragment;
import com.example.enai33.ui.main.MainFragment;

import java.io.BufferedReader;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean startFlag = user.isStartflag();
        boolean finishFlag = user.isFinishflag();

        setContentView(R.layout.main_activity);

       if (savedInstanceState == null) {
           if(startFlag) {
               if(finishFlag){
                   Toast.makeText(this, "finish", Toast.LENGTH_LONG).show();
                   getSupportFragmentManager().beginTransaction()
                           .replace(R.id.fragment, FinishFragment.newInstance(this.getUser()))
                           .commit();
               }else{
                   Toast.makeText(this, "main", Toast.LENGTH_SHORT).show();
                   getSupportFragmentManager().beginTransaction()
                           .replace(R.id.fragment, MainFragment.newInstance(this.getUser()))
                           .commit();
               }
           } else {
               Toast.makeText(this, "index", Toast.LENGTH_LONG).show();
               getSupportFragmentManager().beginTransaction()
                       .replace(R.id.fragment, IndexFragment.newInstance(this.getUser()))
                       .commit();
           }
        }

    }


}
