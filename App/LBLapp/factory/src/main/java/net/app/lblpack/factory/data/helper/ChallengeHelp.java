package net.app.lblpack.factory.data.helper;

import android.util.Log;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import net.app.lblpack.factory.Factory;
import net.app.lblpack.factory.R;
import net.app.lblpack.factory.data.DataSource;
import net.app.lblpack.factory.model.api.RspModel;
import net.app.lblpack.factory.model.api.message.ChallengeModel;
import net.app.lblpack.factory.model.card.ChallengeCard;
import net.app.lblpack.factory.model.db.Challenge;
import net.app.lblpack.factory.model.db.Challenge_Table;
import net.app.lblpack.factory.net.Network;
import net.app.lblpack.factory.net.RemoteService;
import net.app.lblpack.factory.persistence.Account;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChallengeHelp {
    public static void update(ChallengeModel model, final DataSource.Callback<ChallengeCard> callback) {
        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.remote();
        // 得到一个Call
        Log.e("TAG",model.toString());
        Call<RspModel<ChallengeCard>> call = service.challengeupdate(model);
        // 网络请求
        call.enqueue(new Callback<RspModel<ChallengeCard>>() {
            @Override
            public void onResponse(Call<RspModel<ChallengeCard>> call, Response<RspModel<ChallengeCard>> response) {
                RspModel<ChallengeCard> rspModel = response.body();
                Log.e("success",rspModel.toString());
                if (rspModel.success()) {
                    ChallengeCard userCard = rspModel.getResult();
                    // 唤起进行保存的操作
                    Factory.getChallengeCenter().dispatch(userCard);
                    // 返回成功
                } else {
                    // 错误情况下进行错误分配
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<ChallengeCard>> call, Throwable t) {
                callback.onDataNotAvailable(R.string.data_network_error);
            }
        });
    }

    // 搜索的方法
    public static Call search(final DataSource.Callback<ChallengeCard> callback) {
        RemoteService service = Network.remote();
        Call<RspModel<ChallengeCard>> call = service.getChallenge();

        call.enqueue(new Callback<RspModel<ChallengeCard>>() {
            @Override
            public void onResponse(Call<RspModel<ChallengeCard>> call, Response<RspModel<ChallengeCard>> response) {
                RspModel<ChallengeCard> rspModel = response.body();
                if (rspModel.success()) {
                    // 返回数据
                    callback.onDataLoaded(rspModel.getResult());
                } else {
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<ChallengeCard>> call, Throwable t) {
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
    public static void create(final ChallengeModel model, final DataSource.Callback<ChallengeCard> callback) {
        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.remote();
        // 得到一个Call
        Call<RspModel<ChallengeCard>> call = service.challengecreat(model);
        // 得到一个Call
        Log.e("TAG", model.toString());
        // 网络请求
        call.enqueue(new Callback<RspModel<ChallengeCard>>() {
            @Override
            public void onResponse(Call<RspModel<ChallengeCard>> call, Response<RspModel<ChallengeCard>> response) {
                RspModel<ChallengeCard> rspModel = response.body();
                Log.e("success", rspModel.toString());
                if (rspModel.success()) {
                    ChallengeCard loveCard = rspModel.getResult();
                    // 唤起进行保存的操作
                    Factory.getChallengeCenter().dispatch(loveCard);
                    // 返回成功
                    callback.onDataLoaded(loveCard);
                } else {
                    // 错误情况下进行错误分配
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<ChallengeCard>> call, Throwable t) {
                callback.onDataNotAvailable(R.string.data_network_error);
            }
        });
    }
    public static Challenge searchFirstOfNet() {
        Challenge love = findFromNet();
        if (love == null) {
            return findFromLocal(Account.getUserId());
        }
        return love;
    }

    // 从本地查询一个用户的信息
    public static Challenge findFromLocal(String id) {
        return SQLite.select()
                .from(Challenge.class)
                .where(Challenge_Table.originId.eq(id))
                .querySingle();
    }

    // 从网络查询某用户的信息
    public static Challenge findFromNet() {
        RemoteService remoteService = Network.remote();
        try {
            Response<RspModel<ChallengeCard>> response = remoteService.getChallenge().execute();
            ChallengeCard card = response.body().getResult();
            if (card != null) {
                Challenge love = card.build();
                // 数据库的存储并通知
                Factory.getChallengeCenter().dispatch(card);
                return love;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
