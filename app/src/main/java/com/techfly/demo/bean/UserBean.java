package com.techfly.demo.bean;

/**
 * 手机验证码登陆,User
 */
public class UserBean {


    /**
     * lt_token : ddb0e951aa37093a59551a305b73955d
     * shop_id : 67
     * lt_id : 26
     * shopname : 宜阁店铺
     * avatar :
     */

    private DataEntity data;
    /**
     * data : {"lt_token":"ddb0e951aa37093a59551a305b73955d","shop_id":67,"lt_id":26,"shopname":"宜阁店铺","avatar":""}
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
        private String lt_token;
        private int shop_id;
        private int lt_id;
        private String shopname;
        private String avatar;

        public void setLt_token(String lt_token) {
            this.lt_token = lt_token;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        public void setLt_id(int lt_id) {
            this.lt_id = lt_id;
        }

        public void setShopname(String shopname) {
            this.shopname = shopname;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getLt_token() {
            return lt_token;
        }

        public int getShop_id() {
            return shop_id;
        }

        public int getLt_id() {
            return lt_id;
        }

        public String getShopname() {
            return shopname;
        }

        public String getAvatar() {
            return avatar;
        }
    }
}
