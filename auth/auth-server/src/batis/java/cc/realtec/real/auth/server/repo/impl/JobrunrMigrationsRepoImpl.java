package cc.realtec.real.auth.server.repo.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import cc.realtec.real.auth.server.po.JobrunrMigrationsPO;
import cc.realtec.real.auth.server.mapper.JobrunrMigrationsMapper;
import cc.realtec.real.auth.server.repo.JobrunrMigrationsRepo;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author bill
 * @since 2024-08-20
 */
@Service
public class JobrunrMigrationsRepoImpl extends ServiceImpl<JobrunrMigrationsMapper, JobrunrMigrationsPO> implements JobrunrMigrationsRepo {

}
