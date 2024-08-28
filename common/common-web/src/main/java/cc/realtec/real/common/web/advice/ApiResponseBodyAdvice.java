package cc.realtec.real.common.web.advice;

import cc.realtec.real.common.web.entity.ApiResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class ApiResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    final private MappingJackson2HttpMessageConverter converter;

    @Override
    public boolean supports(MethodParameter returnType,
                           @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getDeclaringClass().getName().contains("controller");
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  @NonNull MethodParameter returnType,
                                  @NonNull MediaType selectedContentType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @lombok.NonNull ServerHttpRequest request,
                                  @NonNull ServerHttpResponse response) {
        if (body instanceof ApiResult) {
            return body;
        }
        if (body instanceof String) {
            try {
                if (response instanceof ServletServerHttpResponse) {
                    response.getHeaders().put("content-type", List.of("application/json;charset=UTF-8"));
                }
                return this.converter.getObjectMapper().writeValueAsString(ApiResult.success(body));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return ApiResult.success(body);
    }
}
