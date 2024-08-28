package cc.realtec.real.auth.server.repo.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import cc.realtec.real.auth.server.po.JobrunrMetadataPO;
import cc.realtec.real.auth.server.mapper.JobrunrMetadataMapper;
import cc.realtec.real.auth.server.repo.JobrunrMetadataRepo;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author bill
 * @since 2024-08-20
 */
@Service
public class JobrunrMetadataRepoImpl extends ServiceImpl<JobrunrMetadataMapper, JobrunrMetadataPO> implements JobrunrMetadataRepo {

}
