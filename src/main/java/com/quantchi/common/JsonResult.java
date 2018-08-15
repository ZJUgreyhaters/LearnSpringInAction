package com.quantchi.common;

import com.alibaba.fastjson.JSONObject;

/**
 * 用于构造统一格式的json
 */
public class JsonResult {

  public static String successJson(Object obj) {
    return JSONObject.toJSONString(responseResult(ResultCode.SUCCESS, "success", obj, null));
  }


  public static String successJson(Object obj, String code, String message) {
    return JSONObject.toJSONString(responseResult(code, message, obj, null));
  }

  public static String successJson() {
    return JSONObject.toJSONString(responseResult(ResultCode.SUCCESS, "success", null, null));
  }

  public static String successJson(String total, Object obj) {
    return JSONObject.toJSONString(responseResult(ResultCode.SUCCESS, "success", obj, total));
  }

  public static String successJson(String message) {
    return JSONObject.toJSONString(responseResult(ResultCode.SUCCESS, message, null, null));
  }

  public static String errorJson(String message) {
    return JSONObject.toJSONString(responseResult(ResultCode.ERROR, message, null, null));
  }


  public static String errorJson(String errorCode, String message) {
    return JSONObject.toJSONString(responseResult(errorCode, message, null, null));
  }


  public static JsonResponse responseResult(String code, String msg, Object data, String total) {
    JsonResponse response = new JsonResponse();
    response.setCode(code);
    response.setTotal(total);
    response.setData(data);
    response.setMsg(msg);
    return response;
  }

  public static class JsonResponse {

    private String code;
    private String msg;
    private Object data;
    private String total;

    public String getCode() {
      return code;
    }

    public void setCode(String code) {
      this.code = code;
    }

    public String getMsg() {
      return msg;
    }

    public void setMsg(String msg) {
      this.msg = msg;
    }

    public Object getData() {
      return data;
    }

    public void setData(Object data) {
      this.data = data;
    }

    public String getTotal() {
      return total;
    }

    public void setTotal(String total) {
      this.total = total;
    }
  }
}
