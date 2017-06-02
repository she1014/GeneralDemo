package com.techfly.demo.bean;

import java.util.List;

public class ShopGoodsBean {


    /**
     * datas : [{"id":1062,"unit":"元","text":"新鲜价","price":12,"categroy_name":"煮锅","img":"http://192.168.0.118:8080/weishop_app/uploads/imgs/UPLOAD_TYPE_GOOD_DESIGN/2016092022323126.jpeg","goods_name":"苹果","mark":0,"monthly_sales":0,"goods_speces":[{"unit":"","title":"黑色","goods_num":{"sell_num":0,"rest_num":200},"img":""}]},{"id":679,"unit":"元","text":"来自山东的大苹果","price":0,"categroy_name":"","img":"http://114.55.250.185:80/weishop_app/uploads/imgs/default/avatar.jpg","goods_name":"红富士苹果","mark":0,"monthly_sales":0,"goods_speces":[{"unit":"","title":"200L,黑色","goods_num":{"sell_num":0,"rest_num":100},"img":""},{"unit":"","title":"300L,黑色","goods_num":{"sell_num":0,"rest_num":""},"img":""},{"unit":"","title":"400L,黑色","goods_num":{"sell_num":0,"rest_num":""},"img":""}]},{"id":676,"unit":"元","text":"来自山东的大苹果","price":0,"categroy_name":"","img":"http://114.55.250.185:80/weishop_app/uploads/imgs/default/avatar.jpg","goods_name":"红富士苹果","mark":0,"monthly_sales":0,"goods_speces":[{"unit":"","title":"200L,黑色","goods_num":{"sell_num":0,"rest_num":100},"img":""},{"unit":"","title":"300L,黑色","goods_num":{"sell_num":0,"rest_num":""},"img":""},{"unit":"","title":"400L,黑色","goods_num":{"sell_num":0,"rest_num":""},"img":""}]},{"id":670,"unit":"元","text":"来自山东的大苹果","price":0,"categroy_name":"","img":"http://114.55.250.185:80/weishop_app/uploads/imgs/default/avatar.jpg","goods_name":"红富士苹果","mark":0,"monthly_sales":0,"goods_speces":[]},{"id":668,"unit":"元","text":"来自山东的大苹果","price":0,"categroy_name":"","img":"http://114.55.250.185:80/weishop_app/uploads/imgs/default/avatar.jpg","goods_name":"红富士苹果","mark":0,"monthly_sales":0,"goods_speces":[]},{"id":188,"unit":"元","text":"地方","price":54,"categroy_name":"煮茶器1","img":"http://114.55.250.185:80/weishop/uploads/imgs/147384293396600012.jpg","goods_name":"风扇类型","mark":0,"monthly_sales":0,"goods_speces":[]},{"id":187,"unit":"元","text":"地方","price":79,"categroy_name":"煮茶器1","img":"http://114.55.250.185:80/weishop/uploads/imgs/147384276838700010.jpg","goods_name":"一概","mark":0,"monthly_sales":0,"goods_speces":[]},{"id":186,"unit":"元","text":"不能","price":35,"categroy_name":"煮茶器1","img":"http://114.55.250.185:80/weishop/uploads/imgs/147384269852700008.jpg","goods_name":"茶具","mark":0,"monthly_sales":0,"goods_speces":[]},{"id":185,"unit":"元","text":"发","price":56,"categroy_name":"煮茶器1","img":"http://114.55.250.185:80/weishop/uploads/imgs/147384252794700006.jpg","goods_name":"榨汁机","mark":0,"monthly_sales":0,"goods_speces":[]},{"id":184,"unit":"元","text":"多次","price":66,"categroy_name":"新颖型","img":"http://114.55.250.185:80/weishop/uploads/imgs/147384244750900003.jpg","goods_name":"咖啡机","mark":0,"monthly_sales":0,"goods_speces":[]}]
     * pageSize : 10
     * pageNumber : 1
     * totalRecord : 68
     * totalPage : 7
     */

    private DataEntity data;
    /**
     * data : {"datas":[{"id":1062,"unit":"元","text":"新鲜价","price":12,"categroy_name":"煮锅","img":"http://192.168.0.118:8080/weishop_app/uploads/imgs/UPLOAD_TYPE_GOOD_DESIGN/2016092022323126.jpeg","goods_name":"苹果","mark":0,"monthly_sales":0,"goods_speces":[{"unit":"","title":"黑色","goods_num":{"sell_num":0,"rest_num":200},"img":""}]},{"id":679,"unit":"元","text":"来自山东的大苹果","price":0,"categroy_name":"","img":"http://114.55.250.185:80/weishop_app/uploads/imgs/default/avatar.jpg","goods_name":"红富士苹果","mark":0,"monthly_sales":0,"goods_speces":[{"unit":"","title":"200L,黑色","goods_num":{"sell_num":0,"rest_num":100},"img":""},{"unit":"","title":"300L,黑色","goods_num":{"sell_num":0,"rest_num":""},"img":""},{"unit":"","title":"400L,黑色","goods_num":{"sell_num":0,"rest_num":""},"img":""}]},{"id":676,"unit":"元","text":"来自山东的大苹果","price":0,"categroy_name":"","img":"http://114.55.250.185:80/weishop_app/uploads/imgs/default/avatar.jpg","goods_name":"红富士苹果","mark":0,"monthly_sales":0,"goods_speces":[{"unit":"","title":"200L,黑色","goods_num":{"sell_num":0,"rest_num":100},"img":""},{"unit":"","title":"300L,黑色","goods_num":{"sell_num":0,"rest_num":""},"img":""},{"unit":"","title":"400L,黑色","goods_num":{"sell_num":0,"rest_num":""},"img":""}]},{"id":670,"unit":"元","text":"来自山东的大苹果","price":0,"categroy_name":"","img":"http://114.55.250.185:80/weishop_app/uploads/imgs/default/avatar.jpg","goods_name":"红富士苹果","mark":0,"monthly_sales":0,"goods_speces":[]},{"id":668,"unit":"元","text":"来自山东的大苹果","price":0,"categroy_name":"","img":"http://114.55.250.185:80/weishop_app/uploads/imgs/default/avatar.jpg","goods_name":"红富士苹果","mark":0,"monthly_sales":0,"goods_speces":[]},{"id":188,"unit":"元","text":"地方","price":54,"categroy_name":"煮茶器1","img":"http://114.55.250.185:80/weishop/uploads/imgs/147384293396600012.jpg","goods_name":"风扇类型","mark":0,"monthly_sales":0,"goods_speces":[]},{"id":187,"unit":"元","text":"地方","price":79,"categroy_name":"煮茶器1","img":"http://114.55.250.185:80/weishop/uploads/imgs/147384276838700010.jpg","goods_name":"一概","mark":0,"monthly_sales":0,"goods_speces":[]},{"id":186,"unit":"元","text":"不能","price":35,"categroy_name":"煮茶器1","img":"http://114.55.250.185:80/weishop/uploads/imgs/147384269852700008.jpg","goods_name":"茶具","mark":0,"monthly_sales":0,"goods_speces":[]},{"id":185,"unit":"元","text":"发","price":56,"categroy_name":"煮茶器1","img":"http://114.55.250.185:80/weishop/uploads/imgs/147384252794700006.jpg","goods_name":"榨汁机","mark":0,"monthly_sales":0,"goods_speces":[]},{"id":184,"unit":"元","text":"多次","price":66,"categroy_name":"新颖型","img":"http://114.55.250.185:80/weishop/uploads/imgs/147384244750900003.jpg","goods_name":"咖啡机","mark":0,"monthly_sales":0,"goods_speces":[]}],"pageSize":10,"pageNumber":1,"totalRecord":68,"totalPage":7}
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
         * id : 1062
         * unit : 元
         * text : 新鲜价
         * price : 12.0
         * categroy_name : 煮锅
         * img : http://192.168.0.118:8080/weishop_app/uploads/imgs/UPLOAD_TYPE_GOOD_DESIGN/2016092022323126.jpeg
         * goods_name : 苹果
         * mark : 0.0
         * monthly_sales : 0
         * goods_speces : [{"unit":"","title":"黑色","goods_num":{"sell_num":0,"rest_num":200},"img":""}]
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
            private int id;
            private String unit;
            private String text;
            private double price;
            private String categroy_name;
            private String img;
            private String goods_name;
            private double mark;
            private int monthly_sales;
            /**
             * unit :
             * title : 黑色
             * goods_num : {"sell_num":0,"rest_num":200}
             * img :
             */

            private List<GoodsSpecesEntity> goods_speces;

            public void setId(int id) {
                this.id = id;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public void setText(String text) {
                this.text = text;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public void setCategroy_name(String categroy_name) {
                this.categroy_name = categroy_name;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public void setMark(double mark) {
                this.mark = mark;
            }

            public void setMonthly_sales(int monthly_sales) {
                this.monthly_sales = monthly_sales;
            }

            public void setGoods_speces(List<GoodsSpecesEntity> goods_speces) {
                this.goods_speces = goods_speces;
            }

            public int getId() {
                return id;
            }

            public String getUnit() {
                return unit;
            }

            public String getText() {
                return text;
            }

            public double getPrice() {
                return price;
            }

            public String getCategroy_name() {
                return categroy_name;
            }

            public String getImg() {
                return img;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public double getMark() {
                return mark;
            }

            public int getMonthly_sales() {
                return monthly_sales;
            }

            public List<GoodsSpecesEntity> getGoods_speces() {
                return goods_speces;
            }

            public static class GoodsSpecesEntity {
                private String unit;
                private String title;
                private String img;

                public void setUnit(String unit) {
                    this.unit = unit;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getUnit() {
                    return unit;
                }

                public String getTitle() {
                    return title;
                }

                public String getImg() {
                    return img;
                }
            }
        }
    }
}
