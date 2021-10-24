package com.filmster.application.model;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

 /**
 * This class communicates with Firebase Realtime database.
 */
public class SignupFirebaseHandler {

    public static void signup(String name, String email, String password){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        /**
         * @param name - The name of the user
         * @param email- The users email
         * @param password - The users password
         * Method that creates a user with the help of the firebase instance, User with Email and password.
         */

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
