package cc.realtec.real.xxljob.executor.service;

import cc.realtec.real.xxljob.common.annotation.XxlJobInfoRegistry;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class SampleXxlJob {

//    @XxlJobInfoRegistry(cron = "0/5 * * * * ?", jobDesc = "demo job", author = "demo")
    @XxlJobInfoRegistry(schedule_type = "NONE", jobDesc = "demo job", author = "demo")
    @XxlJob("demoJobHandler")
    public void demoJobHandler() throws InterruptedException {
        log.info(">>>>>> xxl-job demo handler.");

        for (int i = 0; i < 5; i++) {
            XxlJobHelper.log("beat at : " + i);
            TimeUnit.SECONDS.sleep(2);
        }
    }
}
