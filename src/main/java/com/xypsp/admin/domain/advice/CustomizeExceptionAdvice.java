package com.xypsp.admin.domain.advice;

import com.xypsp.admin.domain.exception.CustomizeException;
import com.xypsp.admin.domain.response.ResultVO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author rp
 */
@RestControllerAdvice
public class CustomizeExceptionAdvice {
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(CustomizeException.class)
    public ResultVO nonceExpiredException(CustomizeException exception) {
        return ResultVO.fail(exception.getResponseEnum());
    }
}
