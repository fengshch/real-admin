package cc.realtec.real.auth.server.entity.converter;

import cc.realtec.real.auth.common.entity.SysUser;
import cc.realtec.real.auth.server.po.SysUserPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysUserConverter {

    SysUserConverter INSTANCE = Mappers.getMapper(SysUserConverter.class);

    @Mapping(target = "passwordPlain", ignore = true)
    SysUser toEntity(SysUserPO sysUserPO);

    SysUserPO toPO(SysUser sysUser);
}
