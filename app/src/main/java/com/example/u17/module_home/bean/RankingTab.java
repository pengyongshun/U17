package com.example.u17.module_home.bean;

import java.util.List;

/**
 * Created by Eline on 2016/6/15.
 */
public class RankingTab {

    /**
     * code : 1
     * data : {"stateCode":1,"message":"成功","returnData":{"rankinglist":[{"title":"月票","subTitle":"最受欢迎作品排行","cover":"http://image.mylife.u17t.com/2016/02/01/1454310915_47vAFBYb3BnP.jpg","argName":"sort","argValue":"23","rankingType":"月票值"},{"title":"点击","subTitle":"最具人气作品排行","cover":"http://image.mylife.u17t.com/2016/02/01/1454310826_uz7JrthiE1Ui.jpg","argName":"sort","argValue":"25","rankingType":"点击值"},{"title":"吐槽","subTitle":"最多槽点作品排行","cover":"http://image.mylife.u17t.com/2016/02/01/1454311000_F7X57TtfDfC7.jpg","argName":"sort","argValue":"20","rankingType":"吐槽值"},{"title":"另类","subTitle":"最独特作品排行","cover":"http://image.mylife.u17t.com/2016/02/01/1454311231_a4QhJZVyc1ur.jpg","argName":"sort","argValue":"19","rankingType":"另类值"},{"title":"恐怖","subTitle":"最吓人作品排行","cover":"http://image.mylife.u17t.com/2016/02/01/1454311617_7H7k2y5bZHbM.jpg","argName":"sort","argValue":"17","rankingType":"恐怖值"},{"title":"爆笑","subTitle":"最搞笑作品排行","cover":"http://image.mylife.u17t.com/2016/02/01/1454311832_2X0BBSt33JOS.jpg","argName":"sort","argValue":"18","rankingType":"爆笑值"},{"title":"新作","subTitle":"全新作品排行","cover":"http://image.mylife.u17t.com/2016/02/01/1454311938_Cd7EGoGP3ke3.jpg","argName":"sort","argValue":"2","rankingType":"新作值"},{"title":"感动","subTitle":"最动人作品排行","cover":"http://image.mylife.u17t.com/2016/02/01/1454312303_IoE0MC6vcXCG.jpg","argName":"sort","argValue":"15","rankingType":"感动值"}]}}
     */

    private int code;
    /**
     * stateCode : 1
     * message : 成功
     * returnData : {"rankinglist":[{"title":"月票","subTitle":"最受欢迎作品排行","cover":"http://image.mylife.u17t.com/2016/02/01/1454310915_47vAFBYb3BnP.jpg","argName":"sort","argValue":"23","rankingType":"月票值"},{"title":"点击","subTitle":"最具人气作品排行","cover":"http://image.mylife.u17t.com/2016/02/01/1454310826_uz7JrthiE1Ui.jpg","argName":"sort","argValue":"25","rankingType":"点击值"},{"title":"吐槽","subTitle":"最多槽点作品排行","cover":"http://image.mylife.u17t.com/2016/02/01/1454311000_F7X57TtfDfC7.jpg","argName":"sort","argValue":"20","rankingType":"吐槽值"},{"title":"另类","subTitle":"最独特作品排行","cover":"http://image.mylife.u17t.com/2016/02/01/1454311231_a4QhJZVyc1ur.jpg","argName":"sort","argValue":"19","rankingType":"另类值"},{"title":"恐怖","subTitle":"最吓人作品排行","cover":"http://image.mylife.u17t.com/2016/02/01/1454311617_7H7k2y5bZHbM.jpg","argName":"sort","argValue":"17","rankingType":"恐怖值"},{"title":"爆笑","subTitle":"最搞笑作品排行","cover":"http://image.mylife.u17t.com/2016/02/01/1454311832_2X0BBSt33JOS.jpg","argName":"sort","argValue":"18","rankingType":"爆笑值"},{"title":"新作","subTitle":"全新作品排行","cover":"http://image.mylife.u17t.com/2016/02/01/1454311938_Cd7EGoGP3ke3.jpg","argName":"sort","argValue":"2","rankingType":"新作值"},{"title":"感动","subTitle":"最动人作品排行","cover":"http://image.mylife.u17t.com/2016/02/01/1454312303_IoE0MC6vcXCG.jpg","argName":"sort","argValue":"15","rankingType":"感动值"}]}
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
            /**
             * title : 月票
             * subTitle : 最受欢迎作品排行
             * cover : http://image.mylife.u17t.com/2016/02/01/1454310915_47vAFBYb3BnP.jpg
             * argName : sort
             * argValue : 23
             * rankingType : 月票值
             */

            private List<RankinglistBean> rankinglist;

            public List<RankinglistBean> getRankinglist() {
                return rankinglist;
            }

            public void setRankinglist(List<RankinglistBean> rankinglist) {
                this.rankinglist = rankinglist;
            }

            public static class RankinglistBean {
                private String title;
                private String subTitle;
                private String cover;
                private String argName;
                private String argValue;
                private String rankingType;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getSubTitle() {
                    return subTitle;
                }

                public void setSubTitle(String subTitle) {
                    this.subTitle = subTitle;
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

                public String getArgValue() {
                    return argValue;
                }

                public void setArgValue(String argValue) {
                    this.argValue = argValue;
                }

                public String getRankingType() {
                    return rankingType;
                }

                public void setRankingType(String rankingType) {
                    this.rankingType = rankingType;
                }
            }
        }
    }
}
