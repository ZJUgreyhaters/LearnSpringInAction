package com.quantchi.metadatamgr.data;

public class HiveMetaInfo{
    private String mysqlUrl;
    private String mysqlUser;
    private String mysqlPass;

    public HiveMetaInfo(String mysqlUrl, String mysqlUser, String mysqlPass) {
        this.mysqlUrl = mysqlUrl;
        this.mysqlUser = mysqlUser;
        this.mysqlPass = mysqlPass;
    }

    public String getMysqlUrl() {
        return mysqlUrl;
    }

    public String getMysqlUser() {
        return mysqlUser;
    }

    public String getMysqlPass() {
        return mysqlPass;
    }
}
