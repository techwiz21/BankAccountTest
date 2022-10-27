package com.test.banking.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.test.banking.entity.BankModel;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.ExecutionException;

@Service
public class BankingService {

    private static final String COLLECTION_NAME="accounts";
    public String SaveBankAccount(BankModel bankModel) throws ExecutionException, InterruptedException {

      Firestore dbFirestore=  FirestoreClient.getFirestore();

     ApiFuture<WriteResult> collectionApiFuture= dbFirestore.collection(COLLECTION_NAME).document(bankModel.getCurrency()).set(bankModel);
        return collectionApiFuture.get().getUpdateTime().toString();
    }
}
