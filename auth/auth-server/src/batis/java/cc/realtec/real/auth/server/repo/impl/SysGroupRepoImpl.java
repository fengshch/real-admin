package cc.realtec.real.auth.server.repo.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import cc.realtec.real.auth.server.po.SysGroupPo;
import cc.realtec.real.auth.server.mapper.SysGroupMapper;
import cc.realtec.real.auth.server.repo.SysGroupRepo;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author bill
 * @since 2024-10-24
 */
@Service
public class SysGroupRepoImpl extends ServiceImpl<SysGroupMapper, SysGroupPo> implements SysGroupRepo {

}
