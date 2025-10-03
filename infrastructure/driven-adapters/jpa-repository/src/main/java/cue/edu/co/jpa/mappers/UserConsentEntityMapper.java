package cue.edu.co.jpa.mappers;

import cue.edu.co.jpa.entities.UserConsentEntity;
import cue.edu.co.model.userconsent.UserConsent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserConsentEntityMapper {
    UserConsentEntity toEntity(UserConsent userConsent);
    UserConsent toDomain(UserConsentEntity userConsentEntity);
}
