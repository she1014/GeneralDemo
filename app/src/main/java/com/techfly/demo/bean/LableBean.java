package com.techfly.demo.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 标签
 */

public class LableBean implements Serializable{

    public String id;
    public String name;

    /**
     * state : 1
     * data : [{"id":"1","name":"早餐"},{"id":"2","name":"正餐"},{"id":"3","name":"快餐"},{"id":"4","name":"家常"},{"id":"6","name":"甜品"},{"id":"7","name":"烘焙"},{"id":"8","name":"卤味"},{"id":"9","name":"特色"},{"id":"11","name":"外卖"},{"id":"12","name":"饮品"},{"id":"13","name":"面食"},{"id":"14","name":"创意"}]
     */

    private int state;
    /**
     * id : 1
     * name : 早餐
     */

    private List<DataEntity> data;

    public LableBean() {

    }

    public LableBean(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public int getState() {
        return state;
    }

    public List<DataEntity> getData() {
        return data;
    }


    public static class DataEntity {
        private String id;
        private String name;

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
