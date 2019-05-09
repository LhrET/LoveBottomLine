package net.web.lblpack.push.service;

import com.google.common.base.Strings;
import net.web.lblpack.push.bean.api.base.ResponseModel;
import net.web.lblpack.push.bean.api.user.UpdateChallenge;
import net.web.lblpack.push.bean.api.user.UpdateInfoModel;
import net.web.lblpack.push.bean.card.UserCard;
import net.web.lblpack.push.bean.card.UserChallengeCard;
import net.web.lblpack.push.bean.db.User;
import net.web.lblpack.push.bean.db.UserChallenge;
import net.web.lblpack.push.factory.ChallengeFactory;
import net.web.lblpack.push.factory.LoveFactory;
import net.web.lblpack.push.factory.PushFactory;
import net.web.lblpack.push.factory.UserFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户信息处理的Service
 *
 * @version 1.0.0
 */
// 127.0.0.1/api/user/...
@Path("/user")
public class UserService extends BaseService {

    // 用户信息修改接口
    // 返回自己的个人信息
    @PUT
    //@Path("") //127.0.0.1/api/user 不需要写，就是当前目录
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<UserCard> update(UpdateInfoModel model) {
        if (!UpdateInfoModel.check(model)) {
            return ResponseModel.buildParameterError();
        }

        User self = getSelf();
        // 更新用户信息
        self = model.updateToUser(self);
        self = UserFactory.update(self);
        // 构架自己的用户信息
        UserCard card = new UserCard(self, true,true,true);
        // 返回
        return ResponseModel.buildOk(card);
    }

    // 拉取联系人
    @GET
    @Path("/contact")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<List<UserCard>> contact() {
        User self = getSelf();

        // 拿到我的联系人
        List<User> users = UserFactory.contacts(self);
        // 转换为UserCard
        List<UserCard> userCards = users.stream()
                // map操作，相当于转置操作，User->UserCard
                .map(user -> new UserCard(user, true,LoveFactory.findCCById(self,user)!=null,LoveFactory.findCById(user)!=null))
                .collect(Collectors.toList());
        // 返回
        return ResponseModel.buildOk(userCards);
    }
    // 拉取联系人
    @GET
    @Path("/getlistchallenges")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<List<UserChallengeCard>> getlist() {
        User self = getSelf();

        // 拿到我的联系人
        List<UserChallenge> users = ChallengeFactory.contacts(self);
        // 转换为UserCard
        List<UserChallengeCard> userCards = users.stream()
                // map操作，相当于转置操作，User->UserCard
                .map(user -> new UserChallengeCard(user))
                .collect(Collectors.toList());
        // 返回
        return ResponseModel.buildOk(userCards);
    }
    // 关注人，
    // 简化：关注人的操作其实是双方同时关注
    @PUT // 修改类使用Put
    @Path("/follow/{followId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<UserCard> follow(@PathParam("followId") String followId) {
        User self = getSelf();

        // 不能关注我自己
        if (self.getId().equalsIgnoreCase(followId)
                || Strings.isNullOrEmpty(followId)) {
            // 返回参数异常
            return ResponseModel.buildParameterError();
        }


        // 找到我也关注的人
        User followUser = UserFactory.findById(followId);
        if (followUser == null) {
            // 未找到人
            return ResponseModel.buildNotFoundUserError(null);
        }

        // 备注默认没有，后面可以扩展
        followUser = UserFactory.follow(self, followUser, null);
        if (followUser == null) {
            // 关注失败，返回服务器异常
            return ResponseModel.buildServiceError();
        }

        // 通知我关注的人我关注他
        // 给他发送一个我的信息过去
        PushFactory.pushFollow(followUser, new UserCard(self));

        boolean love = LoveFactory.findCCById(self,followUser)!=null;

        // 返回关注的人的信息
        return ResponseModel.buildOk(new UserCard(followUser, true,love,LoveFactory.findCById(followUser)!=null));
    }
    // 关注人，
    // 简化：关注人的操作其实是双方同时关注
    @PUT // 修改类使用Put
    @Path("/love/{followId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<UserCard> love(@PathParam("followId") String followId) {
        User self = getSelf();

        // 不能关注我自己
        if (self.getId().equalsIgnoreCase(followId)
                || Strings.isNullOrEmpty(followId)) {
            // 返回参数异常
            return ResponseModel.buildParameterError();
        }



        // 找到我也关注的人
        User followUser = UserFactory.findById(followId);
        if (followUser == null) {
            // 未找到人
            return ResponseModel.buildNotFoundUserError(null);
        }

        // 备注默认没有，后面可以扩展
        followUser = LoveFactory.save(self, followUser,60);
        if (followUser == null) {
            // 关注失败，返回服务器异常
            return ResponseModel.buildServiceError();
        }

        // 通知我关注的人我关注他
        // 给他发送一个我的信息过去
        PushFactory.pushFollow(followUser, new UserCard(self));


        // 返回关注的人的信息
        return ResponseModel.buildOk(new UserCard(followUser, true,true,true));
    }
    // 关注人，
    // 简化：关注人的操作其实是双方同时关注
    @PUT // 修改类使用Put
    @Path("/challenge/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<UserChallengeCard> updateChallenge(UpdateChallenge updateChallenge) {

        User self = getSelf();
        User self2 = UserFactory.findById(LoveFactory.findCById(self).getTargetId());
        if(ChallengeFactory.getUserChallenge(self,updateChallenge.getDaySum())==null){
            return ResponseModel.buildNotFoundGroupError(null);
        }


        UserChallenge challenge = ChallengeFactory.getUserChallenge(self,updateChallenge.getDaySum());
        challenge = updateChallenge.updateToUserChallenge(challenge);
        UserChallenge challenge2 = ChallengeFactory.getUserChallenge(self2,updateChallenge.getDaySum());
        challenge2 = updateChallenge.updateToUserChallenge(challenge2);

        challenge = ChallengeFactory.updateChallenge(challenge,challenge2);

        // 返回挑战的信息
        return ResponseModel.buildOk(new UserChallengeCard(challenge));
    }
    @POST // 修改类使用Put
    @Path("/challenge/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<UserChallengeCard> createChallenge(UpdateChallenge updateChallenge) {
        User user = getSelf();
        UserChallenge challenge = ChallengeFactory.getUserChallenge(user,updateChallenge.getDaySum());
        if (challenge != null) {
            // 已挑战
            return ResponseModel.buildHaveAccountError();
        }

        User self2 = UserFactory.findById(LoveFactory.findCById(user).getTargetId());
        challenge = new UserChallenge();
        challenge = updateChallenge.updateToUserChallenge(challenge);
        ChallengeFactory.createChallenge(user,self2,challenge);
        challenge.setReceiveId(self2.getId());
        challenge.setSendId(user.getId());
        // 返回挑战的信息
        return ResponseModel.buildOk(new UserChallengeCard(challenge));
    }
    @DELETE// 修改类使用Put
    @Path("/challenge/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<UserCard> deleteChallenge() {
        User user = getSelf();
        User target = UserFactory.findById(LoveFactory.findCById(user).getTargetId());
        if (target == null) {
            // 已挑战
            return ResponseModel.buildHaveAccountError();
        }
        ChallengeFactory.deleteChallenge(user,target);
        // 返回挑战的信息
        return ResponseModel.buildOk(new UserCard(target));
    }
    // 获取某人的信息
    @GET
    @Path("/getchallenge/{daySum}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<UserChallengeCard> getUserChallenge(@PathParam("daySum") int daySum) {

        User self = getSelf();

        UserChallenge userChallenge = ChallengeFactory.getUserChallenge(self,daySum);
        if (userChallenge == null) {
            // 没找到，返回没找到用户
            return ResponseModel.buildNotFoundUserError(null);
        }
        return ResponseModel.buildOk(new UserChallengeCard(userChallenge));
    }

    // 获取某人的信息
    @GET
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<UserCard> getUser(@PathParam("id") String id) {
        if (Strings.isNullOrEmpty(id)) {
            // 返回参数异常
            return ResponseModel.buildParameterError();
        }


        User self = getSelf();
        if (self.getId().equalsIgnoreCase(id)) {
            // 返回自己，不必查询数据库
            return ResponseModel.buildOk(new UserCard(self, true,true,true));
        }


        User user = UserFactory.findById(id);
        if (user == null) {
            // 没找到，返回没找到用户
            return ResponseModel.buildNotFoundUserError(null);
        }


        // 如果我们直接有关注的记录，则我已关注需要查询信息的用户
        boolean isFollow = UserFactory.getUserFollow(self, user) != null;
        boolean isLove = LoveFactory.findCCById(self, user) != null;
        return ResponseModel.buildOk(new UserCard(user, isFollow,isLove,LoveFactory.findCById(user)!=null));
    }


    // 搜索人的接口实现
    // 为了简化分页：只返回20条数据
    @GET // 搜索人，不涉及数据更改，只是查询，则为GET
    @Path("/search/{name:(.*)?}") // 名字为任意字符，可以为空
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<List<UserCard>> search(@DefaultValue("") @PathParam("name") String name) {
        User self = getSelf();

        // 先查询数据
        List<User> searchUsers = UserFactory.search(name);
        // 把查询的人封装为UserCard
        // 判断这些人是否有我已经关注的人，
        // 如果有，则返回的关注状态中应该已经设置好状态

        // 拿出我的联系人
        final List<User> contacts = UserFactory.contacts(self);

        // 把User->UserCard
        List<UserCard> userCards = searchUsers.stream()
                .map(user -> {
                    // 判断这个人是否是我自己，或者是我的联系人中的人
                    boolean isFollow = user.getId().equalsIgnoreCase(self.getId())
                            // 进行联系人的任意匹配，匹配其中的Id字段
                            || contacts.stream().anyMatch(
                            contactUser -> contactUser.getId()
                                    .equalsIgnoreCase(user.getId())
                    );
                    boolean isLove = user.getId().equalsIgnoreCase(self.getId())
                            || LoveFactory.findCCById(self,user)!=null;
                    boolean haveLove = user.getId().equalsIgnoreCase(self.getId())
                            || LoveFactory.findCById(user)!=null;

                    return new UserCard(user, isFollow,isLove,haveLove);
                }).collect(Collectors.toList());
        // 返回
        return ResponseModel.buildOk(userCards);
    }

}
