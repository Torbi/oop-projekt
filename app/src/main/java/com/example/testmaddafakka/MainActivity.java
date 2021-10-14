package com.example.testmaddafakka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testmaddafakka.view.MainView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();;

    private MainView mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        mainView = new MainView();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer, mainView);
        fragmentTransaction.commit();

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Test");


        Button btn = findViewById(R.id.loginBtn);

        EditText edt_loginEmail=findViewById(R.id.edt_loginEmail);
        EditText edt_loginPassword= findViewById(R.id.edt_loginPassword);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edt_loginEmail.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Your Email", Toast.LENGTH_SHORT).show();
                }

                if (edt_loginPassword.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Your Password", Toast.LENGTH_SHORT).show();
                }


                if (!(edt_loginEmail.getText().toString().isEmpty() && edt_loginPassword.getText().toString().isEmpty())) {

                    mAuth.signInWithEmailAndPassword(edt_loginEmail.getText().toString(),edt_loginPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Intent in = new Intent(MainActivity.this,DashBoard.class);
                                startActivity(in);
                            }

                            else {

                                Toast.makeText(getApplicationContext(),task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();wr


                            }
                        }
                    });
                }
            }
        });




    }
}

