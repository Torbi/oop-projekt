package com.filmster.application.model;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupFirebaseHandler {

    public static void signup(String name, String email, String password){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    String uid = mAuth.getCurrentUser().getUid();

                    // Write a message to the database
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("users");

                    DatabaseReference user = myRef.child(uid);

                    user.child("name").setValue(name);
                    user.child("email").setValue(email);
                    user.child("password").setValue(password);


                    mAuth.signOut();

                }


            }
        });
    }
}
