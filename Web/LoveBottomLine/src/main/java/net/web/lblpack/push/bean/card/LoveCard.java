package net.web.lblpack.push.bean.card;

import com.google.gson.annotations.Expose;
import net.web.lblpack.push.bean.db.Love;
import net.web.lblpack.push.bean.db.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

public class LoveCard {
    @Expose
    private String id;
    @Expose
    private String originId;
    @Expose
    private String targetId;
    @Expose
    private int love_num;
    @Expose
    private LocalDateTime createAt;// 创建时间

    public LoveCard(final Love love) {
        this.id = love.getId();
        this.originId = love.getOriginId();
        this.targetId = love.getTargetId();
        this.love_num = love.getLove_num();
        this.createAt = love.getCreateAt();
    }

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

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}
