package com.blubito.backend.web;

import com.blubito.backend.domain.Device;
import com.blubito.backend.service.DeviceService;
import com.blubito.backend.service.NotificationSenderService;
import com.blubito.backend.service.dto.DeviceDTO;
import com.blubito.backend.service.mapper.DeviceMapper;
import com.blubito.backend.web.exception.HttpBadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:1914","http://localhost:3000"})
public class DeviceController {

    public static final String ID_NULL = "The deviceId is null!";
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

    @PostMapping("/devices")
    public String createDevice(@RequestBody DeviceDTO deviceDTO) {
        if(deviceDTO != null){
            Device device = deviceMapper.toEntity(deviceDTO);
            return deviceService.createDevice(device);
        }
        throw new HttpBadRequestException("deviceDTO is null");
    }

    @GetMapping("/devices/{id}")
    public String getDevice(@PathVariable String id){
        if(id != null){
            return deviceService.getDeviceById(id);
        }
        throw new HttpBadRequestException(ID_NULL);
    }

    @GetMapping("/devices/{deviceId}/value")
    public String getLastValueForADevice(@PathVariable String deviceId){
        if(deviceId != null){
            return deviceService.getValueForADevice(deviceId);
        }
        throw new HttpBadRequestException(ID_NULL);
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
        return notificationSenderService.getNotification(currentTime);
    }

    @GetMapping("/devices/{type}/notification-dummy-values")
    public HashMap<String, List<String>> getDummyNotification(@PathVariable String type) {
        return notificationSenderService.getDummyNotification(type);
    }
}
