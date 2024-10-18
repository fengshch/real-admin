package cc.realtec.real.xxljob.controller;

import cc.realtec.real.xxljob.controller.annotation.PermissionLimit;
import cc.realtec.real.xxljob.core.conf.XxlJobAdminConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xxl.job.core.biz.AdminBiz;
import com.xxl.job.core.biz.model.HandleCallbackParam;
import com.xxl.job.core.biz.model.RegistryParam;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.util.GsonTool;
import com.xxl.job.core.util.XxlJobRemotingUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class JobApiController {

    private final AdminBiz adminBiz;

    @PostMapping("/callback")
    @PermissionLimit(limit = false)
    public ReturnT<String> apiCallback(HttpServletRequest request, @RequestBody(required = false) String data) throws JsonProcessingException {
        if (XxlJobAdminConfig.getAdminConfig().getAccessToken() != null && !XxlJobAdminConfig.getAdminConfig().getAccessToken().trim().isEmpty() && !XxlJobAdminConfig.getAdminConfig().getAccessToken().equals(request.getHeader(XxlJobRemotingUtil.XXL_JOB_ACCESS_TOKEN))) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "The access token is wrong.");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<HandleCallbackParam> callbackParamList = objectMapper.readValue(data, new TypeReference<>() {
        });
        return adminBiz.callback(callbackParamList);
    }


    @PostMapping("/registry")
    @PermissionLimit(limit = false)
    public ReturnT<String> apiRegistry(HttpServletRequest request, @RequestBody(required = false) String data) throws JsonProcessingException {

        if (XxlJobAdminConfig.getAdminConfig().getAccessToken() != null && !XxlJobAdminConfig.getAdminConfig().getAccessToken().trim().isEmpty() && !XxlJobAdminConfig.getAdminConfig().getAccessToken().equals(request.getHeader(XxlJobRemotingUtil.XXL_JOB_ACCESS_TOKEN))) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "The access token is wrong.");
        }

        RegistryParam registryParam = GsonTool.fromJson(data, RegistryParam.class);
        return adminBiz.registry(registryParam);

    }

    @PostMapping("/registryRemove")
    @PermissionLimit(limit = false)
    public ReturnT<String> apiRegistryRemove(HttpServletRequest request, @RequestBody(required = false) String data) throws JsonProcessingException {

        if (XxlJobAdminConfig.getAdminConfig().getAccessToken() != null && !XxlJobAdminConfig.getAdminConfig().getAccessToken().trim().isEmpty() && !XxlJobAdminConfig.getAdminConfig().getAccessToken().equals(request.getHeader(XxlJobRemotingUtil.XXL_JOB_ACCESS_TOKEN))) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "The access token is wrong.");
        }
        RegistryParam registryParam = GsonTool.fromJson(data, RegistryParam.class);
        return adminBiz.registryRemove(registryParam);
    }
}
