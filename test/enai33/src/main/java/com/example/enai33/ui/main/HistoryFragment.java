package com.example.enai33.ui.main;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enai33.Method.FileMethod;
import com.example.enai33.Method.ImageMethod;
import com.example.enai33.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.history_fragment, container, false);
        final int imageid = R.drawable.wancheng;
        String[] taskId = FileMethod.readAllFile("dailyTask.txt");
        String[] saidId = FileMethod.readAllFile("info.txt");
        String[] dayId = FileMethod.readAllFile("dayNum.txt");
        List<Map<String,Object>> listItem = new ArrayList<>();
        for(int i = 0; i < saidId.length; i++){
            Map<String, Object> map = new HashMap<>();
            map.put("item_day",dayId[i]);
            map.put("item_task",taskId[i]);
            map.put("item_image",imageid);
            map.put("item_said",saidId[i]);
            listItem.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(getActivity(),listItem,R.layout.history_item,
                new String[]{"item_day","item_task","item_image","item_said"},new int[]{R.id.item_day,R.id.item_task,R.id.item_image,R.id.item_said});
        final ListView listView = view.findViewById(R.id.history_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> map = (Map<String, Object>) parent.getItemAtPosition(position);
                Toast.makeText(getActivity(),map.get("item_task").toString(),Toast.LENGTH_SHORT).show();

                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                View viewItem = View.inflate(getActivity(),R.layout.finish_fragment,null);
                TextView day = viewItem.findViewById(R.id.day_text);
                TextView task = viewItem.findViewById(R.id.task_text);
                ImageView pic = viewItem.findViewById(R.id.picture_imageView);
                TextView said = viewItem.findViewById(R.id.said_text);
                FrameLayout layout = viewItem.findViewById(R.id.finish_layout);
                layout.setBackgroundResource(R.mipmap.beijing7);
                day.setText(map.get("item_day").toString());
                task.setText(map.get("item_task").toString());
                pic.setImageResource(Integer.parseInt(map.get("item_image").toString()));
                said.setText(map.get("item_said").toString());
                alert
                        .setTitle("回顾")
                        .setPositiveButton("返回",null)
                        .setView(viewItem)
                        .show();

            }
        });

        return view;
    }
}
