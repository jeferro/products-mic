package com.jeferro.shared.mappers;

import com.jeferro.shared.mappers.others.LocaleMapper;
import com.jeferro.shared.mappers.others.StringIdentifierMapper;
import com.jeferro.shared.mappers.others.ValueObjectMapper;
import org.mapstruct.MapperConfig;

@MapperConfig(uses = {
	ValueObjectMapper.class,
	LocaleMapper.class,
	StringIdentifierMapper.class
})
public class MapstructConfig {

}
