package org.shirakawatyu.webnotes.common;

import org.shirakawatyu.webnotes.common.code.PublicCode;

import java.util.HashMap;

/**
 * @author 86138
 */

public class R extends HashMap<String, Object>{

    private R(){
    }

    public static R ok(){
        R r = new R();
        r.put("code", PublicCode.SUCCESS.getCode());
        r.put("msg", PublicCode.SUCCESS.getMessage());
        return r;
    }

    public static R error(int code, String msg){
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public R put(Object data){
        this.put("data", data);
        return this;
    }

    public R putUrl(String url) {
        this.put("url", url);
        return this;
    }

}