package com.quantchi.metadatamgr.data;

public class DSMetaInfo {
    private String hiveAddr;
    private String hivePort;
    private String hiveUser;
    private String hivePass;
    private HiveMetaInfo hiveMetaInfo;

    public DSMetaInfo(String hiveAddr, String hivePort, String hiveUser, String hivePass, HiveMetaInfo hiveMetaInfo) {
        this.hiveAddr = hiveAddr;
        this.hivePort = hivePort;
        this.hiveUser = hiveUser;
        this.hivePass = hivePass;
        this.hiveMetaInfo = hiveMetaInfo;
    }

    public String getHiveAddr() {
        return hiveAddr;
    }

    public String getHivePort() {
        return hivePort;
    }

    public String getHiveUser() {
        return hiveUser;
    }

    public String getHivePass() {
        return hivePass;
    }

    public HiveMetaInfo getHiveMetaInfo() {
        return hiveMetaInfo;
    }
}
