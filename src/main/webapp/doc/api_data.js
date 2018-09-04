define({ "api": [
  {
    "type": "post",
    "url": "/api/addRole",
    "title": "插入新角色",
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/api/addRole"
      }
    ],
    "name": "addRole",
    "group": "AuthorityController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Object",
            "optional": true,
            "field": "role",
            "description": "<p>角色</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "role.c_isenable",
            "description": "<p>是否有效</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "role.c_roledesc",
            "description": "<p>角色描述</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "role.c_rolename",
            "description": "<p>角色名称</p>"
          },
          {
            "group": "Parameter",
            "type": "Int",
            "optional": true,
            "field": "role.l_roleid",
            "description": "<p>角色id   因为还未插入表 填0</p>"
          },
          {
            "group": "Parameter",
            "type": "List",
            "optional": true,
            "field": "roleAuthRela",
            "description": "<p>角色权限关系 可以装入多个</p>"
          },
          {
            "group": "Parameter",
            "type": "Int",
            "optional": true,
            "field": "roleAuthRela.l_authid",
            "description": "<p>权限ID</p>"
          },
          {
            "group": "Parameter",
            "type": "Int",
            "optional": true,
            "field": "roleAuthRela.l_roleauthid",
            "description": "<p>关系id  因为还未插入表 填0</p>"
          },
          {
            "group": "Parameter",
            "type": "Int",
            "optional": true,
            "field": "roleAuthRela.l_roleid",
            "description": "<p>角色ID 因为还未插入表 填0</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-example:",
          "content": "{\"role\":{\"c_isenable\":\"0\",\"c_roledesc\":\"xxxx\",\"c_rolename\":\"xxxxxx\",\"l_roleid\":0},\n\"roleAuthRela\":[{\"l_authid\":1,\"l_roleauthid\":123,\"l_roleid\":789},{\"l_authid\":2,\"l_roleauthid\":1223,\"l_roleid\":789}]}",
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
            "field": "msg",
            "description": "<p>成功或者错误信息</p>"
          }
        ]
      }
    },
    "filename": "../../java/com/quantchi/authority/controller/AuthorityController.java",
    "groupTitle": "AuthorityController"
  },
  {
    "type": "post",
    "url": "/api/deleAuth",
    "title": "删除权限",
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/api/deleAuth"
      }
    ],
    "name": "deleAuth",
    "group": "AuthorityController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Int",
            "optional": true,
            "field": "authId",
            "description": "<p>权限ID</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-example:",
          "content": "{\"authId\":1}",
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
            "field": "msg",
            "description": "<p>成功或者错误信息</p>"
          }
        ]
      }
    },
    "filename": "../../java/com/quantchi/authority/controller/AuthorityDetailController.java",
    "groupTitle": "AuthorityController"
  },
  {
    "type": "post",
    "url": "/api/deleRole",
    "title": "删除角色",
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/api/deleRole"
      }
    ],
    "name": "deleRole",
    "group": "AuthorityController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "roleId",
            "description": "<p>角色ID</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-example:",
          "content": "{ \"roleId\":\"1\"}",
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
            "field": "msg",
            "description": "<p>成功或者错误信息</p>"
          }
        ]
      }
    },
    "filename": "../../java/com/quantchi/authority/controller/AuthorityController.java",
    "groupTitle": "AuthorityController"
  },
  {
    "type": "post",
    "url": "/api/listRoleByFilter",
    "title": "根据条件查询角色 传空则返回所有",
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/api/listRoleByFilter"
      }
    ],
    "name": "listRoleByFilter",
    "group": "AuthorityController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "c_rolename",
            "description": "<p>角色名字</p>"
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
            "description": "<p>返回数据 指标信息列表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.l_roleid",
            "description": "<p>角色id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.c_rolename",
            "description": "<p>角色名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.c_isenable",
            "description": "<p>是否有效</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.c_roledesc",
            "description": "<p>角色描述信息</p>"
          }
        ]
      }
    },
    "filename": "../../java/com/quantchi/authority/controller/AuthorityController.java",
    "groupTitle": "AuthorityController"
  },
  {
    "type": "post",
    "url": "/api/listRoleByRoleid",
    "title": "根据角色ID 查询角色明细",
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/api/listRoleByRoleid"
      }
    ],
    "name": "listRoleByRoleid",
    "group": "AuthorityController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Int",
            "optional": true,
            "field": "l_roleid",
            "description": "<p>角色</p>"
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
            "description": "<p>返回数据 指标信息列表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.l_roleid",
            "description": "<p>角色id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.c_rolename",
            "description": "<p>角色名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.c_isenable",
            "description": "<p>是否有效</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.c_roledesc",
            "description": "<p>角色描述信息</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.c_authname",
            "description": "<p>权限名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.c_authtype",
            "description": "<p>权限类型 0:功能权限  1:数据权限</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.l_authid_",
            "description": "<p>权限id</p>"
          }
        ]
      }
    },
    "filename": "../../java/com/quantchi/authority/controller/AuthorityController.java",
    "groupTitle": "AuthorityController"
  },
  {
    "type": "post",
    "url": "/api/modifyAuth",
    "title": "修改权限说明",
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/api/modifyAuth"
      }
    ],
    "name": "modifyAuth",
    "group": "AuthorityController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Object",
            "optional": true,
            "field": "authority",
            "description": "<p>权限</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "authority.c_isenable",
            "description": "<p>是否有效</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "authority.c_authname",
            "description": "<p>权限名称</p>"
          },
          {
            "group": "Parameter",
            "type": "Int",
            "optional": true,
            "field": "authority.l_authid",
            "description": "<p>权限ID</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "authority.c_authtype",
            "description": "<p>权限类型 0:功能权限  1:数据权限</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "authority.l_datatype",
            "description": "<p>数据权限类型 0表示非数据权限 1表示表权限 2 表示字段权限 3 表示行数据权限</p>"
          },
          {
            "group": "Parameter",
            "type": "List",
            "optional": true,
            "field": "authDetail",
            "description": "<p>权限明细 可以装入多个</p>"
          },
          {
            "group": "Parameter",
            "type": "Int",
            "optional": true,
            "field": "authDetail.l_authdetailid",
            "description": "<p>明细权限ID</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "authDetail.c_tablename",
            "description": "<p>表名字</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "authDetail.c_column",
            "description": "<p>表字段名字</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "authDetail.c_fiterdesc",
            "description": "<p>过滤描述</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "authDetail.c_fiter",
            "description": "<p>过滤条件</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "authDetail.c_funcname",
            "description": "<p>功能名称</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "authDetail.c_url",
            "description": "<p>功能路径</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "authDetail.c_isenable",
            "description": "<p>是否有效</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-example:",
          "content": "{ \"authority\":{\"c_authname\":\"dyaln1720\",\"c_isenable\":\"0\",\"c_authtype\":\"1\",\"l_datatype\":\"1\",\"l_authid\":43},\"authDetail\":[{\"l_authdetailid\":0,\"c_tablename\":\"wcer\" , \"c_isenable\":\"0\"},{\"l_authdetailid\":0,\"c_tablename\":\"tdercor\" , \"c_isenable\":\"0\"}]}",
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
            "field": "msg",
            "description": "<p>成功或者错误信息</p>"
          }
        ]
      }
    },
    "filename": "../../java/com/quantchi/authority/controller/AuthorityDetailController.java",
    "groupTitle": "AuthorityController"
  },
  {
    "type": "post",
    "url": "/api/modifyRole",
    "title": "修改角色",
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/api/modifyRole"
      }
    ],
    "name": "modifyRole",
    "group": "AuthorityController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Object",
            "optional": true,
            "field": "role",
            "description": "<p>角色</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "role.c_isenable",
            "description": "<p>权限名称</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "role.c_roledesc",
            "description": "<p>是否生效</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "role.c_rolename",
            "description": "<p>角色名称</p>"
          },
          {
            "group": "Parameter",
            "type": "Int",
            "optional": true,
            "field": "role.l_roleid",
            "description": "<p>角色ID</p>"
          },
          {
            "group": "Parameter",
            "type": "List",
            "optional": true,
            "field": "addauthRoleRela",
            "description": "<p>表数据权限明细 可以装入多个</p>"
          },
          {
            "group": "Parameter",
            "type": "Int",
            "optional": true,
            "field": "addauthRoleRela.l_authid",
            "description": "<p>权限ID</p>"
          },
          {
            "group": "Parameter",
            "type": "Int",
            "optional": true,
            "field": "addauthRoleRela.l_roleauthid",
            "description": "<p>关系id</p>"
          },
          {
            "group": "Parameter",
            "type": "Int",
            "optional": true,
            "field": "addauthRoleRela.l_roleid",
            "description": "<p>角色ID</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-example:例子:",
          "content": "{\"role\":{\"c_isenable\":\"0\",\"c_roledesc\":\"xxxx\",\"c_rolename\":\"xxxxxx\",\"l_roleid\":0},",
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
            "field": "msg",
            "description": "<p>成功或者错误信息</p>"
          }
        ]
      }
    },
    "filename": "../../java/com/quantchi/authority/controller/AuthorityController.java",
    "groupTitle": "AuthorityController"
  },
  {
    "type": "post",
    "url": "/api/selectAuthByFilter",
    "title": "根据权限名称查权限 传空则返回所有",
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/api/selectAuthByFilter"
      }
    ],
    "name": "selectAuthByFilter",
    "group": "AuthorityController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "c_authname",
            "description": "<p>权限名称</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "c_authtype",
            "description": "<p>权限类型 0:功能权限  1:数据权限</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-example:",
          "content": "{\"c_authname\":\"xxx\"}    或者 {\"c_authname\":\"xxxxx\",\"c_authtype\":\"0\"}",
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
            "field": "msg",
            "description": "<p>成功或者错误信息</p>"
          },
          {
            "group": "Success 200",
            "type": "List",
            "optional": true,
            "field": "data",
            "description": "<p>返回数据 指标信息列表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.c_authname",
            "description": "<p>权限名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.c_authtype",
            "description": "<p>权限类型 0:功能权限  1:数据权限</p>"
          },
          {
            "group": "Success 200",
            "type": "Int",
            "optional": true,
            "field": "data.l_authid",
            "description": "<p>权限id</p>"
          },
          {
            "group": "Success 200",
            "type": "Int",
            "optional": true,
            "field": "data.c_isenable",
            "description": "<p>是否有效</p>"
          },
          {
            "group": "Success 200",
            "type": "Int",
            "optional": true,
            "field": "data.l_datatype",
            "description": "<p>数据权限类型 0表示非数据权限 1表示表权限 2 表示字段权限 3 表示行数据权限</p>"
          }
        ]
      }
    },
    "filename": "../../java/com/quantchi/authority/controller/AuthorityController.java",
    "groupTitle": "AuthorityController"
  },
  {
    "type": "post",
    "url": "/api/addDataAuth",
    "title": "添加新的数据权限",
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/api/addDataAuth"
      }
    ],
    "name": "addDataAuth",
    "group": "AuthorityDetailController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Object",
            "optional": true,
            "field": "authority",
            "description": "<p>权限</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "authority.c_authname",
            "description": "<p>权限名称</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "authority.c_isenable",
            "description": "<p>是否生效</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "authority.c_authtype",
            "description": "<p>权限类型  0:功能权限  1:数据权限'</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "authority.l_datatype",
            "description": "<p>数据权限类型  0表示非数据权限 1表示表权限 2 表示字段权限 3 表示行数据权限</p>"
          },
          {
            "group": "Parameter",
            "type": "Int",
            "optional": true,
            "field": "authority.l_authid",
            "description": "<p>权限id   因为还未插入表 填0</p>"
          },
          {
            "group": "Parameter",
            "type": "List",
            "optional": true,
            "field": "authDetail",
            "description": "<p>权限明细 可以装入多个</p>"
          },
          {
            "group": "Parameter",
            "type": "Int",
            "optional": true,
            "field": "authDetail.l_authdetailid",
            "description": "<p>明细权限ID    因为还未插入表 填0</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "authDetail.c_tablename",
            "description": "<p>表名字</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "authDetail.c_column",
            "description": "<p>表字段名字</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "authDetail.c_fiterdesc",
            "description": "<p>过滤描述</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "authDetail.c_fiter",
            "description": "<p>过滤条件</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "authDetail.c_funcname",
            "description": "<p>功能名称</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "authDetail.c_url",
            "description": "<p>功能路径</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "authDetail.c_isenable",
            "description": "<p>是否有效</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-example:",
          "content": "{ \"authority\":{\"c_authname\":\"dyaln1645\",\"c_isenable\":\"0\",\"c_authtype\":\"1\",\"l_datatype\":\"3\",\"l_authid\":0},\n\"authDetail\":[{\"l_authdetailid\":0,\"c_tablename\":\"tstockholder\",\"c_column\":\"Ace\",\"c_fiterdesc\":\"ccss\",\"c_fiter\":\"1122\",\"c_isenable\":\"0\"},{\"l_authdetailid\":0,\"c_tablename\":\"tdercor\",\"c_column\":\"Ace\",\"c_fiterdesc\":\"ccss\",\"c_fiter\":\"1122\",\"c_isenable\":\"0\"}]}\n或者\n{ \"authority\":{\"c_authname\":\"dyaln1648\",\"c_isenable\":\"0\",\"c_authtype\":\"0\",\"l_datatype\":\"0\",\"l_authid\":0},\"authDetail\":[{\"l_authdetailid\":0,\"c_funcname\":\"tstockholder\",\"c_url\":\"Ace\" ,\"c_isenable\":\"0\"}]}",
          "type": "json"
        }
      ]
    },
    "filename": "../../java/com/quantchi/authority/controller/AuthorityDetailController.java",
    "groupTitle": "AuthorityDetailController"
  },
  {
    "type": "post",
    "url": "/api/getDataAuthDetail",
    "title": "查询权限的明细",
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/api/getDataAuthDetail"
      }
    ],
    "name": "getDataAuthDetail",
    "group": "AuthorityDetailController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "c_authname",
            "description": "<p>权限名称</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "c_isenable",
            "description": "<p>是否生效</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "c_authtype",
            "description": "<p>权限类型  0:功能权限  1:数据权限'</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "l_datatype",
            "description": "<p>数据权限类型  0表示非数据权限 1表示表权限 2 表示字段权限 3 表示行数据权限</p>"
          },
          {
            "group": "Parameter",
            "type": "Int",
            "optional": true,
            "field": "l_authid",
            "description": "<p>权限id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-example:",
          "content": "{\"l_authid\":1,\"c_authtype\":\"0\",\"l_datatype\":\"1\"}",
          "type": "json"
        }
      ]
    },
    "filename": "../../java/com/quantchi/authority/controller/AuthorityDetailController.java",
    "groupTitle": "AuthorityDetailController"
  },
  {
    "type": "post",
    "url": "/api/getUserByUserName",
    "title": "根据姓名查员工",
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/api/getUserByUserName"
      }
    ],
    "name": "getUserByUserName",
    "group": "AuthorityUserController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "userName",
            "description": "<p>角色姓名</p>"
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
            "description": "<p>返回数据 指标信息列表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.userAccount",
            "description": "<p>客户账号</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.userId",
            "description": "<p>&quot;:]  客户id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.userName",
            "description": "<p>客户姓名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.userRoles",
            "description": "<p>客户角色</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.userStatus",
            "description": "<p>客户状态</p>"
          }
        ]
      }
    },
    "filename": "../../java/com/quantchi/authority/controller/AuthorityUserController.java",
    "groupTitle": "AuthorityUserController"
  },
  {
    "type": "post",
    "url": "/api/getUserList",
    "title": "查员工",
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/api/getUserList"
      }
    ],
    "name": "getUserList",
    "group": "AuthorityUserController",
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
            "description": "<p>返回数据 指标信息列表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.userAccount",
            "description": "<p>客户账号</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.userId",
            "description": "<p>&quot;:]  客户id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.userName",
            "description": "<p>客户姓名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.userRoles",
            "description": "<p>客户角色</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.userStatus",
            "description": "<p>客户状态</p>"
          }
        ]
      }
    },
    "filename": "../../java/com/quantchi/authority/controller/AuthorityUserController.java",
    "groupTitle": "AuthorityUserController"
  },
  {
    "type": "post",
    "url": "/api/getUserTree",
    "title": "查询公司信息",
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/api/getUserTree"
      }
    ],
    "name": "getUserTree",
    "group": "AuthorityUserController",
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
            "description": "<p>返回数据 指标信息列表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.company",
            "description": "<p>公司名称</p>"
          },
          {
            "group": "Success 200",
            "type": "List",
            "optional": true,
            "field": "depart",
            "description": "<p>部门</p>"
          }
        ]
      }
    },
    "filename": "../../java/com/quantchi/authority/controller/AuthorityUserController.java",
    "groupTitle": "AuthorityUserController"
  },
  {
    "type": "post",
    "url": "/api/associateQuery",
    "title": "智能取数联想语句查询接口",
    "permission": [
      {
        "name": "none"
      }
    ],
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/api/associateQuery"
      }
    ],
    "name": "associateQuery",
    "group": "IntelQueryController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "businessName",
            "description": "<p>业务名称</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "businessID",
            "description": "<p>业务名称</p>"
          },
          {
            "group": "Parameter",
            "type": "List",
            "optional": false,
            "field": "candidates",
            "description": "<p>需要替換词集合</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "candidates.begIndex",
            "description": "<p>需要替換词的起始下标</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "candidates.serializeNode",
            "description": "<p>需要替換词的序列化</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "candidates.endIndex",
            "description": "<p>需要替換词的终止下标</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "queryWithNode",
            "description": "<p>原始节点序列化</p>"
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
            "description": "<p>返回推荐问句列表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "data.sentenceId",
            "description": "<p>问句列表搜索库中的id,用于点赞</p>"
          },
          {
            "group": "Success 200",
            "type": "List",
            "optional": true,
            "field": "data.steps",
            "description": "<p>返回数据结果</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.steps.node",
            "description": "<p>数据</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.steps.serializeNode",
            "description": "<p>序列化数据</p>"
          },
          {
            "group": "Success 200",
            "type": "List",
            "optional": true,
            "field": "data.indexInfo",
            "description": "<p>返回指标信息</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.indexInfo.entityId",
            "description": "<p>指标id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.indexInfo.entityName",
            "description": "<p>指标英文名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.indexInfo.entityDesc",
            "description": "<p>指标中文名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.indexInfo.businessDefinition",
            "description": "<p>业务定义</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.indexInfo.businessRule",
            "description": "<p>业务口径</p>"
          },
          {
            "group": "Success 200",
            "type": "List",
            "optional": true,
            "field": "data.tabulate",
            "description": "<p>返回列表结果</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response: {\"data\":{ \"steps\":[{\"node\":\"\",\"serializeNode\":\"\"}],",
          "content": "\"tabulate\":[{id:\"\",\"name\":\"\",\"amount\":\"\",\"maintenance\":\"\",\"totalAssets\":\"\"}],\n\"indexInfo\":[{\"entityId\":\"\",\"entityName\":\"\",\"entityDesc\":\"\",\"businessDefinition\":\"\",\"businessRule\":\"\"}]\n} }",
          "type": "json"
        }
      ]
    },
    "contentType": "application/json",
    "filename": "../../java/com/quantchi/intelquery/controller/IntelQueryController.java",
    "groupTitle": "IntelQueryController"
  },
  {
    "type": "post",
    "url": "/api/basicQuery",
    "title": "智能取数查询接口",
    "permission": [
      {
        "name": "none"
      }
    ],
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/api/basicQuery"
      }
    ],
    "name": "basicQuery",
    "group": "IntelQueryController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "query",
            "description": "<p>查询语句</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "businessName",
            "description": "<p>业务名称</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "businessID",
            "description": "<p>业务名称</p>"
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
            "description": "<p>返回推荐问句列表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "data.sentenceId",
            "description": "<p>问句列表搜索库中的id,用于点赞</p>"
          },
          {
            "group": "Success 200",
            "type": "Map",
            "optional": true,
            "field": "data.candidates",
            "description": "<p>返回联想问句</p>"
          },
          {
            "group": "Success 200",
            "type": "List",
            "optional": true,
            "field": "data.candidates.queryNodes",
            "description": "<p>返回问句的分词集合</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.candidates.queryNodes.node",
            "description": "<p>问句的分词</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.candidates.queryNodes.serializeNode",
            "description": "<p>问句的分词的序列化</p>"
          },
          {
            "group": "Success 200",
            "type": "List",
            "optional": true,
            "field": "data.candidates.composeList",
            "description": "<p>问句截断结合</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.candidates.composeList.begin",
            "description": "<p>问句起截断</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.candidates.composeList.end",
            "description": "<p>问句始截断</p>"
          },
          {
            "group": "Success 200",
            "type": "List",
            "optional": true,
            "field": "data.candidates.composeList.compose",
            "description": "<p>替换词集合</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.candidates.composeList.compose.node",
            "description": "<p>替换词分词</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.candidates.composeList.compose.serializeNode",
            "description": "<p>替换词分词的序列化</p>"
          },
          {
            "group": "Success 200",
            "type": "List",
            "optional": true,
            "field": "data.steps",
            "description": "<p>返回数据结果</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.steps.node",
            "description": "<p>数据</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.steps.serializeNode",
            "description": "<p>序列化数据</p>"
          },
          {
            "group": "Success 200",
            "type": "List",
            "optional": true,
            "field": "data.indexInfo",
            "description": "<p>返回指标信息</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.indexInfo.entityId",
            "description": "<p>指标id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.indexInfo.entityName",
            "description": "<p>指标英文名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.indexInfo.entityDesc",
            "description": "<p>指标中文名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.indexInfo.businessDefinition",
            "description": "<p>业务定义</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.indexInfo.businessRule",
            "description": "<p>业务口径</p>"
          },
          {
            "group": "Success 200",
            "type": "List",
            "optional": true,
            "field": "data.tabulate",
            "description": "<p>返回列表结果</p>"
          },
          {
            "group": "Success 200",
            "type": "List",
            "optional": true,
            "field": "data.metrics",
            "description": "<p>返回指标结果，如果没有则返回空数组</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.metrics.category",
            "description": "<p>指标分类目录</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.metrics.seg_name",
            "description": "<p>指标分词结果</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.metrics.definition",
            "description": "<p>指标业务定义</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.metrics.db_field",
            "description": "<p>指标数据库字段</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.metrics.id",
            "description": "<p>指标id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.metrics.type",
            "description": "<p>指标类型</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.metrics.dept",
            "description": "<p>两融部门</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.metrics.en_name",
            "description": "<p>指标英文名</p>"
          },
          {
            "group": "Success 200",
            "type": "double",
            "optional": true,
            "field": "data.metrics.hit_ratio",
            "description": "<p>指标搜索分数</p>"
          },
          {
            "group": "Success 200",
            "type": "double",
            "optional": true,
            "field": "data.metrics._version_",
            "description": "<p>搜索系统里的版本号</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response: {\"data\":{\"candidates\":{\"queryNodes\":[{\"node\":\"\",\"serializeNode\":\"\"}],",
          "content": "\"composeList\":[{\"begin\":\"\", \"end\":\"\", \"compose\":[{\"node\":\"\",\"serializeNode\":\"\"},{\"node\":\"\",\"serializeNode\":\"\"}]},\n{\"begin\":\"\", \"end\":\"\",\"compose\":[{\"node\":\"\",\"serializeNode\":\"\"},{\"node\":\"\",\"serializeNode\":\"\"}]}\n]}, \"steps\":[{\"node\":\"\",\"serializeNode\":\"\"}], \"tabulate\":[{id:\"\",\"name\":\"\",\"amount\":\"\",\"maintenance\":\"\",\"totalAssets\":\"\"}],\n\"metrics\":[ { \"cn_name\": \"融资负债\", \"category\": \"融资融券>两融客户>资产负债\", \"seg_name\": \"融资 负债\",\n\"definition\": \"融资负债\", \"db_field\": \"dmp_demo.fact_cust_balance.fin_debit\", \"id\": \"224\", \"type\":\n\"entity\", \"dept\": \"两融部门\", \"en_name\": \"fin_debit\", \"_version_\": 1602053511199064069,\n\"hit_ratio\": 1 } ], \"indexInfo\":[{\"entityId\":\"\",\"entityName\":\"\",\"entityDesc\":\"\",\"businessDefinition\":\"\",\"businessRule\":\"\"}]\n} }",
          "type": "json"
        }
      ]
    },
    "contentType": "application/json",
    "filename": "../../java/com/quantchi/intelquery/controller/IntelQueryController.java",
    "groupTitle": "IntelQueryController"
  },
  {
    "type": "get",
    "url": "/api/download",
    "title": "智能取数下载接口",
    "permission": [
      {
        "name": "none"
      }
    ],
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/api/download"
      }
    ],
    "name": "download",
    "group": "IntelQueryController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "queryWithNodes",
            "description": "<p>查询语句的序列化</p>"
          }
        ]
      }
    },
    "filename": "../../java/com/quantchi/intelquery/controller/IntelQueryController.java",
    "groupTitle": "IntelQueryController"
  },
  {
    "type": "get",
    "url": "/api/getBusiCate",
    "title": "智能取数页面获取业务接口",
    "permission": [
      {
        "name": "none"
      }
    ],
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/api/getBusiCate"
      }
    ],
    "name": "getBusiCate",
    "group": "IntelQueryController",
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
            "description": "<p>返回业务类别数据列表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.id",
            "description": "<p>返回业务类别id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.businessTypeName",
            "description": "<p>返回业务类别名称</p>"
          }
        ]
      }
    },
    "filename": "../../java/com/quantchi/intelquery/controller/IntelQueryController.java",
    "groupTitle": "IntelQueryController"
  },
  {
    "type": "get",
    "url": "/api/getRecommendQuery",
    "title": "获取推荐问句接口",
    "permission": [
      {
        "name": "none"
      }
    ],
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/api/getRecommendQuery"
      }
    ],
    "name": "getRecommendQuery",
    "group": "IntelQueryController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "businessTypeId",
            "description": "<p>业务分类id</p>"
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
            "description": "<p>返回推荐问句列表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.id",
            "description": "<p>返回推荐问句id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.query",
            "description": "<p>返回推荐问句</p>"
          }
        ]
      }
    },
    "filename": "../../java/com/quantchi/intelquery/controller/IntelQueryController.java",
    "groupTitle": "IntelQueryController"
  },
  {
    "type": "get",
    "url": "/api/getRelatedQuery",
    "title": "获取相关问句接口",
    "permission": [
      {
        "name": "none"
      }
    ],
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/api/getRelatedQuery"
      }
    ],
    "name": "getRelatedQuery",
    "group": "IntelQueryController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "keyword",
            "description": "<p>关键词</p>"
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
            "description": "<p>返回推荐问句列表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.id",
            "description": "<p>返回相关问句id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.query",
            "description": "<p>返回相关问句</p>"
          }
        ]
      }
    },
    "filename": "../../java/com/quantchi/intelquery/controller/IntelQueryController.java",
    "groupTitle": "IntelQueryController"
  },
  {
    "type": "post",
    "url": "/api/likenum",
    "title": "点赞接口",
    "permission": [
      {
        "name": "none"
      }
    ],
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/api/likenum"
      }
    ],
    "name": "likenum",
    "group": "IntelQueryController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>点赞语句id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "dislikeNums",
            "description": "<p>否定数</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "query",
            "description": "<p>点赞语句</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "likeNums",
            "description": "<p>点赞数</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "username",
            "description": "<p>用户名</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "businessName",
            "description": "<p>业务类型</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "querySql",
            "description": "<p>查询的sql</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "intelqueryVer",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "feedback",
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
            "field": "msg",
            "description": "<p>成功或者错误信息</p>"
          }
        ]
      }
    },
    "filename": "../../java/com/quantchi/intelquery/controller/IntelQueryController.java",
    "groupTitle": "IntelQueryController"
  },
  {
    "type": "get",
    "url": "/api/queryInstance",
    "title": "键盘精灵接口",
    "permission": [
      {
        "name": "none"
      }
    ],
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/api/queryInstance"
      }
    ],
    "name": "queryInstance",
    "group": "IntelQueryController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "q",
            "description": "<p>查询语句</p>"
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
            "description": "<p>返回推荐问句列表</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response: \"data\": [ { \"_version_\": 1602053511204306951,",
          "content": "\"category\": \"融资融券>两融合同>合同主信息\", \"cn_name\": \"融资头寸\", \"db_field\": \"dmp_demo.dim_contract.fin_cashgroup_no\",\n\"definition\": \"融资头寸\", \"dept\": \"两融部门\", \"en_name\": \"fin_cashgroup_no\", \"hit_ratio\": 0.5, \"id\":\n\"348\", \"replace_origin\": \"融资\\\"\", \"replace_origin_seg\": \"融资\", \"seg_name\": \"融资 头寸\", \"type\":\n\"entity\", \"weight\": 0.65 } ]",
          "type": "json"
        }
      ]
    },
    "contentType": "application/json",
    "filename": "../../java/com/quantchi/intelquery/controller/IntelQueryController.java",
    "groupTitle": "IntelQueryController"
  },
  {
    "type": "post",
    "url": "/api/stepsQuery",
    "title": "智能取数步骤查询接口",
    "permission": [
      {
        "name": "none"
      }
    ],
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/api/stepsQuery"
      }
    ],
    "name": "stepsQuery",
    "group": "IntelQueryController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "query",
            "description": "<p>查询语句</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "querySerialize",
            "description": "<p>序列化之后的查询语句</p>"
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
            "description": "<p>返回推荐问句列表</p>"
          },
          {
            "group": "Success 200",
            "type": "List",
            "optional": true,
            "field": "data.tabulate",
            "description": "<p>返回列表结果</p>"
          }
        ]
      }
    },
    "filename": "../../java/com/quantchi/intelquery/controller/IntelQueryController.java",
    "groupTitle": "IntelQueryController"
  },
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
            "field": "metricId",
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
            "type": "Object",
            "optional": true,
            "field": "data",
            "description": "<p>返回血缘数据列表</p>"
          },
          {
            "group": "Success 200",
            "type": "List",
            "optional": true,
            "field": "data.nodes",
            "description": "<p>返回血缘数据列表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.nodes.metricId",
            "description": "<p>指标id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.nodes.metricName",
            "description": "<p>指标名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.nodes.phsicalFieldName",
            "description": "<p>物理字段名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.nodes.phsicalFieldDesc",
            "description": "<p>物理字段中文名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.nodes.isOpen",
            "description": "<p>是否可点击</p>"
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
            "type": "String",
            "optional": true,
            "field": "data.edges.source",
            "description": "<p>边源节点id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
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
    "type": "post",
    "url": "/api/codeDefinition",
    "title": "查询代码定义",
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/api/codeDefinition"
      }
    ],
    "name": "codeDefinition",
    "group": "StandInfoController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "id",
            "description": "<p>id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "udcCode",
            "description": "<p>代码编号</p>"
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
            "description": "<p>返回数据 代码列表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.id",
            "description": "<p>代码id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.udcRuleName",
            "description": "<p>代码规则名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.udcCode",
            "description": "<p>编码值</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.udcValue",
            "description": "<p>编码取值</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.udcDesc",
            "description": "<p>编码说明</p>"
          }
        ]
      }
    },
    "filename": "../../java/com/quantchi/termInfo/controller/StandInfoController.java",
    "groupTitle": "StandInfoController"
  },
  {
    "type": "post",
    "url": "/api/insertMetric",
    "title": "指标新增接口",
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/api/insertMetric"
      }
    ],
    "name": "insertMetric",
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
          }
        ]
      }
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "entityId",
            "description": "<p>指标id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "entityName",
            "description": "<p>指标名称</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "entityDesc",
            "description": "<p>指标描述</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "entityDomainId",
            "description": "<p>指标主题id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "entityCategory",
            "description": "<p>指标分类</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "standardLevel",
            "description": "<p>指标层次</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "business_definition",
            "description": "<p>业务定义</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "according",
            "description": "<p>制定依据</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "supervision",
            "description": "<p>监管标志</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "udcRuleName",
            "description": "<p>编码规则</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "entityType",
            "description": "<p>指标类型</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "businessRule",
            "description": "<p>业务口径</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "displayType",
            "description": "<p>常用维度</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "frequency",
            "description": "<p>统计频率</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "dataType",
            "description": "<p>数据类型</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "dataUnit",
            "description": "<p>度量单位</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "dataLength",
            "description": "<p>数据长度</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "dataPrecision",
            "description": "<p>数据精度</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "dataArea",
            "description": "<p>取值范围</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "udcCode",
            "description": "<p>引用代码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "systemUsed",
            "description": "<p>落地系统</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "techniqueRule",
            "description": "<p>技术口径</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "systemFrom",
            "description": "<p>来源系统</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "oralTechniqueRule",
            "description": "<p>源系统技术口径</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "controlDept",
            "description": "<p>管理部门</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "entityStatus",
            "description": "<p>发布状态</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "effectiveTime",
            "description": "<p>生效日期</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "offlineTime",
            "description": "<p>失效日期</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "remark",
            "description": "<p>备注</p>"
          }
        ]
      }
    },
    "filename": "../../java/com/quantchi/termInfo/controller/StandInfoController.java",
    "groupTitle": "StandInfoController"
  },
  {
    "type": "post",
    "url": "/api/selectBusiness",
    "title": "业务线查询接口",
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/api/selectBusiness"
      }
    ],
    "name": "selectBusiness",
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
            "description": "<p>返回数据 业务分类列表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.id",
            "description": "<p>业务分类id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.businessName",
            "description": "<p>业务线名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.domainName",
            "description": "<p>业务主体名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.tableName",
            "description": "<p>表名称</p>"
          }
        ]
      }
    },
    "filename": "../../java/com/quantchi/termInfo/controller/StandInfoController.java",
    "groupTitle": "StandInfoController"
  },
  {
    "type": "post",
    "url": "/api/selectMetric",
    "title": "指标查询接口",
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/api/selectMetric"
      }
    ],
    "name": "selectMetric",
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
            "field": "entityName",
            "description": "<p>指标名称关键字</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "entityId",
            "description": "<p>指标编码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "entityCategory",
            "description": "<p>指标分类Id</p>"
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
            "description": "<p>返回数据 指标信息列表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entityId",
            "description": "<p>指标id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entityName",
            "description": "<p>指标英文名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entityDesc",
            "description": "<p>指标中文名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entityDomainId",
            "description": "<p>指标主题id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entityCategory",
            "description": "<p>指标分类</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.standardLevel",
            "description": "<p>指标层次</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.businessDefinition",
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
    "type": "post",
    "url": "/api/selectPhysicalProperty",
    "title": "查询指标物理字段信息",
    "version": "1.0.0",
    "sampleRequest": [
      {
        "url": "http://192.168.2.61:8082/quantchiAPI/api/selectPhysicalProperty"
      }
    ],
    "name": "selectPhysicalProperty",
    "group": "StandInfoController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "entityId",
            "description": "<p>指标编号</p>"
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
            "description": "<p>返回数据 物理信息列表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.fieldId",
            "description": "<p>字段编号</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.entityId",
            "description": "<p>指标编号</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.physicalTable",
            "description": "<p>表名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.physicalDb",
            "description": "<p>数据源</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "data.physicalField",
            "description": "<p>字段名</p>"
          }
        ]
      }
    },
    "filename": "../../java/com/quantchi/termInfo/controller/StandInfoController.java",
    "groupTitle": "StandInfoController"
  },
  {
    "type": "post",
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
            "field": "entityName",
            "description": "<p>标准名称关键字</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "entityId",
            "description": "<p>标准编码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "entityCategory",
            "description": "<p>标准分类Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "entityDomainId",
            "description": "<p>主题id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "entityType",
            "description": "<p>指标类型</p>"
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
            "field": "data.businessDefinition",
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
    "type": "post",
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
