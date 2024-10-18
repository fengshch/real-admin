package cc.realtec.real.auth.server.domain.converter;


import cc.realtec.real.auth.server.domain.EmailVerification;
import cc.realtec.real.auth.server.po.EmailVerificationPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmailVerificationConverter {

    EmailVerificationConverter INSTANCE = Mappers.getMapper(EmailVerificationConverter.class);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    EmailVerificationPo domainToPo(EmailVerification emailVerification);
}
