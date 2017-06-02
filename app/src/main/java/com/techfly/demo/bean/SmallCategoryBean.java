package com.techfly.demo.bean;

import java.util.List;

/**
 * 水果
 */
public class SmallCategoryBean {


    /**
     * data : [{"id":223,"name":"小煮锅","img":""},{"id":224,"name":"品牌煮锅","img":""},{"id":229,"name":"煮锅1号","img":"http://114.55.250.185:80/weishop/uploads/imgs/147376105675400027.jpg"},{"id":230,"name":"煮锅2号","img":"http://114.55.250.185:80/weishop/uploads/imgs/147376105675400027.jpg"},{"id":231,"name":"煮锅3号","img":"http://114.55.250.185:80/weishop/uploads/imgs/147376105675400027.jpg"},{"id":235,"name":"煮锅4号","img":"http://114.55.250.185:80/weishop/uploads/imgs/147376105675400027.jpg"},{"id":236,"name":"煮锅5号","img":"http://114.55.250.185:80/weishop/uploads/imgs/147376105675400027.jpg"},{"id":237,"name":"煮锅6号","img":"http://114.55.250.185:80/weishop/uploads/imgs/147376105675400027.jpg"}]
     * code : 000
     */

    private String code;
    /**
     * id : 223
     * name : 小煮锅
     * img :
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
        private int id;
        private String name;
        private String img;

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getImg() {
            return img;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
