package cue.edu.co.api.utils;

import org.mapstruct.Mapper;

import java.time.LocalDate;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface OptionalMapper {

    default Optional<String> toOptional(String value) {
        return Optional.ofNullable(value);
    }

    default String fromOptional(Optional<String> value) {
        return value.orElse(null);
    }

    default Optional<LocalDate> toOptional(LocalDate value) {
        return Optional.ofNullable(value);
    }

    default LocalDate fromOptional(LocalDate value) {
        return value;
    }

    default LocalDate fromOptional(LocalDate value, LocalDate fallback) {
        return value != null ? value : fallback;
    }
}
