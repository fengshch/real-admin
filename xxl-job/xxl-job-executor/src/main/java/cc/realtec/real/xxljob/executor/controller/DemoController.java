package cc.realtec.real.xxljob.executor.controller;

import cc.realtec.real.xxljob.common.model.XxlJobTaskRequest;
import cc.realtec.real.xxljob.executor.config.XxlJobConfig;
import cc.realtec.real.xxljob.common.client.XxlJobClient;
import com.xxl.job.core.biz.model.ReturnT;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
@RequiredArgsConstructor
public class DemoController {
    private final XxlJobClient xxlJobClient;
    private final XxlJobConfig xxlJobConfig;

    @GetMapping("/hello")
    public String hello() {
        return xxlJobClient.hello("bill");
    }

    @GetMapping("/trigger")
    public ReturnT<String> trigger() {
        XxlJobTaskRequest xxlJobTaskRequest = new XxlJobTaskRequest();
        xxlJobTaskRequest.setAppName(xxlJobConfig.getExecutorAppName());
        xxlJobTaskRequest.setExecutorHandler("demoJobHandler");
        return xxlJobClient.jobInfoTrigger(xxlJobTaskRequest);
    }

    @GetMapping("/start")
    public ReturnT<String> start() {
        XxlJobTaskRequest xxlJobTaskRequest = new XxlJobTaskRequest();
        xxlJobTaskRequest.setAppName(xxlJobConfig.getExecutorAppName());
        xxlJobTaskRequest.setExecutorHandler("demoJobHandler");
        return xxlJobClient.jobInfoStart(xxlJobTaskRequest);
    }

    @GetMapping("/stop")
    public ReturnT<String> stop() {
        XxlJobTaskRequest xxlJobTaskRequest = new XxlJobTaskRequest();
        xxlJobTaskRequest.setAppName(xxlJobConfig.getExecutorAppName());
        xxlJobTaskRequest.setExecutorHandler("demoJobHandler");
        return xxlJobClient.jobInfoStop(xxlJobTaskRequest);
    }
}
