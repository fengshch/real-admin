package cc.realtec.real.xxljob.dao;

import cc.realtec.real.xxljob.core.model.XxlJobLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * job log
 *
 * @author xuxueli 2016-1-12 18:03:06
 */
@Mapper
public interface XxlJobLogDao {

    List<XxlJobLog> pageList(@Param("offset") int offset,
                             @Param("pageSize") int pageSize,
                             @Param("jobGroup") int jobGroup,
                             @Param("jobId") int jobId,
                             @Param("triggerTimeStart") LocalDateTime triggerTimeStart,
                             @Param("triggerTimeEnd") LocalDateTime triggerTimeEnd,
                             @Param("logStatus") int logStatus);

    int pageListCount(@Param("offset") int offset,
                      @Param("pageSize") int pageSize,
                      @Param("jobGroup") int jobGroup,
                      @Param("jobId") int jobId,
                      @Param("triggerTimeStart") LocalDateTime triggerTimeStart,
                      @Param("triggerTimeEnd") LocalDateTime triggerTimeEnd,
                      @Param("logStatus") int logStatus);

    XxlJobLog load(@Param("id") long id);

    long save(XxlJobLog xxlJobLog);

    void updateTriggerInfo(XxlJobLog xxlJobLog);

    int updateHandleInfo(XxlJobLog xxlJobLog);

    int delete(@Param("jobId") int jobId);

    Map<String, Object> findLogReport(@Param("from") LocalDateTime from,
                                      @Param("to") LocalDateTime to);

    List<Long> findClearLogIds(@Param("jobGroup") int jobGroup,
                               @Param("jobId") int jobId,
                               @Param("clearBeforeTime") LocalDateTime clearBeforeTime,
                               @Param("clearBeforeNum") int clearBeforeNum,
                               @Param("pageSize") int pageSize);

    void clearLog(@Param("logIds") List<Long> logIds);

    List<Long> findFailJobLogIds(@Param("pageSize") int pageSize);

    int updateAlarmStatus(@Param("logId") long logId,
                          @Param("oldAlarmStatus") int oldAlarmStatus,
                          @Param("newAlarmStatus") int newAlarmStatus);

    List<Long> findLostJobIds(@Param("lostTime") LocalDateTime lostTime);
}