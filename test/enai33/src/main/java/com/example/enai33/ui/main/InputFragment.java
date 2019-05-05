package com.example.enai33.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.enai33.Method.FileMethod;
import com.example.enai33.R;
import com.example.enai33.bean.User;


public class InputFragment extends Fragment {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static InputFragment newInstance(User u){
        InputFragment input = new InputFragment();
        input.setUser(u);
        return input;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.input_activity, container, false);
        ImageButton ib = view.findViewById(R.id.pic);
        Button input = view.findViewById(R.id.finish);
        final EditText etext = view.findViewById(R.id.text);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加图片
                Toast.makeText(getActivity(), "添加图片", Toast.LENGTH_SHORT).show();
            }
        });
        final User u = this.getUser();
        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //上传并存储文字和图片...
                String text = etext.getText().toString();
                if(text.equals("")){
                    Toast.makeText(getActivity(), "请说点什么吧！", Toast.LENGTH_SHORT).show();
                    return;
                }
                FileMethod.writeLineFile("info.txt", text);
                u.setFinishflag(true);
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction()
                        .detach(InputFragment.this)
                        .replace(R.id.fragment,FinishFragment.newInstance(u))
                        .commit();
            }
        });
        return view;
    }


}
