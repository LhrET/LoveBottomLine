package com.example.enai33.ui.main;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enai33.MainActivity;
import com.example.enai33.Method.FileMethod;
import com.example.enai33.Method.ImageMethod;
import com.example.enai33.R;
import com.example.enai33.bean.User;

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

        if(user.getDayNum() == 33){
            //提示完成33关任务
            ImageView img = new ImageView(getActivity());
            img.setImageResource(R.drawable.wancheng);
            new AlertDialog.Builder(getActivity())
                    .setTitle("恭喜完成恩爱33关！！！")
                    .setMessage("点击历史记录查看33天历史，我们会为您保存长图的哦~")
                    .setView(img)
                    .setPositiveButton("确定", null)
                    .show();
            View v = inflater.inflate(R.layout.history_fragment, container,false);
            ListView listView = v.findViewById(R.id.history_list);
            Bitmap bitmap = ImageMethod.shotListView(listView);
            FileMethod.writeBitmap(bitmap);
            Toast.makeText(getActivity(),"长图保存成功",Toast.LENGTH_SHORT).show();

        }
        return view;
    }
}
