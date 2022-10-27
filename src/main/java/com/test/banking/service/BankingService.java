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
    //Defining Variables
    private static final String COLLECTION_NAME="accounts";

    //Creating a bank account
    public String createBankAccount(BankModel bankModel) throws ExecutionException, InterruptedException {

        Firestore dbFirestore=  FirestoreClient.getFirestore();
        DocumentReference documentReference =dbFirestore.collection(COLLECTION_NAME).document(bankModel.getCurrency());

        ApiFuture<DocumentSnapshot> future=documentReference.get();

        DocumentSnapshot document =future.get();
        //Checking if bank account already exists
        if(document.exists()) {
            return "Account with that currency already exists";
        }else{
            ApiFuture<WriteResult> collectionApiFuture= dbFirestore.collection(COLLECTION_NAME).document(bankModel.getCurrency()).set(bankModel);
            return "Successfully Created an account";
        }
    }
    //Getting total money in bank account
    public BankModel getAccountAmount(String currency) throws ExecutionException, InterruptedException {

        Firestore dbFirestore=  FirestoreClient.getFirestore();

    DocumentReference documentReference =dbFirestore.collection(COLLECTION_NAME).document(currency);

    ApiFuture<DocumentSnapshot> future=documentReference.get();

    DocumentSnapshot document =future.get();
    //Checking if Bank Account exists
    if(document.exists()){
        return document.toObject(BankModel.class);
    }else{
        return null;
        }
    }

    //Updating Account Money
    public String updateAccountAmount(BankModel bankModel) throws ExecutionException,InterruptedException{
        Firestore dbFirestore=  FirestoreClient.getFirestore();

        DocumentReference documentReference =dbFirestore.collection(COLLECTION_NAME).document(bankModel.getCurrency());

        ApiFuture<DocumentSnapshot> future=documentReference.get();

        DocumentSnapshot document =future.get();
        BankModel bankModelServer=null;
        //Checking if bank acccount exists
        if(document.exists()) {
            bankModelServer = document.toObject(BankModel.class);
            //Adding the two amounts together
            bankModelServer.setAmount(bankModel.getAmount()+bankModelServer.getAmount());
            ApiFuture<WriteResult> collectionApiFuture= dbFirestore.collection(COLLECTION_NAME).document(bankModel.getCurrency()).set(bankModelServer);
            return collectionApiFuture.get().getUpdateTime().toString();

        }else{
            return null;
        }

    }
    //Deleting the Bank Account
    public String deleteBankAccount(String currency) throws ExecutionException, InterruptedException {

        Firestore dbFirestore=  FirestoreClient.getFirestore();
        DocumentReference documentReference =dbFirestore.collection(COLLECTION_NAME).document(currency);

        ApiFuture<DocumentSnapshot> future=documentReference.get();

        DocumentSnapshot document =future.get();
        //Checking if Bank Account exists
        if(document.exists()) {
            ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(currency).delete();
            return "Successfully Deleted account with currency " + currency;
        }else{
            return "No account found with currency "+currency;
        }
    }




}
