package com.quantchi.metadatamgr.data.entity;

public class DSMetaInfoDB {
    private Integer id;

    private String dsName;

    private String dsType;

    private String host;

    private String port;

    private String username;

    private String password;

    private String hiveMetaMysqlUrl;

    private String hiveMetaUsername;

    private String hiveMetaPswd;

    private String createUserId;

    private String table_num;

    private String mountNodeId;

    private String dsEnglishName;

    private String effectiveTime;

    private String metadataType;

    private String contextPath;

    private String creationDate;

    private String operationSystem;

    private String databaseVersion;

    private String databaseInstance;

    private String databaseDesc;

    public String getDsEnglishName() {
        return dsEnglishName;
    }

    public void setDsEnglishName(String dsEnglishName) {
        this.dsEnglishName = dsEnglishName;
    }

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getMetadataType() {
        return metadataType;
    }

    public void setMetadataType(String metadataType) {
        this.metadataType = metadataType;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getOperationSystem() {
        return operationSystem;
    }

    public void setOperationSystem(String operationSystem) {
        this.operationSystem = operationSystem;
    }

    public String getDatabaseVersion() {
        return databaseVersion;
    }

    public void setDatabaseVersion(String databaseVersion) {
        this.databaseVersion = databaseVersion;
    }

    public String getDatabaseInstance() {
        return databaseInstance;
    }

    public void setDatabaseInstance(String databaseInstance) {
        this.databaseInstance = databaseInstance;
    }

    public String getDatabaseDesc() {
        return databaseDesc;
    }

    public void setDatabaseDesc(String databaseDesc) {
        this.databaseDesc = databaseDesc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDsName() {
        return dsName;
    }

    public void setDsName(String dsName) {
        this.dsName = dsName == null ? null : dsName.trim();
    }

    public String getDsType() {
        return dsType;
    }

    public void setDsType(String dsType) {
        this.dsType = dsType == null ? null : dsType.trim();
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host == null ? null : host.trim();
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port == null ? null : port.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getHiveMetaMysqlUrl() {
        return hiveMetaMysqlUrl;
    }

    public void setHiveMetaMysqlUrl(String hiveMetaMysqlUrl) {
        this.hiveMetaMysqlUrl = hiveMetaMysqlUrl == null ? null : hiveMetaMysqlUrl.trim();
    }

    public String getHiveMetaUsername() {
        return hiveMetaUsername;
    }

    public void setHiveMetaUsername(String hiveMetaUsername) {
        this.hiveMetaUsername = hiveMetaUsername == null ? null : hiveMetaUsername.trim();
    }

    public String getHiveMetaPswd() {
        return hiveMetaPswd;
    }

    public void setHiveMetaPswd(String hiveMetaPswd) {
        this.hiveMetaPswd = hiveMetaPswd == null ? null : hiveMetaPswd.trim();
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public String getTable_num() {
        return table_num;
    }

    public void setTable_num(String table_num) {
        this.table_num = table_num;
    }

    public String getMountNodeId() {
        return mountNodeId;
    }

    public void setMountNodeId(String mountNodeId) {
        this.mountNodeId = mountNodeId;
    }
}