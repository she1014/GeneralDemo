package com.techfly.demo.bean;

/**
 * BannerDetailBean
 */
public class LogisticsInfoBean {


    /**
     * logistics_no :
     * logistics_company :
     */

    private DataEntity data;
    /**
     * data : {"logistics_no":"","logistics_company":""}
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
        private String logistics_no;
        private String logistics_company;

        public void setLogistics_no(String logistics_no) {
            this.logistics_no = logistics_no;
        }

        public void setLogistics_company(String logistics_company) {
            this.logistics_company = logistics_company;
        }

        public String getLogistics_no() {
            return logistics_no;
        }

        public String getLogistics_company() {
            return logistics_company;
        }
    }
}
