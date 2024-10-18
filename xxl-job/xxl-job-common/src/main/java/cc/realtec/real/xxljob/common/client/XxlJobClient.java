package cc.realtec.real.xxljob.common.client;

import cc.realtec.real.xxljob.common.model.XxlJobGroupRequest;
import cc.realtec.real.xxljob.common.model.XxlJobInfoRequest;
import cc.realtec.real.xxljob.common.model.XxlJobTaskRequest;
import com.xxl.job.core.biz.model.ReturnT;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange(url = "${xxl.job.server-url}", accept = "application/json", contentType = "application/json")
public interface XxlJobClient {

    @PostExchange("/api/demo/hello/{username}")
    String hello(@PathVariable String username);

    @PostExchange("/api/jobGroup/registry")
    ReturnT<String> jobGroupRegistry(@RequestBody XxlJobGroupRequest xxlJobGroupRequest);

    @PostExchange("/api/jobInfo/registry")
    ReturnT<String> jobInfoRegistry(@RequestBody XxlJobInfoRequest xxlJobInfoRequest);

    @PostExchange("/api/jobInfo/trigger")
    ReturnT<String> jobInfoTrigger(@RequestBody XxlJobTaskRequest xxlJobTaskRequest);

    @PostExchange("/api/jobInfo/start")
    ReturnT<String> jobInfoStart(@RequestBody XxlJobTaskRequest xxlJobTaskRequest);

    @PostExchange("/api/jobInfo/stop")
    ReturnT<String> jobInfoStop(@RequestBody XxlJobTaskRequest xxlJobTaskRequest);
}
