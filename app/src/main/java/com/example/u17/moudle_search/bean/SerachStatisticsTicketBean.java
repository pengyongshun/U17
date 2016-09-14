package com.example.u17.moudle_search.bean;

/**
 * @Author:pengyongshun
 * @Desc:
 * @Time:2016/9/10
 */
public class SerachStatisticsTicketBean {
                private String comic_id;
                private int total_ticket;
                private int total_tucao;
                private String month_ticket;
                private int favorite_total;
                private int total_click;
                private String commentCount;

    @Override
    public String toString() {
        return "SerachStatisticsTicketBean{" +
                "comic_id='" + comic_id + '\'' +
                ", total_ticket=" + total_ticket +
                ", total_tucao=" + total_tucao +
                ", month_ticket='" + month_ticket + '\'' +
                ", favorite_total=" + favorite_total +
                ", total_click=" + total_click +
                ", commentCount='" + commentCount + '\'' +
                '}';
    }

    public String getComic_id() {
        return comic_id;
    }

    public void setComic_id(String comic_id) {
        this.comic_id = comic_id;
    }

    public int getTotal_ticket() {
        return total_ticket;
    }

    public void setTotal_ticket(int total_ticket) {
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

    public int getFavorite_total() {
        return favorite_total;
    }

    public void setFavorite_total(int favorite_total) {
        this.favorite_total = favorite_total;
    }

    public int getTotal_click() {
        return total_click;
    }

    public void setTotal_click(int total_click) {
        this.total_click = total_click;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }
}
