package cc.realtec.real.common.web.constants;

import lombok.Getter;

@Getter
public enum ResultEnum {
    RC200(200, "success"),

    RC401(401, "Unauthorized"),

    RC403(403, "Forbidden"),

    RC404(404, "Not Found"),

    RC500(500, "Internal Server Error"),

    RC900(900, "Business Error");

    private final int code;

    private final String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
