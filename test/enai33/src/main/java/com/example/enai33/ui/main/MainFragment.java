package com.example.enai33.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enai33.Method.AssetsMethod;
import com.example.enai33.Method.FileMethod;
import com.example.enai33.R;
import com.example.enai33.bean.User;

import java.util.Date;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
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
        Button input = view.findViewById(R.id.input);
        TextView dayNum = view.findViewById(R.id.day);
        TextView task = view.findViewById(R.id.task);

        //根据当天日期，更改内容

        getUser().changeDayNum(new Date());
        int day = getUser().getDayNum();
        String[] arr = getResources().getStringArray(R.array.task_array);
        int len = arr.length;
        int random = (int)(Math.random() * (len - 1));
        Toast.makeText(getActivity(), random + " and " + len, Toast.LENGTH_LONG).show();

        task.setText(arr[random]);
        dayNum.setText("第" + day + "关");
        FileMethod.writeLineFile("dailyTask.txt",arr[random]);

        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction()
                        .detach(MainFragment.this)
                        .replace(R.id.fragment, InputFragment.newInstance(u))
                        .commit();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        // TODO: Use the ViewModel
    }

}
