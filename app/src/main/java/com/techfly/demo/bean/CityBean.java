package com.techfly.demo.bean;

import java.io.Serializable;
import java.util.List;

public class CityBean implements Serializable {


    /**
     * data : [{"id":10,"is":"","name":"芜湖市"},{"id":12,"is":"","name":"合肥市"},{"id":20,"is":"","name":"常州市"},{"id":18,"is":"","name":"蚌埠市"},{"id":19,"is":"","name":"南京市"},{"id":21,"is":"","name":"徐州市"},{"id":22,"is":"","name":"武汉市"},{"id":23,"is":"","name":"马鞍山市"}]
     * code : 000
     */

    private String code;
    /**
     * id : 10
     * is :
     * name : 芜湖市
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
        private String is;
        private String name;

        public void setId(int id) {
            this.id = id;
        }

        public void setIs(String is) {
            this.is = is;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getIs() {
            return is;
        }

        public String getName() {
            return name;
        }
    }
}
