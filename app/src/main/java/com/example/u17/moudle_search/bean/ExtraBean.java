package com.example.u17.moudle_search.bean;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * @Author:pengyongshun
 * @Desc:
 * @Time:2016/9/9
 */
public class ExtraBean implements Serializable {
    private String title;
    private List<TabListBean> tabList;

    @Override
    public String toString() {
        return "ExtraBean{" +
                "title='" + title + '\'' +
                ", tabList=" + tabList +
                '}';
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TabListBean> getTabList() {
        return tabList;
    }

    public void setTabList(List<TabListBean> tabList) {
        this.tabList = tabList;
    }

    /*
        * TabListBean类
        *
        * **/
    public static class TabListBean implements Serializable{
        private String argName;
        private int argValue;
        private int argCon;
        private String tabTitle;

        @Override
        public String toString() {
            return "TabListBean{" +
                    "argName='" + argName + '\'' +
                    ", argValue=" + argValue +
                    ", argCon=" + argCon +
                    ", tabTitle='" + tabTitle + '\'' +
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

        public int getArgCon() {
            return argCon;
        }

        public void setArgCon(int argCon) {
            this.argCon = argCon;
        }

        public String getTabTitle() {
            return tabTitle;
        }

        public void setTabTitle(String tabTitle) {
            this.tabTitle = tabTitle;
        }
    }

}
