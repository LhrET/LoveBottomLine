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
import android.widget.ImageButton;

import com.example.enai33.R;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private boolean flage = true; //是否开启33关
    private boolean flage_finish = false;  //当天任务是否完成

    public boolean isFlage() {
        return flage;
    }

    public void setFlage(boolean flage) {
        this.flage = flage;
    }

    public boolean isFlage_finish() {
        return flage_finish;
    }

    public void setFlage_finish(boolean flage_finish) {
        this.flage_finish = flage_finish;
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view;
        if(isFlage()) {
            //开启恩爱33关
            if(isFlage_finish()) {
                //已完成当天任务
                view = inflater.inflate(R.layout.finish_fragment, container, false);
            }else {
                //未完成当天任务
                view = inflater.inflate(R.layout.main_fragment, container, false);
                Button input = view.findViewById(R.id.input);
                input.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.beginTransaction()
                                .replace(R.id.fragment, new InputFragment())
                                .commit();
                    }
                });
            }

        } else {
            // 未开启
            view = inflater.inflate(R.layout.index, container, false);
            ImageButton start = view.findViewById(R.id.start);
            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setFlage(true);
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.beginTransaction()
                            .replace(R.id.fragment, new MainFragment())
                            .commit();
                }
            });
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        // TODO: Use the ViewModel
    }



}
