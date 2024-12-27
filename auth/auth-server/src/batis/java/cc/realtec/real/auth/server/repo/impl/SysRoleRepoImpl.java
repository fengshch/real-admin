package cc.realtec.real.auth.server.repo.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import cc.realtec.real.auth.server.po.SysRolePo;
import cc.realtec.real.auth.server.mapper.SysRoleMapper;
import cc.realtec.real.auth.server.repo.SysRoleRepo;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author bill
 * @since 2024-10-24
 */
@Service
public class SysRoleRepoImpl extends ServiceImpl<SysRoleMapper, SysRolePo> implements SysRoleRepo {

}
