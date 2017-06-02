package com.techfly.demo.bean;

import java.util.List;

/**
 * Created by 作者wang on 2016/10/26.
 */
public class LuckyInfoBean {


    /**
     * luck_draw_area : [{"id":5,"area_name":"特等奖","goods_name":"10个积分","code":"大转盘积分奖励","points":3},{"id":6,"area_name":"二等奖","goods_name":"10元代金券","code":{},"points":{}},{"id":7,"area_name":"三等奖","goods_name":"谢谢参与","code":{},"points":{}},{"id":8,"area_name":"四等奖","goods_name":"5元代金券","code":"大转盘积分奖励","points":66},{"id":9,"area_name":"五等奖","goods_name":"十个积分","code":"大转盘积分奖励","points":20},{"id":10,"area_name":"六等奖","goods_name":"30枚土鸡蛋","code":"大转盘积分奖励","points":10},{"id":11,"area_name":"谢谢参与","goods_name":"5积分","code":{},"points":{}},{"id":12,"area_name":"谢谢参与","goods_name":"谢谢参与","code":{},"points":{}}]
     * win_person : {"datas":[{"nickname":"13655595038","goods_name":"谢谢参与","code":{},"points":{},"mobile":"13655595038"},{"nickname":"13655595038","goods_name":"5积分","code":{},"points":{},"mobile":"13655595038"},{"nickname":"13655595038","goods_name":"谢谢参与","code":{},"points":{},"mobile":"13655595038"},{"nickname":"13655595038","goods_name":"5积分","code":{},"points":{},"mobile":"13655595038"},{"nickname":"13655595038","goods_name":"十个积分","code":"大转盘积分奖励","points":20,"mobile":"13655595038"},{"nickname":"13655595038","goods_name":"十个积分","code":"大转盘积分奖励","points":20,"mobile":"13655595038"}],"pageSize":10,"pageNumber":1,"totalRecord":6,"totalPage":1}
     * luck_draw_rule : {"img":"http://121.41.11.52:80/fresh2/uploads/imgs/2016103116283829.png","points":10,"info":"积分抽奖"}
     */

    private DataEntity data;
    /**
     * data : {"luck_draw_area":[{"id":5,"area_name":"特等奖","goods_name":"10个积分","code":"大转盘积分奖励","points":3},{"id":6,"area_name":"二等奖","goods_name":"10元代金券","code":{},"points":{}},{"id":7,"area_name":"三等奖","goods_name":"谢谢参与","code":{},"points":{}},{"id":8,"area_name":"四等奖","goods_name":"5元代金券","code":"大转盘积分奖励","points":66},{"id":9,"area_name":"五等奖","goods_name":"十个积分","code":"大转盘积分奖励","points":20},{"id":10,"area_name":"六等奖","goods_name":"30枚土鸡蛋","code":"大转盘积分奖励","points":10},{"id":11,"area_name":"谢谢参与","goods_name":"5积分","code":{},"points":{}},{"id":12,"area_name":"谢谢参与","goods_name":"谢谢参与","code":{},"points":{}}],"win_person":{"datas":[{"nickname":"13655595038","goods_name":"谢谢参与","code":{},"points":{},"mobile":"13655595038"},{"nickname":"13655595038","goods_name":"5积分","code":{},"points":{},"mobile":"13655595038"},{"nickname":"13655595038","goods_name":"谢谢参与","code":{},"points":{},"mobile":"13655595038"},{"nickname":"13655595038","goods_name":"5积分","code":{},"points":{},"mobile":"13655595038"},{"nickname":"13655595038","goods_name":"十个积分","code":"大转盘积分奖励","points":20,"mobile":"13655595038"},{"nickname":"13655595038","goods_name":"十个积分","code":"大转盘积分奖励","points":20,"mobile":"13655595038"}],"pageSize":10,"pageNumber":1,"totalRecord":6,"totalPage":1},"luck_draw_rule":{"img":"http://121.41.11.52:80/fresh2/uploads/imgs/2016103116283829.png","points":10,"info":"积分抽奖"}}
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
         * datas : [{"nickname":"13655595038","goods_name":"谢谢参与","code":{},"points":{},"mobile":"13655595038"},{"nickname":"13655595038","goods_name":"5积分","code":{},"points":{},"mobile":"13655595038"},{"nickname":"13655595038","goods_name":"谢谢参与","code":{},"points":{},"mobile":"13655595038"},{"nickname":"13655595038","goods_name":"5积分","code":{},"points":{},"mobile":"13655595038"},{"nickname":"13655595038","goods_name":"十个积分","code":"大转盘积分奖励","points":20,"mobile":"13655595038"},{"nickname":"13655595038","goods_name":"十个积分","code":"大转盘积分奖励","points":20,"mobile":"13655595038"}]
         * pageSize : 10
         * pageNumber : 1
         * totalRecord : 6
         * totalPage : 1
         */

        private WinPersonEntity win_person;
        /**
         * img : http://121.41.11.52:80/fresh2/uploads/imgs/2016103116283829.png
         * points : 10.0
         * info : 积分抽奖
         */

        private LuckDrawRuleEntity luck_draw_rule;
        /**
         * id : 5
         * area_name : 特等奖
         * goods_name : 10个积分
         * code : 大转盘积分奖励
         * points : 3.0
         */

        private List<LuckDrawAreaEntity> luck_draw_area;

        public void setWin_person(WinPersonEntity win_person) {
            this.win_person = win_person;
        }

        public void setLuck_draw_rule(LuckDrawRuleEntity luck_draw_rule) {
            this.luck_draw_rule = luck_draw_rule;
        }

        public void setLuck_draw_area(List<LuckDrawAreaEntity> luck_draw_area) {
            this.luck_draw_area = luck_draw_area;
        }

        public WinPersonEntity getWin_person() {
            return win_person;
        }

        public LuckDrawRuleEntity getLuck_draw_rule() {
            return luck_draw_rule;
        }

        public List<LuckDrawAreaEntity> getLuck_draw_area() {
            return luck_draw_area;
        }

        public static class WinPersonEntity {
            private int pageSize;
            private int pageNumber;
            private int totalRecord;
            private int totalPage;
            /**
             * nickname : 13655595038
             * goods_name : 谢谢参与
             * code : {}
             * points : {}
             * mobile : 13655595038
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
                private String nickname;
                private String goods_name;
                private String mobile;

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public void setGoods_name(String goods_name) {
                    this.goods_name = goods_name;
                }

                public void setMobile(String mobile) {
                    this.mobile = mobile;
                }

                public String getNickname() {
                    return nickname;
                }

                public String getGoods_name() {
                    return goods_name;
                }

                public String getMobile() {
                    return mobile;
                }
            }
        }

        public static class LuckDrawRuleEntity {
            private String img;
            private String points;
            private String info;

            public void setImg(String img) {
                this.img = img;
            }

            public void setPoints(String points) {
                this.points = points;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public String getImg() {
                return img;
            }

            public String getPoints() {
                return points;
            }

            public String getInfo() {
                return info;
            }
        }

        public static class LuckDrawAreaEntity {
            private int id;
            private String area_name;
            private String goods_name;
            private String code;
            private String points;

            public void setId(int id) {
                this.id = id;
            }

            public void setArea_name(String area_name) {
                this.area_name = area_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public void setPoints(String points) {
                this.points = points;
            }

            public int getId() {
                return id;
            }

            public String getArea_name() {
                return area_name;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public String getCode() {
                return code;
            }

            public String getPoints() {
                return points;
            }
        }
    }
}
