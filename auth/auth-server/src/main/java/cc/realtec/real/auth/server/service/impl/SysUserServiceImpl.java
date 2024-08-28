package cc.realtec.real.auth.server.service.impl;

import cc.realtec.real.auth.common.entity.SysUser;
import cc.realtec.real.auth.server.entity.converter.SysUserConverter;
import cc.realtec.real.auth.server.po.SysUserPO;
import cc.realtec.real.auth.server.repo.SysUserRepo;
import cc.realtec.real.auth.server.service.SysUserService;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SysUserServiceImpl implements SysUserService {

    private final SysUserRepo sysUserRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean create(SysUser sysUser) {
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPasswordPlain()));
        SysUserPO sysUserPO = SysUserConverter.INSTANCE.toPO(sysUser);
        return sysUserRepo.save(sysUserPO);
    }

    @Override
    public SysUser findByUsername(String username) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
        SysUserPO sysUserPO = sysUserRepo.getOne(queryWrapper);
        return SysUserConverter.INSTANCE.toEntity(sysUserPO);
    }

    @Override
    public SysUser findByEmail(String email) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("email", email);
        SysUserPO sysUserPO = sysUserRepo.getOne(queryWrapper);
        return SysUserConverter.INSTANCE.toEntity(sysUserPO);
    }

    @Override
    public SysUser findByPhoneNumber(String phoneNumber) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("phoneNumber", phoneNumber);
        SysUserPO sysUserPO = sysUserRepo.getOne(queryWrapper);
        return SysUserConverter.INSTANCE.toEntity(sysUserPO);
    }

    @Override
    public SysUser findById(String id) {
        SysUserPO sysUserPO = sysUserRepo.getById(id);
        return SysUserConverter.INSTANCE.toEntity(sysUserPO);
    }

    @Override
    public boolean update(SysUser sysUser) {
        SysUserPO sysUserPO = SysUserConverter.INSTANCE.toPO(sysUser);
        return sysUserRepo.updateById(sysUserPO);
    }

    @Override
    public boolean deleteById(String id) {
        return sysUserRepo.removeById(id);
    }

    @Override
    public boolean deleteByUsername(String username) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
        return sysUserRepo.remove(queryWrapper);
    }

    @Override
    public boolean deleteByEmail(String email) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("email", email);
        return sysUserRepo.remove(queryWrapper);
    }


    @Override
    public boolean existsByUsername(String username) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
        return sysUserRepo.exists(queryWrapper);
    }

    @Override
    public boolean existsByEmail(String email) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("email", email);
        return sysUserRepo.exists(queryWrapper);
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("phoneNumber", phoneNumber);
        return sysUserRepo.exists(queryWrapper);
    }

    @Override
    public boolean changePassword(String id, String oldPassword, String newPassword) {
        return false;
    }

    @Override
    public void forgetPassword(String email) {

    }

    @Override
    public void updatePassword(String email, String newPassword) {

    }

    @Override
    public void updateAvatarUrl(String id, String avatarUrl) {

    }

    @Override
    public void resetPassword(String id, String newPassword) {

    }

}
