package com.example.alert_notification_firebase.controller;

import com.example.alert_notification_firebase.service.FirestoreService;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class apiController {

  @Autowired
  private FirestoreService firestoreService;

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/test_connection")
  public String testConnection() throws ExecutionException, InterruptedException {
    Map<String, Object> testData = new HashMap<>();
    testData.put("message", "Connection successful!");
    testData.put("timestamp", System.currentTimeMillis());

    return firestoreService.saveData("test-connection-doc", testData); // Use FirestoreService
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/get-message")
  public Map<String, Object> getMessage() throws ExecutionException, InterruptedException {
    // Retrieve data from Firestore
    Map<String, Object> data = firestoreService.getData("test-connection-doc");

    if (data != null) {
      return data;
    } else {
      Map<String, Object> error = new HashMap<>();
      error.put("message", "No data found!");
      return error;
    }
  }
}
