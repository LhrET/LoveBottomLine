package com.example.enai33.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.enai33.Method.FileMethod;
import com.example.enai33.R;
import com.example.enai33.bean.User;

import java.util.Date;

public class IndexFragment extends Fragment {
    private User user;

    public static IndexFragment newInstance(User u) {
        IndexFragment index = new IndexFragment();
        index.setUser(u);
        return index;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.index_fragment, container, false);
        ImageButton start = view.findViewById(R.id.start);
        final User u = getUser();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清空存储文件
                FileMethod.removeFile("info.txt");
                FileMethod.removeFile("dailyTask.txt");
                FileMethod.removeFile("dayNum.txt");
                //改变user属性
                u.setNextDayFlag(true);
                u.setStartflag(true);
                u.setDayNum(1);
                u.setStartDate(new Date());
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.fragment, MainFragment.newInstance(u))
                        .commit();
            }
        });
        return view;
    }
}
