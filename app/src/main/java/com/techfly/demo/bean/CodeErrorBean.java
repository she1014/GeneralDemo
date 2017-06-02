package com.techfly.demo.bean;

import com.google.gson.Gson;

/**
 * 验证码错误
 */
public class CodeErrorBean {

    /**
     * status : error
     * error : {"code":"1007","error":"CODE_TIME_OUT","message":"验证码错误"}
     */

    private String status;
    /**
     * code : 1007
     * error : CODE_TIME_OUT
     * message : 验证码错误
     */

    private ErrorEntity error;

    public static CodeErrorBean objectFromData(String str) {

        return new Gson().fromJson(str, CodeErrorBean.class);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setError(ErrorEntity error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public ErrorEntity getError() {
        return error;
    }

    public static class ErrorEntity {
        private String code;
        private String error;
        private String message;

        public static ErrorEntity objectFromData(String str) {

            return new Gson().fromJson(str, ErrorEntity.class);
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void setError(String error) {
            this.error = error;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getError() {
            return error;
        }

        public String getMessage() {
            return message;
        }
    }
}
