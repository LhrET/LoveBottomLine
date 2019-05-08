package net.web.lblpack.push.factory;

import net.web.lblpack.push.bean.card.UserChallengeCard;
import net.web.lblpack.push.bean.db.User;
import net.web.lblpack.push.bean.db.UserChallenge;
import net.web.lblpack.push.utils.Hib;

public class ChallengeFactory {
    public static User createChallenge(User orgin,User target,UserChallenge Challenge) {

        return Hib.query(session -> {
            // 想要操作懒加载的数据，需要重新load一次
            session.load(orgin, orgin.getId());
            session.load(target, target.getId());

            // 挑战是相互的
            UserChallenge userChallenge = new UserChallenge();
            userChallenge.setSender(orgin);
            userChallenge.setReceiver(target);
            userChallenge.setDaySum(Challenge.getDaySum());
            userChallenge.setStartFlag(Challenge.isStartFlag());
            userChallenge.setFinishFlag(Challenge.isFinishFlag());
            // 备注是我对他的备注，他对我默认没有备注

            // 发起者是我
            UserChallenge challenge1 = new UserChallenge();
            challenge1.setSender(target);
            challenge1.setReceiver(orgin);
            challenge1.setDaySum(Challenge.getDaySum());
            challenge1.setStartFlag(Challenge.isStartFlag());
            challenge1.setFinishFlag(Challenge.isFinishFlag());
            // 保存数据库
            session.save(userChallenge);
            session.save(challenge1);
            return Challenge.getReceiver();
        });
    }

    public static UserChallenge updateChallenge(UserChallenge challenge1,UserChallenge userChallenge) {
        return Hib.query(session -> {
            // 保存数据库
            session.saveOrUpdate(userChallenge);
            session.saveOrUpdate(challenge1);
            return challenge1;
        });
    }

    public static UserChallenge getUserChallenge(final User origin) {
        return Hib.query(session -> (UserChallenge) session
                .createQuery("from UserChallenge where sendId = :originId")
                .setParameter("originId", origin.getId())
                .setMaxResults(1)
                // 唯一查询返回
                .uniqueResult());
    }
}
