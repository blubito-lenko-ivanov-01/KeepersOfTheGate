package com.blubito.backend.service;

import com.blubito.backend.domain.Device;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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
            return("Error occurred during POST request: " + e.getMessage());
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
                return("GET request failed with response code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            return"Error occurred during GET request: " + e.getMessage();
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

}


