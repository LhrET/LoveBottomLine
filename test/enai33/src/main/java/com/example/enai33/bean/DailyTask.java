package com.example.enai33.bean;

public class DailyTask {
    private String task;
    private String src;
    boolean finish;

    public DailyTask() {
        setFinish(false);
        setTask(null);
        setSrc(null);
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }
}
