package com.example.enai33.bean;

import java.util.Date;

public class User {
    private int sex;
    private boolean startflag;
    private boolean finishflag;
    private int dayNum;   //存储开始第几天
    private int startDate;
    private boolean nextDayFlag;

    public User() {
        this.sex = 1;
        this.startflag = false;
        this.finishflag = false;
    }

    public boolean isNextDayFlag() { return nextDayFlag; }

    public void setNextDayFlag(boolean nextDayFlag) { this.nextDayFlag = nextDayFlag; }

    public int getSex() { return sex; }

    public void setSex(int sex) { this.sex = sex; }

    public int getStartDate() {
        return startDate;
    }

    public void setStartDate(int s) {
        this.startDate = s;
    }

    public void setStartDate(Date date) {
        this.startDate = (int) (date.getTime() / (1000*3600*24));
    }

    public int getDayNum() {
        return dayNum;
    }

    public void setDayNum(int dayNum) {
        this.dayNum = dayNum;
    }

    public boolean isStartflag() {
        return startflag;
    }

    public void setStartflag(boolean startflag) {
        this.startflag = startflag;
    }

    public boolean isFinishflag() {
        return finishflag;
    }

    public void setFinishflag(boolean finishflag) {
        this.finishflag = finishflag;
    }

    public int changeDayNum(Date date){
        int today = (int)(date.getTime() / (1000*3600*24));
        this.dayNum = today - this.startDate + 1;
        return this.dayNum;
    }

    public boolean isNextDay(Date date){
        int today = (int)(date.getTime() / (1000*3600*24));
        if(this.dayNum == today - this.startDate + 1) {
            return this.nextDayFlag = false;
        }else {
            return this.nextDayFlag = true;
        }
    }

    public void init(){
        this.dayNum = 0;
        this.startflag = false;
        this.finishflag = false;
        this.startDate = 0;
    }
}
