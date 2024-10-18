package cc.realtec.real.xxljob.dao;

import cc.realtec.real.xxljob.core.model.XxlJobLogGlue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface XxlJobLogGlueDao {

    int save(XxlJobLogGlue xxlJobLogGlue);

    List<XxlJobLogGlue> findByJobId(@Param("jobId") int jobId);

    void removeOld(@Param("jobId") int jobId, @Param("limit") int limit);

    void deleteByJobId(@Param("jobId") int jobId);

}
