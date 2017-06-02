package com.techfly.demo.bean;

import java.util.List;

/**
 * BannerDetailBean
 */
public class LogisticsDetailBean {

    /**
     * infos : [{"acceptStation":"【到件】快件到达【陕西西安航空部】,上一站是【陕西西安公司】,扫描员是【大客户称】","acceptTime":"2016-08-29 11:49:22"},{"acceptStation":"【收件】【陕西西安公司】的【西安仓储】已收件,扫描员是【西安仓储】","acceptTime":"2016-08-29 11:49:28"},{"acceptStation":"【发件】快件在【陕西西安航空部】由【黄顺利】扫描发往【湖南长沙中转部】","acceptTime":"2016-08-29 23:36:53"},{"acceptStation":"【装袋】【陕西西安航空部】已进行【装袋】扫描,袋号【900494925359】单号【3313162005775】","acceptTime":"2016-08-29 23:36:53"},{"acceptStation":"【发件】快件在【湖南长沙航空部】由【阳红梅】扫描发往【湖南长沙福元路分部】","acceptTime":"2016-08-30 22:40:04"},{"acceptStation":"【到件】快件到达【湖南长沙福元路分部】,上一站是【】,扫描员是【长沙福元路分部】","acceptTime":"2016-08-31 04:52:50"},{"acceptStation":"【派件】【湖南长沙福元路分部】的【彭牧高 手机(17773839119)】正在派件,扫描员是【长沙福元路分部】","acceptTime":"2016-08-31 11:45:04"},{"acceptStation":"【签收】已签收,签收人是:【朋友代签彭灿滨】","acceptTime":"2016-08-31 11:50:35"}]
     * no : 3313162005775
     * company : STO
     */

    private DataEntity data;
    /**
     * data : {"infos":[{"acceptStation":"【到件】快件到达【陕西西安航空部】,上一站是【陕西西安公司】,扫描员是【大客户称】","acceptTime":"2016-08-29 11:49:22"},{"acceptStation":"【收件】【陕西西安公司】的【西安仓储】已收件,扫描员是【西安仓储】","acceptTime":"2016-08-29 11:49:28"},{"acceptStation":"【发件】快件在【陕西西安航空部】由【黄顺利】扫描发往【湖南长沙中转部】","acceptTime":"2016-08-29 23:36:53"},{"acceptStation":"【装袋】【陕西西安航空部】已进行【装袋】扫描,袋号【900494925359】单号【3313162005775】","acceptTime":"2016-08-29 23:36:53"},{"acceptStation":"【发件】快件在【湖南长沙航空部】由【阳红梅】扫描发往【湖南长沙福元路分部】","acceptTime":"2016-08-30 22:40:04"},{"acceptStation":"【到件】快件到达【湖南长沙福元路分部】,上一站是【】,扫描员是【长沙福元路分部】","acceptTime":"2016-08-31 04:52:50"},{"acceptStation":"【派件】【湖南长沙福元路分部】的【彭牧高 手机(17773839119)】正在派件,扫描员是【长沙福元路分部】","acceptTime":"2016-08-31 11:45:04"},{"acceptStation":"【签收】已签收,签收人是:【朋友代签彭灿滨】","acceptTime":"2016-08-31 11:50:35"}],"no":"3313162005775","company":"STO"}
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
        private String no;
        private String company;
        /**
         * acceptStation : 【到件】快件到达【陕西西安航空部】,上一站是【陕西西安公司】,扫描员是【大客户称】
         * acceptTime : 2016-08-29 11:49:22
         */

        private List<InfosEntity> infos;

        public void setNo(String no) {
            this.no = no;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public void setInfos(List<InfosEntity> infos) {
            this.infos = infos;
        }

        public String getNo() {
            return no;
        }

        public String getCompany() {
            return company;
        }

        public List<InfosEntity> getInfos() {
            return infos;
        }

        public static class InfosEntity {
            private String acceptStation;
            private String acceptTime;

            public InfosEntity(String acceptStation,String acceptTime){
                this.acceptStation = acceptStation;
                this.acceptTime = acceptTime;
            }

            public void setAcceptStation(String acceptStation) {
                this.acceptStation = acceptStation;
            }

            public void setAcceptTime(String acceptTime) {
                this.acceptTime = acceptTime;
            }

            public String getAcceptStation() {
                return acceptStation;
            }

            public String getAcceptTime() {
                return acceptTime;
            }
        }
    }
}
