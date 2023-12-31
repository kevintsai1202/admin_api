package com.xypsp.admin.domain.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author rp
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class CodeMsgException extends RuntimeException {
    private Integer code;
    private String msg;

    public CodeMsgException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

}
