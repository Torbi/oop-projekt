package com.example.testmaddafakka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);




        Button backToLogin = findViewById(R.id.backToLogin);


        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(in);
            }
        });



        Button signup = findViewById(R.id.btnSignup);

        EditText edtName = findViewById(R.id.edtName);
        EditText edtEmail = findViewById(R.id.edtEmail);
        EditText edtPassword = findViewById(R.id.edtPassword);

        signup.setOnClickListener(new View.OnClickListener() { //methods to check if the textFields are empty
            @Override
            public void onClick(View view) {
                if (edtName.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Your Name", Toast.LENGTH_SHORT).show();
                }

                if (edtEmail.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Your Email", Toast.LENGTH_SHORT).show();
                }

                if (edtPassword.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Your Password", Toast.LENGTH_SHORT).show();
                }




                if (!(edtName.getText().toString().isEmpty() && edtEmail.getText().toString().isEmpty() && edtPassword.getText().toString().isEmpty())) {

                    mAuth.createUserWithEmailAndPassword(edtEmail.getText().toString(),edtPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){

                                Intent in=new Intent(SignupActivity.this,DashBoard.class);
                                startActivity(in);
                            }

                            else{

                                s
                            }


                        }
                    });



                }



            }


        });
    }
}