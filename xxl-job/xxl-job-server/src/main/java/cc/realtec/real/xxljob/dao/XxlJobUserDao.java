package cc.realtec.real.xxljob.dao;

import cc.realtec.real.xxljob.core.model.XxlJobUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xuxueli 2019-05-04 16:44:59
 */
@Mapper
public interface XxlJobUserDao {

    List<XxlJobUser> pageList(@Param("offset") int offset,
                              @Param("pageSize") int pageSize,
                              @Param("username") String username,
                              @Param("role") int role);

    int pageListCount(@Param("offset") int offset,
                      @Param("pageSize") int pageSize,
                      @Param("username") String username,
                      @Param("role") int role);

    XxlJobUser loadByUserName(@Param("username") String username);

    int save(XxlJobUser xxlJobUser);

    int update(XxlJobUser xxlJobUser);

    int delete(@Param("id") int id);
}
