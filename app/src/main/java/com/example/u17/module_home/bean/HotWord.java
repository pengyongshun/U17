package com.example.u17.module_home.bean;

import java.util.List;


public class HotWord {

    /**
     * code : 1
     * data : {"stateCode":1,"message":"成功","returnData":[{"tag":"长歌行","search_num":"230089","bgColor":"#D8577340"},{"tag":"火影","search_num":"206230","bgColor":"#D8138D7E"},{"tag":"黑瞳","search_num":"195470","bgColor":"#D85DA446"},{"tag":"海贼王","search_num":"177473","bgColor":"#D800A652"},{"tag":"十万个冷笑话","search_num":"161697","bgColor":"#D8F5702D"},{"tag":"盗墓笔记","search_num":"158199","bgColor":"#D8AF8B6B"},{"tag":"镇魂街","search_num":"132562","bgColor":"#D8F8A032"},{"tag":"夏达","search_num":"123958","bgColor":"#D89DA23E"},{"tag":"火影忍者","search_num":"119374","bgColor":"#D8784B8E"},{"tag":"端脑","search_num":"119144","bgColor":"#D8527FC2"},{"tag":"死神","search_num":"117114","bgColor":"#D87B7B93"},{"tag":"斗破苍穹","search_num":"111759","bgColor":"#D8207F9F"},{"tag":"完美世界","search_num":"105608","bgColor":"#D8E54662"},{"tag":"武动乾坤","search_num":"99258","bgColor":"#D8C0658E"},{"tag":"幻想女仆","search_num":"93018","bgColor":"#D8FD783D"},{"tag":"遮天","search_num":"90018","bgColor":"#D8CB7D57"}]}
     */

    private int code;
    /**
     * stateCode : 1
     * message : 成功
     * returnData : [{"tag":"长歌行","search_num":"230089","bgColor":"#D8577340"},{"tag":"火影","search_num":"206230","bgColor":"#D8138D7E"},{"tag":"黑瞳","search_num":"195470","bgColor":"#D85DA446"},{"tag":"海贼王","search_num":"177473","bgColor":"#D800A652"},{"tag":"十万个冷笑话","search_num":"161697","bgColor":"#D8F5702D"},{"tag":"盗墓笔记","search_num":"158199","bgColor":"#D8AF8B6B"},{"tag":"镇魂街","search_num":"132562","bgColor":"#D8F8A032"},{"tag":"夏达","search_num":"123958","bgColor":"#D89DA23E"},{"tag":"火影忍者","search_num":"119374","bgColor":"#D8784B8E"},{"tag":"端脑","search_num":"119144","bgColor":"#D8527FC2"},{"tag":"死神","search_num":"117114","bgColor":"#D87B7B93"},{"tag":"斗破苍穹","search_num":"111759","bgColor":"#D8207F9F"},{"tag":"完美世界","search_num":"105608","bgColor":"#D8E54662"},{"tag":"武动乾坤","search_num":"99258","bgColor":"#D8C0658E"},{"tag":"幻想女仆","search_num":"93018","bgColor":"#D8FD783D"},{"tag":"遮天","search_num":"90018","bgColor":"#D8CB7D57"}]
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
         * tag : 长歌行
         * search_num : 230089
         * bgColor : #D8577340
         */

        private List<ReturnDataBean> returnData;

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

        public List<ReturnDataBean> getReturnData() {
            return returnData;
        }

        public void setReturnData(List<ReturnDataBean> returnData) {
            this.returnData = returnData;
        }

        public static class ReturnDataBean {
            private String tag;
            private String search_num;
            private String bgColor;

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getSearch_num() {
                return search_num;
            }

            public void setSearch_num(String search_num) {
                this.search_num = search_num;
            }

            public String getBgColor() {
                return bgColor;
            }

            public void setBgColor(String bgColor) {
                this.bgColor = bgColor;
            }
        }
    }
}
