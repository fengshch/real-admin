package cc.realtec.real.auth.server.repo.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import cc.realtec.real.auth.server.po.JobrunrRecurringJobsPO;
import cc.realtec.real.auth.server.mapper.JobrunrRecurringJobsMapper;
import cc.realtec.real.auth.server.repo.JobrunrRecurringJobsRepo;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author bill
 * @since 2024-08-20
 */
@Service
public class JobrunrRecurringJobsRepoImpl extends ServiceImpl<JobrunrRecurringJobsMapper, JobrunrRecurringJobsPO> implements JobrunrRecurringJobsRepo {

}
