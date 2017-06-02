package com.techfly.demo.bean;

/**
 * Created by wang on 2016/3/31.
 */
public class CheckVersionBean {

    /**
     * message : {}
     * log :
     1.新增充值卡功能，多充多送。
     2.全新的登录方式，无需注册，验证码登录。
     * url : http://121.43.158.189/liu/src/app/ltt230.apk
     * version : {}
     */
    private DataEntity data;
    /**
     * data : {"message":{},"log":"\n1.新增充值卡功能，多充多送。\n2.全新的登录方式，无需注册，验证码登录。","url":"http://121.43.158.189/liu/src/app/ltt230.apk","version":{}}
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
        private MessageEntity message;
        private String log;
        private String url;
        private VersionEntity version;

        public void setMessage(MessageEntity message) {
            this.message = message;
        }

        public void setLog(String log) {
            this.log = log;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setVersion(VersionEntity version) {
            this.version = version;
        }

        public MessageEntity getMessage() {
            return message;
        }

        public String getLog() {
            return log;
        }

        public String getUrl() {
            return url;
        }

        public VersionEntity getVersion() {
            return version;
        }

        public static class MessageEntity {
        }

        public static class VersionEntity {
        }
    }
}
