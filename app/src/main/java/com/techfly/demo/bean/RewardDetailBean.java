package com.techfly.demo.bean;

import java.util.List;

/*
 * 我的提成
 */
public class RewardDetailBean {

    /**
     * datas : [{"rewardMoney":"12.5","orderDate":"2016-2-18","inviterPhone":"15755122960","orderStatus":"已下单"},{"rewardMoney":"12.5","orderDate":"2016-2-18","inviterPhone":"15755122960","orderStatus":"已下单"}]
     * pageSize : 2
     * pageNumber : 1
     * totalRecord : 0
     * totalPage : 0
     */

    private DataEntity data;
    /**
     * data : {"datas":[{"rewardMoney":"12.5","orderDate":"2016-2-18","inviterPhone":"15755122960","orderStatus":"已下单"},{"rewardMoney":"12.5","orderDate":"2016-2-18","inviterPhone":"15755122960","orderStatus":"已下单"}],"pageSize":2,"pageNumber":1,"totalRecord":0,"totalPage":0}
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
         * rewardMoney : 12.5
         * orderDate : 2016-2-18
         * inviterPhone : 15755122960
         * orderStatus : 已下单
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
        	private String inviterPhone;
        	private String orderStatus;
            private String rewardMoney;
            private String orderDate;

			public DatasEntity(String inviterPhone, String orderStatus,
					String rewardMoney, String orderDate) {
				super();
				this.inviterPhone = inviterPhone;
				this.orderStatus = orderStatus;
				this.rewardMoney = rewardMoney;
				this.orderDate = orderDate;
			}

			public void setRewardMoney(String rewardMoney) {
                this.rewardMoney = rewardMoney;
            }

            public void setOrderDate(String orderDate) {
                this.orderDate = orderDate;
            }

            public void setInviterPhone(String inviterPhone) {
                this.inviterPhone = inviterPhone;
            }

            public void setOrderStatus(String orderStatus) {
                this.orderStatus = orderStatus;
            }

            public String getRewardMoney() {
                return rewardMoney;
            }

            public String getOrderDate() {
                return orderDate;
            }

            public String getInviterPhone() {
                return inviterPhone;
            }

            public String getOrderStatus() {
                return orderStatus;
            }
        }
    }
}
