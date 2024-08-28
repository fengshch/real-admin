package cc.realtec.real.auth.server.repo.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import cc.realtec.real.auth.server.po.UploadedFilePO;
import cc.realtec.real.auth.server.mapper.UploadedFileMapper;
import cc.realtec.real.auth.server.repo.UploadedFileRepo;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author bill
 * @since 2024-08-20
 */
@Service
public class UploadedFileRepoImpl extends ServiceImpl<UploadedFileMapper, UploadedFilePO> implements UploadedFileRepo {

}
