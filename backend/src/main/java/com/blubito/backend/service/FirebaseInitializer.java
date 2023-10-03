package com.blubito.backend.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

@Service
public class FirebaseInitializer {

    private DatabaseReference databaseReference;
    @PostConstruct
    public void initialize() {
        try {
            // reads the configurations from the JSON file and then initializes the connection for the specified database
            FileInputStream serviceAccount =
                    new FileInputStream("backend/src/main/resources/database/serviceAccount.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://midnightsun-98820-default-rtdb.europe-west1.firebasedatabase.app")
                    .build();

            FirebaseApp.initializeApp(options);
            this.databaseReference = FirebaseDatabase.getInstance().getReference();
        } catch (Exception e) {
            //DANGER change
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize Firebase database");
        }

    }
}
