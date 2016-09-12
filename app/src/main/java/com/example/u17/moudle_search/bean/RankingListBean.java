package com.example.u17.moudle_search.bean;

import java.io.Serializable;

/**
 * @Author:pengyongshun
 * @Desc:
 * @Time:2016/9/10
 */
public class RankingListBean implements Serializable{
    private String argName;
    private int argValue;
    private String sortName;
    private int argCon;

    @Override
    public String toString() {
        return "RankingListBean{" +
                "argName='" + argName + '\'' +
                ", argValue=" + argValue +
                ", sortName='" + sortName + '\'' +
                ", argCon=" + argCon +
                '}';
    }

    public String getArgName() {
        return argName;
    }

    public void setArgName(String argName) {
        this.argName = argName;
    }

    public int getArgValue() {
        return argValue;
    }

    public void setArgValue(int argValue) {
        this.argValue = argValue;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public int getArgCon() {
        return argCon;
    }

    public void setArgCon(int argCon) {
        this.argCon = argCon;
    }
}
