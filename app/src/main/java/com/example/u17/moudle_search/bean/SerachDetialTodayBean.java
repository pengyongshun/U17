package com.example.u17.moudle_search.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @Author:pengyongshun
 * @Desc:
 * @Time:2016/9/9
 */
public class SerachDetialTodayBean implements Serializable{
                private String conTag;
                private String cover;
                private String name;
                private int comicId;
                private String description;
                private int flag;
                private String author;
                private List<String> tags;

    public String getConTag() {
        return conTag;
    }

    public void setConTag(String conTag) {
        this.conTag = conTag;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getComicId() {
        return comicId;
    }

    public void setComicId(int comicId) {
        this.comicId = comicId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "SerachDetialTodayBean{" +
                "conTag='" + conTag + '\'' +
                ", cover='" + cover + '\'' +
                ", name='" + name + '\'' +
                ", comicId=" + comicId +
                ", description='" + description + '\'' +
                ", flag=" + flag +
                ", author='" + author + '\'' +
                ", tags=" + tags +
                '}';
    }
}
