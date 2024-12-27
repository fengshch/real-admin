package cc.realtec.real.auth.server.service;

import cc.realtec.real.auth.common.domain.dto.SysUserDto;
import cc.realtec.real.auth.server.domain.ChangePasswordRequest;
import cc.realtec.real.auth.server.domain.SysUserRequest;
import cc.realtec.real.auth.server.po.SysUserPo;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface SysUserService {

    SysUserPo create(SysUserRequest sysUser) throws Exception;

    SysUserDto findByUsername(String username) throws UsernameNotFoundException;

    SysUserDto findByEmail(String email) throws UsernameNotFoundException;

    SysUserDto findByPhoneNumber(String phoneNumber) throws UsernameNotFoundException;

    SysUserDto findById(String id) throws UsernameNotFoundException;

    void update(SysUserDto sysUser);

    boolean deleteById(String id);

    boolean deleteByUsername(String username);

    boolean deleteByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    void changePassword(String username, ChangePasswordRequest changePasswordRequest) throws Exception;

    void forgetPassword(String email);

    void updatePassword(String email, String newPassword);

    void updateAvatarUrl(String id, String avatarUrl);

    void resetPassword(String id, String newPassword);
}
