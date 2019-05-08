package net.app.lblpack.factory.data.message;

import android.text.TextUtils;

import net.app.lblpack.factory.data.helper.DbHelper;
import net.app.lblpack.factory.model.card.UserChallengeCard;
import net.app.lblpack.factory.model.db.UserChallenge;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class UserChallengeDispatcher implements UserChallengeCenter{
    private static UserChallengeCenter instance;
    // 单线程池；处理卡片一个个的消息进行处理
    private final Executor executor = Executors.newSingleThreadExecutor();
    public static UserChallengeCenter instance() {
        if (instance == null) {
            synchronized (UserChallengeDispatcher.class) {
                if (instance == null)
                    instance = new UserChallengeDispatcher();
            }
        }
        return instance;
    }
    @Override
    public void dispatch(UserChallengeCard... cards) {
        if (cards == null || cards.length == 0)
            return;

        // 丢到单线程池中
        executor.execute(new UserChallengeHandler(cards));
    }


    private class UserChallengeHandler implements Runnable {
        private final UserChallengeCard[] cards;

        UserChallengeHandler(UserChallengeCard[] cards) {
            this.cards = cards;
        }

        @Override
        public void run() {
            // 单被线程调度的时候触发
            List<UserChallenge> users = new ArrayList<>();
            for (UserChallengeCard card : cards) {
                // 进行过滤操作
                if (card == null || TextUtils.isEmpty(card.getId()))
                    continue;
                // 添加操作
                users.add(card.build());
            }

            // 进行数据库存储，并分发通知, 异步的操作
            DbHelper.save(UserChallenge.class, users.toArray(new UserChallenge[0]));
        }
    }
}
