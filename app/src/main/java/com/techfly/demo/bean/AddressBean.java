package com.techfly.demo.bean;

import java.util.List;

public class AddressBean {


    /**
     * datas : [{"id":2705,"isdefault":1,"address":"点击选择地址点击选择地址点击选择地址刘太太生活","name":"佘绍敏","lng":0,"lat":0,"cname":"芜湖市","mobile":"18205183644","city":10},{"id":2698,"isdefault":0,"address":"点击选择地址安徽工程大学'MSN女女","name":"1111","lng":0,"lat":0,"cname":"芜湖市","mobile":"18205183644","city":10},{"id":2697,"isdefault":0,"address":"安徽工程大学1110","name":"1010","lng":118.42122,"lat":31.34273,"cname":"芜湖市","mobile":"18205183644","city":10},{"id":2696,"isdefault":0,"address":"安徽工程大学给我说一下去","name":"999","lng":118.42122,"lat":31.34273,"cname":"芜湖市","mobile":"18205183644","city":10},{"id":2695,"isdefault":0,"address":"安徽工程大学擒贼先擒王去","name":"888","lng":118.42122,"lat":31.34273,"cname":"芜湖市","mobile":"18205183644","city":10}]
     * pageSize : 10
     * pageNumber : 1
     * totalRecord : 5
     * totalPage : 1
     */

    private DataEntity data;
    /**
     * data : {"datas":[{"id":2705,"isdefault":1,"address":"点击选择地址点击选择地址点击选择地址刘太太生活","name":"佘绍敏","lng":0,"lat":0,"cname":"芜湖市","mobile":"18205183644","city":10},{"id":2698,"isdefault":0,"address":"点击选择地址安徽工程大学'MSN女女","name":"1111","lng":0,"lat":0,"cname":"芜湖市","mobile":"18205183644","city":10},{"id":2697,"isdefault":0,"address":"安徽工程大学1110","name":"1010","lng":118.42122,"lat":31.34273,"cname":"芜湖市","mobile":"18205183644","city":10},{"id":2696,"isdefault":0,"address":"安徽工程大学给我说一下去","name":"999","lng":118.42122,"lat":31.34273,"cname":"芜湖市","mobile":"18205183644","city":10},{"id":2695,"isdefault":0,"address":"安徽工程大学擒贼先擒王去","name":"888","lng":118.42122,"lat":31.34273,"cname":"芜湖市","mobile":"18205183644","city":10}],"pageSize":10,"pageNumber":1,"totalRecord":5,"totalPage":1}
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
         * id : 2705
         * isdefault : 1
         * address : 点击选择地址点击选择地址点击选择地址刘太太生活
         * name : 佘绍敏
         * lng : 0.0
         * lat : 0.0
         * cname : 芜湖市
         * mobile : 18205183644
         * city : 10
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
            private String id;
            private int isdefault;
            private String address;
            private String name;
            private String lng;
            private String lat;
            private String cname;
            private String mobile;
            private String city;

            public void setId(String id) {
                this.id = id;
            }

            public void setIsdefault(int isdefault) {
                this.isdefault = isdefault;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public void setCname(String cname) {
                this.cname = cname;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getId() {
                return id;
            }

            public int getIsdefault() {
                return isdefault;
            }

            public String getAddress() {
                return address;
            }

            public String getName() {
                return name;
            }

            public String getLng() {
                return lng;
            }

            public String getLat() {
                return lat;
            }

            public String getCname() {
                return cname;
            }

            public String getMobile() {
                return mobile;
            }

            public String getCity() {
                return city;
            }
        }
    }
}
