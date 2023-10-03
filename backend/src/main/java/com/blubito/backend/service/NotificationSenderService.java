package com.blubito.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class NotificationSenderService {
    @Autowired
    private final DeviceService deviceService;
    private static final String OPEN_DOOR_MESSAGE = "ATTENTION: The door is OPENED!";


    public NotificationSenderService(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    public String sendNotification(String timestamp) {
        LocalDateTime localTime = deviceService.parseTimestamp(timestamp);
        LocalDateTime notificationTime = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.of(18, 0));
        if (localTime.isAfter(notificationTime)) {
            return OPEN_DOOR_MESSAGE;
        }
        return null;
    }
}

