package com.techfly.demo.bean;

import java.io.Serializable;

public class GoodsListDetailBean implements Serializable{


    /**
     * sell_num : 0
     * title : 博文扣带商务记事本笔记本本子文具日记
     * price : 14.8
     * rest_num : 124
     * market_price : 25.9
     * feature_labels : 好用好些耐磨
     * goods_id : 21
     * price_label : 优惠价
     * images : http://192.168.1.86:80/fresh2/uploads/imgs/201606231710416.JPG
     * mark : 5.0
     * monthly_sales : 0
     */

    private DataEntity data;
    /**
     * data : {"sell_num":0,"title":"博文扣带商务记事本笔记本本子文具日记","price":14.8,"rest_num":124,"market_price":"25.9","feature_labels":"好用好些耐磨","goods_id":21,"price_label":"优惠价","images":"http://192.168.1.86:80/fresh2/uploads/imgs/201606231710416.JPG","mark":5,"monthly_sales":0}
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
        private int sell_num;
        private String title;
        private double price;
        private int rest_num;
        private String market_price;
        private String feature_labels;
        private int goods_id;
        private String price_label;
        private String images;
        private double mark;
        private int monthly_sales;

        public void setSell_num(int sell_num) {
            this.sell_num = sell_num;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public void setRest_num(int rest_num) {
            this.rest_num = rest_num;
        }

        public void setMarket_price(String market_price) {
            this.market_price = market_price;
        }

        public void setFeature_labels(String feature_labels) {
            this.feature_labels = feature_labels;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public void setPrice_label(String price_label) {
            this.price_label = price_label;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public void setMark(double mark) {
            this.mark = mark;
        }

        public void setMonthly_sales(int monthly_sales) {
            this.monthly_sales = monthly_sales;
        }

        public int getSell_num() {
            return sell_num;
        }

        public String getTitle() {
            return title;
        }

        public double getPrice() {
            return price;
        }

        public int getRest_num() {
            return rest_num;
        }

        public String getMarket_price() {
            return market_price;
        }

        public String getFeature_labels() {
            return feature_labels;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public String getPrice_label() {
            return price_label;
        }

        public String getImages() {
            return images;
        }

        public double getMark() {
            return mark;
        }

        public int getMonthly_sales() {
            return monthly_sales;
        }
    }
}
