package cc.realtec.real.auth.server.domain.converter;

import cc.realtec.real.auth.common.domain.dto.SysUserDto;
import cc.realtec.real.auth.server.domain.SysUserRequest;
import cc.realtec.real.auth.server.po.SysUserPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysUserConverter {

    SysUserConverter INSTANCE = Mappers.getMapper(SysUserConverter.class);

    SysUserDto poToDto(SysUserPo sysUserPo);

    @Mapping(target = "emailToken", ignore = true)
    @Mapping(target = "passwordToken", ignore = true)
    SysUserPo dtoToPo(SysUserDto sysUserDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "emailToken", ignore = true)
    @Mapping(target = "passwordToken", ignore = true)
    SysUserPo requestToPo(SysUserRequest sysUserRequest);
}
