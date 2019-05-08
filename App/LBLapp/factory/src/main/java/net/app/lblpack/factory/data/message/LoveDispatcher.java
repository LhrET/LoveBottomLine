package net.app.lblpack.factory.data.message;

import android.text.TextUtils;

import net.app.lblpack.factory.data.helper.DbHelper;
import net.app.lblpack.factory.model.card.LoveCard;
import net.app.lblpack.factory.model.db.Love;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LoveDispatcher implements LoveCenter {
    private static LoveCenter instance;
    // 单线程池；处理卡片一个个的消息进行处理
    private final Executor executor = Executors.newSingleThreadExecutor();
    public static LoveCenter instance() {
        if (instance == null) {
            synchronized (LoveDispatcher.class) {
                if (instance == null)
                    instance = new LoveDispatcher();
            }
        }
        return instance;
    }
    @Override
    public void dispatch(LoveCard... cards) {
        if (cards == null || cards.length == 0)
            return;

        // 丢到单线程池中
        executor.execute(new LoveCardHandler(cards));
    }


    private class LoveCardHandler implements Runnable {
        private final LoveCard[] cards;

        LoveCardHandler(LoveCard[] cards) {
            this.cards = cards;
        }

        @Override
        public void run() {
            // 单被线程调度的时候触发
            List<Love> users = new ArrayList<>();
            for (LoveCard card : cards) {
                // 进行过滤操作
                if (card == null || TextUtils.isEmpty(card.getId()))
                    continue;
                // 添加操作
                users.add(card.build());
            }

            // 进行数据库存储，并分发通知, 异步的操作
            DbHelper.save(Love.class, users.toArray(new Love[0]));
        }
    }


}
