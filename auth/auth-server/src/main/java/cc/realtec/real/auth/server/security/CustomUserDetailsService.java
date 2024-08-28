package cc.realtec.real.auth.server.security;

import cc.realtec.real.auth.common.entity.SysUser;
import cc.realtec.real.auth.server.service.SysUserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    private final SysUserService sysUserService;

    public CustomUserDetailsService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.findByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("The user not found");
        }
        return User.builder()
                .username(sysUser.getUsername())
                .password(sysUser.getPassword())
                .accountExpired(!sysUser.getAccountNonExpired())
                .accountLocked(!sysUser.getAccountNonLocked())
                .credentialsExpired(!sysUser.getCredentialsNonExpired())
                .disabled(!sysUser.getEnabled())
                .authorities("ADMIN")
                .build();
    }
}
