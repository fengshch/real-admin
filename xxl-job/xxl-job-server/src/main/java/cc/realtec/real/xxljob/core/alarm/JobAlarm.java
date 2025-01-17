package cc.realtec.real.xxljob.core.alarm;

import cc.realtec.real.xxljob.core.model.XxlJobInfo;
import cc.realtec.real.xxljob.core.model.XxlJobLog;

/**
 * @author xuxueli 2020-01-19
 */
public interface JobAlarm {

    /**
     * job alarm
     *
     * @param info
     * @param jobLog
     * @return
     */
    public boolean doAlarm(XxlJobInfo info, XxlJobLog jobLog);

}
