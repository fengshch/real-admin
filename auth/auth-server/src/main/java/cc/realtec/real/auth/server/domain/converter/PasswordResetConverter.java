package cc.realtec.real.auth.server.domain.converter;

import cc.realtec.real.auth.server.domain.PasswordReset;
import cc.realtec.real.auth.server.po.PasswordResetPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PasswordResetConverter {
    PasswordResetConverter INSTANCE = Mappers.getMapper(PasswordResetConverter.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    PasswordResetPo domainToPo(PasswordReset passwordReset);
}
