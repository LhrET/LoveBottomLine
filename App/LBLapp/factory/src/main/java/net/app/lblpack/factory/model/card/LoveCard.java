package net.app.lblpack.factory.model.card;

import net.app.lblpack.factory.model.db.Love;

import java.util.Date;

public class LoveCard {
    private String id;
    private String originId;
    private String targetId;
    private int love_num;
    private Date createAt;// 创建时间

    private transient Love love;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
    public Love build() {
        if (love == null) {
            Love love = new Love();
            love.setId(id);
            love.setOriginId(originId);
            love.setTargetId(targetId);
            love.setLove_num(love_num);
            love.setCreateAt(createAt);
            this.love = love;
        }
        return love;
    }
}
