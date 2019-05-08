package net.app.lblpack.factory.model.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

import java.util.Date;
import java.util.Objects;

@Table(database = AppDatabase.class)
public class Challenge extends BaseDbModel<Challenge>{
    @PrimaryKey
    private String id;
    @Column
    private String originId;
    @Column
    private int dayNum;
    @Column
    private String targetId;
    @Column
    private boolean startFlag;
    @Column
    private boolean finishFlag;
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

    public boolean isStartFlag() {
        return startFlag;
    }

    public void setStartFlag(boolean startFlag) {
        this.startFlag = startFlag;
    }

    public boolean isFinishFlag() {
        return finishFlag;
    }

    public void setFinishFlag(boolean finishFlag) {
        this.finishFlag = finishFlag;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public int getDayNum() {
        return dayNum;
    }

    public void setDayNum(int dayNum) {
        this.dayNum = dayNum;
    }

    @Override
    public boolean isSame(Challenge old) {
        return this == old || Objects.equals(id, old.id);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Challenge love = (Challenge) o;
        return startFlag == love.startFlag
                && finishFlag == love.finishFlag
                && Objects.equals(id, love.id)
                && Objects.equals(dayNum, love.dayNum)
                && Objects.equals(originId, love.originId)
                && Objects.equals(targetId, love.targetId)
                && Objects.equals(createAt, love.createAt);
    }
    @Override
    public boolean isUiContentSame(Challenge old) {
        return this == old || (
                Objects.equals(originId, old.originId)
                        && Objects.equals(targetId, old.targetId)
                        && Objects.equals(startFlag, old.startFlag)
                        && Objects.equals(finishFlag, old.finishFlag)
        );
    }
}
