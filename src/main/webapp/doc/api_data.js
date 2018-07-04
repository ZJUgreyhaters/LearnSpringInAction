define({ "api": [
  {
    "type": "post",
    "url": "/term/:id",
    "title": "术语添加接口",
    "name": "TermInfoController",
    "group": "TermInfoController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Object[]",
            "optional": false,
            "field": "termGenInfos",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "PhysicalFieldInfo[]",
            "optional": false,
            "field": "fieldInfoList",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "fieldInfoList.id",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "fieldInfoList.entityId",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "fieldInfoList.physicalFieldHash",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "fieldInfoList.physicalField",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "fieldInfoList.physicalFieldDesc",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "fieldInfoList.physicalTable",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "fieldInfoList.physicalDb",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "fieldInfoList.dataType",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "fieldInfoList.dataLength",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "fieldInfoList.dataPrecision",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "fieldInfoList.dataPattern",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "fieldInfoList.dataUnit",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "fieldInfoList.partitionFlag",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "fieldInfoList.udcRuleName",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "fieldInfoList.udcCode",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "BigDecimal",
            "optional": true,
            "field": "fieldInfoList.max",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "BigDecimal",
            "optional": true,
            "field": "fieldInfoList.min",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "BigDecimal",
            "optional": true,
            "field": "fieldInfoList.avg",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "BigDecimal",
            "optional": true,
            "field": "fieldInfoList.dataNull",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "fieldInfoList.distribution",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "fieldInfoList.enumeration",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "PhysicalTableInfo",
            "optional": false,
            "field": "tableInfo",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "tableInfo.id",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "tableInfo.physicalTableHash",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "tableInfo.physicalTable",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "tableInfo.physicalDb",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "tableInfo.tableType",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "tableInfo.tableName",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "tableInfo.tableDesc",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "Date",
            "optional": true,
            "field": "tableInfo.lastModifiedTime",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "tableInfo.partitionFlag",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "tableInfo.primaryKey",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "tableInfo.foreignKey",
            "description": ""
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>成功或者错误代码200成功，500错误</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>成功或者错误信息</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "../java/com/quantchi/termInfo/controller/TermInfoController.java",
    "groupTitle": "TermInfoController"
  }
] });
