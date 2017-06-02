package com.techfly.demo.bean;

import java.io.Serializable;
import java.util.List;

public class GoodReviewsBean implements Serializable{


    /**
     * datas : [{"content":"还不错","id":56,"last_reply":"哈哈哈","nickname":"我是坑逼，我怕谁","create_time":"2016-07-16 14:08:01","images":"http://192.168.0.100:8025/fresh_app/uploads/imgs/order_review/2016071614080119.jpg","mark":5,"code":"2016071600030190","can_review":0,"mobile":"17705502495"},{"content":"咔咔水果店测试","id":54,"last_reply":"谢谢惠顾","nickname":"咔咔","create_time":"2016-07-16 11:59:44","images":"","mark":5,"code":"2016071600030189","can_review":0,"mobile":"18205183644"},{"content":"还可以","id":47,"last_reply":"哈哈","nickname":"咔咔","create_time":"2016-07-14 19:38:45","images":"http://120.26.53.241:8025/fresh_app/uploads/imgs/order_review/2016071419384425.jpg http://120.26.53.241:8025/fresh_app/uploads/imgs/order_review/2016071419384525.jpg","mark":5,"code":"2016071400030182","can_review":0,"mobile":"18205183644"}]
     * pageSize : 10
     * pageNumber : 1
     * totalRecord : 3
     * totalPage : 1
     */

    private DataEntity data;
    /**
     * data : {"datas":[{"content":"还不错","id":56,"last_reply":"哈哈哈","nickname":"我是坑逼，我怕谁","create_time":"2016-07-16 14:08:01","images":"http://192.168.0.100:8025/fresh_app/uploads/imgs/order_review/2016071614080119.jpg","mark":5,"code":"2016071600030190","can_review":0,"mobile":"17705502495"},{"content":"咔咔水果店测试","id":54,"last_reply":"谢谢惠顾","nickname":"咔咔","create_time":"2016-07-16 11:59:44","images":"","mark":5,"code":"2016071600030189","can_review":0,"mobile":"18205183644"},{"content":"还可以","id":47,"last_reply":"哈哈","nickname":"咔咔","create_time":"2016-07-14 19:38:45","images":"http://120.26.53.241:8025/fresh_app/uploads/imgs/order_review/2016071419384425.jpg http://120.26.53.241:8025/fresh_app/uploads/imgs/order_review/2016071419384525.jpg","mark":5,"code":"2016071400030182","can_review":0,"mobile":"18205183644"}],"pageSize":10,"pageNumber":1,"totalRecord":3,"totalPage":1}
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
         * content : 还不错
         * id : 56
         * last_reply : 哈哈哈
         * nickname : 我是坑逼，我怕谁
         * create_time : 2016-07-16 14:08:01
         * images : http://192.168.0.100:8025/fresh_app/uploads/imgs/order_review/2016071614080119.jpg
         * mark : 5.0
         * code : 2016071600030190
         * can_review : 0
         * mobile : 17705502495
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

        public static class DatasEntity implements  Serializable{
            private String content;
            private int id;
            private String last_reply;
            private String nickname;
            private String create_time;
            private String images;
            private double mark;
            private double review_mark;
            private String code;
            private int can_review;
            private int user_id;
            private String mobile;

            public void setContent(String content) {
                this.content = content;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setLast_reply(String last_reply) {
                this.last_reply = last_reply;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public void setImages(String images) {
                this.images = images;
            }

            public void setMark(double mark) {
                this.mark = mark;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public void setCan_review(int can_review) {
                this.can_review = can_review;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getContent() {
                return content;
            }

            public int getId() {
                return id;
            }

            public String getLast_reply() {
                return last_reply;
            }

            public String getNickname() {
                return nickname;
            }

            public String getCreate_time() {
                return create_time;
            }

            public String getImages() {
                return images;
            }

            public double getMark() {
                return mark;
            }

            public String getCode() {
                return code;
            }

            public int getCan_review() {
                return can_review;
            }

            public String getMobile() {
                return mobile;
            }

            public double getReview_mark() {
                return review_mark;
            }

            public void setReview_mark(double review_mark) {
                this.review_mark = review_mark;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }
        }
    }
}
