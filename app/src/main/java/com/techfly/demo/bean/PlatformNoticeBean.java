package com.techfly.demo.bean;

import java.io.Serializable;
import java.util.List;

public class PlatformNoticeBean implements Serializable {


    /**
     * data : [{"content":"三分类收代理费那事了对方","title":"测试公告1！"},{"content":"测试公告AA！！","title":"测试公告2！"}]
     * code : 000
     */

    private String code;
    /**
     * content : 三分类收代理费那事了对方
     * title : 测试公告1！
     */

    private List<DataEntity> data;

    public void setCode(String code) {
        this.code = code;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String content;
        private String title;

        public DataEntity(String title, String content) {
            this.title = title;
            this.content = content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public String getTitle() {
            return title;
        }
    }
}
