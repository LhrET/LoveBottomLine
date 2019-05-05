package com.example.enai33.ui.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.enai33.MainActivity;
import com.example.enai33.Method.FileMethod;
import com.example.enai33.R;

public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_activity);

        ImageButton ib = findViewById(R.id.pic);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加图片
                Toast.makeText(InputActivity.this, "添加图片", Toast.LENGTH_SHORT).show();
            }
        });

        Button input = findViewById(R.id.finish);
        final EditText etext = findViewById(R.id.text);
        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //上传并存储文字和图片...
                String text = etext.getText().toString();
                if(text.equals("")){
                    Toast.makeText(InputActivity.this, "请说点什么吧！", Toast.LENGTH_SHORT).show();
                    return;
                }
                FileMethod.writeLineFile("info.txt", text);
                Intent intent = new Intent(InputActivity.this, MainActivity.class);
                intent.putExtra("id",1);
                intent.putExtra("u_dayNum",getIntent().getIntExtra("u_dayNum",1));
                intent.putExtra("u_startDay",getIntent().getIntExtra("u_startDay",-1));
                startActivity(intent);

            }
        });
    }
}
