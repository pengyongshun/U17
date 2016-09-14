package com.example.u17.base_bean;

import java.util.List;

/**
 * @Author:pengyongshun
 * @Desc:
 * @Time:2016/9/12
 */
public class StatisticsBottomBean {
            private String comic_id;
            private String name;
            private String cover;

    @Override
    public String toString() {
        return "StatisticsBottomBean{" +
                "comic_id='" + comic_id + '\'' +
                ", name='" + name + '\'' +
                ", cover='" + cover + '\'' +
                '}';
    }

    public String getComic_id() {
        return comic_id;
    }

    public void setComic_id(String comic_id) {
        this.comic_id = comic_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
