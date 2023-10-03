package com.blubito.backend.service.mapper;

import com.blubito.backend.domain.Device;
import com.blubito.backend.service.dto.DeviceDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeviceMapper {
    DeviceDTO toDTO(Device device);
    Device toEntity(DeviceDTO deviceDTO);
}
