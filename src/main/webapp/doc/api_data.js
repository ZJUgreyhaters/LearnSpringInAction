define({ "api": [
  {
    "type": "get",
    "url": "/api/lineage",
    "title": "血缘关系查询接口",
    "permission": [
      {
        "name": "none"
      }
    ],
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/appi/lineage"
      }
    ],
    "name": "lineage",
    "group": "LineAgeController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "termId",
            "description": "<p>指标编号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "type",
            "description": "<p>查询类型:ALL 代表全链路</p>"
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
            "field": "msg",
            "description": "<p>成功或者错误信息</p>"
          },
          {
            "group": "Success 200",
            "type": "List",
            "optional": true,
            "field": "data",
            "description": "<p>返回血缘数据列表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.metricId",
            "description": "<p>指标id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.metricName",
            "description": "<p>指标名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.phsicalFieldId",
            "description": "<p>物理字段id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.phsicalFieldName",
            "description": "<p>物理字段名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.hasChild",
            "description": "<p>是否有孩子节点</p>"
          },
          {
            "group": "Success 200",
            "type": "List",
            "optional": true,
            "field": "data.edges",
            "description": "<p>边信息列表</p>"
          },
          {
            "group": "Success 200",
            "type": "List",
            "optional": true,
            "field": "data.edges.source",
            "description": "<p>边源节点id</p>"
          },
          {
            "group": "Success 200",
            "type": "List",
            "optional": true,
            "field": "data.edges.target",
            "description": "<p>边目标节点id</p>"
          }
        ]
      }
    },
    "filename": "../../java/com/quantchi/lineage/controller/LineageController.java",
    "groupTitle": "LineAgeController"
  },
  {
    "type": "POST",
    "url": "/api/metadata/insertField",
    "title": "操作字段接口",
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/api/metadata/insertField"
      }
    ],
    "group": "MetaDataMgrApiController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>表id值</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "data_source_id",
            "description": "<p>数据源id</p>"
          },
          {
            "group": "Parameter",
            "type": "List",
            "optional": false,
            "field": "operationField",
            "description": "<p>操作字段</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "operationField.fieldName",
            "description": "<p>字段名称</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "operationField.oldName",
            "description": "<p>原字段名称（新增和删除可不填，更新必填）</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "operationField.operate",
            "description": "<p>具体操作（insert代表新增，update代表更新，delete代表删除）</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "operationField.type",
            "description": "<p>字段类型（更新和删除可不填，新增必填）</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "operationField.oldNameId",
            "description": "<p>字段id（新增可不填，更新和删除必填）</p>"
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
            "field": "msg",
            "description": "<p>成功或者错误信息</p>"
          }
        ]
      }
    },
    "contentType": "application/json",
    "filename": "../../java/com/quantchi/metadatamgr/controller/MetaDataMgrApiController.java",
    "groupTitle": "MetaDataMgrApiController",
    "name": "PostApiMetadataInsertfield"
  },
  {
    "type": "POST",
    "url": "/api/metadata/loadSheet",
    "title": "加载数据表回显表接口",
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/api/metadata/loadSheet"
      }
    ],
    "group": "MetaDataMgrApiController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "data_source_id",
            "description": "<p>数据源id</p>"
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
            "field": "msg",
            "description": "<p>成功或者错误信息</p>"
          },
          {
            "group": "Success 200",
            "type": "List",
            "optional": true,
            "field": "data",
            "description": "<p>返回数据</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.id",
            "description": "<p>id值</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.datasource_id",
            "description": "<p>数据源id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.table_english_name",
            "description": "<p>数据表英文名</p>"
          }
        ]
      }
    },
    "contentType": "application/json",
    "filename": "../../java/com/quantchi/metadatamgr/controller/MetaDataMgrApiController.java",
    "groupTitle": "MetaDataMgrApiController",
    "name": "PostApiMetadataLoadsheet"
  },
  {
    "type": "POST",
    "url": "/api/metadata/updateField",
    "title": "更新字段接口",
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/api/metadata/updatefield"
      }
    ],
    "group": "MetaDataMgrApiController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "tableEnglishName",
            "description": "<p>表英文名称</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "id",
            "description": "<p>表id值</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "data_source_id",
            "description": "<p>数据源id</p>"
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
            "field": "msg",
            "description": "<p>成功或者错误信息</p>"
          },
          {
            "group": "Success 200",
            "type": "Map",
            "optional": true,
            "field": "data",
            "description": "<p>返回数据</p>"
          },
          {
            "group": "Success 200",
            "type": "List",
            "optional": true,
            "field": "data.same",
            "description": "<p>以存在字段</p>"
          },
          {
            "group": "Success 200",
            "type": "Map",
            "optional": true,
            "field": "data.newDifferent",
            "description": "<p>新增或者改名的字段</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.newDifferent.name",
            "description": "<p>新增或者改名的字段名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.newDifferent.type",
            "description": "<p>新增或者改名的字段类型</p>"
          },
          {
            "group": "Success 200",
            "type": "Map",
            "optional": true,
            "field": "data.oldDifferent",
            "description": "<p>可能删除的字段</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.newDifferent.id",
            "description": "<p>可能删除的字段id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "\"data\":{",
          "content": "\"data\":{\n \"newDifferent\": [{\"name\":\"\",\"type\":\"\"}],\n \"oldDifferent\": [{\"name\":\"\",\"id\":\"\"}],\n \"same\":[\"branch_no\",customer_no\"]\n }",
          "type": "json"
        }
      ]
    },
    "contentType": "application/json",
    "filename": "../../java/com/quantchi/metadatamgr/controller/MetaDataMgrApiController.java",
    "groupTitle": "MetaDataMgrApiController",
    "name": "PostApiMetadataUpdatefield"
  },
  {
    "type": "get",
    "url": "/api/standard",
    "title": "标准查询接口",
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/api/standard"
      }
    ],
    "name": "standard",
    "group": "StandInfoController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "description": "<p>页数</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page_size",
            "description": "<p>每页数据数</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "keyword",
            "description": "<p>标准名称关键字</p>"
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
            "field": "msg",
            "description": "<p>成功或者错误信息</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "total",
            "description": "<p>返回记录总数</p>"
          },
          {
            "group": "Success 200",
            "type": "List",
            "optional": true,
            "field": "data",
            "description": "<p>返回数据 标准信息列表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entityId",
            "description": "<p>标准id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entityName",
            "description": "<p>标准名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entityDesc",
            "description": "<p>标准描述</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entityDomainId",
            "description": "<p>标准主题id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entityCategory",
            "description": "<p>标准分类</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.standardLevel",
            "description": "<p>标准层次</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.business_definition",
            "description": "<p>业务定义</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.according",
            "description": "<p>制定依据</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.supervision",
            "description": "<p>监管标志</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.udcRuleName",
            "description": "<p>编码规则</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entityType",
            "description": "<p>指标类型</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.businessRule",
            "description": "<p>业务口径</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.displayType",
            "description": "<p>常用维度</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.frequency",
            "description": "<p>统计频率</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.dataType",
            "description": "<p>数据类型</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.dataUnit",
            "description": "<p>度量单位</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.dataLength",
            "description": "<p>数据长度</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.dataPrecision",
            "description": "<p>数据精度</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.dataArea",
            "description": "<p>取值范围</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.udcCode",
            "description": "<p>引用代码</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.systemUsed",
            "description": "<p>落地系统</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.techniqueRule",
            "description": "<p>技术口径</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.systemFrom",
            "description": "<p>来源系统</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.oralTechniqueRule",
            "description": "<p>源系统技术口径</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.controlDept",
            "description": "<p>管理部门</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entityStatus",
            "description": "<p>发布状态</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.effectiveTime",
            "description": "<p>生效日期</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.offlineTime",
            "description": "<p>失效日期</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.remark",
            "description": "<p>备注</p>"
          }
        ]
      }
    },
    "filename": "../../java/com/quantchi/termInfo/controller/StandInfoController.java",
    "groupTitle": "StandInfoController"
  },
  {
    "type": "get",
    "url": "/api/standardCategory",
    "title": "标准类目获得",
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/api/standardCategory"
      }
    ],
    "name": "standardCategory",
    "group": "StandInfoController",
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
            "field": "msg",
            "description": "<p>成功或者错误信息</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "total",
            "description": "<p>返回记录总数</p>"
          },
          {
            "group": "Success 200",
            "type": "List",
            "optional": true,
            "field": "data",
            "description": "<p>返回数据 标准信息列表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.id",
            "description": "<p>标准类目id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.name",
            "description": "<p>标准类目名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.description",
            "description": "<p>标准类目描述</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.level",
            "description": "<p>标准类目分类</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.domain",
            "description": "<p>标准主题</p>"
          },
          {
            "group": "Success 200",
            "type": "List",
            "optional": true,
            "field": "data.children",
            "description": "<p>标准类目的孩子节点</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.children.name",
            "description": "<p>标准类目名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.children.id",
            "description": "<p>标准类目id</p>"
          }
        ]
      }
    },
    "filename": "../../java/com/quantchi/termInfo/controller/StandInfoController.java",
    "groupTitle": "StandInfoController"
  },
  {
    "type": "post",
    "url": "/term/import",
    "title": "文件上传接口",
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/term/import"
      }
    ],
    "name": "importExcel",
    "group": "TermFileController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "MultipartFile",
            "optional": false,
            "field": "file",
            "description": "<p>文件</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "typeOne",
            "description": "<p>类型1（标准：standard，指标：target）</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "typeTwo",
            "description": "<p>类型2（映射：mapping,普通：common）</p>"
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
            "field": "msg",
            "description": "<p>成功或者错误信息</p>"
          }
        ]
      }
    },
    "filename": "../../java/com/quantchi/termInfo/controller/TermFileController.java",
    "groupTitle": "TermFileController"
  },
  {
    "type": "post",
    "url": "/term",
    "title": "术语添加接口",
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/term"
      }
    ],
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
            "description": "<p>表id（）</p>"
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
          },
          {
            "group": "Parameter",
            "type": "TermMainInfo",
            "optional": false,
            "field": "termMainInfo",
            "description": "<p>术语主信息 如果有传入值，则是前端录入，如果没有则是后端录入</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "termMainInfo.entityType",
            "description": "<p>实体类型</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "termMainInfo.entityId",
            "description": "<p>实体ID</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "termMainInfo.entityHash",
            "description": "<p>实体MD5校验值</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "termMainInfo.entityName",
            "description": "<p>实体英文名称</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "termMainInfo.entityDesc",
            "description": "<p>实体中文名称</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "termMainInfo.entityStatus",
            "description": "<p>实体状态</p>"
          },
          {
            "group": "Parameter",
            "type": "Date",
            "optional": true,
            "field": "termMainInfo.createTime",
            "description": "<p>生效时间</p>"
          },
          {
            "group": "Parameter",
            "type": "Date",
            "optional": true,
            "field": "termMainInfo.offlineTime",
            "description": "<p>失效时间</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "termMainInfo.creator",
            "description": "<p>创建人ID</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "termMainInfo.controlDept",
            "description": "<p>主管部门</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "termMainInfo.assistDept",
            "description": "<p>协办部门</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "termMainInfo.businessDesc",
            "description": "<p>业务定义</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "termMainInfo.entityCategory",
            "description": "<p>逻辑分类</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "termMainInfo.regulatory",
            "description": "<p>是否监管</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "termMainInfo.logicType",
            "description": "<p>术语值类型</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "termMainInfo.displayType",
            "description": "<p>显示英文类型</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "termMainInfo.entityAlias",
            "description": "<p>常用名称</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "termMainInfo.businessRule",
            "description": "<p>业务口径</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "termMainInfo.techniqueRule",
            "description": "<p>技术口径（SQL）</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "termMainInfo.devPolicy",
            "description": "<p>制定依据</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "termMainInfo.remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "termMainInfo.frequency",
            "description": "<p>统计频率</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-example: [{ \"fieldInfoList\": [ { \"dataType\": \"string\",",
          "content": "\"physicalDb\": \"cust_mining\", \"physicalField\": \"init_date\", \"physicalTable\":\n\"agg_cust_balance_feature_test\" }, { \"dataType\": \"double\", \"physicalDb\": \"cust_mining\",\n\"physicalField\": \"median_fund_asset\", \"physicalTable\": \"agg_cust_balance_feature_test\" }, {\n\"dataType\": \"double\", \"physicalDb\": \"cust_mining\", \"physicalField\": \"var_fund_asset\",\n\"physicalTable\": \"agg_cust_balance_feature_test\" }, { \"dataType\": \"double\", \"physicalDb\":\n\"cust_mining\", \"physicalField\": \"avg_secu_market_value\", \"physicalTable\":\n\"agg_cust_balance_feature_test\" }, { \"dataType\": \"double\", \"physicalDb\": \"cust_mining\",\n\"physicalField\": \"max_secu_market_value\", \"physicalTable\": \"agg_cust_balance_feature_test\" }, {\n\"dataType\": \"double\", \"physicalDb\": \"cust_mining\", \"physicalField\": \"min_secu_market_value\",\n\"physicalTable\": \"agg_cust_balance_feature_test\" }, { \"dataType\": \"double\", \"physicalDb\":\n\"cust_mining\", \"physicalField\": \"median_secu_market_value\", \"physicalTable\":\n\"agg_cust_balance_feature_test\" }, { \"dataType\": \"double\", \"physicalDb\": \"cust_mining\",\n\"physicalField\": \"var_secu_market_value\", \"physicalTable\": \"agg_cust_balance_feature_test\" }, {\n\"dataType\": \"double\", \"physicalDb\": \"cust_mining\", \"physicalField\": \"avg_opfund_market_value\",\n\"physicalTable\": \"agg_cust_balance_feature_test\" }, { \"dataType\": \"double\", \"physicalDb\":\n\"cust_mining\", \"physicalField\": \"max_opfund_market_value\", \"physicalTable\":\n\"agg_cust_balance_feature_test\" }, { \"dataType\": \"double\", \"physicalDb\": \"cust_mining\",\n\"physicalField\": \"min_opfund_market_value\", \"physicalTable\": \"agg_cust_balance_feature_test\" },\n{ \"dataType\": \"double\", \"physicalDb\": \"cust_mining\", \"physicalField\":\n\"median_opfund_market_value\", \"physicalTable\": \"agg_cust_balance_feature_test\" }, { \"dataType\":\n\"double\", \"physicalDb\": \"cust_mining\", \"physicalField\": \"var_opfund_market_value\",\n\"physicalTable\": \"agg_cust_balance_feature_test\" }, { \"dataType\": \"double\", \"physicalDb\":\n\"cust_mining\", \"physicalField\": \"avg_total_asset\", \"physicalTable\":\n\"agg_cust_balance_feature_test\" }, { \"dataType\": \"double\", \"physicalDb\": \"cust_mining\",\n\"physicalField\": \"max_total_asset\", \"physicalTable\": \"agg_cust_balance_feature_test\" }, {\n\"dataType\": \"double\", \"physicalDb\": \"cust_mining\", \"physicalField\": \"min_total_asset\",\n\"physicalTable\": \"agg_cust_balance_feature_test\" }, { \"dataType\": \"double\", \"physicalDb\":\n\"cust_mining\", \"physicalField\": \"median_total_asset\", \"physicalTable\":\n\"agg_cust_balance_feature_test\" }, { \"dataType\": \"double\", \"physicalDb\": \"cust_mining\",\n\"physicalField\": \"var_total_asset\", \"physicalTable\": \"agg_cust_balance_feature_test\" } ],\n\"tableInfo\": { \"physicalDb\": \"cust_mining\", \"physicalTable\": \"agg_cust_balance_feature_test\",\n\"tableType\": \"\" }, \"termMainInfo\":{ \"entityType\": \"1\", \"entityId\": \"PT000001\",\n\"entityHash\":\"55e4e1b391831960d8b7e4b61fdfaf7c\", \"entityName\": \"gender\", \"entityDesc\": \"客户性别\",\n\"entityAlias\": \"性别\", \"businessRule\": \"记录个人客户的性别状况，如“男”、“女”等。\", \"techniqueRule\": \"select gender\nfrom dim_customer\", \"entityStatus\": \"正常\", \"createTime\": \"2017-01-02T00:00:00.000Z\",\n\"offlineTime\": \"\", \"creator\": \"dmp_admin\", \"controlDept\": \"机构管理部\", \"assistDept\": \"信息管理中心\",\n\"devPolicy\": \"1.GB/T2261.1-2003个人基本信息分类与代码第1部分:人的性别代码\", \"regulatory\": \"是\", \"logicType\": \"代码\",\n\"displayType\": \"CheckBox\", } }]",
          "type": "json"
        }
      ]
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
            "description": "<p>成功或者错误信息 * @apiSuccessExample {json} Success-Response: HTTP/1.1 200 OK { &quot;code&quot;: &quot;200&quot;, &quot;data&quot;: 18, &quot;msg&quot;: &quot;success&quot; }</p>"
          }
        ]
      }
    },
    "filename": "../../java/com/quantchi/termInfo/controller/TermInfoController.java",
    "groupTitle": "TermInfoController"
  },
  {
    "type": "get",
    "url": "/term/{EntityId}",
    "title": "按id术语查询接口",
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/term/"
      }
    ],
    "name": "selectTerm",
    "group": "TermInfoController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "Entityid",
            "description": "<p>术语id</p>"
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
            "field": "msg",
            "description": "<p>成功或者错误信息</p>"
          },
          {
            "group": "Success 200",
            "type": "List",
            "optional": true,
            "field": "data",
            "description": "<p>返回数据</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entityType",
            "description": "<p>实体类型</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entityId",
            "description": "<p>实体id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entityHash",
            "description": "<p>实体hash码</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entityName",
            "description": "<p>实体名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entityDesc",
            "description": "<p>实体描述</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entityAlias",
            "description": "<p>实体别名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.businessRule",
            "description": "<p>规范描述</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.techniqueRule",
            "description": "<p>sql语句</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entityStatus",
            "description": "<p>实体状态</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.controlDept",
            "description": "<p>管理部门</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.assistDept",
            "description": "<p>分配部门</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.devPolicy",
            "description": "<p>个人信息分类</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.regulatory",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.logicType",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.displayType",
            "description": "<p>数据显示类型</p>"
          },
          {
            "group": "Success 200",
            "type": "List",
            "optional": true,
            "field": "data.physicalField",
            "description": "<p>物理字段</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.physicalField.entityId",
            "description": "<p>实体id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.physicalField.physicalFieldHash",
            "description": "<p>物理字段hash码</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.physicalField.physicalFieldId",
            "description": "<p>物理字段id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.physicalField.physicalFieldDesc",
            "description": "<p>物理字段描述</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.physicalField.physicalTable",
            "description": "<p>物理表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.physicalField.physicalDB",
            "description": "<p>物理数据库</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.physicalField.dataType",
            "description": "<p>数据类型</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.physicalField.dataLength",
            "description": "<p>数据长度</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.physicalField.dataPattern",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.physicalField.dataUnit",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.physicalField.fieldPartition",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "List",
            "optional": true,
            "field": "data.physicalField.dataUDC",
            "description": "<p>条件数据</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.physicalField.dataUDC.dataUDCCode",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.physicalField.dataUDC.dataUDCDesc",
            "description": "<p>数据描述</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.physicalField.dataUDC.dataUDCRule",
            "description": "<p>数据规范</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.physicalField.dataUDC.dataUDCValue",
            "description": "<p>数据值</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.physicalField.dataUDC.entityHash",
            "description": "<p>数据hash码</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.physicalField.dataUDC.entityId",
            "description": "<p>数据id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.physicalField.dataUDC.entityType",
            "description": "<p>数据类型</p>"
          }
        ]
      }
    },
    "filename": "../../java/com/quantchi/termInfo/controller/TermInfoController.java",
    "groupTitle": "TermInfoController"
  },
  {
    "type": "get",
    "url": "/term",
    "title": "术语查询接口",
    "permission": [
      {
        "name": "none"
      }
    ],
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/term"
      }
    ],
    "name": "term",
    "group": "TermInfoController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "description": "<p>页数</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page_size",
            "description": "<p>每页数据数</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "entityCategory",
            "description": "<p>逻辑分类ID</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "entityDesc",
            "description": "<p>实体描述</p>"
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
            "field": "msg",
            "description": "<p>成功或者错误信息</p>"
          },
          {
            "group": "Success 200",
            "type": "List",
            "optional": true,
            "field": "data",
            "description": "<p>返回数据</p>"
          },
          {
            "group": "Success 200",
            "type": "List",
            "optional": true,
            "field": "entitys",
            "description": "<p>实体信息</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entitys.entityType",
            "description": "<p>实体类型</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entitys.entityId",
            "description": "<p>实体id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entitys.entityHash",
            "description": "<p>实体hash码</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entitys.entityName",
            "description": "<p>实体名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entitys.entityDesc",
            "description": "<p>实体描述</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entitys.entityAlias",
            "description": "<p>实体别名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entitys.businessRule",
            "description": "<p>规范描述</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entitys.techniqueRule",
            "description": "<p>sql语句</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entitys.entityStatus",
            "description": "<p>实体状态</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entitys.controlDept",
            "description": "<p>管理部门</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entitys.assistDept",
            "description": "<p>分配部门</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entitys.devPolicy",
            "description": "<p>个人信息分类</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entitys.regulatory",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entitys.logicType",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entitys.displayType",
            "description": "<p>数据显示类型</p>"
          },
          {
            "group": "Success 200",
            "type": "List",
            "optional": true,
            "field": "data.entitys.physicalField",
            "description": "<p>物理字段</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entitys.physicalField.entityId",
            "description": "<p>实体id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entitys.physicalField.physicalFieldHash",
            "description": "<p>物理字段hash码</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entitys.physicalField.physicalFieldId",
            "description": "<p>物理字段id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entitys.physicalField.physicalFieldDesc",
            "description": "<p>物理字段描述</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entitys.physicalField.physicalTable",
            "description": "<p>物理表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entitys.physicalField.physicalDB",
            "description": "<p>物理数据库</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entitys.physicalField.dataType",
            "description": "<p>数据类型</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entitys.physicalField.dataLength",
            "description": "<p>数据长度</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entitys.physicalField.dataPattern",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entitys.physicalField.dataUnit",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entitys.physicalField.fieldPartition",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "List",
            "optional": true,
            "field": "data.entitys.physicalField.dataUDC",
            "description": "<p>条件数据</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entitys.physicalField.dataUDC.dataUDCCode",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entitys.physicalField.dataUDC.dataUDCDesc",
            "description": "<p>数据描述</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entitys.physicalField.dataUDC.dataUDCRule",
            "description": "<p>数据规范</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entitys.physicalField.dataUDC.dataUDCValue",
            "description": "<p>数据值</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entitys.physicalField.dataUDC.entityHash",
            "description": "<p>数据hash码</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entitys.physicalField.dataUDC.entityId",
            "description": "<p>数据id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entitys.physicalField.dataUDC.entityType",
            "description": "<p>数据类型</p>"
          }
        ]
      }
    },
    "filename": "../../java/com/quantchi/termInfo/controller/TermInfoController.java",
    "groupTitle": "TermInfoController"
  }
] });
