package com.example.enai33.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.enai33.Method.FileMethod;
import com.example.enai33.R;
import com.example.enai33.bean.User;

public class MainFragment extends Fragment {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static MainFragment newInstance(User u){
        MainFragment fm = new MainFragment();
        fm.setUser(u);
        return fm;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final User u = this.getUser();
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        TextView dayNum = view.findViewById(R.id.day);
        TextView task = view.findViewById(R.id.task);
        Button input = view.findViewById(R.id.input);

        String dailyTask;
        int day = u.getDayNum();

        if(u.isNextDayFlag()) {
            //如果是第一天或下一天，随机选取任务
            String[] arr0 = getResources().getStringArray(R.array.task_array_0);
            String[] arr1 = getResources().getStringArray(R.array.task_array_1);
            String[] arr2 = getResources().getStringArray(R.array.task_array);
            int len0 = arr0.length;
            int len1 = arr1.length;
            int len2 = arr2.length;
            int random0 = (int)(Math.random() * (len0 - 1));
            int random1 = (int)(Math.random() * (len1 - 1));
            int random2 = (int)(Math.random() * (len2 - 1));

            if(day % 2 == 0){
                dailyTask = arr2[random2];
            } else {
                if(u.getSex() == 0){
                    dailyTask = arr0[random0];
                } else {
                    dailyTask = arr1[random1];
                }
            }

            FileMethod.writeLineFile("dailyTask.txt",dailyTask);
            FileMethod.writeLineFile("dayNum.txt","第" + day + "关");
            u.setNextDayFlag(false);
        } else{
            dailyTask = FileMethod.readLastLineFile("dailyTask.txt");
        }

        dayNum.setText("第" + day + "关");
        task.setText(dailyTask);
        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(),InputActivity.class);
                intent.putExtra("u_dayNum",u.getDayNum());
                intent.putExtra("u_startDay",u.getStartDate());
                startActivity(intent);
            }
        });
        return view;
    }


}
