package org.shirakawatyu.webnotes.common.code;

public enum LoginCode implements ReturnCode {
    NOT_LOGIN(-6, "NOT_LOGIN");


    int code;
    String msg;

    LoginCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
