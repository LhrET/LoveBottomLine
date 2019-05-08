package net.app.lblpack.factory.data.message;

import android.text.TextUtils;

import net.app.lblpack.factory.data.helper.DbHelper;
import net.app.lblpack.factory.model.card.ChallengeCard;
import net.app.lblpack.factory.model.db.Challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ChallengeDispatcher implements ChallengeCenter{
    private static ChallengeCenter instance;
    // 单线程池；处理卡片一个个的消息进行处理
    private final Executor executor = Executors.newSingleThreadExecutor();
    public static ChallengeCenter instance() {
        if (instance == null) {
            synchronized (ChallengeDispatcher.class) {
                if (instance == null)
                    instance = new ChallengeDispatcher();
            }
        }
        return instance;
    }
    @Override
    public void dispatch(ChallengeCard... cards) {
        if (cards == null || cards.length == 0)
            return;

        // 丢到单线程池中
        executor.execute(new ChallengeDispatcher.ChallengeCardHandler(cards));
    }


    private class ChallengeCardHandler implements Runnable {
        private final ChallengeCard[] cards;

        ChallengeCardHandler(ChallengeCard[] cards) {
            this.cards = cards;
        }

        @Override
        public void run() {
            // 单被线程调度的时候触发
            List<Challenge> users = new ArrayList<>();
            for (ChallengeCard card : cards) {
                // 进行过滤操作
                if (card == null || TextUtils.isEmpty(card.getId()))
                    continue;
                // 添加操作
                users.add(card.build());
            }

            // 进行数据库存储，并分发通知, 异步的操作
            DbHelper.save(Challenge.class, users.toArray(new Challenge[0]));
        }
    }

}
