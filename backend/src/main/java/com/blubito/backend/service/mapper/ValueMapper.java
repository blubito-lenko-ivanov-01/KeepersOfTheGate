package com.blubito.backend.service.mapper;

import com.blubito.backend.domain.Value;
import com.blubito.backend.service.dto.ValueDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ValueMapper {
    ValueDTO toDTO(Value value);
    Value toEntity(ValueDTO valueDTO);
}
