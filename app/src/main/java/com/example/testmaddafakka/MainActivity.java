package com.example.testmaddafakka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StartPage startPage = new StartPage();
        final ImageView backBtn = findViewById(R.id.backArrow);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.fragmentContainer, startPage);
        fragmentTransaction.commit();
        //testMovieGetter();
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer, new StartPage());
                fr.commit();

            }
        });

    }

    private void testMovieGetter() {
        IAdapter adapter = new IMDbApiAdapter();
        LinkedList<Movie> list = (LinkedList<Movie>) adapter.getMovies();
        Movie movie = list.getFirst();
        System.out.println(movie.getTitle());
    }
}