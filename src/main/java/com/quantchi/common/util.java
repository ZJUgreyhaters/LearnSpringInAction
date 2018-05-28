package com.quantchi.common;

import java.util.HashMap;
import java.util.Map;

public class util {

    public static Map<String, Object> genRet(int code,Object data,String retMsg,int total){
        Map<String, Object> _ret = new HashMap<String, Object>();
        _ret.put("code",code);
        _ret.put("data",data);
        _ret.put("msg",retMsg);
        _ret.put("total",total);
        return _ret;
    }

}
