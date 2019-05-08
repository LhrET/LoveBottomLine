package net.web.lblpack.push.service;

import net.web.lblpack.push.bean.api.base.ResponseModel;
import net.web.lblpack.push.bean.api.message.ChallengeModel;
import net.web.lblpack.push.bean.api.message.LoveModel;
import net.web.lblpack.push.bean.card.ChallengeCard;
import net.web.lblpack.push.bean.card.LoveCard;
import net.web.lblpack.push.bean.db.Challenge;
import net.web.lblpack.push.bean.db.Love;
import net.web.lblpack.push.bean.db.User;
import net.web.lblpack.push.factory.ChallFactory;
import net.web.lblpack.push.factory.ChallengeFactory;
import net.web.lblpack.push.factory.LoveFactory;
import net.web.lblpack.push.factory.UserFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/chall")
public class ChallengeService extends BaseService {
    @PUT
    @Path("/update") //127.0.0.1/api/user 不需要写，就是当前目录
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<ChallengeCard> update(ChallengeModel model) {
        if (!ChallengeModel.check(model)) {
            return ResponseModel.buildParameterError();
        }
        User user = getSelf();
        Challenge love = ChallFactory.findById(getSelf());
        Challenge love1 = ChallFactory.findById(UserFactory.findById(model.getTargetId()));
        if(love == null){
            return ResponseModel.buildCreateError(ResponseModel.ERROR_CREATE_LOVE);
        }
        if(model.getCreateAt()==null){
            love = model.updateChallenge(love);
            love1 = model.updateChallenge(love1);
        }else {
            love = model.updateChallengeC(love);
            love1 = model.updateChallengeC(love1);
        }

        ChallFactory.update(love,love1);
        // 构架自己的用户信息
        ChallengeCard card = new ChallengeCard(love);
        // 返回
        return ResponseModel.buildOk(card);
    }

    // 拉取联系人
    @GET
    @Path("/get")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<ChallengeCard> contact() {
        User self = getSelf();

        // 拿到我的联系人
        Challenge challenge = ChallFactory.findById(self);
        // 转换为UserCard
        ChallengeCard love1 = new ChallengeCard(challenge);

        // 返回
        return ResponseModel.buildOk(love1);
    }
    // 注册
    @POST
    @Path("/create")
    // 指定请求与返回的相应体为JSON
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<ChallengeCard> register(ChallengeModel model) {
        if (!ChallengeModel.check(model)) {
            // 返回参数异常
            return ResponseModel.buildParameterError();
        }
        User self = getSelf();
        User target = UserFactory.findById(LoveFactory.findCById(self).getTargetId());

        Challenge challenge = ChallFactory.save(self,target,model.isStartFlag(),model.isFinishFlag());

        ChallengeCard challengeCard = new ChallengeCard(challenge);
        return ResponseModel.buildOk(challengeCard);
    }
}
