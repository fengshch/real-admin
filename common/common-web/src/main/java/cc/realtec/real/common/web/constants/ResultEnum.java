package cc.realtec.real.common.web.constants;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS(200, "success"),

    BAD_REQUEST(400, "Bad Request"),

    UNAUTHORIZED(401, "Unauthorized"),

    FORBIDDEN(403, "Forbidden"),

    NOT_FOUND(404, "Not Found"),

    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),

    ERROR(500, "Error"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    BAD_GATEWAY(502, "Bad Gateway"),

    SERVICE_UNAVAILABLE(503, "Service Unavailable"),

    GATEWAY_TIMEOUT(504, "Gateway Timeout"),

    BUSINESS_ERROR(900, "Business Error");

    private final int code;

    private final String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
