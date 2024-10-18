package cc.realtec.real.common.web.advice;

import cc.realtec.real.common.web.domain.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Arrays;
import java.util.List;

@ControllerAdvice
@Slf4j
public class CustomResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    final private MappingJackson2HttpMessageConverter converter;

    public CustomResponseBodyAdvice(MappingJackson2HttpMessageConverter converter) {
        this.converter = converter;
    }

    @Override
    public boolean supports(@NonNull MethodParameter returnType,
                           @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        log.info("ApiResponseBodyAdvice supports: {}", returnType);
        if(returnType.getContainingClass().isAnnotationPresent(RestController.class)){
            return true;
        }else{
            return Arrays.stream(returnType.getContainingClass().getInterfaces()).anyMatch(i -> i.isAnnotationPresent(RestController.class));
        }
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  @NonNull MethodParameter returnType,
                                  @NonNull MediaType selectedContentType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @lombok.NonNull ServerHttpRequest request,
                                  @NonNull ServerHttpResponse response) {
        log.info("ApiResponseBodyAdvice beforeBodyWrite: {}", body);
        if (body == null) {
            return ApiResponse.error();
        }
        if (body instanceof ApiResponse) {
            return body;
        }
        if (body instanceof String || body instanceof Number) {
            try {
                if (response instanceof ServletServerHttpResponse) {
                    response.getHeaders().put("content-type", List.of("application/json;charset=UTF-8"));
                }
                return this.converter.getObjectMapper().writeValueAsString(ApiResponse.successMessage(body.toString()));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return ApiResponse.success(body);
    }
}
