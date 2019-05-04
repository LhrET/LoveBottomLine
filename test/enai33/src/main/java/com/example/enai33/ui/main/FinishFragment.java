package com.example.enai33.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.enai33.Method.FileMethod;
import com.example.enai33.R;
import com.example.enai33.bean.User;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class   FinishFragment extends Fragment {
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public static FinishFragment newInstance(User u){
        FinishFragment finish = new FinishFragment();
        finish.setUser(u);
        return finish;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.finish_fragment, container, false);
        TextView saidText = view.findViewById(R.id.said_text);
        TextView dayNum = view.findViewById(R.id.day_text);
        TextView taskText = view.findViewById(R.id.task_text);
        //读取文件中信息并改变内容
        String saidStr = FileMethod.readLastLineFile("info.txt");
        saidText.setText(saidStr);
        dayNum.setText("第" + getUser().getDayNum() + "关");
        String taskStr = FileMethod.readLastLineFile("dailyTask.txt");
        taskText.setText(taskStr);

        return view;
    }
}
