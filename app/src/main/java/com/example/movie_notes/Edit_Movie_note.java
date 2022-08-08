package com.example.movie_notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

import java.util.ArrayList;

public class Edit_Movie_note extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie_note);

        EditText txt_edit_title = findViewById(R.id.txt_edit_title);
        EditText txt_edit_notes = findViewById(R.id.txt_edit_notes);
        Spinner edit_spinner = findViewById(R.id.edit_spinner);
        EditText txt_edit_date = findViewById(R.id.txt_edit_date);
        RatingBar edit_rating = findViewById(R.id.edit_rating);
        Button btn_update = findViewById(R.id.btn_update);

        Movie movie = new Movie(Edit_Movie_note.this, getIntent().getExtras().getInt("movie_id"));

        ArrayList<String> categories = Movie.getAllCategories(Edit_Movie_note.this);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(Edit_Movie_note.this, R.layout.spinner_item, categories);
        edit_spinner.setAdapter(spinnerAdapter);

        txt_edit_title.setText(movie.getMovie_title());
        txt_edit_notes.setText(movie.getNotes());
        txt_edit_date.setText(movie.getDate());
        edit_spinner.setSelection(movie.getCategory_id() - 1);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movie.update(Edit_Movie_note.this, txt_edit_title.getText().toString(), txt_edit_notes.getText().toString(), txt_edit_date.getText().toString(), edit_spinner.getSelectedItemPosition() - 1);
                Edit_Movie_note.this.finish();
            }
        });
    }
}