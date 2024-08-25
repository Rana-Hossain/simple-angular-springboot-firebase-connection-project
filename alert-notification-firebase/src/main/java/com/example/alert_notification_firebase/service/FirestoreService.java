package com.example.alert_notification_firebase.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class FirestoreService {

  @Autowired
  private Firestore firestore;

  public String saveData(String documentId, Map<String, Object> data) throws ExecutionException, InterruptedException {
    DocumentReference docRef = firestore.collection("test-connection-doc").document(documentId);
    ApiFuture<WriteResult> result = docRef.set(data);
    return result.get().getUpdateTime().toString();
  }

  public Map<String, Object> getData(String documentId) throws ExecutionException, InterruptedException {
    DocumentReference docRef = firestore.collection("test-connection-doc").document(documentId);
    ApiFuture<DocumentSnapshot> future = docRef.get();
    DocumentSnapshot document = future.get();

    if (document.exists()) {
      return document.getData();
    } else {
      return null;
    }
  }
}
