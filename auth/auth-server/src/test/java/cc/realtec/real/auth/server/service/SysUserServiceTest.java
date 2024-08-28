package cc.realtec.real.auth.server.service;

import cc.realtec.real.auth.common.entity.SysUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SysUserServiceTest {

    @Autowired
    private SysUserService sysUserService;

    @AfterEach
    public void tearDown() {
       boolean ret = sysUserService.deleteByUsername("test");
    }

    @Test
    void create() {
        SysUser sysUser = new SysUser();
        sysUser.setUsername("test");
        sysUser.setPassword("test");
        sysUser.setEmail("test@example.com");
        sysUser.setPhoneNumber("1234567890");
        sysUser.setAvatarUrl("https://example.com/avatar.jpg");
        sysUser.setNickname("test");
        boolean result = sysUserService.create(sysUser);
        assertTrue(result);
    }

    @Test
    void findByUsername() {
    }

    @Test
    void findByEmail() {
    }

    @Test
    void findByPhoneNumber() {
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void existsByUsername() {
    }

    @Test
    void existsByEmail() {
    }

    @Test
    void existsByPhoneNumber() {
    }

    @Test
    void changePassword() {
    }

    @Test
    void forgetPassword() {
    }

    @Test
    void updatePassword() {
    }

    @Test
    void updateAvatarUrl() {
    }

    @Test
    void resetPassword() {
    }
}