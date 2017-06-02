package com.techfly.demo.bean;

public class BookingTimeBean {


    /**
     * curTime : 2016-03-16 14:09
     * beginTime : 2016-03-16 09:00
     * endTime : 2016-03-16 21:00
     * space : 3
     */

    private DataEntity data;
    /**
     * data : {"curTime":"2016-03-16 14:09","beginTime":"2016-03-16 09:00","endTime":"2016-03-16 21:00","space":3}
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
        private String curTime;
        private String beginTime;
        private String endTime;
        private int space;

        public void setCurTime(String curTime) {
            this.curTime = curTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public void setSpace(int space) {
            this.space = space;
        }

        public String getCurTime() {
            return curTime;
        }

        public String getBeginTime() {
            return beginTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public int getSpace() {
            return space;
        }
    }
}
