package cc.realtec.real.xxljob.executor.listener;

import cc.realtec.real.xxljob.common.model.XxlJobGroupRequest;
import cc.realtec.real.xxljob.common.model.XxlJobInfoRequest;
import cc.realtec.real.xxljob.common.annotation.XxlJobInfoRegistry;
import cc.realtec.real.xxljob.executor.config.XxlJobConfig;
import cc.realtec.real.xxljob.common.client.XxlJobClient;
import cc.realtec.real.xxljob.executor.service.XxlJobInfoAnnotationScanner;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicationInitiator implements ApplicationListener<ApplicationReadyEvent> {
    private final XxlJobConfig xxlJobConfig;
    private final XxlJobClient xxlJobClient;
    private final XxlJobInfoAnnotationScanner xxlJobInfoAnnotationScanner;

    @Override
    public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
        registryJobGroup();
        registryJobInfo();
    }

    private void registryJobGroup() {
        XxlJobGroupRequest xxlJobGroup = new XxlJobGroupRequest();
        xxlJobGroup.setAppName(xxlJobConfig.getExecutorAppName());
        xxlJobGroup.setTitle(xxlJobConfig.getExecutorTitle());
        ReturnT<String> rtn = xxlJobClient.jobGroupRegistry(xxlJobGroup);
        log.info("Registry Job Group :{}", rtn);
    }

    private void registryJobInfo() {
        List<Method> methods = xxlJobInfoAnnotationScanner.findMethodsAnnotatedWithXxlJobRegistry();
        methods.forEach(method -> {
            XxlJobInfoRegistry xxlJobInfoRegistry = method.getAnnotation(XxlJobInfoRegistry.class);
            XxlJob xxlJob = method.getAnnotation(XxlJob.class);

            XxlJobInfoRequest xxlJobInfoRequest = new XxlJobInfoRequest();
            xxlJobInfoRequest.setAppName(xxlJobConfig.getExecutorAppName());
            xxlJobInfoRequest.setJobDesc(xxlJobInfoRegistry.jobDesc());
            xxlJobInfoRequest.setAuthor(xxlJobInfoRegistry.author());
            xxlJobInfoRequest.setExecutorHandler(xxlJob.value());
            xxlJobInfoRequest.setScheduleType(xxlJobInfoRegistry.schedule_type());
            xxlJobInfoRequest.setScheduleConf(xxlJobInfoRegistry.cron());
            xxlJobInfoRequest.setExecutorParam("");

            ReturnT<String> rtn = xxlJobClient.jobInfoRegistry(xxlJobInfoRequest);
            log.info("Registry Job Info :{}", rtn);
        });
    }

}
