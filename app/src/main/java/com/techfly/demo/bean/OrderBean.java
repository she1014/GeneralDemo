package com.techfly.demo.bean;

import java.io.Serializable;
import java.util.List;


/*
 * 订单类
 */
public class OrderBean implements Serializable {


    /**
     * datas : [{"payment":"YES","count":2,"remark":"","status":"FINISHED","commentStatus":"NO","pickupTime":"2016.4.6 19:00-22:00","freight":10,"code":"RSV2016040600013","type":"洗鞋","orderId":1656,"totalMoney":0.03},{"payment":"YES","count":4,"remark":"","status":"FINISHED","commentStatus":"NO","pickupTime":"2016.4.8 09:00-11:00","freight":10,"code":"RSV2016040600011","type":"洗衣","orderId":1652,"totalMoney":7.91},{"payment":"YES","count":3,"remark":"","status":"FINISHED","commentStatus":"NO","pickupTime":"2016.4.7 15:00-17:00","freight":10,"code":"RSV2016040600010","type":"精洗","orderId":1649,"totalMoney":2.03},{"payment":"YES","count":3,"remark":"","status":"FINISHED","commentStatus":"NO","pickupTime":"2016.4.6 20:00-22:00","freight":10,"code":"RSV2016040600009","type":"奢侈品保养","orderId":1648,"totalMoney":320.01},{"payment":"YES","count":4,"remark":"","status":"FINISHED","commentStatus":"NO","pickupTime":"2016.4.6 18:00-20:00","freight":10,"code":"RSV2016040600008","type":"洗家纺","orderId":1647,"totalMoney":2.02},{"payment":"YES","count":3,"remark":"","status":"FINISHED","commentStatus":"NO","pickupTime":"2016.4.6 15:00-18:00","freight":10,"code":"RSV2016040600007","type":"洗鞋","orderId":1644,"totalMoney":1.02},{"payment":"YES","count":2,"remark":"","status":"FINISHED","commentStatus":"NO","pickupTime":"2016.4.5 22:00-01:00","freight":10,"code":"RSV2016040500035","type":"洗衣","orderId":1624,"totalMoney":1.01},{"payment":"YES","count":2,"remark":"","status":"FINISHED","commentStatus":"NO","pickupTime":"2016.4.5 10:00-13:00","freight":10,"code":"RSV2016040500001","type":"洗衣","orderId":1527,"totalMoney":0.03},{"payment":"YES","count":2,"remark":"","status":"FINISHED","commentStatus":"NO","pickupTime":"2016.4.3 09:00-11:00","freight":10,"code":"RSV2016040100014","type":"洗衣","orderId":1524,"totalMoney":0.02},{"payment":"YES","count":2,"remark":"","status":"FINISHED","commentStatus":"NO","pickupTime":"2016.4.2 11:00-13:00","freight":10,"code":"RSV2016040100013","type":"洗衣","orderId":1522,"totalMoney":0.04}]
     * pageSize : 10
     * pageNumber : 1
     * totalRecord : 14
     * totalPage : 2
     */

    private DataEntity data;
    /**
     * data : {"datas":[{"payment":"YES","count":2,"remark":"","status":"FINISHED","commentStatus":"NO","pickupTime":"2016.4.6 19:00-22:00","freight":10,"code":"RSV2016040600013","type":"洗鞋","orderId":1656,"totalMoney":0.03},{"payment":"YES","count":4,"remark":"","status":"FINISHED","commentStatus":"NO","pickupTime":"2016.4.8 09:00-11:00","freight":10,"code":"RSV2016040600011","type":"洗衣","orderId":1652,"totalMoney":7.91},{"payment":"YES","count":3,"remark":"","status":"FINISHED","commentStatus":"NO","pickupTime":"2016.4.7 15:00-17:00","freight":10,"code":"RSV2016040600010","type":"精洗","orderId":1649,"totalMoney":2.03},{"payment":"YES","count":3,"remark":"","status":"FINISHED","commentStatus":"NO","pickupTime":"2016.4.6 20:00-22:00","freight":10,"code":"RSV2016040600009","type":"奢侈品保养","orderId":1648,"totalMoney":320.01},{"payment":"YES","count":4,"remark":"","status":"FINISHED","commentStatus":"NO","pickupTime":"2016.4.6 18:00-20:00","freight":10,"code":"RSV2016040600008","type":"洗家纺","orderId":1647,"totalMoney":2.02},{"payment":"YES","count":3,"remark":"","status":"FINISHED","commentStatus":"NO","pickupTime":"2016.4.6 15:00-18:00","freight":10,"code":"RSV2016040600007","type":"洗鞋","orderId":1644,"totalMoney":1.02},{"payment":"YES","count":2,"remark":"","status":"FINISHED","commentStatus":"NO","pickupTime":"2016.4.5 22:00-01:00","freight":10,"code":"RSV2016040500035","type":"洗衣","orderId":1624,"totalMoney":1.01},{"payment":"YES","count":2,"remark":"","status":"FINISHED","commentStatus":"NO","pickupTime":"2016.4.5 10:00-13:00","freight":10,"code":"RSV2016040500001","type":"洗衣","orderId":1527,"totalMoney":0.03},{"payment":"YES","count":2,"remark":"","status":"FINISHED","commentStatus":"NO","pickupTime":"2016.4.3 09:00-11:00","freight":10,"code":"RSV2016040100014","type":"洗衣","orderId":1524,"totalMoney":0.02},{"payment":"YES","count":2,"remark":"","status":"FINISHED","commentStatus":"NO","pickupTime":"2016.4.2 11:00-13:00","freight":10,"code":"RSV2016040100013","type":"洗衣","orderId":1522,"totalMoney":0.04}],"pageSize":10,"pageNumber":1,"totalRecord":14,"totalPage":2}
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
         * payment : YES
         * count : 2
         * remark :
         * status : FINISHED
         * commentStatus : NO
         * pickupTime : 2016.4.6 19:00-22:00
         * freight : 10.0
         * code : RSV2016040600013
         * type : 洗鞋
         * orderId : 1656
         * totalMoney : 0.03
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
            private String payment;
            private int count;
            private String remark;
            private String status;
            private String commentStatus;
            private String pickupTime;
            private double freight;
            private String code;
            private String type;
            private int orderId;
            private double totalMoney;

            public void setPayment(String payment) {
                this.payment = payment;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public void setCommentStatus(String commentStatus) {
                this.commentStatus = commentStatus;
            }

            public void setPickupTime(String pickupTime) {
                this.pickupTime = pickupTime;
            }

            public void setFreight(double freight) {
                this.freight = freight;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public void setTotalMoney(double totalMoney) {
                this.totalMoney = totalMoney;
            }

            public String getPayment() {
                return payment;
            }

            public int getCount() {
                return count;
            }

            public String getRemark() {
                return remark;
            }

            public String getStatus() {
                return status;
            }

            public String getCommentStatus() {
                return commentStatus;
            }

            public String getPickupTime() {
                return pickupTime;
            }

            public double getFreight() {
                return freight;
            }

            public String getCode() {
                return code;
            }

            public String getType() {
                return type;
            }

            public int getOrderId() {
                return orderId;
            }

            public double getTotalMoney() {
                return totalMoney;
            }
        }
    }
}
