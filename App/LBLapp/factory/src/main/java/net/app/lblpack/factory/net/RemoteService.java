package net.app.lblpack.factory.net;


import net.app.lblpack.factory.model.api.RspModel;
import net.app.lblpack.factory.model.api.account.AccountRspModel;
import net.app.lblpack.factory.model.api.account.LoginModel;
import net.app.lblpack.factory.model.api.account.RegisterModel;
import net.app.lblpack.factory.model.api.message.ChallengeModel;
import net.app.lblpack.factory.model.api.message.LoveModel;
import net.app.lblpack.factory.model.api.message.MsgCreateModel;
import net.app.lblpack.factory.model.api.message.UpdateChallenge;
import net.app.lblpack.factory.model.api.user.UserUpdateModel;
import net.app.lblpack.factory.model.card.ChallengeCard;
import net.app.lblpack.factory.model.card.LoveCard;
import net.app.lblpack.factory.model.card.MessageCard;
import net.app.lblpack.factory.model.card.UserCard;
import net.app.lblpack.factory.model.card.UserChallengeCard;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * 网络请求的所有的接口
 *
 * @version 1.0.0
 */
public interface RemoteService {

    /**
     * 注册接口
     *
     * @param model 传入的是RegisterModel
     * @return 返回的是RspModel<AccountRspModel>
     */
    @POST("account/register")
    Call<RspModel<AccountRspModel>> accountRegister(@Body RegisterModel model);

    /**
     * 登录接口
     *
     * @param model LoginModel
     * @return RspModel<AccountRspModel>
     */
    @POST("account/login")
    Call<RspModel<AccountRspModel>> accountLogin(@Body LoginModel model);

    /**
     * 绑定设备Id
     *
     * @param pushId 设备Id
     * @return RspModel<AccountRspModel>
     */
    @POST("account/bind/{pushId}")
    Call<RspModel<AccountRspModel>> accountBind(@Path(encoded = true, value = "pushId") String pushId);

    // 用户更新的接口
    @PUT("user")
    Call<RspModel<UserCard>> userUpdate(@Body UserUpdateModel model);

    // 用户搜索的接口
    @GET("user/search/{name}")
    Call<RspModel<List<UserCard>>> userSearch(@Path("name") String name);

    // 用户关注接口
    @PUT("user/follow/{userId}")
    Call<RspModel<UserCard>> userFollow(@Path("userId") String userId);

    // 获取联系人列表
    @GET("user/contact")
    Call<RspModel<List<UserCard>>> userContacts();

    // 查询某人的信息
    @GET("user/{userId  }")
    Call<RspModel<UserCard>> userFind(@Path("userId") String userId);

    // 发送消息的接口
    @POST("msg")
    Call<RspModel<MessageCard>> msgPush(@Body MsgCreateModel model);

    @POST("love/create")
    Call<RspModel<LoveCard>> lovecreat(@Body LoveModel model);

    // 用户更新的接口
    @PUT("love/update")
    Call<RspModel<LoveCard>> loveupdate(@Body LoveModel model);

    // 用户搜索的接口
    @GET("love/get")
    Call<RspModel<LoveCard>> getlove();

    @POST("user/challenge/create")
    Call<RspModel<UserChallengeCard>> userchallengecreat(@Body UpdateChallenge model);

    // 用户更新的接口
    @PUT("user/challenge/update")
    Call<RspModel<UserChallengeCard>> userchallengepdate(@Body UpdateChallenge model);

    // 用户搜索的接口
    @GET("user/getchallenge/{daySum}")
    Call<RspModel<UserChallengeCard>> getUserChallengeOne(@Path("daySum") int daySum);

    @GET("user/getlistchallenges")
    Call<RspModel<List<UserChallengeCard>>> getUserChallengelist();

    @POST("chall/create")
    Call<RspModel<ChallengeCard>> challengecreat(@Body ChallengeModel model);

    // 用户更新的接口
    @PUT("chall/update")
    Call<RspModel<ChallengeCard>> challengeupdate(@Body ChallengeModel model);

    // 用户搜索的接口
    @GET("chall/get")
    Call<RspModel<ChallengeCard>> getChallenge();


}
