package com.example.himanshurawat.buildit.Utility;

import com.google.firebase.database.FirebaseDatabase;

public class DatabaseUtility {

    private static FirebaseDatabase database;

    public static FirebaseDatabase getDatabase(){
        if(database==null){
            database=FirebaseDatabase.getInstance();
            database.setPersistenceEnabled(true);
        }
        return database;
    }
}
