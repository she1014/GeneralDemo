package com.techfly.demo.bean;

import java.util.List;
import java.util.Map;

public class ProductRule {
    private String name;
    private List<Map<String, List<ProductStock>>> proRuleValuesList;// 该规格下的说有数组

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Map<String, List<ProductStock>>> getProRuleValuesList() {
        return proRuleValuesList;
    }

    public void setProRuleValuesList(List<Map<String, List<ProductStock>>> proRuleValuesList) {
        this.proRuleValuesList = proRuleValuesList;
    }


}
