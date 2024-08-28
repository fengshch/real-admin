package cc.realtec.real.auth.server.repo.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import cc.realtec.real.auth.server.po.SysUserGroupPO;
import cc.realtec.real.auth.server.mapper.SysUserGroupMapper;
import cc.realtec.real.auth.server.repo.SysUserGroupRepo;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author bill
 * @since 2024-08-20
 */
@Service
public class SysUserGroupRepoImpl extends ServiceImpl<SysUserGroupMapper, SysUserGroupPO> implements SysUserGroupRepo {

}
