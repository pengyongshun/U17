package com.example.u17.moudle_search.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @Author:pengyongshun
 * @Desc:
 * @Time:2016/9/12
 */
public class StatisticsBean implements Serializable{
    private String avatar;
    private String name;
    private String description;
    private String last_update_week;
    private double total_ticket;
    private int total_tucao;
    private String month_ticket;
    private String cate_id;
    private String thread_id;
    private List<String> theme_ids;
    private int favorite_total;
    private List<OtherWorksBean> otherWorks;

    public List<OtherWorksBean> getOtherWorks() {
        return otherWorks;
    }

    @Override
    public String toString() {
        return "StatisticsBean{" +
                "avatar='" + avatar + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", last_update_week='" + last_update_week + '\'' +
                ", total_ticket=" + total_ticket +
                ", total_tucao=" + total_tucao +
                ", month_ticket='" + month_ticket + '\'' +
                ", cate_id='" + cate_id + '\'' +
                ", thread_id='" + thread_id + '\'' +
                ", theme_ids=" + theme_ids +
                ", favorite_total=" + favorite_total +
                ", otherWorks=" + otherWorks +
                '}';
    }

    public void setOtherWorks(List<OtherWorksBean> otherWorks) {
        this.otherWorks = otherWorks;
    }

    public static class OtherWorksBean implements Serializable{
        private String comicId;
        private String coverUrl;
        private String name;
        private String passChapterNum;
        public String getComicId() {
            return comicId;
        }

        public void setComicId(String comicId) {
            this.comicId = comicId;
        }

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassChapterNum() {
            return passChapterNum;
        }

        public void setPassChapterNum(String passChapterNum) {
            this.passChapterNum = passChapterNum;
        }
    }

    public int getFavorite_total() {
        return favorite_total;
    }

    public void setFavorite_total(int favorite_total) {
        this.favorite_total = favorite_total;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLast_update_week() {
        return last_update_week;
    }

    public void setLast_update_week(String last_update_week) {
        this.last_update_week = last_update_week;
    }

    public double getTotal_ticket() {
        return total_ticket;
    }

    public void setTotal_ticket(double total_ticket) {
        this.total_ticket = total_ticket;
    }

    public int getTotal_tucao() {
        return total_tucao;
    }

    public void setTotal_tucao(int total_tucao) {
        this.total_tucao = total_tucao;
    }

    public String getMonth_ticket() {
        return month_ticket;
    }

    public void setMonth_ticket(String month_ticket) {
        this.month_ticket = month_ticket;
    }

    public String getCate_id() {
        return cate_id;
    }

    public void setCate_id(String cate_id) {
        this.cate_id = cate_id;
    }

    public String getThread_id() {
        return thread_id;
    }

    public void setThread_id(String thread_id) {
        this.thread_id = thread_id;
    }

    public List<String> getTheme_ids() {
        return theme_ids;
    }

    public void setTheme_ids(List<String> theme_ids) {
        this.theme_ids = theme_ids;
    }
}
