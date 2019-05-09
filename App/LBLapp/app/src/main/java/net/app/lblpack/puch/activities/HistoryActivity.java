package net.app.lblpack.puch.activities;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import net.app.lblpack.common.app.PresenterToolbarActivity;
import net.app.lblpack.factory.data.helper.UserChallengeHelper;
import net.app.lblpack.factory.model.db.UserChallenge;
import net.app.lblpack.factory.presenter.message.ChallenegListContract;
import net.app.lblpack.factory.presenter.message.ChallengeListPresenter;
import net.app.lblpack.puch.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class HistoryActivity extends PresenterToolbarActivity<ChallenegListContract.Presenter>
    implements ChallenegListContract.View{
    @BindView(R.id.listview_history)
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private List<UserChallenge> list;
    @Override
    protected void initWidget() {
        super.initWidget();
        mPresenter.start();
    }

    @Override
    protected void initData() {
        super.initData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> map = (Map<String, Object>) parent.getItemAtPosition(position);
                Toast.makeText(HistoryActivity.this,map.get("item_task").toString(),Toast.LENGTH_SHORT).show();

                AlertDialog.Builder alert = new AlertDialog.Builder(HistoryActivity.this);
                View viewItem = View.inflate(HistoryActivity.this,R.layout.fragment_finish,null);
                TextView day = viewItem.findViewById(R.id.day_text);
                TextView task = viewItem.findViewById(R.id.task_text);
                ImageView pic = viewItem.findViewById(R.id.picture_imageView);
                TextView said = viewItem.findViewById(R.id.said_text);
                FrameLayout layout = viewItem.findViewById(R.id.finish_layout);
                layout.setBackgroundResource(R.drawable.personal_bg);
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

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_history;
    }

    @Override
    protected ChallenegListContract.Presenter initPresenter() {
        return new ChallengeListPresenter(this);
    }

    @Override
    public void getSuccessed() {
        this.list = UserChallengeHelper.getContact();
        List<Map<String,Object>> listItem = new ArrayList<>();
        for(UserChallenge userChallenge : list){
            Map<String, Object> map = new HashMap<>();
            map.put("item_day","第"+userChallenge.getDaySum()+"关");
            map.put("item_task",userChallenge.getGuanka());
            ImageView imageView = new ImageView(getBaseContext());
            Glide.with(this)
                    .load(userChallenge.getUrl())
                    .asBitmap()
                    .centerCrop()
                    .into(imageView);

            map.put("item_image",imageView);
            map.put("item_said",userChallenge.getDescription());
            listItem.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,listItem,R.layout.history_item,
                new String[]{"item_day","item_task","item_image","item_said"},new int[]{R.id.item_day,R.id.item_task,R.id.item_image,R.id.item_said});

        listView.setAdapter(adapter);
    }
}
