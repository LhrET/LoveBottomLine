package net.web.lblpack.push.factory;

import net.web.lblpack.push.bean.db.Challenge;
import net.web.lblpack.push.bean.db.Love;
import net.web.lblpack.push.bean.db.User;
import net.web.lblpack.push.utils.Hib;

public class ChallFactory {
    public static Challenge findById(User user) {
        return Hib.query(session -> (Challenge) session
                .createQuery("from Challenge where originId=:name")
                .setParameter("name", user.getId())
                .uniqueResult());
    }

    public static Challenge update(Challenge love1,Challenge love2) {
        return Hib.query(session -> {
            session.saveOrUpdate(love1);
            session.saveOrUpdate(love2);
            return love1;
        });
    }
    public static Challenge save(User origin,User target,boolean startFlag,boolean finishFlag,int day) {

        return Hib.query(session -> {
            // 想要操作懒加载的数据，需要重新load一次
            session.load(origin, origin.getId());
            session.load(target, target.getId());

            // 我关注人的时候，同时他也关注我，
            // 所有需要添加两条UserFollow数据
            Challenge originFollow = new Challenge();
            originFollow.setOrigin(origin);
            originFollow.setOriginId(origin.getId());
            originFollow.setTargetId(target.getId());
            originFollow.setTarget(target);
            // 备注是我对他的备注，他对我默认没有备注
            originFollow.setStartFlag(startFlag);
            originFollow.setFinishFlag(finishFlag);
            originFollow.setDayNum(day);

            // 发起者是他，我是被关注的人的记录
            Challenge targetFollow = new Challenge();
            targetFollow.setOrigin(target);
            targetFollow.setTarget(origin);
            targetFollow.setStartFlag(startFlag);
            targetFollow.setFinishFlag(finishFlag);
            targetFollow.setDayNum(day);

            // 保存数据库
            session.save(originFollow);
            session.save(targetFollow);

            return originFollow;
        });
    }

}
