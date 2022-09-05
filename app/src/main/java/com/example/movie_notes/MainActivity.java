package com.example.movie_notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Movie> movies = new ArrayList<>();

    private void getMovies(String searchTerm) {
        movies.clear();
        SqlHelper helper = new SqlHelper(this);
        SQLiteDatabase database = helper.getWritableDatabase();
        SqlSearch search = new SqlSearch(this);
        Movie movie = null;
        Cursor cursor = search.retrieve(searchTerm);

         while (cursor.moveToNext()) {
             int movie_id = cursor.getInt(0);
             String movie_title = cursor.getString(1);

             movie = new Movie();
             movie.setMovie_id(movie_id);
             movie.setMovie_title(movie_title);

             movies.add(movie);
         }
         database.close();
         helper.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_add_movie = findViewById(R.id.btn_add_movie);
        recyclerView = findViewById(R.id.recyclerView);

        btn_add_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Add_Movie_Activity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        ArrayList<Movie> movies = Movie.getAll(MainActivity.this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        MovieAdapter adapter = new MovieAdapter(MainActivity.this, movies);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_bar, menu);

        MenuItem menuItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Αναζήτηση...");
        searchView.setSubmitButtonEnabled(true);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                getMovies(query);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}

