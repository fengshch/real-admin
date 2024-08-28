package cc.realtec.real.common.web.advice;

import cc.realtec.real.common.web.constants.ResultEnum;
import cc.realtec.real.common.web.entity.ApiResult;
import cc.realtec.real.common.web.exception.BusinessException;
import cc.realtec.real.common.web.exception.ForbiddenException;
import cc.realtec.real.common.web.exception.ResourceNotFoundException;
import cc.realtec.real.common.web.exception.UnauthorizedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResult<?>> exceptionHandler(Exception e){
        return  ResponseEntity.ok(ApiResult.error(500, e.getMessage()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResult<?>> notFoundExceptionHandler(ResourceNotFoundException exception){
        return ResponseEntity.ok(ApiResult.error(ResultEnum.RC404.getCode(), exception.getMessage()));
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ApiResult<?>> accessDeniedExceptionHandler(ForbiddenException exception){
       return ResponseEntity.ok(ApiResult.error(ResultEnum.RC403.getCode(), exception.getMessage()));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResult<?>> authenticationExceptionHandler(UnauthorizedException exception){
        return ResponseEntity.ok(ApiResult.error(ResultEnum.RC401, exception.getMessage()));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResult<?>> businessExceptionHandler(BusinessException exception){
        return ResponseEntity.ok(ApiResult.error(ResultEnum.RC900.getCode(),exception.getMessage()));
    }
}
