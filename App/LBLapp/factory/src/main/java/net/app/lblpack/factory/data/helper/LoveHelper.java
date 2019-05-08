package net.app.lblpack.factory.data.helper;

import android.util.Log;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import net.app.lblpack.factory.Factory;
import net.app.lblpack.factory.R;
import net.app.lblpack.factory.data.DataSource;
import net.app.lblpack.factory.model.api.RspModel;
import net.app.lblpack.factory.model.api.message.LoveModel;
import net.app.lblpack.factory.model.card.LoveCard;
import net.app.lblpack.factory.model.db.Love;
import net.app.lblpack.factory.model.db.Love_Table;
import net.app.lblpack.factory.net.Network;
import net.app.lblpack.factory.net.RemoteService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoveHelper {
    public static void update(LoveModel model, final DataSource.Callback<LoveCard> callback) {
        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.remote();
        // 得到一个Call
        Log.e("TAG",model.toString());
        Call<RspModel<LoveCard>> call = service.loveupdate(model);
        // 网络请求
        call.enqueue(new Callback<RspModel<LoveCard>>() {
            @Override
            public void onResponse(Call<RspModel<LoveCard>> call, Response<RspModel<LoveCard>> response) {
                RspModel<LoveCard> rspModel = response.body();
                Log.e("success",rspModel.toString());
                if (rspModel.success()) {
                    LoveCard userCard = rspModel.getResult();
                    // 唤起进行保存的操作
                    Factory.getLoveCenter().dispatch(userCard);
                    // 返回成功
                    callback.onDataLoaded(userCard);
                } else {
                    // 错误情况下进行错误分配
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<LoveCard>> call, Throwable t) {
                callback.onDataNotAvailable(R.string.data_network_error);
            }
        });
    }

    // 搜索的方法
    public static Call search(final DataSource.Callback<LoveCard> callback) {
        RemoteService service = Network.remote();
        Call<RspModel<LoveCard>> call = service.getlove();

        call.enqueue(new Callback<RspModel<LoveCard>>() {
            @Override
            public void onResponse(Call<RspModel<LoveCard>> call, Response<RspModel<LoveCard>> response) {
                RspModel<LoveCard> rspModel = response.body();
                if (rspModel.success()) {
                    // 返回数据
                    callback.onDataLoaded(rspModel.getResult());
                } else {
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<LoveCard>> call, Throwable t) {
                callback.onDataNotAvailable(R.string.data_network_error);
            }
        });

        // 把当前的调度者返回
        return call;
    }
    /**
     * 注册的接口，异步的调用
     *
     * @param model    传递一个注册的Model进来
     * @param callback 成功与失败的接口回送
     */
    public static void create(final LoveModel model, final DataSource.Callback<LoveCard> callback) {
        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.remote();
        // 得到一个Call
        Call<RspModel<LoveCard>> call = service.lovecreat(model);
        // 得到一个Call
        Log.e("TAG", model.toString());
        // 网络请求
        call.enqueue(new Callback<RspModel<LoveCard>>() {
            @Override
            public void onResponse(Call<RspModel<LoveCard>> call, Response<RspModel<LoveCard>> response) {
                RspModel<LoveCard> rspModel = response.body();
                Log.e("success", rspModel.toString());
                if (rspModel.success()) {
                    LoveCard loveCard = rspModel.getResult();
                    // 唤起进行保存的操作
                    Factory.getLoveCenter().dispatch(loveCard);
                    // 返回成功
                    callback.onDataLoaded(loveCard);
                } else {
                    // 错误情况下进行错误分配
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<LoveCard>> call, Throwable t) {
                callback.onDataNotAvailable(R.string.data_network_error);
            }
        });
    }
    public static Love searchFirstOfNet(String id) {
        Love love = findFromNet();
        if (love == null) {
            return findFromLocal(id);
        }
        return love;
    }

    // 从本地查询一个用户的信息
    public static Love findFromLocal(String id) {
        return SQLite.select()
                .from(Love.class)
                .where(Love_Table.originId.eq(id))
                .querySingle();
    }

    // 从网络查询某用户的信息
    public static Love findFromNet() {
        RemoteService remoteService = Network.remote();
        try {
            Response<RspModel<LoveCard>> response = remoteService.getlove().execute();
            LoveCard card = response.body().getResult();
            if (card != null) {
                Love love = card.build();
                // 数据库的存储并通知
                Factory.getLoveCenter().dispatch(card);
                return love;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
