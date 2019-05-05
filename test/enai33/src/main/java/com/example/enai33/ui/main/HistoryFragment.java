package com.example.enai33.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.enai33.MainActivity;
import com.example.enai33.Method.FileMethod;
import com.example.enai33.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_fragment, container, false);
        int imageid = R.drawable.wancheng;
        String[] taskId = FileMethod.readAllFile("dailyTask.txt");
        String[] saidId = FileMethod.readAllFile("info.txt");
        List<Map<String,Object>> listItem = new ArrayList<>();
        for(int i = 0; i < saidId.length; i++){
            Map<String, Object> map = new HashMap<>();
            map.put("item_day","第" + (i+1) + "天");
            map.put("item_task",taskId[i]);
            map.put("item_image",imageid);
            map.put("item_said",saidId[i]);
            listItem.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(getActivity(),listItem,R.layout.history_item,
                new String[]{"item_day","item_task","item_image","item_said"},new int[]{R.id.item_day,R.id.item_task,R.id.item_image,R.id.item_said});
        ListView listView = view.findViewById(R.id.history_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> map = (Map<String, Object>) parent.getItemAtPosition(position);
                Toast.makeText(getActivity(),map.get("item_task").toString(),Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
