package cc.realtec.real.auth.server.repo.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import cc.realtec.real.auth.server.po.SysAuthorityPo;
import cc.realtec.real.auth.server.mapper.SysAuthorityMapper;
import cc.realtec.real.auth.server.repo.SysAuthorityRepo;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author bill
 * @since 2024-10-24
 */
@Service
public class SysAuthorityRepoImpl extends ServiceImpl<SysAuthorityMapper, SysAuthorityPo> implements SysAuthorityRepo {

}
