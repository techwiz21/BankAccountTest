package com.test.banking.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.test.banking.entity.BankModel;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class BankingService {

    private static final String COLLECTION_NAME="accounts";
    public String SaveBankAccount(BankModel bankModel) throws ExecutionException, InterruptedException {

      Firestore dbFirestore=  FirestoreClient.getFirestore();

     ApiFuture<WriteResult> collectionApiFuture= dbFirestore.collection(COLLECTION_NAME).document(bankModel.getCurrency()).set(bankModel);
        return collectionApiFuture.get().getUpdateTime().toString();
    }
    public BankModel getAccountAmount(String currency) throws ExecutionException, InterruptedException {

        Firestore dbFirestore=  FirestoreClient.getFirestore();

    DocumentReference documentReference =dbFirestore.collection(COLLECTION_NAME).document(currency);

    ApiFuture<DocumentSnapshot> future=documentReference.get();

    DocumentSnapshot document =future.get();

    if(document.exists()){
        return document.toObject(BankModel.class);
    }else{
        return null;
        }
    }

    public String updateAccountAmount(BankModel bankModel) throws ExecutionException,InterruptedException{
        Firestore dbFirestore=  FirestoreClient.getFirestore();

        DocumentReference documentReference =dbFirestore.collection(COLLECTION_NAME).document(bankModel.getCurrency());

        ApiFuture<DocumentSnapshot> future=documentReference.get();

        DocumentSnapshot document =future.get();
        BankModel bankModelServer=null;
        if(document.exists()) {
            bankModelServer = document.toObject(BankModel.class);
            bankModelServer.setAmount(bankModel.getAmount()+bankModelServer.getAmount());
            ApiFuture<WriteResult> collectionApiFuture= dbFirestore.collection(COLLECTION_NAME).document(bankModel.getCurrency()).set(bankModelServer);
            return collectionApiFuture.get().getUpdateTime().toString();

        }else{
            return null;
        }

    }
    public String deleteBankAccount(String currency) throws ExecutionException, InterruptedException {

        Firestore dbFirestore=  FirestoreClient.getFirestore();
        DocumentReference documentReference =dbFirestore.collection(COLLECTION_NAME).document(currency);

        ApiFuture<DocumentSnapshot> future=documentReference.get();

        DocumentSnapshot document =future.get();
        if(document.exists()) {
            ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(currency).delete();
            return "Successfully Deleted account with currency " + currency;
        }else{
            return "No account found with currency "+currency;
        }
    }




}
