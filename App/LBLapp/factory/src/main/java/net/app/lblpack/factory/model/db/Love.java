package net.app.lblpack.factory.model.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

import java.util.Date;
import java.util.Objects;

@Table(database = AppDatabase.class)
public class Love extends BaseDbModel<Love>{
    @PrimaryKey
    private String id;
    @Column
    private String originId;
    @Column
    private String targetId;
    @Column
    private int love_num;
    @Column
    private Date createAt;// 创建时间

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

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public boolean isSame(Love old) {
        return this == old || Objects.equals(id, old.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Love love = (Love) o;
        return love_num == love.love_num
                && Objects.equals(id, love.id)
                && Objects.equals(originId, love.originId)
                && Objects.equals(targetId, love.targetId)
                && Objects.equals(createAt, love.createAt);
    }

    @Override
    public boolean isUiContentSame(Love old) {
        return this == old || (
                Objects.equals(originId, old.originId)
                        && Objects.equals(targetId, old.targetId)
                        && Objects.equals(love_num, old.love_num)
        );
    }
}
