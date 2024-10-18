package cc.realtec.real.common.web.advice;

import cc.realtec.real.common.web.domain.ApiResponse;
import cc.realtec.real.common.web.exception.BusinessException;
import cc.realtec.real.common.web.exception.ForbiddenException;
import cc.realtec.real.common.web.exception.ResourceNotFoundException;
import cc.realtec.real.common.web.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> exceptionHandler(Exception e){
        log.error("Exception: ", e);
        ApiResponse<Object> error = ApiResponse.error(e.getMessage());
        return  ResponseEntity.status(error.getCode()).body(error);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> usernameNotFoundExceptionHandler(UsernameNotFoundException exception){
        log.error("User not found: ", exception);
        return ResponseEntity.ok(ApiResponse.notFound(exception.getMessage()));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<?>> noResourceFoundExceptionHandler(NoResourceFoundException exception){
        log.error("Resource not found: ", exception);
        return ResponseEntity.ok(ApiResponse.notFound(exception.getMessage()));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<?>> noHandlerFoundExceptionHandler(NoHandlerFoundException exception){
        log.error("Handler not found: ", exception);
        return ResponseEntity.ok(ApiResponse.notFound(exception.getMessage()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> notFoundExceptionHandler(ResourceNotFoundException exception){
        log.error("Resource not found: ", exception);
        return ResponseEntity.ok(ApiResponse.notFound(exception.getMessage()));
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ApiResponse<?>> accessDeniedExceptionHandler(ForbiddenException exception){
        log.error("Access denied: ", exception);
       return ResponseEntity.ok(ApiResponse.forbidden(exception.getMessage()));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse<?>> authenticationExceptionHandler(UnauthorizedException exception){
        log.error("Unauthorized: ", exception);
        return ResponseEntity.ok(ApiResponse.unauthorized(exception.getMessage()));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<?>> businessExceptionHandler(BusinessException exception){
        log.error("Business error: ", exception);
        return ResponseEntity.ok(ApiResponse.business_error(exception.getMessage()));
    }
}
