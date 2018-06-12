package com.quantchi.metadatamgr.data;

public class DSMetaInfo {
    private String hiveAddr;
    private String hivePort;
    private String hiveUser;
    private String hivePass;
    private HiveMetaInfo hiveMetaInfo;

    public String getHiveAddr() {
        return hiveAddr;
    }

    public void setHiveAddr(String hiveAddr) {
        this.hiveAddr = hiveAddr;
    }

    public String getHivePort() {
        return hivePort;
    }

    public void setHivePort(String hivePort) {
        this.hivePort = hivePort;
    }

    public String getHiveUser() {
        return hiveUser;
    }

    public void setHiveUser(String hiveUser) {
        this.hiveUser = hiveUser;
    }

    public String getHivePass() {
        return hivePass;
    }

    public void setHivePass(String hivePass) {
        this.hivePass = hivePass;
    }

    public HiveMetaInfo getHiveMetaInfo() {
        return hiveMetaInfo;
    }

    public void setHiveMetaInfo(HiveMetaInfo hiveMetaInfo) {
        this.hiveMetaInfo = hiveMetaInfo;
    }
}
