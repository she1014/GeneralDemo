package com.techfly.demo.bean;

import java.io.Serializable;

public class ShopBaseBean implements Serializable {


    /**
     * avatar : http://114.55.250.185:80/weishop_app/uploads/imgs/UPLOAD_AVATAR/2016092910045737.png
     * freight_info : {"point":1,"money":8}
     * dayProfit :
     * monthProfit : 93.0
     * shop_info : {"id":78,"shopname":"测试店铺","mobile":"123456789"}
     */

    private DataEntity data;
    /**
     * data : {"avatar":"http://114.55.250.185:80/weishop_app/uploads/imgs/UPLOAD_AVATAR/2016092910045737.png","freight_info":{"point":1,"money":8},"dayProfit":"","monthProfit":93,"shop_info":{"id":78,"shopname":"测试店铺","mobile":"123456789"}}
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
        private String avatar;
        /**
         * point : 1.0
         * money : 8.0
         */

        private FreightInfoEntity freight_info;
        private String dayProfit;
        private String monthProfit;
        /**
         * id : 78
         * shopname : 测试店铺
         * mobile : 123456789
         */

        private ShopInfoEntity shop_info;

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public void setFreight_info(FreightInfoEntity freight_info) {
            this.freight_info = freight_info;
        }

        public void setDayProfit(String dayProfit) {
            this.dayProfit = dayProfit;
        }

        public void setMonthProfit(String monthProfit) {
            this.monthProfit = monthProfit;
        }

        public void setShop_info(ShopInfoEntity shop_info) {
            this.shop_info = shop_info;
        }

        public String getAvatar() {
            return avatar;
        }

        public FreightInfoEntity getFreight_info() {
            return freight_info;
        }

        public String getDayProfit() {
            return dayProfit;
        }

        public String getMonthProfit() {
            return monthProfit;
        }

        public ShopInfoEntity getShop_info() {
            return shop_info;
        }

        public static class FreightInfoEntity {
            private double point;
            private double money;

            public void setPoint(double point) {
                this.point = point;
            }

            public void setMoney(double money) {
                this.money = money;
            }

            public double getPoint() {
                return point;
            }

            public double getMoney() {
                return money;
            }
        }

        public static class ShopInfoEntity {
            private int id;
            private String shopname;
            private String mobile;

            public void setId(int id) {
                this.id = id;
            }

            public void setShopname(String shopname) {
                this.shopname = shopname;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public int getId() {
                return id;
            }

            public String getShopname() {
                return shopname;
            }

            public String getMobile() {
                return mobile;
            }
        }
    }
}
