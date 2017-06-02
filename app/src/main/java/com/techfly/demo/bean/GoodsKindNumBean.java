package com.techfly.demo.bean;

/**
 */
public class GoodsKindNumBean {


    /**
     * offshelf_count : 0
     * normal_count : 68
     */

    private DataEntity data;
    /**
     * data : {"offshelf_count":0,"normal_count":68}
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
        private int offshelf_count;
        private int normal_count;

        public void setOffshelf_count(int offshelf_count) {
            this.offshelf_count = offshelf_count;
        }

        public void setNormal_count(int normal_count) {
            this.normal_count = normal_count;
        }

        public int getOffshelf_count() {
            return offshelf_count;
        }

        public int getNormal_count() {
            return normal_count;
        }
    }
}
