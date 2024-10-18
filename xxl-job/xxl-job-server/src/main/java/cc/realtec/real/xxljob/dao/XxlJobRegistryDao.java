package cc.realtec.real.xxljob.dao;

import cc.realtec.real.xxljob.core.model.XxlJobRegistry;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by xuxueli on 16/9/30.
 */
@Mapper
public interface XxlJobRegistryDao {

    List<Integer> findDead(@Param("timeout") int timeout,
                           @Param("nowTime") LocalDateTime nowTime);

    void removeDead(@Param("ids") List<Integer> ids);

    List<XxlJobRegistry> findAll(@Param("timeout") int timeout,
                                 @Param("nowTime") LocalDateTime nowTime);

    int registryUpdate(@Param("registryGroup") String registryGroup,
                       @Param("registryKey") String registryKey,
                       @Param("registryValue") String registryValue,
                       @Param("updateTime") LocalDateTime updateTime);

    void registrySave(@Param("registryGroup") String registryGroup,
                      @Param("registryKey") String registryKey,
                      @Param("registryValue") String registryValue,
                      @Param("updateTime") LocalDateTime updateTime);

    int registryDelete(@Param("registryGroup") String registryGroup,
                       @Param("registryKey") String registryKey,
                       @Param("registryValue") String registryValue);

}
