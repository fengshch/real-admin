package cc.realtec.real.common.web.entity;

import cc.realtec.real.common.web.constants.ResultEnum;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.io.Serial;
import java.io.Serializable;

@Data
public class ApiResult<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 8404836422718930994L;

    public ApiResult(){
        timestamp = System.currentTimeMillis();
    }

    private int code;

    private T data;

    private String message;

    private long timestamp;

    public static <T> ApiResult<T> success(){
        return success(null);
    }

    public static <T> ApiResult<T> success(T data){
        ApiResult<T> apiResult = new ApiResult<T>();
        apiResult.setData(data);
        apiResult.setCode(ResultEnum.RC200.getCode());
        apiResult.setMessage(ResultEnum.RC200.getMessage());
        return apiResult;
    }

    public static <T> ApiResult<T> error(ResultEnum resultEnum){
        ApiResult<T> apiResult = new ApiResult<T>();
        apiResult.setCode(resultEnum.getCode());
        apiResult.setMessage(resultEnum.getMessage());
        return apiResult;
    }

    public static <T> ApiResult<T> error(int code, String message){
        ApiResult<T> apiResult = new ApiResult<T>();
        apiResult.setCode(code);
        apiResult.setMessage(message);
        return apiResult;
    }

    public static <T> ApiResult<T> error(ResultEnum resultEnum, String message) {
        ApiResult<T> apiResult = new ApiResult<T>();
        apiResult.setCode(resultEnum.getCode());
        if (StringUtils.hasText(message)) {
            apiResult.setMessage(message);
        }else{
            apiResult.setMessage(resultEnum.getMessage());
        }
        return apiResult;
    }
}
