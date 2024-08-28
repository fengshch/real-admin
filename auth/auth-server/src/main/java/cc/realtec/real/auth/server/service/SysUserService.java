package cc.realtec.real.auth.server.service;

import cc.realtec.real.auth.common.entity.SysUser;

public interface SysUserService {

    boolean create(SysUser sysUser);

    SysUser findByUsername(String username);

    SysUser findByEmail(String email);

    SysUser findByPhoneNumber(String phoneNumber);

    SysUser findById(String id);

    boolean update(SysUser sysUser);

    boolean deleteById(String id);

    boolean deleteByUsername(String username);

    boolean deleteByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean changePassword(String id, String oldPassword, String newPassword);

    void forgetPassword(String email);

    void updatePassword(String email, String newPassword);

    void updateAvatarUrl(String id, String avatarUrl);

    void resetPassword(String id, String newPassword);

}
