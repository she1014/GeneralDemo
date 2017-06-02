package com.techfly.demo.bean;

import java.util.List;
/*
* 我的推荐人
* */
public class RecommentDetailBean {

	/**
     * datas : [{"status":"已登录","date":"2016-2-22","mobile":"155****8909"}]
     * pageSize : 10
     * pageNumber : 1
     * totalRecord : 1
     * totalPage : 1
     */

    private DataEntity data;
    /**
     * data : {"datas":[{"status":"已登录","date":"2016-2-22","mobile":"155****8909"}],"pageSize":10,"pageNumber":1,"totalRecord":1,"totalPage":1}
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
        private int pageSize;
        private int pageNumber;
        private int totalRecord;
        private int totalPage;
        /**
         * status : 已登录
         * date : 2016-2-22
         * mobile : 155****8909
         */

        private List<DatasEntity> datas;

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public void setPageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
        }

        public void setTotalRecord(int totalRecord) {
            this.totalRecord = totalRecord;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public void setDatas(List<DatasEntity> datas) {
            this.datas = datas;
        }

        public int getPageSize() {
            return pageSize;
        }

        public int getPageNumber() {
            return pageNumber;
        }

        public int getTotalRecord() {
            return totalRecord;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public List<DatasEntity> getDatas() {
            return datas;
        }

        public static class DatasEntity {
            private String status;
            private String date;
            private String mobile;
            //需要删除
            public DatasEntity(String status, String date,String mobile) {

                this.status = status;
                this.date = date;
                this.mobile = mobile;
            }
            public void setStatus(String status) {
                this.status = status;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getStatus() {
                return status;
            }

            public String getDate() {
                return date;
            }

            public String getMobile() {
                return mobile;
            }
        }
    }
	
}
