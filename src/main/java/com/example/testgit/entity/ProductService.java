package com.example.testgit.entity;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ProductService {
    private static final String NAME ="product";

    public  String saveProduct(Product product) throws ExecutionException, InterruptedException {

        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionWritefuture = firestore.collection(NAME).document(product.getName()).set(product);

        return collectionWritefuture.get().getUpdateTime().toString();
    }
    public Product getProduct(String name) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection(NAME).document(name);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot documentSnapshot = future.get();

        Product product = null;
        if(documentSnapshot.exists()){
            product = documentSnapshot.toObject(Product.class);
            return product;
        }
        else return null;
    }
    public  String updateProduct(Product product) throws ExecutionException, InterruptedException {

        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionWritefuture = firestore.collection(NAME).document(product.getName()).set(product);

        return collectionWritefuture.get().getUpdateTime().toString();
    }
    public  String deleteProduct(String name) throws ExecutionException, InterruptedException {

        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionWritefuture = firestore.collection("product").document(name).delete();

        return "Documment product ID"+name+"has been deleted successfully";
    }

    public List<Product> getAllProduct() throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        Iterable<DocumentReference> iterable = (Iterable<DocumentReference>)firestore.collection(NAME).listDocuments();
        Iterator<DocumentReference> iterator = iterable.iterator();
        List<Product> products = new ArrayList<>();
        while (iterator.hasNext()){
            DocumentReference documentReference = iterator.next();
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot documentSnapshot = future.get();
            Product product = documentSnapshot.toObject(Product.class);
            products.add(product);
        }
        return products;
    }
}
