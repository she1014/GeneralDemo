package com.techfly.demo.bean;

import java.io.Serializable;
import java.util.List;

public class GoodDetailBean implements Serializable{
    /**
     * goods_info : {"shelf_life":"","text":"","shop_id":73,"status":"正常","feature_labels":"","img":"http://114.55.250.185:80/weishop_app/uploads/imgs/UPLOAD_TYPE_GOOD_DESIGN/2016092613505832.jpg","category_id":304,"mark":0,"monthly_sales":0,"city_id":"","id":2223,"unit":"元","title":"清风纸巾","price":4.5,"update_time":"2016-09-26 13:51:32","market_price":0,"description":"","create_time":"2016-09-26 13:51:32","images":"http://114.55.250.185:80/weishop_app/uploads/imgs/UPLOAD_TYPE_GOOD_DESIGN/2016092613505832.jpg"}
     * goods_speces : [{"id":2718,"unit":"","title":"","goods_price":{"unit":"","price":4.5,"price_id":2667},"goods_num":{"sell_num":0,"num_id":2829,"rest_num":99},"update_time":"2016-09-26 13:51:32","goods_id":2223,"create_time":"2016-09-26 13:51:32","img":"","value":""}]
     */

    private DataEntity data;
    /**
     * data : {"goods_info":{"shelf_life":"","text":"","shop_id":73,"status":"正常","feature_labels":"","img":"http://114.55.250.185:80/weishop_app/uploads/imgs/UPLOAD_TYPE_GOOD_DESIGN/2016092613505832.jpg","category_id":304,"mark":0,"monthly_sales":0,"city_id":"","id":2223,"unit":"元","title":"清风纸巾","price":4.5,"update_time":"2016-09-26 13:51:32","market_price":0,"description":"","create_time":"2016-09-26 13:51:32","images":"http://114.55.250.185:80/weishop_app/uploads/imgs/UPLOAD_TYPE_GOOD_DESIGN/2016092613505832.jpg"},"goods_speces":[{"id":2718,"unit":"","title":"","goods_price":{"unit":"","price":4.5,"price_id":2667},"goods_num":{"sell_num":0,"num_id":2829,"rest_num":99},"update_time":"2016-09-26 13:51:32","goods_id":2223,"create_time":"2016-09-26 13:51:32","img":"","value":""}]}
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
        /**
         * shelf_life :
         * text :
         * shop_id : 73
         * status : 正常
         * feature_labels :
         * img : http://114.55.250.185:80/weishop_app/uploads/imgs/UPLOAD_TYPE_GOOD_DESIGN/2016092613505832.jpg
         * category_id : 304
         * mark : 0.0
         * monthly_sales : 0
         * city_id :
         * id : 2223
         * unit : 元
         * title : 清风纸巾
         * price : 4.5
         * update_time : 2016-09-26 13:51:32
         * market_price : 0.0
         * description :
         * create_time : 2016-09-26 13:51:32
         * images : http://114.55.250.185:80/weishop_app/uploads/imgs/UPLOAD_TYPE_GOOD_DESIGN/2016092613505832.jpg
         */

        private GoodsInfoEntity goods_info;
        /**
         * id : 2718
         * unit :
         * title :
         * goods_price : {"unit":"","price":4.5,"price_id":2667}
         * goods_num : {"sell_num":0,"num_id":2829,"rest_num":99}
         * update_time : 2016-09-26 13:51:32
         * goods_id : 2223
         * create_time : 2016-09-26 13:51:32
         * img :
         * value :
         */

        private List<GoodsSpecesEntity> goods_speces;

        public void setGoods_info(GoodsInfoEntity goods_info) {
            this.goods_info = goods_info;
        }

        public void setGoods_speces(List<GoodsSpecesEntity> goods_speces) {
            this.goods_speces = goods_speces;
        }

        public GoodsInfoEntity getGoods_info() {
            return goods_info;
        }

        public List<GoodsSpecesEntity> getGoods_speces() {
            return goods_speces;
        }

        public static class GoodsInfoEntity {
            private String shelf_life;
            private String text;
            private int shop_id;
            private String status;
            private String feature_labels;
            private String img;
            private int category_id;
            private String mark;
            private int monthly_sales;
            private String city_id;
            private int id;
            private String unit;
            private String title;
            private String price;
            private String update_time;
            private String market_price;
            private String description;
            private String create_time;
            private String images;

            public void setShelf_life(String shelf_life) {
                this.shelf_life = shelf_life;
            }

            public void setText(String text) {
                this.text = text;
            }

            public void setShop_id(int shop_id) {
                this.shop_id = shop_id;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public void setFeature_labels(String feature_labels) {
                this.feature_labels = feature_labels;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public void setMark(String mark) {
                this.mark = mark;
            }

            public void setMonthly_sales(int monthly_sales) {
                this.monthly_sales = monthly_sales;
            }

            public void setCity_id(String city_id) {
                this.city_id = city_id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public void setImages(String images) {
                this.images = images;
            }

            public String getShelf_life() {
                return shelf_life;
            }

            public String getText() {
                return text;
            }

            public int getShop_id() {
                return shop_id;
            }

            public String getStatus() {
                return status;
            }

            public String getFeature_labels() {
                return feature_labels;
            }

            public String getImg() {
                return img;
            }

            public int getCategory_id() {
                return category_id;
            }

            public String getMark() {
                return mark;
            }

            public int getMonthly_sales() {
                return monthly_sales;
            }

            public String getCity_id() {
                return city_id;
            }

            public int getId() {
                return id;
            }

            public String getUnit() {
                return unit;
            }

            public String getTitle() {
                return title;
            }

            public String getPrice() {
                return price;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public String getMarket_price() {
                return market_price;
            }

            public String getDescription() {
                return description;
            }

            public String getCreate_time() {
                return create_time;
            }

            public String getImages() {
                return images;
            }
        }

        public static class GoodsSpecesEntity {
            private int id;
            private String unit;
            private String title;
            /**
             * unit :
             * price : 4.5
             * price_id : 2667
             */

            private GoodsPriceEntity goods_price;
            /**
             * sell_num : 0
             * num_id : 2829
             * rest_num : 99
             */

            private GoodsNumEntity goods_num;
            private String update_time;
            private int goods_id;
            private String create_time;
            private String img;
            private String value;

            public void setId(int id) {
                this.id = id;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setGoods_price(GoodsPriceEntity goods_price) {
                this.goods_price = goods_price;
            }

            public void setGoods_num(GoodsNumEntity goods_num) {
                this.goods_num = goods_num;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public int getId() {
                return id;
            }

            public String getUnit() {
                return unit;
            }

            public String getTitle() {
                return title;
            }

            public GoodsPriceEntity getGoods_price() {
                return goods_price;
            }

            public GoodsNumEntity getGoods_num() {
                return goods_num;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public String getCreate_time() {
                return create_time;
            }

            public String getImg() {
                return img;
            }

            public String getValue() {
                return value;
            }

            public static class GoodsPriceEntity {
                private String unit;
                private double price;
                private int price_id;

                public void setUnit(String unit) {
                    this.unit = unit;
                }

                public void setPrice(double price) {
                    this.price = price;
                }

                public void setPrice_id(int price_id) {
                    this.price_id = price_id;
                }

                public String getUnit() {
                    return unit;
                }

                public double getPrice() {
                    return price;
                }

                public int getPrice_id() {
                    return price_id;
                }
            }

            public static class GoodsNumEntity {
                private int sell_num;
                private int num_id;
                private int rest_num;

                public void setSell_num(int sell_num) {
                    this.sell_num = sell_num;
                }

                public void setNum_id(int num_id) {
                    this.num_id = num_id;
                }

                public void setRest_num(int rest_num) {
                    this.rest_num = rest_num;
                }

                public int getSell_num() {
                    return sell_num;
                }

                public int getNum_id() {
                    return num_id;
                }

                public int getRest_num() {
                    return rest_num;
                }
            }
        }
    }


    /**
     * goods_info : {"shelf_life":"","text":"市场价","shop_id":67,"status":"正常","feature_labels":"国行正品","img":"http://192.168.0.118:8080/weishop_app/uploads/imgs/UPLOAD_TYPE_GOOD_DESIGN/2016092214141426.jpg","category_id":230,"mark":0,"monthly_sales":0,"city_id":3,"id":1353,"unit":"元","title":"努比亚z9 黑色版","price":0,"update_time":"2016-09-22 14:15:30","description":"","create_time":"2016-09-22 14:15:30","images":"http://192.168.0.118:8080/weishop_app/uploads/imgs/UPLOAD_TYPE_GOOD_DESIGN/2016092214141926.jpg"}
     * goods_speces : [{"id":1433,"unit":{},"title":"","goods_price":{"unit":{},"price":1999,"price_id":1382},"goods_num":{"sell_num":0,"num_id":1545,"rest_num":100},"update_time":"2016-09-22 14:15:30","goods_id":1353,"create_time":"2016-09-22 14:15:30","img":{},"value":{}}]
     *//*

    private DataEntity data;
    *//**
     * data : {"goods_info":{"shelf_life":"","text":"市场价","shop_id":67,"status":"正常","feature_labels":"国行正品","img":"http://192.168.0.118:8080/weishop_app/uploads/imgs/UPLOAD_TYPE_GOOD_DESIGN/2016092214141426.jpg","category_id":230,"mark":0,"monthly_sales":0,"city_id":3,"id":1353,"unit":"元","title":"努比亚z9 黑色版","price":0,"update_time":"2016-09-22 14:15:30","description":"","create_time":"2016-09-22 14:15:30","images":"http://192.168.0.118:8080/weishop_app/uploads/imgs/UPLOAD_TYPE_GOOD_DESIGN/2016092214141926.jpg"},"goods_speces":[{"id":1433,"unit":{},"title":"","goods_price":{"unit":{},"price":1999,"price_id":1382},"goods_num":{"sell_num":0,"num_id":1545,"rest_num":100},"update_time":"2016-09-22 14:15:30","goods_id":1353,"create_time":"2016-09-22 14:15:30","img":{},"value":{}}]}
     * code : 000
     *//*

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
        *//**
         * shelf_life :
         * text : 市场价
         * shop_id : 67
         * status : 正常
         * feature_labels : 国行正品
         * img : http://192.168.0.118:8080/weishop_app/uploads/imgs/UPLOAD_TYPE_GOOD_DESIGN/2016092214141426.jpg
         * category_id : 230
         * mark : 0.0
         * monthly_sales : 0
         * city_id : 3
         * id : 1353
         * unit : 元
         * title : 努比亚z9 黑色版
         * price : 0.0
         * update_time : 2016-09-22 14:15:30
         * description :
         * create_time : 2016-09-22 14:15:30
         * images : http://192.168.0.118:8080/weishop_app/uploads/imgs/UPLOAD_TYPE_GOOD_DESIGN/2016092214141926.jpg
         *//*

        private GoodsInfoEntity goods_info;
        *//**
         * id : 1433
         * unit : {}
         * title :
         * goods_price : {"unit":{},"price":1999,"price_id":1382}
         * goods_num : {"sell_num":0,"num_id":1545,"rest_num":100}
         * update_time : 2016-09-22 14:15:30
         * goods_id : 1353
         * create_time : 2016-09-22 14:15:30
         * img : {}
         * value : {}
         *//*

        private List<GoodsSpecesEntity> goods_speces;

        public void setGoods_info(GoodsInfoEntity goods_info) {
            this.goods_info = goods_info;
        }

        public void setGoods_speces(List<GoodsSpecesEntity> goods_speces) {
            this.goods_speces = goods_speces;
        }

        public GoodsInfoEntity getGoods_info() {
            return goods_info;
        }

        public List<GoodsSpecesEntity> getGoods_speces() {
            return goods_speces;
        }

        public static class GoodsInfoEntity {
            private String shelf_life;
            private String text;
            private int shop_id;
            private String status;
            private String feature_labels;
            private String img;
            private int category_id;
            private String mark;
            private int monthly_sales;
            private int city_id;
            private int id;
            private String unit;
            private String title;
            private String price;
            private String update_time;
            private String description;
            private String create_time;
            private String images;

            public void setShelf_life(String shelf_life) {
                this.shelf_life = shelf_life;
            }

            public void setText(String text) {
                this.text = text;
            }

            public void setShop_id(int shop_id) {
                this.shop_id = shop_id;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public void setFeature_labels(String feature_labels) {
                this.feature_labels = feature_labels;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public void setMark(String mark) {
                this.mark = mark;
            }

            public void setMonthly_sales(int monthly_sales) {
                this.monthly_sales = monthly_sales;
            }

            public void setCity_id(int city_id) {
                this.city_id = city_id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public void setImages(String images) {
                this.images = images;
            }

            public String getShelf_life() {
                return shelf_life;
            }

            public String getText() {
                return text;
            }

            public int getShop_id() {
                return shop_id;
            }

            public String getStatus() {
                return status;
            }

            public String getFeature_labels() {
                return feature_labels;
            }

            public String getImg() {
                return img;
            }

            public int getCategory_id() {
                return category_id;
            }

            public String getMark() {
                return mark;
            }

            public int getMonthly_sales() {
                return monthly_sales;
            }

            public int getCity_id() {
                return city_id;
            }

            public int getId() {
                return id;
            }

            public String getUnit() {
                return unit;
            }

            public String getTitle() {
                return title;
            }

            public String getPrice() {
                return price;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public String getDescription() {
                return description;
            }

            public String getCreate_time() {
                return create_time;
            }

            public String getImages() {
                return images;
            }
        }

        public static class GoodsSpecesEntity {
            private int id;
            private String title;
            *//**
             * unit : {}
             * price : 1999.0
             * price_id : 1382
             *//*

            private GoodsPriceEntity goods_price;
            *//**
             * sell_num : 0
             * num_id : 1545
             * rest_num : 100
             *//*

            private GoodsNumEntity goods_num;
            private String update_time;
            private int goods_id;
            private String create_time;

            public void setId(int id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setGoods_price(GoodsPriceEntity goods_price) {
                this.goods_price = goods_price;
            }

            public void setGoods_num(GoodsNumEntity goods_num) {
                this.goods_num = goods_num;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public int getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }

            public GoodsPriceEntity getGoods_price() {
                return goods_price;
            }

            public GoodsNumEntity getGoods_num() {
                return goods_num;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public String getCreate_time() {
                return create_time;
            }

            public static class GoodsPriceEntity {
                private double price;
                private int price_id;

                public void setPrice(double price) {
                    this.price = price;
                }

                public void setPrice_id(int price_id) {
                    this.price_id = price_id;
                }

                public double getPrice() {
                    return price;
                }

                public int getPrice_id() {
                    return price_id;
                }
            }

            public static class GoodsNumEntity {
                private int sell_num;
                private int num_id;
                private int rest_num;

                public void setSell_num(int sell_num) {
                    this.sell_num = sell_num;
                }

                public void setNum_id(int num_id) {
                    this.num_id = num_id;
                }

                public void setRest_num(int rest_num) {
                    this.rest_num = rest_num;
                }

                public int getSell_num() {
                    return sell_num;
                }

                public int getNum_id() {
                    return num_id;
                }

                public int getRest_num() {
                    return rest_num;
                }
            }
        }
    }*/

}
