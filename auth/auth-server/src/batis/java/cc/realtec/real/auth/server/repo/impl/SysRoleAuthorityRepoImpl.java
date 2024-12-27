package cc.realtec.real.auth.server.repo.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import cc.realtec.real.auth.server.po.SysRoleAuthorityPo;
import cc.realtec.real.auth.server.mapper.SysRoleAuthorityMapper;
import cc.realtec.real.auth.server.repo.SysRoleAuthorityRepo;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author bill
 * @since 2024-10-24
 */
@Service
public class SysRoleAuthorityRepoImpl extends ServiceImpl<SysRoleAuthorityMapper, SysRoleAuthorityPo> implements SysRoleAuthorityRepo {

}
