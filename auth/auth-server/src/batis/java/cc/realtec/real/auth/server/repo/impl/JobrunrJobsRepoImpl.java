package cc.realtec.real.auth.server.repo.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import cc.realtec.real.auth.server.po.JobrunrJobsPO;
import cc.realtec.real.auth.server.mapper.JobrunrJobsMapper;
import cc.realtec.real.auth.server.repo.JobrunrJobsRepo;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author bill
 * @since 2024-08-20
 */
@Service
public class JobrunrJobsRepoImpl extends ServiceImpl<JobrunrJobsMapper, JobrunrJobsPO> implements JobrunrJobsRepo {

}
