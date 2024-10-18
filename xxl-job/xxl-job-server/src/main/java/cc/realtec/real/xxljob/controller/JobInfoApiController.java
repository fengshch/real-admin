package cc.realtec.real.xxljob.controller;

import cc.realtec.real.xxljob.common.model.XxlJobInfoRequest;
import cc.realtec.real.xxljob.common.model.XxlJobTaskRequest;
import cc.realtec.real.xxljob.controller.annotation.PermissionLimit;
import cc.realtec.real.xxljob.core.model.XxlJobGroup;
import cc.realtec.real.xxljob.core.model.XxlJobInfo;
import cc.realtec.real.xxljob.core.util.I18nUtil;
import cc.realtec.real.xxljob.dao.XxlJobGroupDao;
import cc.realtec.real.xxljob.service.XxlJobService;
import com.xxl.job.core.biz.model.ReturnT;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jobInfo")
@RequiredArgsConstructor
public class JobInfoApiController {

    private final XxlJobService xxlJobService;
    private final XxlJobGroupDao xxlJobGroupDao;


    @PostMapping("/registry")
    @PermissionLimit(limit = false)
    public ReturnT<String> registry(@RequestBody XxlJobInfoRequest xxlJobInfoRequest) {
        XxlJobInfo xxlJobInfo = new XxlJobInfo();
        XxlJobGroup xxlJobGroup = xxlJobGroupDao.findByAppName(xxlJobInfoRequest.getAppName());
        if (xxlJobGroup == null) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_choose") + I18nUtil.getString("jobinfo_field_jobgroup")));
        }
        xxlJobInfo.setJobGroup(xxlJobGroup.getId());
        xxlJobInfo.setJobDesc(xxlJobInfoRequest.getJobDesc());
        xxlJobInfo.setAuthor(xxlJobInfoRequest.getAuthor());
        xxlJobInfo.setExecutorHandler(xxlJobInfoRequest.getExecutorHandler());
        xxlJobInfo.setScheduleType(xxlJobInfoRequest.getScheduleType());
        if (xxlJobInfoRequest.getScheduleType().equals("NONE")) {
            xxlJobInfo.setScheduleConf("");
        }
        xxlJobInfo.setScheduleConf(xxlJobInfoRequest.getScheduleConf());
        xxlJobInfo.setExecutorParam(xxlJobInfoRequest.getExecutorParam());
        xxlJobInfo.setGlueType("BEAN");
        xxlJobInfo.setExecutorRouteStrategy("FIRST");
        xxlJobInfo.setMisfireStrategy("DO_NOTHING");
        xxlJobInfo.setExecutorBlockStrategy("SERIAL_EXECUTION");
        return xxlJobService.add(xxlJobInfo);
    }

    @PostMapping("/trigger")
    @PermissionLimit(limit = false)
    public ReturnT<String> triggerJob(@RequestBody XxlJobTaskRequest xxlJobTaskRequest) {
        String appName = xxlJobTaskRequest.getAppName();
        String executorHandler = xxlJobTaskRequest.getExecutorHandler();
        String executorParam = xxlJobTaskRequest.getExecutorParam();
        if (appName == null || appName.isEmpty()) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_choose") + I18nUtil.getString("jobinfo_field_jobgroup")));
        }
        if (executorHandler == null || executorHandler.isEmpty()) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_choose") + I18nUtil.getString("jobinfo_field_executorHandler")));
        }
        return xxlJobService.trigger(appName, executorHandler, executorParam);
    }

    @PostMapping("/start")
    @PermissionLimit(limit = false)
    public ReturnT<String> start(@RequestBody XxlJobTaskRequest xxlJobTaskRequest) {
        String appName = xxlJobTaskRequest.getAppName();
        String executorHandler = xxlJobTaskRequest.getExecutorHandler();
        if (appName == null || appName.isEmpty()) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_choose") + I18nUtil.getString("jobinfo_field_jobgroup")));
        }
        if (executorHandler == null || executorHandler.isEmpty()) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_choose") + I18nUtil.getString("jobinfo_field_executorHandler")));
        }
        return xxlJobService.start(appName, executorHandler);
    }

    @PostMapping("/stop")
    @PermissionLimit(limit = false)
    public ReturnT<String> stop(@RequestBody XxlJobTaskRequest xxlJobTaskRequest) {
        String appName = xxlJobTaskRequest.getAppName();
        String executorHandler = xxlJobTaskRequest.getExecutorHandler();
        if (appName == null || appName.isEmpty()) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_choose") + I18nUtil.getString("jobinfo_field_jobgroup")));
        }
        if (executorHandler == null || executorHandler.isEmpty()) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_choose") + I18nUtil.getString("jobinfo_field_executorHandler")));
        }
        return xxlJobService.stop(appName, executorHandler);
    }
}
