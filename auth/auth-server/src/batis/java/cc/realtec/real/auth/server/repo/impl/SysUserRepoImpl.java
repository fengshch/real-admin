package cc.realtec.real.auth.server.repo.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import cc.realtec.real.auth.server.po.SysUserPo;
import cc.realtec.real.auth.server.mapper.SysUserMapper;
import cc.realtec.real.auth.server.repo.SysUserRepo;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author bill
 * @since 2024-10-24
 */
@Service
public class SysUserRepoImpl extends ServiceImpl<SysUserMapper, SysUserPo> implements SysUserRepo {

}
