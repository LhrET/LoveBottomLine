package net.web.lblpack.push.bean.db;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.security.Principal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_LOVE")
public class Love{
    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    @Column(updatable = false,nullable = false)
    private String id;

    //一对一绑定
    @OneToOne(optional = false)
    @JoinColumn(name = "originId")
    private User origin;

    @Column(nullable = false,updatable = false,insertable = false)
    private String originId;

    @OneToOne(optional = false)
    @JoinColumn(name = "targetId")
    private User target;

    @Column(nullable = false,updatable = false,insertable = false)
    private String targetId;

    //对target备注
    @Column
    private int love_num = 0;


    //创建时间戳
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getOrigin() {
        return origin;
    }

    public void setOrigin(User origin) {
        this.origin = origin;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public User getTarget() {
        return target;
    }

    public void setTarget(User target) {
        this.target = target;
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
