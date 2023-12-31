package com.xypsp.admin.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xypsp.admin.enums.ResponseEnum;
import lombok.Data;

/**
 * @author rp
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> {
    private Integer code;
    private String msg;
    private T data;

    private ResultVO(T data) {
        this.code = 0;
        this.msg = "成功";
        this.data = data;
    }

    private ResultVO(Integer code, String message) {
        this.code = code;
        this.msg = message;
    }

    public static <T> ResultVO<T> success(T data) {
        return new ResultVO<>(data);
    }

    public static <T> ResultVO<T> success() {
        return success(null);
    }

    public static <T> ResultVO<T> fail(Integer code, String message) {
        return new ResultVO<>(code, message);
    }

    public static <T> ResultVO<T> fail(String message) {
        return new ResultVO<>(1, message);
    }

    public static <T> ResultVO<T> fail(ResponseEnum responseEnum) {
        return new ResultVO<>(responseEnum.getCode(), responseEnum.getMsg());
    }
}
