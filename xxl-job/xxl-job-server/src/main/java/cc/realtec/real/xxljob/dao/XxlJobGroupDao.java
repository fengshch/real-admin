package cc.realtec.real.xxljob.dao;


import cc.realtec.real.xxljob.core.model.XxlJobGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface XxlJobGroupDao {

    List<XxlJobGroup> findAll();

    List<XxlJobGroup> findByAddressType(@Param("addressType") int addressType);

    XxlJobGroup findByAppName(@Param("appName") String appName);

    int save(XxlJobGroup xxlJobGroup);

    int update(XxlJobGroup xxlJobGroup);

    int remove(@Param("id") int id);

    XxlJobGroup load(@Param("id") int id);

    List<XxlJobGroup> pageList(@Param("offset") int offset,
                               @Param("pageSize") int pageSize,
                               @Param("appName") String appName,
                               @Param("title") String title);

    int pageListCount(@Param("offset") int offset,
                      @Param("pageSize") int pageSize,
                      @Param("appName") String appName,
                      @Param("title") String title);

}
