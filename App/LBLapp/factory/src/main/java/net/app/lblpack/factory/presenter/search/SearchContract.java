package net.app.lblpack.factory.presenter.search;

import net.app.lblpack.factory.model.card.UserCard;
import net.app.lblpack.factory.presenter.BaseContract;

import java.util.List;

/**
 * @version 1.0.0
 */
public interface SearchContract {
    interface Presenter extends BaseContract.Presenter {
        // 搜索内容
        void search(String content);
    }

    // 搜索人的界面
    interface UserView extends BaseContract.View<Presenter> {
        void onSearchDone(List<UserCard> userCards);
    }


}
