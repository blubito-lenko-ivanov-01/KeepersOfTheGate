package com.blubito.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;

@Service
public class NotificationSenderService {
    @Autowired
    private final DeviceService deviceService;
    private static final String OPEN_DOOR_MESSAGE = "ATTENTION: The door is OPENED!";

    public NotificationSenderService(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    public String getNotification(String timestamp) {
        LocalDateTime localTime = deviceService.parseTimestamp(timestamp);
        LocalDateTime notificationTime = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.of(18, 0));
        if (localTime.isAfter(notificationTime)) {
            return OPEN_DOOR_MESSAGE;
        }
        return null;
    }

    public HashMap<String, List<String>> getDummyNotification(String type) {

        HashMap<String, List<String>> messagesForType = new HashMap<String, List<String>>();
        List<String> doorMessages = new ArrayList<>();
        List<String> thermometerMessages = new ArrayList<>();

        switch (type){
            case"door":
                doorMessages = generateDoorMessages(doorMessages);
                messagesForType.put(type,doorMessages);
                break;
            case"thermometer":
                thermometerMessages = generateThermometherMessages(thermometerMessages);
                messagesForType.put(type,thermometerMessages);
                break;
        }
        return messagesForType;
    }

    private List<String> generateDoorMessages(List<String> doorMessages){
        doorMessages.add("It is 19:00h - the door is STILL opened");
        doorMessages.add("It is 20:00h - the door is STILL opened");
        doorMessages.add("It is 21:00h - the door is STILL opened");
        doorMessages.add("It is 22:00h - the door is STILL opened");
        doorMessages.add("It is 23:00h - the door is STILL opened");
        doorMessages.add("It is 00:00h - the door is STILL opened");
        doorMessages.add("It is 01:00h - the door is STILL opened");
        return doorMessages;
    }

    private List<String> generateThermometherMessages(List<String> thermometerMessages){
        thermometerMessages.add("It is 19:00h - It is 25.9 °C");
        thermometerMessages.add("It is 18:00h - It is 25.5 °C");
        thermometerMessages.add("It is 20:00h - It is 24.9 °C");
        thermometerMessages.add("It is 16:00h - It is 28.9 °C");
        thermometerMessages.add("It is 11:00h - It is 26.9 °C");
        thermometerMessages.add("It is 10:00h - It is 23.9 °C");
        return thermometerMessages;
    }
}

