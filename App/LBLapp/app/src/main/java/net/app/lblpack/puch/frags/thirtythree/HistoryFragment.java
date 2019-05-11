package net.app.lblpack.puch.frags.thirtythree;


import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import net.app.lblpack.common.app.Fragment;
import net.app.lblpack.common.app.PresenterFragment;
import net.app.lblpack.common.tools.MyAdapter;
import net.app.lblpack.factory.data.helper.UserChallengeHelper;
import net.app.lblpack.factory.model.db.UserChallenge;
import net.app.lblpack.factory.presenter.message.ChallenegListContract;
import net.app.lblpack.factory.presenter.message.ChallengeListPresenter;
import net.app.lblpack.puch.R;
import net.app.lblpack.utils.FileMethod;
import net.app.lblpack.utils.ImageMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends PresenterFragment<ChallenegListContract.Presenter>
    implements ChallenegListContract.View{
    @BindView(R.id.listview_history)
    ListView listView;

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    protected void onFirstInit() {
        super.onFirstInit();
        mPresenter.start();
    }

    @Override
    protected void initData() {
        super.initData();
        List<UserChallenge> userChallenges = UserChallengeHelper.getContact();
        List<Map<String,Object>> listItem = new ArrayList<>();
        for(UserChallenge userChallenge : userChallenges){

            Map<String, Object> map = new HashMap<>();
                map.put("item_day","第"+userChallenge.getDaySum()+"天");
                map.put("item_task",userChallenge.getGuanka());
                //map.put("item_image",R.drawable.chuangguan_tu);
                map.put("item_image",userChallenge.getUrl());
                map.put("item_said",userChallenge.getDescription());
                listItem.add(map);
        }

        MyAdapter adapter = new MyAdapter(getActivity(),listItem,R.layout.history_item,
                new String[]{"item_day","item_task","item_image","item_said"},new int[]{R.id.item_day,R.id.item_task,R.id.item_image,R.id.item_said});
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> map = (Map<String, Object>) parent.getItemAtPosition(position);
                Toast.makeText(getActivity(),map.get("item_task").toString(),Toast.LENGTH_SHORT).show();

                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                View viewItem = View.inflate(getActivity(),R.layout.fragment_finish,null);
                TextView day = viewItem.findViewById(R.id.day_text);
                TextView task = viewItem.findViewById(R.id.task_text);
                ImageView pic = viewItem.findViewById(R.id.picture_imageView);
                TextView said = viewItem.findViewById(R.id.said_text);
                FrameLayout layout = viewItem.findViewById(R.id.finish_layout);
                layout.setBackgroundResource(R.drawable.personal_bg);
                day.setText(map.get("item_day").toString());
                task.setText(map.get("item_task").toString());

                Glide.with(getActivity())
                        .load(map.get("item_image").toString())
                        .dontAnimate() // CircleImageView 控件中不能使用渐变动画，会导致显示延迟
                        .into(pic);

                said.setText(map.get("item_said").toString());
                alert
                        .setTitle("回顾")
                        .setPositiveButton("返回",null)
                        .setView(viewItem)
                        .show();

            }
        });
        if(listItem.size()>=33){
            ImageView img = new ImageView(getActivity());
            img.setImageResource(R.drawable.bg_register);
            new AlertDialog.Builder(getActivity())
                    .setTitle("恭喜完成恩爱33关！！！")
                    .setMessage("点击确定，我们会为您保存长图的哦~")
                    .setView(img)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Bitmap bitmap = ImageMethod.shotListView(listView);
                            FileMethod.writeBitmap(bitmap);

                        }
                    })
                    .show();
        }

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_history2;
    }

    @Override
    protected ChallenegListContract.Presenter initPresenter() {
        return new ChallengeListPresenter(this);
    }

    @Override
    public void getSuccessed() {

    }
}
