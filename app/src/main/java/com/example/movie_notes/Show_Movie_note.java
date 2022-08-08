package com.example.movie_notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

public class Show_Movie_note extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_movie_note);

        EditText txt_show_title = findViewById(R.id.txt_show_title);
        EditText txt_show_notes = findViewById(R.id.txt_show_notes);
        EditText txt_show_category = findViewById(R.id.txt_show_category);
        EditText txt_show_date = findViewById(R.id.txt_show_date);
        RatingBar show_rating = findViewById(R.id.show_rating);

        Movie movie = new Movie(Show_Movie_note.this, getIntent().getExtras().getInt("movie_id"));

        txt_show_title.setText(movie.getMovie_title());
        txt_show_notes.setText(movie.getNotes());
        txt_show_date.setText(movie.getDate());
        txt_show_category.setText(movie.getCategory(Show_Movie_note.this));
        // show_rating.setRating(movie.getRating());


    }
}