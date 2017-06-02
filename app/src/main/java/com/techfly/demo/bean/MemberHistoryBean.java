package com.techfly.demo.bean;

import java.util.List;

public class MemberHistoryBean {


    /**
     * datas : [{"id":93,"username":"13053273332","count":0,"create_time":"2016-09-18 11:34:07","mobile":"13053273332"},{"id":92,"username":"18130603619","count":0,"create_time":"2016-09-14 13:54:30","mobile":"18130603619"},{"id":89,"username":"17755350505","count":0,"create_time":"2016-09-14 10:32:29","mobile":"17755350505"}]
     * pageSize : 10
     * pageNumber : 1
     * totalRecord : 3
     * totalPage : 1
     */

    private DataEntity data;
    /**
     * data : {"datas":[{"id":93,"username":"13053273332","count":0,"create_time":"2016-09-18 11:34:07","mobile":"13053273332"},{"id":92,"username":"18130603619","count":0,"create_time":"2016-09-14 13:54:30","mobile":"18130603619"},{"id":89,"username":"17755350505","count":0,"create_time":"2016-09-14 10:32:29","mobile":"17755350505"}],"pageSize":10,"pageNumber":1,"totalRecord":3,"totalPage":1}
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
         * id : 93
         * username : 13053273332
         * count : 0
         * create_time : 2016-09-18 11:34:07
         * mobile : 13053273332
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
            private String username;
            private int count;
            private String create_time;
            private String mobile;

            public void setId(int id) {
                this.id = id;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public int getId() {
                return id;
            }

            public String getUsername() {
                return username;
            }

            public int getCount() {
                return count;
            }

            public String getCreate_time() {
                return create_time;
            }

            public String getMobile() {
                return mobile;
            }
        }
    }
}
