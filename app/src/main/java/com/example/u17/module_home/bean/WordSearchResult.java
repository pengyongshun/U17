package com.example.u17.module_home.bean;

import java.util.List;


public class WordSearchResult {

    /**
     * code : 1
     * data : {"stateCode":1,"message":"成功","returnData":{"comicNum":2,"hasMore":false,"page":1,"comics":[{"comicId":14325,"cover":"http://cover2.u17i.com/2012/02/108005_1330077408_3j1MZQMJIKZ4.sbig.jpg","passChapterNum":"第118话","name":"长歌行","flag":"0","tags":["战争"],"author":"夏达","description":"玄武门之变后，唐都长安暗流汹涌。","conTag":"895202983"},{"comicId":93932,"cover":"http://cover2.u17i.com/2015/04/5071476_1428938341_C8CC7yEVyhdh.sbig.jpg","passChapterNum":"第1话","name":"梦中客 长歌行同人","flag":"0","tags":["同人"],"author":"豌豆豌","description":"《长歌行》同人，长歌X弥弥。怀念弥弥的产物。内容大概是多年后的长歌做了一个梦。","conTag":"271145"}]}}
     */

    private int code;
    /**
     * stateCode : 1
     * message : 成功
     * returnData : {"comicNum":2,"hasMore":false,"page":1,"comics":[{"comicId":14325,"cover":"http://cover2.u17i.com/2012/02/108005_1330077408_3j1MZQMJIKZ4.sbig.jpg","passChapterNum":"第118话","name":"长歌行","flag":"0","tags":["战争"],"author":"夏达","description":"玄武门之变后，唐都长安暗流汹涌。","conTag":"895202983"},{"comicId":93932,"cover":"http://cover2.u17i.com/2015/04/5071476_1428938341_C8CC7yEVyhdh.sbig.jpg","passChapterNum":"第1话","name":"梦中客 长歌行同人","flag":"0","tags":["同人"],"author":"豌豆豌","description":"《长歌行》同人，长歌X弥弥。怀念弥弥的产物。内容大概是多年后的长歌做了一个梦。","conTag":"271145"}]}
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
         * comicNum : 2
         * hasMore : false
         * page : 1
         * comics : [{"comicId":14325,"cover":"http://cover2.u17i.com/2012/02/108005_1330077408_3j1MZQMJIKZ4.sbig.jpg","passChapterNum":"第118话","name":"长歌行","flag":"0","tags":["战争"],"author":"夏达","description":"玄武门之变后，唐都长安暗流汹涌。","conTag":"895202983"},{"comicId":93932,"cover":"http://cover2.u17i.com/2015/04/5071476_1428938341_C8CC7yEVyhdh.sbig.jpg","passChapterNum":"第1话","name":"梦中客 长歌行同人","flag":"0","tags":["同人"],"author":"豌豆豌","description":"《长歌行》同人，长歌X弥弥。怀念弥弥的产物。内容大概是多年后的长歌做了一个梦。","conTag":"271145"}]
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
            private int comicNum;
            private boolean hasMore;
            private int page;
            /**
             * comicId : 14325
             * cover : http://cover2.u17i.com/2012/02/108005_1330077408_3j1MZQMJIKZ4.sbig.jpg
             * passChapterNum : 第118话
             * name : 长歌行
             * flag : 0
             * tags : ["战争"]
             * author : 夏达
             * description : 玄武门之变后，唐都长安暗流汹涌。
             * conTag : 895202983
             */

            private List<ComicsBean> comics;

            public int getComicNum() {
                return comicNum;
            }

            public void setComicNum(int comicNum) {
                this.comicNum = comicNum;
            }

            public boolean isHasMore() {
                return hasMore;
            }

            public void setHasMore(boolean hasMore) {
                this.hasMore = hasMore;
            }

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public List<ComicsBean> getComics() {
                return comics;
            }

            public void setComics(List<ComicsBean> comics) {
                this.comics = comics;
            }

            public static class ComicsBean {
                private int comicId;
                private String cover;
                private String passChapterNum;
                private String name;
                private String flag;
                private String author;
                private String description;
                private String conTag;
                private List<String> tags;

                public int getComicId() {
                    return comicId;
                }

                public void setComicId(int comicId) {
                    this.comicId = comicId;
                }

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public String getPassChapterNum() {
                    return passChapterNum;
                }

                public void setPassChapterNum(String passChapterNum) {
                    this.passChapterNum = passChapterNum;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getFlag() {
                    return flag;
                }

                public void setFlag(String flag) {
                    this.flag = flag;
                }

                public String getAuthor() {
                    return author;
                }

                public void setAuthor(String author) {
                    this.author = author;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getConTag() {
                    return conTag;
                }

                public void setConTag(String conTag) {
                    this.conTag = conTag;
                }

                public List<String> getTags() {
                    return tags;
                }

                public void setTags(List<String> tags) {
                    this.tags = tags;
                }
            }
        }
    }
}
