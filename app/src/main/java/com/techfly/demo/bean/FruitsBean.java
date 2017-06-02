package com.techfly.demo.bean;

import java.util.List;

/**
 * 水果
 */
public class FruitsBean {
    /**
     * data : [{"id":72,"level":3,"name":"提子","img":"http://192.168.0.100:8052/fresh/uploads/imgs/201606131442026.png","super_id":67},{"id":73,"level":3,"name":"柑橘","img":"http://192.168.0.100:8052/fresh/uploads/imgs/201606131442266.jpg","super_id":67},{"id":74,"level":3,"name":"荔枝","img":"http://192.168.0.100:8052/fresh/uploads/imgs/201606131442386.jpg","super_id":67}]
     * code : 000
     */

    private String code;
    /**
     * id : 72
     * level : 3
     * name : 提子
     * img : http://192.168.0.100:8052/fresh/uploads/imgs/201606131442026.png
     * super_id : 67
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
        private int level;
        private String name;
        private String img;
        private int super_id;

        public void setId(int id) {
            this.id = id;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setSuper_id(int super_id) {
            this.super_id = super_id;
        }

        public int getId() {
            return id;
        }

        public int getLevel() {
            return level;
        }

        public String getName() {
            return name;
        }

        public String getImg() {
            return img;
        }

        public int getSuper_id() {
            return super_id;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    /*private String id;
    private String pic;
    private String name;

    public FruitsBean(String id, String pic, String name) {
        this.id = id;
        this.pic = pic;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }*/
}
