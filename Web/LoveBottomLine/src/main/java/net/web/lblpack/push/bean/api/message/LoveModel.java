package net.web.lblpack.push.bean.api.message;

import com.google.common.base.Strings;
import com.google.gson.annotations.Expose;
import net.web.lblpack.push.bean.db.Love;

import java.time.LocalDateTime;

public class LoveModel {
    @Expose
    private String originId;
    @Expose
    private String targetId;
    @Expose
    private int love_num;

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public int getLove_num() {
        return love_num;
    }

    public void setLove_num(int love_num) {
        this.love_num = love_num;
    }

    public Love updateLove(Love love) {

        if (love_num != 0)
            love.setLove_num(love_num);

        return love;

    }
    public static boolean check(LoveModel model) {
        return model != null
                && (!Strings.isNullOrEmpty(model.targetId) ||
                !Strings.isNullOrEmpty(model.originId) ||
                model.love_num != 0);
    }
}
