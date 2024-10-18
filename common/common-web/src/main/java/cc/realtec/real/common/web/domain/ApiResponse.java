package cc.realtec.real.common.web.domain;

import cc.realtec.real.common.web.constants.ResultEnum;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class ApiResponse<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 8404836422718930994L;

    private int code;

    private T data;

    private String message;

    private long timestamp;

    public ApiResponse() {
        timestamp = System.currentTimeMillis();
    }

    public ApiResponse(int code, String message) {
        this();
        this.code = code;
        this.message = message;
    }

    public ApiResponse(int code, String message, T data) {
        this();
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage());
    }

    public static <T> ApiResponse<T> successMessage(String message) {
        return new ApiResponse<>(ResultEnum.SUCCESS.getCode(),message);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), data);
    }

    public static <T> ApiResponse<T> badRequest() {
        return new ApiResponse<>(ResultEnum.BAD_REQUEST.getCode(), ResultEnum.BAD_REQUEST.getMessage());
    }

    public static <T> ApiResponse<T> badRequest(String message) {
        return new ApiResponse<>(ResultEnum.BAD_REQUEST.getCode(), message);
    }

    public static <T> ApiResponse<T> unauthorized() {
        return new ApiResponse<>(ResultEnum.UNAUTHORIZED.getCode(), ResultEnum.UNAUTHORIZED.getMessage());
    }

    public static <T> ApiResponse<T> unauthorized(String message) {
        return new ApiResponse<>(ResultEnum.UNAUTHORIZED.getCode(), message);
    }

    public static <T> ApiResponse<T> forbidden() {
        return new ApiResponse<>(ResultEnum.FORBIDDEN.getCode(), ResultEnum.FORBIDDEN.getMessage());
    }

    public static <T> ApiResponse<T> forbidden(String message) {
        return new ApiResponse<>(ResultEnum.FORBIDDEN.getCode(), message);
    }

    public static <T> ApiResponse<T> notFound() {
        return new ApiResponse<>(ResultEnum.NOT_FOUND.getCode(), ResultEnum.NOT_FOUND.getMessage());
    }

    public static <T> ApiResponse<T> notFound(String message) {
        return new ApiResponse<>(ResultEnum.NOT_FOUND.getCode(), message);
    }

    public static <T> ApiResponse<T> methodNotAllowed() {
        return new ApiResponse<>(ResultEnum.METHOD_NOT_ALLOWED.getCode(), ResultEnum.METHOD_NOT_ALLOWED.getMessage());
    }

    public static <T> ApiResponse<T> methodNotAllowed(String message) {
        return new ApiResponse<>(ResultEnum.METHOD_NOT_ALLOWED.getCode(), message);
    }

    public static <T> ApiResponse<T> error() {
        return new ApiResponse<>(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMessage());
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(ResultEnum.ERROR.getCode(), message);
    }

    public static <T> ApiResponse<T> internalServerError() {
        return new ApiResponse<>(ResultEnum.INTERNAL_SERVER_ERROR.getCode(), ResultEnum.INTERNAL_SERVER_ERROR.getMessage());
    }

    public static <T> ApiResponse<T> internalServerError(String message) {
        return new ApiResponse<>(ResultEnum.INTERNAL_SERVER_ERROR.getCode(), message);
    }

    public static <T> ApiResponse<T> badGateway() {
        return new ApiResponse<>(ResultEnum.BAD_GATEWAY.getCode(), ResultEnum.BAD_GATEWAY.getMessage());
    }

    public static <T> ApiResponse<T> badGateway(String message) {
        return new ApiResponse<>(ResultEnum.BAD_GATEWAY.getCode(), message);
    }

    public static <T> ApiResponse<T> serviceUnavailable() {
        return new ApiResponse<>(ResultEnum.SERVICE_UNAVAILABLE.getCode(), ResultEnum.SERVICE_UNAVAILABLE.getMessage());
    }

    public static <T> ApiResponse<T> serviceUnavailable(String message) {
        return new ApiResponse<>(ResultEnum.SERVICE_UNAVAILABLE.getCode(), message);
    }

    public static <T> ApiResponse<T> gatewayTimeout() {
        return new ApiResponse<>(ResultEnum.GATEWAY_TIMEOUT.getCode(), ResultEnum.GATEWAY_TIMEOUT.getMessage());
    }

    public static <T> ApiResponse<T> gatewayTimeout(String message) {
        return new ApiResponse<>(ResultEnum.GATEWAY_TIMEOUT.getCode(), message);
    }

    public static <T> ApiResponse<T> business_error() {
        return new ApiResponse<>(ResultEnum.BUSINESS_ERROR.getCode(), ResultEnum.BUSINESS_ERROR.getMessage());
    }

    public static <T> ApiResponse<T> business_error(String message) {
        return new ApiResponse<>(ResultEnum.BUSINESS_ERROR.getCode(), message);
    }
    public static <T> ApiResponse<T> of(int code, String message) {
        return new ApiResponse<>(code, message);
    }

    public static <T> ApiResponse<T> of(int code, String message, T data) {
        return new ApiResponse<>(code, message, data);
    }

    public static <T> ApiResponse<T> of(ResultEnum resultEnum) {
        return new ApiResponse<>(resultEnum.getCode(), resultEnum.getMessage());
    }
}
