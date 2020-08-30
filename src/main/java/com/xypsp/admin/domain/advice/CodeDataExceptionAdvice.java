package com.xypsp.admin.domain.advice;

import com.xypsp.admin.domain.exception.CodeMsgException;
import com.xypsp.admin.domain.response.ResultVO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author rp
 */
@RestControllerAdvice
public class CodeDataExceptionAdvice {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(CodeMsgException.class)
    public ResultVO nonceExpiredException(CodeMsgException exception) {
        return ResultVO.fail(exception.getCode(),exception.getMsg());
    }

}
