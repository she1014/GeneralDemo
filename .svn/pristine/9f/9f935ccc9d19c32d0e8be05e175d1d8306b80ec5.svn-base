package com.techfly.demo.bean;

import java.util.List;

/**
 * 洗衣计价
 */
public class ClothTitleBean {


    /**
     * title : {"bottomTitle":"羽绒服、棉被等厚重衣物预计3-5天送回#皮衣、裘衣预计7-9天送回","topTitle":"洗衣计价","mainTitle":"专业洗衣，72小时内送回"}
     * navs : [{"categoryName":"上衣","categoryId":56},{"categoryName":"大衣外套","categoryId":57},{"categoryName":"裤装裙装","categoryId":58},{"categoryName":"配件","categoryId":59},{"categoryName":"其他","categoryId":60}]
     */

    private DataEntity data;
    /**
     * data : {"title":{"bottomTitle":"羽绒服、棉被等厚重衣物预计3-5天送回#皮衣、裘衣预计7-9天送回","topTitle":"洗衣计价","mainTitle":"专业洗衣，72小时内送回"},"navs":[{"categoryName":"上衣","categoryId":56},{"categoryName":"大衣外套","categoryId":57},{"categoryName":"裤装裙装","categoryId":58},{"categoryName":"配件","categoryId":59},{"categoryName":"其他","categoryId":60}]}
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
         * bottomTitle : 羽绒服、棉被等厚重衣物预计3-5天送回#皮衣、裘衣预计7-9天送回
         * topTitle : 洗衣计价
         * mainTitle : 专业洗衣，72小时内送回
         */

        private TitleEntity title;
        /**
         * categoryName : 上衣
         * categoryId : 56
         */

        private List<NavsEntity> navs;

        public void setTitle(TitleEntity title) {
            this.title = title;
        }

        public void setNavs(List<NavsEntity> navs) {
            this.navs = navs;
        }

        public TitleEntity getTitle() {
            return title;
        }

        public List<NavsEntity> getNavs() {
            return navs;
        }

        public static class TitleEntity {
            private String bottomTitle;
            private String topTitle;
            private String mainTitle;

            public void setBottomTitle(String bottomTitle) {
                this.bottomTitle = bottomTitle;
            }

            public void setTopTitle(String topTitle) {
                this.topTitle = topTitle;
            }

            public void setMainTitle(String mainTitle) {
                this.mainTitle = mainTitle;
            }

            public String getBottomTitle() {
                return bottomTitle;
            }

            public String getTopTitle() {
                return topTitle;
            }

            public String getMainTitle() {
                return mainTitle;
            }
        }

        public static class NavsEntity {
            private String categoryName;
            private int categoryId;

            public void setCategoryName(String categoryName) {
                this.categoryName = categoryName;
            }

            public void setCategoryId(int categoryId) {
                this.categoryId = categoryId;
            }

            public String getCategoryName() {
                return categoryName;
            }

            public int getCategoryId() {
                return categoryId;
            }
        }
    }
}
