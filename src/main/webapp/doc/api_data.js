define({ "api": [
  {
    "type": "post",
    "url": "/term",
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
            "description": "<p>字段信息列表</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "fieldInfoList.id",
            "description": "<p>字段id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "fieldInfoList.entityId",
            "description": "<p>实体id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "fieldInfoList.physicalFieldHash",
            "description": "<p>物理字段hash码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "fieldInfoList.physicalField",
            "description": "<p>物理字段名</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "fieldInfoList.physicalFieldDesc",
            "description": "<p>物理字段描述</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "fieldInfoList.physicalTable",
            "description": "<p>对应物理表</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "fieldInfoList.physicalDb",
            "description": "<p>对应物理数据库</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "fieldInfoList.dataType",
            "description": "<p>字段数据类型</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "fieldInfoList.dataLength",
            "description": "<p>字段数据类型长度</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "fieldInfoList.dataPrecision",
            "description": "<p>字段数据精准度</p>"
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
            "description": "<p>表信息</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "tableInfo.id",
            "description": "<p>表id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "tableInfo.physicalTableHash",
            "description": "<p>物理表hash码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "tableInfo.physicalTable",
            "description": "<p>物理表名</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "tableInfo.physicalDb",
            "description": "<p>表的物理数据库名</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "tableInfo.tableType",
            "description": "<p>表类型</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "tableInfo.tableName",
            "description": "<p>表名</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "tableInfo.tableDesc",
            "description": "<p>表描述</p>"
          },
          {
            "group": "Parameter",
            "type": "Date",
            "optional": true,
            "field": "tableInfo.lastModifiedTime",
            "description": "<p>表最后修改时间</p>"
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
            "description": "<p>表的主键</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "tableInfo.foreignKey",
            "description": "<p>表的外键</p>"
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
