package net.app.lblpack.factory.data.helper;

import android.util.Log;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import net.app.lblpack.factory.Factory;
import net.app.lblpack.factory.R;
import net.app.lblpack.factory.data.DataSource;
import net.app.lblpack.factory.model.api.RspModel;
import net.app.lblpack.factory.model.api.message.UpdateChallenge;
import net.app.lblpack.factory.model.card.UserChallengeCard;
import net.app.lblpack.factory.model.db.UserChallenge;
import net.app.lblpack.factory.model.db.UserChallenge_Table;
import net.app.lblpack.factory.net.Network;
import net.app.lblpack.factory.net.RemoteService;
import net.app.lblpack.factory.persistence.Account;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserChallengeHelper {

    public static void update(UpdateChallenge model, final DataSource.Callback<UserChallengeCard> callback) {
        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.remote();
        // 得到一个Call
        Log.e("TAG",model.toString());
        Call<RspModel<UserChallengeCard>> call = service.userchallengepdate(model);
        // 网络请求
        call.enqueue(new Callback<RspModel<UserChallengeCard>>() {
            @Override
            public void onResponse(Call<RspModel<UserChallengeCard>> call, Response<RspModel<UserChallengeCard>> response) {
                RspModel<UserChallengeCard> rspModel = response.body();
                Log.e("success",rspModel.toString());
                if (rspModel.success()) {
                    UserChallengeCard userCard = rspModel.getResult();
                    // 唤起进行保存的操作
                    Factory.getUserChallengeCenter().dispatch(userCard);
                    // 返回成功
                    callback.onDataLoaded(userCard);
                } else {
                    // 错误情况下进行错误分配
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<UserChallengeCard>> call, Throwable t) {
                callback.onDataNotAvailable(R.string.data_network_error);
            }
        });
    }

    // 搜索的方法
    public static Call search(int dayNum,final DataSource.Callback<UserChallengeCard> callback) {
        RemoteService service = Network.remote();
        Call<RspModel<UserChallengeCard>> call = service.getUserChallengeOne(dayNum);

        call.enqueue(new Callback<RspModel<UserChallengeCard>>() {
            @Override
            public void onResponse(Call<RspModel<UserChallengeCard>> call, Response<RspModel<UserChallengeCard>> response) {
                RspModel<UserChallengeCard> rspModel = response.body();
                if (rspModel.success()) {
                    // 返回数据
                    callback.onDataLoaded(rspModel.getResult());
                } else {
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<UserChallengeCard>> call, Throwable t) {
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
    public static void create(final UpdateChallenge model, final DataSource.Callback<UserChallengeCard> callback) {
        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.remote();
        // 得到一个Call
        Call<RspModel<UserChallengeCard>> call = service.userchallengecreat(model);
        // 得到一个Call
        Log.e("TAG", model.toString());
        // 网络请求
        call.enqueue(new Callback<RspModel<UserChallengeCard>>() {
            @Override
            public void onResponse(Call<RspModel<UserChallengeCard>> call, Response<RspModel<UserChallengeCard>> response) {
                RspModel<UserChallengeCard> rspModel = response.body();
                Log.e("success", rspModel.toString());
                if (rspModel.success()) {
                    UserChallengeCard loveCard = rspModel.getResult();
                    // 唤起进行保存的操作
                    Factory.getUserChallengeCenter().dispatch(loveCard);
                    // 返回成功
                    callback.onDataLoaded(loveCard);
                } else {
                    // 错误情况下进行错误分配
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<UserChallengeCard>> call, Throwable t) {
                callback.onDataNotAvailable(R.string.data_network_error);
            }
        });
    }
    public static UserChallenge searchFirstOfNet(int dayNum) {
        UserChallenge love = findFromNet(dayNum);
        if (love == null) {
            return findFromLocal(Account.getUserId(),dayNum);
        }
        return love;
    }

    // 从本地查询一个用户的信息
    public static UserChallenge findFromLocal(String id,int dayNum) {
        return SQLite.select()
                .from(UserChallenge.class)
                .where(UserChallenge_Table.daySum.eq(dayNum))
                .and(UserChallenge_Table.sendId.eq(id))
                .querySingle();
    }

    // 从网络查询某用户的信息
    public static UserChallenge findFromNet(int dayNum) {
        RemoteService remoteService = Network.remote();
        try {
            Response<RspModel<UserChallengeCard>> response = remoteService.getUserChallengeOne(dayNum).execute();
            UserChallengeCard card = response.body().getResult();
            if (card != null) {
                UserChallenge love = card.build();
                // 数据库的存储并通知
                Factory.getUserChallengeCenter().dispatch(card);
                return love;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void refreshUCContacts() {
        RemoteService service = Network.remote();
        Call<RspModel<List<UserChallengeCard>>> call  =service.getUserChallengelist();

        call.enqueue(new Callback<RspModel<List<UserChallengeCard>>>() {
                    @Override
                    public void onResponse(Call<RspModel<List<UserChallengeCard>>> call, Response<RspModel<List<UserChallengeCard>>> response) {
                        RspModel<List<UserChallengeCard>> rspModel = response.body();
                        if (rspModel.success()) {
                            // 拿到集合
                            List<UserChallengeCard> cards = rspModel.getResult();
                            if (cards == null || cards.size() == 0)
                                return;

                            UserChallengeCard[] cards1 = cards.toArray(new UserChallengeCard[0]);
                            // CollectionUtil.toArray(cards, UserCard.class);

                            Factory.getUserChallengeCenter().dispatch(cards1);

                        } else {
                            Factory.decodeRspCode(rspModel, null);
                        }
                    }

                    @Override
                    public void onFailure(Call<RspModel<List<UserChallengeCard>>> call, Throwable t) {
                        // nothing
                    }
                });
    }
    public static List<UserChallenge> getContact() {
        return SQLite.select()
                .from(UserChallenge.class)
                .where(UserChallenge_Table.sendId.eq(Account.getUserId()))
                .orderBy(UserChallenge_Table.daySum, true)
                .limit(33)
                .queryList();
    }

}
