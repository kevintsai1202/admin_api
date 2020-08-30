package com.xypsp.admin.enums;

/**
 * @author rp
 */

public enum ImageDirEnum implements BaseEnum {
    /**
     * 图片类型
     * */
    banner(0, "banner"),
    music(1, "header"),
    img(2,"storeImg");
    private Integer code;
    private String msg;

    ImageDirEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
