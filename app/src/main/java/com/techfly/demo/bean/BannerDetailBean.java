package com.techfly.demo.bean;

import java.util.List;

/**
 * BannerDetailBean
 */
public class BannerDetailBean {


    /**
     * data : [{"description":"<p style=\"text-align: center\"><img src=\"http://121.43.158.189/liuTai/ueditor/jsp/upload/image/20160228/1456625871093055673.jpg\" title=\"1456625871093055673.jpg\" alt=\"1456625871093055673.jpg\" width=\"320\" height=\"851\" border=\"0\" vspace=\"0\" style=\"width: 320px; height: 851px;\"/><\/p><p style=\"text-align: center\"><img src=\"http://121.43.158.189/liuTai/ueditor/jsp/upload/image/20160228/1456625879374048809.jpg\" title=\"1456625879374048809.jpg\" alt=\"1456625879374048809.jpg\" width=\"320\" height=\"645\" border=\"0\" vspace=\"0\" style=\"width: 320px; height: 645px;\"/><\/p><p style=\"text-align: center\"><img src=\"http://121.43.158.189/liuTai/ueditor/jsp/upload/image/20160228/1456625886626030971.jpg\" title=\"1456625886626030971.jpg\" alt=\"1456625886626030971.jpg\" width=\"320\" height=\"529\" border=\"0\" vspace=\"0\" style=\"width: 320px; height: 529px;\"/><\/p><p style=\"text-align: center\"><img src=\"http://121.43.158.189/liuTai/ueditor/jsp/upload/image/20160228/1456625894052049887.jpg\" title=\"1456625894052049887.jpg\" alt=\"1456625894052049887.jpg\" width=\"320\" height=\"525\" border=\"0\" vspace=\"0\" style=\"width: 320px; height: 525px;\"/><\/p><p style=\"text-align: center\"><img src=\"http://121.43.158.189/liuTai/ueditor/jsp/upload/image/20160228/1456625900722009678.jpg\" title=\"1456625900722009678.jpg\" alt=\"1456625900722009678.jpg\" width=\"320\" height=\"322\" border=\"0\" vspace=\"0\" style=\"width: 320px; height: 322px;\"/><\/p><p style=\"text-align: center\"><img src=\"http://121.43.158.189/liuTai/ueditor/jsp/upload/image/20160228/1456625906872027227.jpg\" title=\"1456625906872027227.jpg\" alt=\"1456625906872027227.jpg\" width=\"320\" height=\"256\" border=\"0\" vspace=\"0\" style=\"width: 320px; height: 256px;\"/><\/p><p><br/><\/p>"}]
     * code : 000
     */

    private String code;
    /**
     * description : <p style="text-align: center"><img src="http://121.43.158.189/liuTai/ueditor/jsp/upload/image/20160228/1456625871093055673.jpg" title="1456625871093055673.jpg" alt="1456625871093055673.jpg" width="320" height="851" border="0" vspace="0" style="width: 320px; height: 851px;"/></p><p style="text-align: center"><img src="http://121.43.158.189/liuTai/ueditor/jsp/upload/image/20160228/1456625879374048809.jpg" title="1456625879374048809.jpg" alt="1456625879374048809.jpg" width="320" height="645" border="0" vspace="0" style="width: 320px; height: 645px;"/></p><p style="text-align: center"><img src="http://121.43.158.189/liuTai/ueditor/jsp/upload/image/20160228/1456625886626030971.jpg" title="1456625886626030971.jpg" alt="1456625886626030971.jpg" width="320" height="529" border="0" vspace="0" style="width: 320px; height: 529px;"/></p><p style="text-align: center"><img src="http://121.43.158.189/liuTai/ueditor/jsp/upload/image/20160228/1456625894052049887.jpg" title="1456625894052049887.jpg" alt="1456625894052049887.jpg" width="320" height="525" border="0" vspace="0" style="width: 320px; height: 525px;"/></p><p style="text-align: center"><img src="http://121.43.158.189/liuTai/ueditor/jsp/upload/image/20160228/1456625900722009678.jpg" title="1456625900722009678.jpg" alt="1456625900722009678.jpg" width="320" height="322" border="0" vspace="0" style="width: 320px; height: 322px;"/></p><p style="text-align: center"><img src="http://121.43.158.189/liuTai/ueditor/jsp/upload/image/20160228/1456625906872027227.jpg" title="1456625906872027227.jpg" alt="1456625906872027227.jpg" width="320" height="256" border="0" vspace="0" style="width: 320px; height: 256px;"/></p><p><br/></p>
     */

    private List<DataEntity> data;

    public void setCode(String code) {
        this.code = code;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String description;

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
