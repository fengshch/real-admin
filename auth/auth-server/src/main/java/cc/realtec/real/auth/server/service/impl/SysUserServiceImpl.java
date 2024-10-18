package cc.realtec.real.auth.server.service.impl;

import cc.realtec.real.auth.common.domain.dto.SysUserDto;
import cc.realtec.real.auth.server.domain.SysUserRequest;
import cc.realtec.real.auth.server.domain.converter.SysUserConverter;
import cc.realtec.real.auth.server.po.SysUserPo;
import cc.realtec.real.auth.server.repo.SysUserRepo;
import cc.realtec.real.auth.server.service.SysUserService;
import cc.realtec.real.common.web.exception.BusinessException;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SysUserServiceImpl implements SysUserService {

    private final SysUserRepo sysUserRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SysUserPo create(SysUserRequest sysUser) throws BusinessException {
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        SysUserPo sysUserPO = SysUserConverter.INSTANCE.requestToPo(sysUser);
        SysUserPo sysUserPo = sysUserRepo.getOne(QueryWrapper.create().eq("username", sysUserPO.getUsername()));
        if (sysUserPo != null) {
            throw new BusinessException("Username is already existed");
        }
        SysUserPo email = sysUserRepo.getOne(QueryWrapper.create().eq("email", sysUserPO.getEmail()));
        if (email != null)
            throw new BusinessException("Email is already existed");
        try {
            sysUserRepo.save(sysUserPO);
            return sysUserPO;
        } catch (Exception e) {
            throw new BusinessException("Create user failed");
        }
    }

    @Override
    public SysUserDto findByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
        SysUserPo sysUserPO = sysUserRepo.getOne(queryWrapper);
        if (sysUserPO == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return SysUserConverter.INSTANCE.poToDto(sysUserPO);
    }

    @Override
    public SysUserDto findByEmail(String email) throws UsernameNotFoundException {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("email", email);
        SysUserPo sysUserPO = sysUserRepo.getOne(queryWrapper);
        if (sysUserPO == null) {
            throw new UsernameNotFoundException("Invalid email");
        }
        return SysUserConverter.INSTANCE.poToDto(sysUserPO);
    }

    @Override
    public SysUserDto findByPhoneNumber(String phoneNumber) throws UsernameNotFoundException {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("phoneNumber", phoneNumber);
        SysUserPo sysUserPO = sysUserRepo.getOne(queryWrapper);
        if (sysUserPO == null) {
            throw new UsernameNotFoundException("Invalid phone number");
        }
        return SysUserConverter.INSTANCE.poToDto(sysUserPO);
    }

    @Override
    public SysUserDto findById(String id) throws UsernameNotFoundException {
        SysUserPo sysUserPO = sysUserRepo.getById(id);
        if (sysUserPO == null) {
            throw new UsernameNotFoundException("User is not existed");
        }
        return SysUserConverter.INSTANCE.poToDto(sysUserPO);
    }

    @Override
    public boolean update(SysUserDto sysUser) {
        SysUserPo sysUserPO = SysUserConverter.INSTANCE.dtoToPo(sysUser);
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
