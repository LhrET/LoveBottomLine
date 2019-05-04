package com.example.enai33.bean;

import java.util.Date;

public class User {
    private int sex;
    private String username;
    private String password;
    private boolean startflag;
    private boolean finishflag;
    private int dayNum;   //存储开始第几天
    private int startDate;

    public User() {
        this.sex = 1;
        this.username = "hyc";
        this.password = "123";
        this.startflag = false;
        this.finishflag = false;
    }

    public int getStartDate() {
        return startDate;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        this.dayNum = today - getStartDate() + 1;
        return this.dayNum;
    }

}
