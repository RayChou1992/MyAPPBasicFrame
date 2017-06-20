package com.zl.myappbasicframe.beans;

/**
 * 生产线
 * 用于判定某一处的工作是否完成
 * Created by Ray on 2017/5/23.
 */


public class WorkLine {
    //距离（位置）
    private String distance;
    //是否完成
    private  boolean isCompleted;

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
