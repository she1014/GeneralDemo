package com.techfly.demo.bean;

import java.util.List;

public class VoucherBean {


    /**
     * datas : [{"need":"26.0元","id":12832,"money":"5.0元"},{"need":"26.0元","id":12833,"money":"5.0元"}]
     * pageSize : 10
     * pageNumber : 1
     * totalRecord : 2
     * totalPage : 1
     */

    private DataEntity data;
    /**
     * data : {"datas":[{"need":"26.0元","id":12832,"money":"5.0元"},{"need":"26.0元","id":12833,"money":"5.0元"}],"pageSize":10,"pageNumber":1,"totalRecord":2,"totalPage":1}
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
         * need : 26.0元
         * id : 12832
         * money : 5.0元
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
            private String need;
            private int id;
            private String money;

            public void setNeed(String need) {
                this.need = need;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getNeed() {
                return need;
            }

            public int getId() {
                return id;
            }

            public String getMoney() {
                return money;
            }
        }
    }
}
