package com.techfly.demo.bean;

/**
 * Created by wang on 2016/3/31.
 */
public class UpdataVersionBean {


    /**
     * id : 1
     * target : 客户端
     * comment : #1.升级描述A#2.升级描述B
     * must : 0  //0-自愿
     * url : 1
     * version : 2
     */

    private DataEntity data;
    /**
     * data : {"id":1,"target":"客户端","comment":"#1.升级描述A#2.升级描述B","must":0,"url":"1","version":2}
     * code : 000
     */

    private String code;

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataEntity getData() {
        return data;
    }

    public String getCode() {
        return code;
    }

    public static class DataEntity {
        private int id;
        private String target;
        private String comment;
        private int must;
        private String url;
        private int version;

        public void setId(int id) {
            this.id = id;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public void setMust(int must) {
            this.must = must;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public int getId() {
            return id;
        }

        public String getTarget() {
            return target;
        }

        public String getComment() {
            return comment;
        }

        public int getMust() {
            return must;
        }

        public String getUrl() {
            return url;
        }

        public int getVersion() {
            return version;
        }
    }
}
