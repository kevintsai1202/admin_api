package com.xypsp.admin.domain.advice;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.xypsp.admin.domain.response.ResultVO;
import com.xypsp.admin.enums.ResponseEnum;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author rp
 */
@RestControllerAdvice
public class AuthorizationExceptionAdvice {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(JWTDecodeException.class)
    public ResultVO nonceExpiredException(JWTDecodeException exception) {
        return ResultVO.fail(ResponseEnum.TOKEN_PARSING_ERROR);
    }

}
