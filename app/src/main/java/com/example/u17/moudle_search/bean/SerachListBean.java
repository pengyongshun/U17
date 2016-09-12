package com.example.u17.moudle_search.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @Author:pengyongshun
 * @Desc:
 * @Time:2016/9/8
 */
public class SerachListBean {

    /**
     * code : 1
     * data : {"stateCode":1,"message":"成功","returnData":{"rankingList":[{"sortName":"古风","cover":"http://image.mylife.u17t.com/2016/08/22/1471856799_g34OsbI4PSeb.jpg","argName":"special","argValue":121,"argCon":0},{"sortName":"穿越","cover":"http://image.mylife.u17t.com/2016/08/22/1471856751_ss0I63WCzdkN.jpg","argName":"special","argValue":117,"argCon":0},{"sortName":"耽美","cover":"http://image.mylife.u17t.com/2016/08/22/1471858681_PikE4P2b4R29.jpg","argName":"theme","argValue":10},{"sortName":"恐怖","cover":"http://image.mylife.u17t.com/2016/08/22/1471857028_F78hO8o9dbuW.jpg","argName":"theme","argValue":11},{"sortName":"小说改编","cover":"http://image.mylife.u17t.com/2016/08/22/1471856898_l2y4rVvpr225.jpg","argName":"special","argValue":128,"argCon":0},{"sortName":"恋爱","cover":"http://image.mylife.u17t.com/2016/08/22/1471860696_4D98T4m08984.jpg","argName":"theme","argValue":4},{"sortName":"魔幻","cover":"http://image.mylife.u17t.com/2016/08/22/1471856737_F60y496YfAS4.jpg","argName":"theme","argValue":2},{"sortName":"搞笑","cover":"http://image.mylife.u17t.com/2016/08/22/1471858264_ttzW85KDmZgw.jpg","argName":"theme","argValue":1},{"sortName":"动作","cover":"http://image.mylife.u17t.com/2016/08/22/1471856769_Hcqqj695V911.jpg","argName":"theme","argValue":5},{"sortName":"少女","cover":"http://image.mylife.u17t.com/2016/08/22/1471856885_EMUYMYcd1zcc.jpg","argName":"cate","argValue":2},{"sortName":"科幻","cover":"http://image.mylife.u17t.com/2016/08/22/1471856818_7Gg6526tYG62.jpg","argName":"theme","argValue":6},{"sortName":"同人","cover":"http://image.mylife.u17t.com/2016/08/22/1471856871_o5VKzWWNI4Pm.jpg","argName":"theme","argValue":12},{"sortName":"生活","cover":"http://image.mylife.u17t.com/2016/08/22/1471856830_02bblPzwP5P5.jpg","argName":"theme","argValue":3},{"sortName":"少年","cover":"http://image.mylife.u17t.com/2016/08/22/1471858828_59HmtKlz999X.jpg","argName":"cate","argValue":1},{"sortName":"四格","cover":"http://image.mylife.u17t.com/2016/08/22/1471856914_aDtATdb2DT2A.jpg","argName":"cate","argValue":4}],"topList":[{"sortId":"127","sortName":"完结作品","cover":"http://image.mylife.u17t.com/2016/08/23/1471921671_A4WaVwI8A38c.png","extra":{"title":"完结作品","tabList":[{"argName":"serial","argValue":1,"argCon":4,"tabTitle":"今日最热"},{"argName":"serial","argValue":1,"argCon":1,"tabTitle":"人气总榜"}]}},{"sortId":"133","sortName":"彩漫作品","cover":"http://image.mylife.u17t.com/2016/08/23/1471921691_Gi5s85U5UR5n.png","extra":{"title":"彩漫作品","tabList":[{"argName":"topic","argValue":7,"argCon":4,"tabTitle":"今日最热"},{"argName":"topic","argValue":7,"argCon":1,"tabTitle":"人气总榜"}]}},{"sortId":"134","sortName":"订阅漫画","cover":"http://image.mylife.u17t.com/2016/08/23/1471921741_2k8z8K88DCl8.png","extra":{"title":"订阅漫画","tabList":[{"argName":"topic","argValue":12,"argCon":2,"tabTitle":"近日更新"},{"argName":"topic","argValue":12,"argCon":1,"tabTitle":"人气总榜"}]}},{"sortId":"119","sortName":"独家签约","cover":"http://image.mylife.u17t.com/2016/08/23/1471921651_lYgt19Ly3PsJ.png","extra":{"title":"独家签约","tabList":[{"argName":"topic","argValue":15,"argCon":4,"tabTitle":"今日最热"},{"argName":"topic","argValue":15,"argCon":1,"tabTitle":"人气总榜"}]}}],"recommendSearch":"镇魂街"}}
     */

    private int code;
    /**
     * stateCode : 1
     * message : 成功
     * returnData : {"rankingList":[{"sortName":"古风","cover":"http://image.mylife.u17t.com/2016/08/22/1471856799_g34OsbI4PSeb.jpg","argName":"special","argValue":121,"argCon":0},{"sortName":"穿越","cover":"http://image.mylife.u17t.com/2016/08/22/1471856751_ss0I63WCzdkN.jpg","argName":"special","argValue":117,"argCon":0},{"sortName":"耽美","cover":"http://image.mylife.u17t.com/2016/08/22/1471858681_PikE4P2b4R29.jpg","argName":"theme","argValue":10},{"sortName":"恐怖","cover":"http://image.mylife.u17t.com/2016/08/22/1471857028_F78hO8o9dbuW.jpg","argName":"theme","argValue":11},{"sortName":"小说改编","cover":"http://image.mylife.u17t.com/2016/08/22/1471856898_l2y4rVvpr225.jpg","argName":"special","argValue":128,"argCon":0},{"sortName":"恋爱","cover":"http://image.mylife.u17t.com/2016/08/22/1471860696_4D98T4m08984.jpg","argName":"theme","argValue":4},{"sortName":"魔幻","cover":"http://image.mylife.u17t.com/2016/08/22/1471856737_F60y496YfAS4.jpg","argName":"theme","argValue":2},{"sortName":"搞笑","cover":"http://image.mylife.u17t.com/2016/08/22/1471858264_ttzW85KDmZgw.jpg","argName":"theme","argValue":1},{"sortName":"动作","cover":"http://image.mylife.u17t.com/2016/08/22/1471856769_Hcqqj695V911.jpg","argName":"theme","argValue":5},{"sortName":"少女","cover":"http://image.mylife.u17t.com/2016/08/22/1471856885_EMUYMYcd1zcc.jpg","argName":"cate","argValue":2},{"sortName":"科幻","cover":"http://image.mylife.u17t.com/2016/08/22/1471856818_7Gg6526tYG62.jpg","argName":"theme","argValue":6},{"sortName":"同人","cover":"http://image.mylife.u17t.com/2016/08/22/1471856871_o5VKzWWNI4Pm.jpg","argName":"theme","argValue":12},{"sortName":"生活","cover":"http://image.mylife.u17t.com/2016/08/22/1471856830_02bblPzwP5P5.jpg","argName":"theme","argValue":3},{"sortName":"少年","cover":"http://image.mylife.u17t.com/2016/08/22/1471858828_59HmtKlz999X.jpg","argName":"cate","argValue":1},{"sortName":"四格","cover":"http://image.mylife.u17t.com/2016/08/22/1471856914_aDtATdb2DT2A.jpg","argName":"cate","argValue":4}],"topList":[{"sortId":"127","sortName":"完结作品","cover":"http://image.mylife.u17t.com/2016/08/23/1471921671_A4WaVwI8A38c.png","extra":{"title":"完结作品","tabList":[{"argName":"serial","argValue":1,"argCon":4,"tabTitle":"今日最热"},{"argName":"serial","argValue":1,"argCon":1,"tabTitle":"人气总榜"}]}},{"sortId":"133","sortName":"彩漫作品","cover":"http://image.mylife.u17t.com/2016/08/23/1471921691_Gi5s85U5UR5n.png","extra":{"title":"彩漫作品","tabList":[{"argName":"topic","argValue":7,"argCon":4,"tabTitle":"今日最热"},{"argName":"topic","argValue":7,"argCon":1,"tabTitle":"人气总榜"}]}},{"sortId":"134","sortName":"订阅漫画","cover":"http://image.mylife.u17t.com/2016/08/23/1471921741_2k8z8K88DCl8.png","extra":{"title":"订阅漫画","tabList":[{"argName":"topic","argValue":12,"argCon":2,"tabTitle":"近日更新"},{"argName":"topic","argValue":12,"argCon":1,"tabTitle":"人气总榜"}]}},{"sortId":"119","sortName":"独家签约","cover":"http://image.mylife.u17t.com/2016/08/23/1471921651_lYgt19Ly3PsJ.png","extra":{"title":"独家签约","tabList":[{"argName":"topic","argValue":15,"argCon":4,"tabTitle":"今日最热"},{"argName":"topic","argValue":15,"argCon":1,"tabTitle":"人气总榜"}]}}],"recommendSearch":"镇魂街"}
     */

    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int stateCode;
        private String message;
        /**
         * rankingList : [{"sortName":"古风","cover":"http://image.mylife.u17t.com/2016/08/22/1471856799_g34OsbI4PSeb.jpg","argName":"special","argValue":121,"argCon":0},{"sortName":"穿越","cover":"http://image.mylife.u17t.com/2016/08/22/1471856751_ss0I63WCzdkN.jpg","argName":"special","argValue":117,"argCon":0},{"sortName":"耽美","cover":"http://image.mylife.u17t.com/2016/08/22/1471858681_PikE4P2b4R29.jpg","argName":"theme","argValue":10},{"sortName":"恐怖","cover":"http://image.mylife.u17t.com/2016/08/22/1471857028_F78hO8o9dbuW.jpg","argName":"theme","argValue":11},{"sortName":"小说改编","cover":"http://image.mylife.u17t.com/2016/08/22/1471856898_l2y4rVvpr225.jpg","argName":"special","argValue":128,"argCon":0},{"sortName":"恋爱","cover":"http://image.mylife.u17t.com/2016/08/22/1471860696_4D98T4m08984.jpg","argName":"theme","argValue":4},{"sortName":"魔幻","cover":"http://image.mylife.u17t.com/2016/08/22/1471856737_F60y496YfAS4.jpg","argName":"theme","argValue":2},{"sortName":"搞笑","cover":"http://image.mylife.u17t.com/2016/08/22/1471858264_ttzW85KDmZgw.jpg","argName":"theme","argValue":1},{"sortName":"动作","cover":"http://image.mylife.u17t.com/2016/08/22/1471856769_Hcqqj695V911.jpg","argName":"theme","argValue":5},{"sortName":"少女","cover":"http://image.mylife.u17t.com/2016/08/22/1471856885_EMUYMYcd1zcc.jpg","argName":"cate","argValue":2},{"sortName":"科幻","cover":"http://image.mylife.u17t.com/2016/08/22/1471856818_7Gg6526tYG62.jpg","argName":"theme","argValue":6},{"sortName":"同人","cover":"http://image.mylife.u17t.com/2016/08/22/1471856871_o5VKzWWNI4Pm.jpg","argName":"theme","argValue":12},{"sortName":"生活","cover":"http://image.mylife.u17t.com/2016/08/22/1471856830_02bblPzwP5P5.jpg","argName":"theme","argValue":3},{"sortName":"少年","cover":"http://image.mylife.u17t.com/2016/08/22/1471858828_59HmtKlz999X.jpg","argName":"cate","argValue":1},{"sortName":"四格","cover":"http://image.mylife.u17t.com/2016/08/22/1471856914_aDtATdb2DT2A.jpg","argName":"cate","argValue":4}]
         * topList : [{"sortId":"127","sortName":"完结作品","cover":"http://image.mylife.u17t.com/2016/08/23/1471921671_A4WaVwI8A38c.png","extra":{"title":"完结作品","tabList":[{"argName":"serial","argValue":1,"argCon":4,"tabTitle":"今日最热"},{"argName":"serial","argValue":1,"argCon":1,"tabTitle":"人气总榜"}]}},{"sortId":"133","sortName":"彩漫作品","cover":"http://image.mylife.u17t.com/2016/08/23/1471921691_Gi5s85U5UR5n.png","extra":{"title":"彩漫作品","tabList":[{"argName":"topic","argValue":7,"argCon":4,"tabTitle":"今日最热"},{"argName":"topic","argValue":7,"argCon":1,"tabTitle":"人气总榜"}]}},{"sortId":"134","sortName":"订阅漫画","cover":"http://image.mylife.u17t.com/2016/08/23/1471921741_2k8z8K88DCl8.png","extra":{"title":"订阅漫画","tabList":[{"argName":"topic","argValue":12,"argCon":2,"tabTitle":"近日更新"},{"argName":"topic","argValue":12,"argCon":1,"tabTitle":"人气总榜"}]}},{"sortId":"119","sortName":"独家签约","cover":"http://image.mylife.u17t.com/2016/08/23/1471921651_lYgt19Ly3PsJ.png","extra":{"title":"独家签约","tabList":[{"argName":"topic","argValue":15,"argCon":4,"tabTitle":"今日最热"},{"argName":"topic","argValue":15,"argCon":1,"tabTitle":"人气总榜"}]}}]
         * recommendSearch : 镇魂街
         */

        private ReturnDataBean returnData;

        public int getStateCode() {
            return stateCode;
        }

        public void setStateCode(int stateCode) {
            this.stateCode = stateCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public ReturnDataBean getReturnData() {
            return returnData;
        }

        public void setReturnData(ReturnDataBean returnData) {
            this.returnData = returnData;
        }

        public static class ReturnDataBean {
            private String recommendSearch;
            /**
             * sortName : 古风
             * cover : http://image.mylife.u17t.com/2016/08/22/1471856799_g34OsbI4PSeb.jpg
             * argName : special
             * argValue : 121
             * argCon : 0
             */

            private List<RankingListBean> rankingList;
            /**
             * sortId : 127
             * sortName : 完结作品
             * cover : http://image.mylife.u17t.com/2016/08/23/1471921671_A4WaVwI8A38c.png
             * extra : {"title":"完结作品","tabList":[{"argName":"serial","argValue":1,"argCon":4,"tabTitle":"今日最热"},{"argName":"serial","argValue":1,"argCon":1,"tabTitle":"人气总榜"}]}
             */

            private List<TopListBean> topList;

            public String getRecommendSearch() {
                return recommendSearch;
            }

            public void setRecommendSearch(String recommendSearch) {
                this.recommendSearch = recommendSearch;
            }

            public List<RankingListBean> getRankingList() {
                return rankingList;
            }

            public void setRankingList(List<RankingListBean> rankingList) {
                this.rankingList = rankingList;
            }

            public List<TopListBean> getTopList() {
                return topList;
            }

            public void setTopList(List<TopListBean> topList) {
                this.topList = topList;
            }

            public static class RankingListBean {
                private String sortName;
                private String cover;
                private String argName;
                private int argValue;

                public String getSortName() {
                    return sortName;
                }

                public void setSortName(String sortName) {
                    this.sortName = sortName;
                }

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
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

            }

            public static class TopListBean {
                private String sortId;
                private String sortName;
                private String cover;
                /**
                 * title : 完结作品
                 * tabList : [{"argName":"serial","argValue":1,"argCon":4,"tabTitle":"今日最热"},{"argName":"serial","argValue":1,"argCon":1,"tabTitle":"人气总榜"}]
                 */

                private ExtraBean extra;

                public String getSortId() {
                    return sortId;
                }

                public void setSortId(String sortId) {
                    this.sortId = sortId;
                }

                public String getSortName() {
                    return sortName;
                }

                public void setSortName(String sortName) {
                    this.sortName = sortName;
                }

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public ExtraBean getExtra() {
                    return extra;
                }

                public void setExtra(ExtraBean extra) {
                    this.extra = extra;
                }

                public static class ExtraBean implements Serializable {
                    private String title;
                    /**
                     * argName : serial
                     * argValue : 1
                     * argCon : 4
                     * tabTitle : 今日最热
                     */

                    private List<TabListBean> tabList;

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

                    public static class TabListBean {
                        private String argName;
                        private int argValue;
                        private String tabTitle;
                        public int argCon;

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
            }
        }
    }
}
