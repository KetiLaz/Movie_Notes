package com.example.movie_notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Add_Movie_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        EditText txt_movie_title = findViewById(R.id.Txt_movie_title);
        EditText txt_notes = findViewById(R.id.Txt_notes);
        Spinner categories_spinner = findViewById(R.id.categories_spinner);
        EditText txt_date = findViewById(R.id.Txt_date);
        RatingBar rating = findViewById(R.id.rating);
        Button btn_save = findViewById(R.id.btn_save);

        txt_date.setText(DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMAN).format(new Date()));


        ArrayList<Category> categories = Category.getAllCategories(Add_Movie_Activity.this);
        Spinner_CustomAdapter customAdapter = new Spinner_CustomAdapter(Add_Movie_Activity.this, categories);

        categories_spinner.setAdapter(customAdapter);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Movie movie = new Movie(txt_movie_title.getText().toString(), txt_notes.getText().toString(), txt_date.getText().toString(), categories_spinner.getSelectedItemPosition(), rating.getRating());
                movie.save(Add_Movie_Activity.this);
                Add_Movie_Activity.this.finish();
            }
        });
    }
}