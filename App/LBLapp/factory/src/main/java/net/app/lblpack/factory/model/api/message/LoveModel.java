package net.app.lblpack.factory.model.api.message;

public class LoveModel {
    private String originId;
    private String targetId;
    private int love_num;

    public LoveModel(String originId,String targetId,int love_num){
        this.originId = originId;
        this.targetId = targetId;
        this.love_num = love_num;
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
}
