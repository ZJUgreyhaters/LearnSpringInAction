package com.quantchi.termInfo.pojo;


/**
* Created by jiang.
* 时间:2018-07-18 11:18:43
*/
public class MdTermExternalInfo {
     
    private Integer id;     
     
    private String entityId;     
     
    private String levelName;     
     
    private String themeName;     
     
    private String category1;     
     
    private String category2;     
     
    private String category3;     
     
    private String suitableType;     
     
    private String suitableCondition;     
     
    private String valueRang;     
     
    private String checkRule;     
    /******************
    * 无参构造器
    *********************/
    public MdTermExternalInfo() {
    }

    /*****************
    *有参构造器
    *********************/
    public MdTermExternalInfo(Integer id,String entityId,String levelName,String themeName,String category1,String category2,String category3,String suitableType,String suitableCondition,String valueRang,String checkRule) {
        this.id = id;
        this.entityId = entityId;
        this.levelName = levelName;
        this.themeName = themeName;
        this.category1 = category1;
        this.category2 = category2;
        this.category3 = category3;
        this.suitableType = suitableType;
        this.suitableCondition = suitableCondition;
        this.valueRang = valueRang;
        this.checkRule = checkRule;
}

    /****************
    *set和get方法
    ****************/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }
    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }
    public String getCategory1() {
        return category1;
    }

    public void setCategory1(String category1) {
        this.category1 = category1;
    }
    public String getCategory2() {
        return category2;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }
    public String getCategory3() {
        return category3;
    }

    public void setCategory3(String category3) {
        this.category3 = category3;
    }
    public String getSuitableType() {
        return suitableType;
    }

    public void setSuitableType(String suitableType) {
        this.suitableType = suitableType;
    }
    public String getSuitableCondition() {
        return suitableCondition;
    }

    public void setSuitableCondition(String suitableCondition) {
        this.suitableCondition = suitableCondition;
    }
    public String getValueRang() {
        return valueRang;
    }

    public void setValueRang(String valueRang) {
        this.valueRang = valueRang;
    }
    public String getCheckRule() {
        return checkRule;
    }

    public void setCheckRule(String checkRule) {
        this.checkRule = checkRule;
    }

    /*****************
    *toString
    ***********************/
    @Override
    public String toString() {
        return "MdTermExternalInfo{" +
            "id='" + id + '\'' +
            "entityId='" + entityId + '\'' +
            "levelName='" + levelName + '\'' +
            "themeName='" + themeName + '\'' +
            "category1='" + category1 + '\'' +
            "category2='" + category2 + '\'' +
            "category3='" + category3 + '\'' +
            "suitableType='" + suitableType + '\'' +
            "suitableCondition='" + suitableCondition + '\'' +
            "valueRang='" + valueRang + '\'' +
            "checkRule='" + checkRule + '\'' +
        '}';
    }
}
