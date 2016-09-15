package com.example.u17.module_home.bean;

import java.util.List;

public class InputText {

    /**
     * code : 1
     * data : {"stateCode":1,"message":"成功","returnData":[{"comic_id":14325,"name":"长歌行"},{"comic_id":93932,"name":"梦中客 长歌行同人"}]}
     */

    private int code;
    /**
     * stateCode : 1
     * message : 成功
     * returnData : [{"comic_id":14325,"name":"长歌行"},{"comic_id":93932,"name":"梦中客 长歌行同人"}]
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
         * comic_id : 14325
         * name : 长歌行
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
            private int comic_id;
            private String name;

            public int getComic_id() {
                return comic_id;
            }

            public void setComic_id(int comic_id) {
                this.comic_id = comic_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
