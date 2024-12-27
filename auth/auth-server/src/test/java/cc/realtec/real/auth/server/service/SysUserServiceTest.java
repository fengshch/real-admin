package cc.realtec.real.auth.server.service;

import cc.realtec.real.auth.common.domain.dto.SysUserDto;
import cc.realtec.real.auth.server.domain.SysUserRequest;
import cc.realtec.real.auth.server.po.SysUserPo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

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
//        SysUserRequest sysUser = new SysUserRequest();
//        sysUser.setUsername("test");
//        sysUser.setPassword("test");
//        sysUser.setEmail("test@example.com");
//        sysUser.setPhoneNumber("1234567890");
//        sysUser.setPicture("https://example.com/avatar.jpg");
//        sysUser.setNickname("test");
//        SysUserPo sysUserpo = sysUserService.create(sysUser);
//        assertNotNull(sysUserpo);
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