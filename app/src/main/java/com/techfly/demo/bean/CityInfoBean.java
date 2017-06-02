package com.techfly.demo.bean;

public class CityInfoBean{


    /**
     * id : 24
     * status : 1
     * name : 芜湖县
     * province : 安徽省
     * code : {}
     */

    private DataEntity data;
    /**
     * data : {"id":24,"status":1,"name":"芜湖县","province":"安徽省","code":{}}
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
        private int status;
        private String name;
        private String province;

        public void setId(int id) {
            this.id = id;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public int getId() {
            return id;
        }

        public int getStatus() {
            return status;
        }

        public String getName() {
            return name;
        }

        public String getProvince() {
            return province;
        }
    }
}
