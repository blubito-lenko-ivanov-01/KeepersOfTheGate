package com.blubito.backend.web;

import com.blubito.backend.domain.Device;
import com.blubito.backend.service.DeviceService;
import com.blubito.backend.service.dto.DeviceDTO;
import com.blubito.backend.service.mapper.DeviceMapper;
import com.blubito.backend.web.exception.HttpBadRequestException;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class DeviceController {
    @Autowired
    private final DeviceService deviceService;

    @Autowired
    private final DeviceMapper deviceMapper;

    public DeviceController(DeviceService deviceService, DeviceMapper deviceMapper) {
        this.deviceService = deviceService;
        this.deviceMapper = deviceMapper;
    }
    @GetMapping("/devices/{id}")
    public String getDevice(@PathVariable String id){
        if(id != null){
            return deviceService.getDeviceById(id);
        }
        throw new HttpBadRequestException("Exception");
    }

    @PostMapping("/devices")
    public String createDevice(@RequestBody DeviceDTO deviceDTO) {
        if(deviceDTO != null){
            Device device = deviceMapper.toEntity(deviceDTO);
            return deviceService.createDevice(device);
        }
        throw new HttpBadRequestException("deviceDTO is null");
    }

    @GetMapping("/devices")
    public String getAllDevices(){
        return deviceService.getAllDevices();
    }
}
