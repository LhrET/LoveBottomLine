package net.web.lblpack.push.service;

import net.web.lblpack.push.bean.api.base.ResponseModel;
import net.web.lblpack.push.bean.api.message.LoveModel;
import net.web.lblpack.push.bean.card.LoveCard;
import net.web.lblpack.push.bean.card.UserCard;
import net.web.lblpack.push.bean.db.Love;
import net.web.lblpack.push.bean.db.User;
import net.web.lblpack.push.factory.LoveFactory;
import net.web.lblpack.push.factory.UserFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/love")
public class LoveService extends BaseService {
    @PUT
    @Path("/update") //127.0.0.1/api/user 不需要写，就是当前目录
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<LoveCard> update(LoveModel model) {
        if (!LoveModel.check(model)) {
            return ResponseModel.buildParameterError();
        }
        Love love = LoveFactory.findCById(UserFactory.findById(model.getOriginId()));
        Love love1 = LoveFactory.findCById(UserFactory.findById(love.getTargetId()));
        if(love == null){
            return ResponseModel.buildCreateError(ResponseModel.ERROR_CREATE_LOVE);
        }
        love = model.updateLove(love);
        love1 = model.updateLove(love1);
        LoveFactory.update(love,love1);
        // 构架自己的用户信息
        LoveCard card = new LoveCard(love);
        // 返回
        return ResponseModel.buildOk(card);
    }

    // 拉取联系人
    @GET
    @Path("/get")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<LoveCard> contact() {
        User self = getSelf();

        // 拿到我的联系人
        Love love = LoveFactory.findCById(self);
        // 转换为UserCard
        LoveCard love1 = new LoveCard(love);

        // 返回
        return ResponseModel.buildOk(love1);
    }
    // 注册
    @POST
    @Path("/create")
    // 指定请求与返回的相应体为JSON
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<LoveCard> register(LoveModel model) {
        if (!LoveModel.check(model)) {
            // 返回参数异常
            return ResponseModel.buildParameterError();
        }
        User self = UserFactory.findById(model.getOriginId());
        User target = UserFactory.findById(model.getTargetId());
        Love love = LoveFactory.findCById(self);
        if (love != null) {
            // 已有情侣
            return ResponseModel.buildHaveAccountError();
        }
        target = LoveFactory.save(self,target,model.getLove_num());
        love = LoveFactory.findCById(self);
        LoveCard loveCard = new LoveCard(love);
        return ResponseModel.buildOk(loveCard);
    }
}
