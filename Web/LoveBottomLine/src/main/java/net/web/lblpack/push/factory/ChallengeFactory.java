package net.web.lblpack.push.factory;

import net.web.lblpack.push.bean.card.UserChallengeCard;
import net.web.lblpack.push.bean.db.Challenge;
import net.web.lblpack.push.bean.db.User;
import net.web.lblpack.push.bean.db.UserChallenge;
import net.web.lblpack.push.bean.db.UserFollow;
import net.web.lblpack.push.utils.Hib;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ChallengeFactory {
    public static User createChallenge(User orgin,User target,UserChallenge Challenge) {

        return Hib.query(session -> {
            // 想要操作懒加载的数据，需要重新load一次
            // 想要操作懒加载的数据，需要重新load一次
            session.load(orgin, orgin.getId());
            session.load(target, target.getId());

            // 我关注人的时候，同时他也关注我，
            // 所有需要添加两条UserFollow数据
            UserChallenge originFollow = new UserChallenge();
            originFollow.setSender(orgin);
            originFollow.setReceiver(target);
            // 备注是我对他的备注，他对我默认没有备注
            originFollow.setDescription(Challenge.getDescription());
            originFollow.setGuanka(Challenge.getGuanka());
            originFollow.setUrl(Challenge.getUrl());
            originFollow.setDaySum(Challenge.getDaySum());

            // 发起者是他，我是被关注的人的记录
            UserChallenge targetFollow = new UserChallenge();
            targetFollow.setSender(target);
            targetFollow.setReceiver(orgin);
            // 备注是我对他的备注，他对我默认没有备注
            targetFollow.setDescription(Challenge.getDescription());
            targetFollow.setGuanka(Challenge.getGuanka());
            targetFollow.setUrl(Challenge.getUrl());
            targetFollow.setDaySum(Challenge.getDaySum());
            // 保存数据库
            session.save(originFollow);
            session.save(targetFollow);
            // 保存数据库
            return targetFollow.getReceiver();
        });
    }
    public static List<UserChallenge> contacts(User self) {
        return Hib.query(session -> (List<UserChallenge>) session
                .createQuery("from UserChallenge where sendId = :originId")
                .setParameter("originId", self.getId())
                .list());
    }

    public static void deleteChallenge(User orgin,User target) {
        Hib.query(session -> (session
                .createQuery("delete from UserChallenge where sendId = :originId or sendId =:receiveId")
                .setParameter("originId", orgin.getId())
                .setParameter("receiveId",target.getId())
                .executeUpdate()));
    }
    public static UserChallenge updateChallenge(UserChallenge challenge1,UserChallenge userChallenge) {
        Challenge challenge = ChallFactory.findById(UserFactory.findById(challenge1.getSendId()));
        challenge.setFinishFlag(true);
        Challenge challenge2 = ChallFactory.findById(UserFactory.findById(challenge1.getReceiveId()));
        challenge2.setFinishFlag(true);
        ChallFactory.update(challenge,challenge2);
        return Hib.query(session -> {
            // 保存数据库
            session.saveOrUpdate(userChallenge);
            session.saveOrUpdate(challenge1);
            return  userChallenge;
        });
    }

    public static UserChallenge getUserChallenge(final User origin,int daysum) {
        return Hib.query(session -> (UserChallenge) session
                .createQuery("from UserChallenge where sendId = :originId and daySum=:daysum")
                .setParameter("originId", origin.getId())
                .setParameter("daysum", daysum)
                .setMaxResults(1)
                // 唯一查询返回
                .uniqueResult());
    }
}
