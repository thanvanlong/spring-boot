package com.example.testgit.service;

import com.example.testgit.entity.message.Message;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class MessageService {
    private static final String COLLECTION_NAME = "users";


    public  String saveMessage(Message message) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> resultApiFuture = dbFirestore
                .collection(COLLECTION_NAME).
                document(message.getFrom()).set(message);
        return resultApiFuture.get().getUpdateTime().toString();
    }
}
