package com.example.himanshurawat.buildit.Utility;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by himanshurawat on 14/10/17.
 */

public class FirebaseAuthUtility {
    //FirebaseAuth Reference
    private static FirebaseAuth mAuth;

    public static FirebaseAuth getAuth(){
        if(mAuth==null){
            mAuth=FirebaseAuth.getInstance();
        }
        return mAuth;
    }

}
