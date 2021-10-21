package com.filmster.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;

import com.filmster.application.view.MainView;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * This is the activity for the app, it sets the appActivity view to activity_main
 * it also sets the "main" fragment of the app to mainView.
 */
public class MainActivity extends AppCompatActivity {

    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private MainView mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView escapeLogo = findViewById(R.id.escapeImage);
        TextView escape = findViewById(R.id.escape);

        escape.setOnClickListener(view -> setMainView());
        escapeLogo.setOnClickListener(view -> setMainView());

        mainView = new MainView();
        setMainView();

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

    }

    /**
     * Sets the activitys fragment to mainview.
     */
    private void setMainView(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragmentInlogg, mainView);
        fragmentTransaction.commit();
    }
}

