package com.techfly.demo.bean;

/**
 * 错误集
 */
public class ErrorBean {


    /**
     * data : 账户不存在！
     * code : 999
     */

    private String data;
    private String code;

    public void setData(String data) {
        this.data = data;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public String getCode() {
        return code;
    }
}
