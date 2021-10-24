package com.filmster.application.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.filmster.application.LoginActivity2;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFirebaseHandler {

    public static void login(String email, String password, LoginActivity2 activity2){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.i("Roro","funkar");
                    activity2.openMainActivity();
                }
            }
        } );



    }
}