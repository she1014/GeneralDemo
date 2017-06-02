package com.techfly.demo.bean;

import java.util.List;

public class ShopRefundOrderListBean {


    /**
     * datas : [{"address":"安徽省芜湖市鸠江区 咯微信","order_time":"2016-09-21 20:05:31","reason":"卖家发错货","name":"罗运人","money":15,"refund_time":"2016-09-24 17:34:00","images":"http://120.26.53.241:8025/fresh_app/uploads/imgs/after_sale/2016081916475735.jpg","refund_status":"申请退款","code":"147445953101800029","order_id":301,"mobile":"18130603619"}]
     * pageSize : 10
     * pageNumber : 1
     * totalRecord : 1
     * totalPage : 1
     */

    private DataEntity data;
    /**
     * data : {"datas":[{"address":"安徽省芜湖市鸠江区 咯微信","order_time":"2016-09-21 20:05:31","reason":"卖家发错货","name":"罗运人","money":15,"refund_time":"2016-09-24 17:34:00","images":"http://120.26.53.241:8025/fresh_app/uploads/imgs/after_sale/2016081916475735.jpg","refund_status":"申请退款","code":"147445953101800029","order_id":301,"mobile":"18130603619"}],"pageSize":10,"pageNumber":1,"totalRecord":1,"totalPage":1}
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
         * address : 安徽省芜湖市鸠江区 咯微信
         * order_time : 2016-09-21 20:05:31
         * reason : 卖家发错货
         * name : 罗运人
         * money : 15.0
         * refund_time : 2016-09-24 17:34:00
         * images : http://120.26.53.241:8025/fresh_app/uploads/imgs/after_sale/2016081916475735.jpg
         * refund_status : 申请退款
         * code : 147445953101800029
         * order_id : 301
         * mobile : 18130603619
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
            private String address;
            private String order_time;
            private String reason;
            private String name;
            private String money;
            private String refund_time;
            private String images;
            private String refund_status;
            private String code;
            private int order_id;
            private String mobile;

            public void setAddress(String address) {
                this.address = address;
            }

            public void setOrder_time(String order_time) {
                this.order_time = order_time;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public void setRefund_time(String refund_time) {
                this.refund_time = refund_time;
            }

            public void setImages(String images) {
                this.images = images;
            }

            public void setRefund_status(String refund_status) {
                this.refund_status = refund_status;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getAddress() {
                return address;
            }

            public String getOrder_time() {
                return order_time;
            }

            public String getReason() {
                return reason;
            }

            public String getName() {
                return name;
            }

            public String getMoney() {
                return money;
            }

            public String getRefund_time() {
                return refund_time;
            }

            public String getImages() {
                return images;
            }

            public String getRefund_status() {
                return refund_status;
            }

            public String getCode() {
                return code;
            }

            public int getOrder_id() {
                return order_id;
            }

            public String getMobile() {
                return mobile;
            }
        }
    }
}
