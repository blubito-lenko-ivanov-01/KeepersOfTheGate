package com.blubito.backend.web;

import com.blubito.backend.domain.Device;
import com.blubito.backend.service.DeviceService;
import com.blubito.backend.service.NotificationSenderService;
import com.blubito.backend.service.dto.DeviceDTO;
import com.blubito.backend.service.mapper.DeviceMapper;
import com.blubito.backend.web.exception.HttpBadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DeviceController {
    @Autowired
    private final DeviceService deviceService;
    @Autowired
    private final DeviceMapper deviceMapper;

    @Autowired
    private final NotificationSenderService notificationSenderService;
    public DeviceController(DeviceService deviceService, DeviceMapper deviceMapper, NotificationSenderService notificationSenderService) {
        this.deviceService = deviceService;
        this.deviceMapper = deviceMapper;
        this.notificationSenderService = notificationSenderService;
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

    //TODO: coming soon
    @GetMapping("/devices/{deviceId}/time-frame")
    public List<String> getDeviceValuesForTimeFrame(@PathVariable String deviceId,
                                                   @RequestParam String startTime,
                                                   @RequestParam String endTime) {
        if(deviceId != null){
            return deviceService.getValuesForTimeframe(deviceId,startTime,endTime);
        }
        throw new HttpBadRequestException("deviceDTO is null");
    }

    @GetMapping("/devices/notification")
    public String getNotification(@RequestParam String currentTime) {
        return notificationSenderService.sendNotification(currentTime);
    }
}
