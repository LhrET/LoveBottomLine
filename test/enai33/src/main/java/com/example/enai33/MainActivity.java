package com.example.enai33;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.enai33.ui.main.MainFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private String yesterday = "01";
    private int dayNum = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
        changeDay();
    }

    public void changeDay() {
        TextView tv = findViewById(R.id.day);
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String d = sdf.format(new Date());
        if(yesterday != d){
            this.dayNum += 1;
            yesterday = d;
            tv.setText("第" + this.dayNum + "天");
        }
    }
}
