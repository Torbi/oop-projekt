package com.filmster.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.testmaddafakka.R;

/**
 * Gui for signing in, not finished yet
 */
public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button backToLogin = findViewById(R.id.backToLogin);


        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(SignupActivity.this,MainActivity.class);
                startActivity(in);
            }
        });
    }
}