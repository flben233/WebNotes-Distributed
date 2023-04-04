package org.shirakawatyu.webnotes.common.code;

public enum PublicCode implements ReturnCode {
    SUCCESS(0, "成功")
    ;

    int code;
    String msg;
    PublicCode(int code, String msg) {
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
