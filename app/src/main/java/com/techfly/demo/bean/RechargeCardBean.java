package com.techfly.demo.bean;

import java.io.Serializable;
import java.util.List;

public class RechargeCardBean {


    /**
     * data : [{"id":1,"description":"充500送5","money":500,"value":505},{"id":17,"description":"测试","money":200,"value":210},{"id":18,"description":"测试充值券","money":0.01,"value":0.02}]
     * code : 000
     */

    private String code;
    /**
     * id : 1
     * description : 充500送5
     * money : 500.0
     * value : 505.0
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

    public static class DataEntity implements Serializable{
        private int id;
        private String description;
        private double money;
        private double value;

        public void setId(int id) {
            this.id = id;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public int getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }

        public double getMoney() {
            return money;
        }

        public double getValue() {
            return value;
        }
    }
}
