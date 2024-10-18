package cc.realtec.real.xxljob.dao;

import cc.realtec.real.xxljob.core.model.XxlJobLogReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * job log
 *
 * @author xuxueli 2019-11-22
 */
@Mapper
public interface XxlJobLogReportDao {

    int save(XxlJobLogReport xxlJobLogReport);

    int update(XxlJobLogReport xxlJobLogReport);

    List<XxlJobLogReport> queryLogReport(@Param("triggerDayFrom") LocalDateTime triggerDayFrom,
                                         @Param("triggerDayTo") LocalDateTime triggerDayTo);

    XxlJobLogReport queryLogReportTotal();

}
