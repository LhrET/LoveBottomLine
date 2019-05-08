package net.web.lblpack.push.factory;

import net.web.lblpack.push.bean.api.message.LoveModel;
import net.web.lblpack.push.bean.db.Love;
import net.web.lblpack.push.bean.db.User;
import net.web.lblpack.push.bean.db.UserFollow;
import net.web.lblpack.push.utils.Hib;

public class LoveFactory {
    public static Love findCById(User user) {
        return Hib.query(session -> (Love) session
                .createQuery("from Love where originId=:name")
                .setParameter("name", user.getId())
                .uniqueResult());
    }

    public static Love update(Love love1,Love love2) {
        return Hib.query(session -> {

            session.saveOrUpdate(love1);
            session.saveOrUpdate(love2);
            return love1;
        });
    }
    public static User save(User origin,User target,int alias) {
        return Hib.query(session -> {
            // 想要操作懒加载的数据，需要重新load一次
            session.load(origin, origin.getId());
            session.load(target, target.getId());

            // 我关注人的时候，同时他也关注我，
            // 所有需要添加两条UserFollow数据
            Love originFollow = new Love();
            originFollow.setOrigin(origin);
            originFollow.setTarget(target);
            // 备注是我对他的备注，他对我默认没有备注
            originFollow.setLove_num(alias);

            // 发起者是他，我是被关注的人的记录
            Love targetFollow = new Love();
            targetFollow.setOrigin(target);
            targetFollow.setTarget(origin);
            targetFollow.setLove_num(alias);

            // 保存数据库
            session.save(originFollow);
            session.save(targetFollow);

            return target;
        });
    }
}
