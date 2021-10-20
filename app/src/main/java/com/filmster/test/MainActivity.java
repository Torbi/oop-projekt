package com.filmster.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testmaddafakka.R;
import com.filmster.test.view.MainView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private MainView mainView;
    private TextView escape;
    private ImageView escapeLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        escapeLogo = findViewById(R.id.escapeImage);
        escape = findViewById(R.id.escape);

        escape.setOnClickListener(view -> setMainView());
        escapeLogo.setOnClickListener(view -> setMainView());

        mainView = new MainView();
        setMainView();

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

    }
    private void setMainView(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragmentInlogg, mainView);
        fragmentTransaction.commit();

    }
}

