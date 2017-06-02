package com.techfly.demo.bean;

import java.io.Serializable;

/**
 * 标签
 */
public class StyleBean implements Serializable{

    private int speces_id;
    private int num_id;
    private int price_id;

    private String title;
    private String price;
    private String rest;

    public StyleBean() {
    }

    public StyleBean(int speces_id, String title, int price_id, String price, int num_id, String rest) {
        this.speces_id = speces_id;
        this.title = title;
        this.price_id = price_id;
        this.price = price;
        this.num_id = num_id;
        this.rest = rest;
    }

    public StyleBean(String title, String price, String rest) {
        this.title = title;
        this.price = price;
        this.rest = rest;
    }

    /*
     * 是否为基本数据
     * 描述为空,价格、库存不为空
     */
    public Boolean isBaseData(){
        if(title.isEmpty() && !price.isEmpty() && !rest.isEmpty()){
            return true;
        }
        return false;
    }

    /*
     * 完整数据
     * 描述,价格、库存都不为空
     */
    public Boolean isFullData(){
        if(!title.isEmpty() && !price.isEmpty() && !rest.isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        if(price.isEmpty() || rest.isEmpty()){
            return "";
        }
        return title+price+rest;
    }

    public String getText() {
        return "title="+title+",price="+price+",rest="+rest+",speces_id="+speces_id+",price_id="+price_id+",num_id="+num_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRest() {
        return rest;
    }

    public void setRest(String rest) {
        this.rest = rest;
    }

    public int getSpeces_id() {
        return speces_id;
    }

    public void setSpeces_id(int speces_id) {
        this.speces_id = speces_id;
    }

    public int getNum_id() {
        return num_id;
    }

    public void setNum_id(int num_id) {
        this.num_id = num_id;
    }

    public int getPrice_id() {
        return price_id;
    }

    public void setPrice_id(int price_id) {
        this.price_id = price_id;
    }
}
