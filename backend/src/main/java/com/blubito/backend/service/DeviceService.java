package com.blubito.backend.service;

import com.blubito.backend.domain.Device;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

import java.io.IOException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
public class DeviceService {
    private HttpClient httpClient = HttpClient.newHttpClient();

    public String createDevice(Device device) {
        String apiUrl = "https://midnightsun-98820-default-rtdb.europe-west1.firebasedatabase.app/device.json";

        String deviceJson = "{\"createdAt\":\"" + device.getCreatedAt() + "\","
                + "\"createdBy\":\"" + device.getCreatedBy() + "\","
                + "\"description\":\"" + device.getDescription() + "\","
                + "\"id\":\"" + device.getId() + "\","
                + "\"imageUrl\":\"" + device.getImageUrl() + "\","
                + "\"isActive\":\"" + device.isActive() + "\","
                + "\"name\":\"" + device.getName() + "\","
                + "\"values\":\"" + device.getValues() + "\","
                + "\"type\":\"" + device.getType() + "\"}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(deviceJson))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return "Device created successfully. Response Body: " + response.body();
            } else {
                return ("POST request failed with response code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            return ("Error occurred during POST request: " + e.getMessage());
        }
    }

    public String getDeviceById(String deviceId) {
        String apiUrl = "https://midnightsun-98820-default-rtdb.europe-west1.firebasedatabase.app/device/" + deviceId + ".json";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Handle the response status and body
            if (response.statusCode() == 200) {
                return response.body();
            } else {
                return ("GET request failed with response code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            return "Error occurred during GET request: " + e.getMessage();
        }
    }

    public String getAllDevices() {
        HttpResponse<String> response = null;
        // Construct the URL for fetching all devices
        String apiUrl = "https://midnightsun-98820-default-rtdb.europe-west1.firebasedatabase.app/device.json";

        // Create a GET request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        // Send the GET request and handle the response
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Handle the response status and body
            if (response.statusCode() == 200) {
                return response.body();
            } else {
                return response.body();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error occurred during GET request: " + e.getMessage());
        }
        return response.body();
    }

    //TODO: coming soon
    public List<String> getValuesForTimeframe(String deviceId, String startTime, String endTime) {
        String deviceFromDatabaseASString = getDeviceById(deviceId);
        JsonObject deviceObjectAsJSON = JsonParser.parseString(deviceFromDatabaseASString).getAsJsonObject();

        String deviceIdFromJSON = null;
        if (deviceObjectAsJSON.get("id") != null) {
            deviceIdFromJSON = deviceObjectAsJSON.get("id").getAsString();
        }
        List<String> valuesForTimeframe = new ArrayList<>();
        String timesTampOfDeviceValue = deviceObjectAsJSON.get("values").getAsJsonObject().get("timestamp").getAsString();

        if (isWithinTimeframe(timesTampOfDeviceValue, startTime, endTime)) {
            valuesForTimeframe.add(deviceFromDatabaseASString);
        }
        return valuesForTimeframe;
    }

    public String getValueForADevice(String deviceId) {
        String deviceFromDatabaseASString = getDeviceById(deviceId);
        JsonObject deviceObjectAsJSON = JsonParser.parseString(deviceFromDatabaseASString).getAsJsonObject();

        String deviceIdFromJSON = null;
        if (deviceObjectAsJSON.get("id") != null) {
            deviceIdFromJSON = deviceObjectAsJSON.get("id").getAsString();
        }
        String timesTampOfDeviceValue = deviceObjectAsJSON.get("values").getAsJsonObject().get("value").getAsString();
        return timesTampOfDeviceValue;
    }

    private boolean isWithinTimeframe(String timestamp, String startTime, String endTime) {
        LocalDateTime valueTimestamp = parseTimestamp(timestamp);
        LocalDateTime startTimestamp = parseTimestamp(startTime);
        LocalDateTime endTimestamp = parseTimestamp(endTime);

        return valueTimestamp.isAfter(startTimestamp) && valueTimestamp.isBefore(endTimestamp);
    }

    public LocalDateTime parseTimestamp(String timestamp) {
        // Parse the timestamp string to LocalDateTime using a specific format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return LocalDateTime.parse(timestamp, formatter).atOffset(ZoneOffset.UTC).toLocalDateTime();
    }

}


