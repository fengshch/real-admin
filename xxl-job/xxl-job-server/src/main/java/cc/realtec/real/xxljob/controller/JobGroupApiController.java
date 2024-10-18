package cc.realtec.real.xxljob.controller;

import cc.realtec.real.xxljob.common.model.XxlJobGroupRequest;
import cc.realtec.real.xxljob.core.model.XxlJobGroup;
import cc.realtec.real.xxljob.controller.annotation.PermissionLimit;
import cc.realtec.real.xxljob.core.util.I18nUtil;
import cc.realtec.real.xxljob.dao.XxlJobGroupDao;
import com.xxl.job.core.biz.model.ReturnT;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/jobGroup")
@RequiredArgsConstructor
public class JobGroupApiController {
    private final XxlJobGroupDao xxlJobGroupDao;

    @PostMapping("/registry")
    @PermissionLimit(limit = false )
    public ReturnT<String> registry(@RequestBody XxlJobGroupRequest xxlJobGroupRequest) {

        String appName = xxlJobGroupRequest.getAppName();
        String title = xxlJobGroupRequest.getTitle();

        if (appName == null || appName.trim().isEmpty()) {
            return new ReturnT<>(500, I18nUtil.getString("system_please_input") + "AppName");
        }
        if (appName.length() < 4 || appName.length() > 64) {
            return new ReturnT<>(500, I18nUtil.getString("jobgroup_field_appname_length"));
        }
        if (appName.contains(">") || appName.contains("<")) {
            return new ReturnT<>(500, "AppName" + I18nUtil.getString("system_unvalid"));
        }
        if (title == null || title.trim().isEmpty()) {
            return new ReturnT<>(500, I18nUtil.getString("system_please_input") + I18nUtil.getString("jobgroup_field_title"));
        }
        if (title.contains(">") || title.contains("<")) {
            return new ReturnT<>(500, I18nUtil.getString("jobgroup_field_title") + I18nUtil.getString("system_unvalid"));
        }

// process
        XxlJobGroup byAppName = xxlJobGroupDao.findByAppName(appName);

        int ret = 0;
        if (byAppName != null) {
            byAppName.setTitle(title);
            byAppName.setAddressType(0);
            byAppName.setUpdateTime(LocalDateTime.now());
            ret =xxlJobGroupDao.update(byAppName);
        } else {
            XxlJobGroup xxlJobGroup = new XxlJobGroup();
            xxlJobGroup.setAppName(appName);
            xxlJobGroup.setTitle(title);
            xxlJobGroup.setAddressType(0);
            ret = xxlJobGroupDao.save(xxlJobGroup);
        }
        return ret > 0 ? ReturnT.SUCCESS : ReturnT.FAIL;
    }
}
