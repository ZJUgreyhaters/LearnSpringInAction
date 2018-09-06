package com.quantchi.metadatamgr.data;


import java.util.Objects;

public class KeyInfo {

  private String tblName;
  private String fieldName;
  private String keyType;
  private String incidenceTBL;

  public KeyInfo(String tblName, String fieldName, String keyType, String incidenceTBL) {
    this.tblName = tblName;
    this.fieldName = fieldName;
    this.keyType = keyType;
    this.incidenceTBL = incidenceTBL;
  }

  public String getTblName() {
    return tblName;
  }

  public String getFieldName() {
    return fieldName;
  }

  public String getKeyType() {
    return keyType;
  }

  public String getIncidenceTBL() {
    return incidenceTBL;
  }

  @Override
  public String toString() {
    return "KeyInfo{" +
        "tblName='" + tblName + '\'' +
        ", fieldName='" + fieldName + '\'' +
        ", keyType='" + keyType + '\'' +
        ", incidenceTBL='" + incidenceTBL + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof KeyInfo)) {
      return false;
    }
    KeyInfo keyInfo = (KeyInfo) o;
    return Objects.equals(getTblName(), keyInfo.getTblName()) &&
        Objects.equals(getFieldName(), keyInfo.getFieldName()) &&
        Objects.equals(getKeyType(), keyInfo.getKeyType()) &&
        Objects.equals(getIncidenceTBL(), keyInfo.getIncidenceTBL());
  }

  @Override
  public int hashCode() {

    return Objects.hash(getTblName(), getFieldName(), getKeyType(), getIncidenceTBL());
  }
}
