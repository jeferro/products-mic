package com.jeferro.shared.mappers.others;

import org.mapstruct.Mapper;

import java.util.Locale;

@Mapper
public class LocaleMapper {

    public Locale toDomain(String dto) {
        return Locale.forLanguageTag(dto);
    }

    public String toDTO(Locale locale) {
        return locale.toLanguageTag();
    }
}
