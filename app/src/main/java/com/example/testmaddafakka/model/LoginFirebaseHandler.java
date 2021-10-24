package com.example.testmaddafakka.model;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.testmaddafakka.LoginActivity2;
import com.example.testmaddafakka.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFirebaseHandler {


        /**
         * This is a class that communicates with the firebaseAuthentication
         * @param email - The email of the user
         * @param password - The users password

         */


    public static void login(String email, String password, LoginActivity2 activity2){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        /**
         * Method that adds a user to the FirebaseAuthentication system
         */

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    activity2.openMainActivity();
                }
            }
        } );



    }
}