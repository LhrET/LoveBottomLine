package net.web.lblpack.push.bean.db;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_USER_CHALLENGE")
public class UserChallenge {


    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    @Column(updatable = false,nullable = false)
    private String id;

    @Column
    private boolean startFlag;

    @Column
    private boolean finishFlag;

    // 消息类型
    @Column(nullable = false)
    private int daySum = 0;


    // 定义为创建时间戳，在创建时就已经写入
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();



    // 发送者 不为空
    // 多个消息对应一个发送者
    @JoinColumn(name = "sendId")
    @OneToOne(optional = false)
    private User sender;
    // 这个字段仅仅只是为了对应sender的数据库字段senderId
    // 不允许手动的更新或者插入
    @Column(nullable = false, updatable = false, insertable = false)
    private String sendId;


    // 接收者 可为空
    // 多个消息对应一个接收者
    @OneToOne
    @JoinColumn(name = "receiveId")
    private User receiver;
    @Column(nullable = false,updatable = false,insertable = false)
    private String receiveId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getDaySum() {
        return daySum;
    }

    public void setDaySum(int daySum) {
        this.daySum = daySum;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(String receiveId) {
        this.receiveId = receiveId;
    }
}
