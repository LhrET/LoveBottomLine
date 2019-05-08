package net.app.lblpack.factory.model.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

import java.util.Date;
import java.util.Objects;

@Table(database = AppDatabase.class)
public class UserChallenge extends BaseDbModel<UserChallenge>{
    @PrimaryKey
    String id;
    @Column
    String Url;

    @Column
    String description;
    @Column
    String guanka;
    @Column
    int daySum;
    @Column
    private String sendId;
    @Column
    private String receiveId;

    @Column
    Date createAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGuanka() {
        return guanka;
    }

    public void setGuanka(String guanka) {
        this.guanka = guanka;
    }

    public int getDaySum() {
        return daySum;
    }

    public void setDaySum(int daySum) {
        this.daySum = daySum;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    public String getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(String receiveId) {
        this.receiveId = receiveId;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public boolean isSame(UserChallenge old) {
        return this == old || Objects.equals(id, old.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserChallenge userChallenge = (UserChallenge) o;
        return daySum == userChallenge.daySum
                && Objects.equals(id, userChallenge.id)
                && Objects.equals(createAt, userChallenge.createAt)
                && Objects.equals(description, userChallenge.description)
                && Objects.equals(guanka, userChallenge.guanka)
                && Objects.equals(Url, userChallenge.Url)
                && Objects.equals(sendId, userChallenge.sendId)
                && Objects.equals(receiveId, userChallenge.receiveId);
    }

    @Override
    public boolean isUiContentSame(UserChallenge old) {
        return this == old || (
                Objects.equals(sendId, old.sendId)
                        && Objects.equals(receiveId, old.receiveId)
                        && Objects.equals(daySum, old.daySum)
                        && Objects.equals(description, old.description)
                        && Objects.equals(guanka, old.guanka)
        );
    }


}
