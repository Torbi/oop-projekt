package com.filmster.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testmaddafakka.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity2 extends AppCompatActivity {

    /*EditText login_edtEmail=findViewById(R.id.edt_loginEmail);
    EditText login_edtPassword=findViewById(R.id.edt_loginPassword);*/

    Button signUp;
    Button logIn;
    EditText emailField;
    EditText passwordField;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        signUp = findViewById(R.id.signUp);
        logIn = findViewById(R.id.loginBtn);
        emailField = findViewById(R.id.edt_loginEmail);
        passwordField = findViewById(R.id.edt_loginPassword);

        mAuth = FirebaseAuth.getInstance();


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity2.this, SignupActivity.class));
            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (emailField.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Your Email", Toast.LENGTH_SHORT).show();
                }

                if (passwordField.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Your Password", Toast.LENGTH_SHORT).show();
                }


                if (!(emailField.getText().toString().isEmpty() && passwordField.getText().toString().isEmpty())) {

                    mAuth.signInWithEmailAndPassword(emailField.getText().toString(),passwordField.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Intent in = new Intent(LoginActivity2.this,MainActivity.class);
                                startActivity(in);
                            }




                            else {
                                Toast.makeText(getApplicationContext(),task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();

                            }


                        }
                    });
                }
            }
        });

    }






    }
