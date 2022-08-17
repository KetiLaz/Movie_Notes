package com.example.movie_notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;


public class Movie {
    private int movie_id;
    private String movie_title;
    private String notes;
    private String date;
    private int category_id;
    private double rating;

    public Movie() {
    }

    public Movie(String movie_title, String notes, String date, int category_id, double rating) {
        this.movie_id = movie_id;
        this.movie_title = movie_title;
        this.notes = notes;
        this.date = date;
        this.category_id = category_id;
        this.rating = rating;
    }

    public Movie(Context context, int movie_id) {
        SqlHelper helper = new SqlHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();

        Cursor cursor = database.query(SqlHelper.TABLE_MOVIES, SqlHelper.MOVIES_COLUMN, "id=?", new String[]{String.valueOf(movie_id)}, null, null, null);

        cursor.moveToFirst();

        this.movie_id = movie_id;
        this.movie_title = cursor.getString(1);
        this.notes = cursor.getString(2);
        this.date = cursor.getString(3);
        this.category_id = cursor.getInt(4);
        this.rating = cursor.getDouble(5);

    }

    public String getCategory(Context context) {
        SqlHelper helper = new SqlHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();

        Cursor cursor = database.query(SqlHelper.TABLE_CATEGORIES, new String[]{SqlHelper.COLUMN_CATEGORY_TITLE},"id=?", new String[]{String.valueOf(this.category_id)}, null, null, null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            return cursor.getString(0);
        }
        return "ΔΡΑΣΗΣ";

    }
    public static ArrayList<Movie> getAll(Context context) {
        ArrayList<Movie> movies = new ArrayList<>();

        SqlHelper helper = new SqlHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();

        Cursor cursor = database.query(SqlHelper.TABLE_MOVIES, SqlHelper.MOVIES_COLUMN, null, null, null, null, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            Movie movie = new Movie();
            movie.movie_id = cursor.getInt(0);
            movie.movie_title = cursor.getString(1);
            movie.notes = cursor.getString(2);
            movie.date = cursor.getString(3);
            movie.category_id = cursor.getInt(4);
            movie.rating = cursor.getDouble(5);

            movies.add(movie);

            cursor.moveToNext();
        }

        database.close();
        helper.close();

        return movies;

    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void save(Context context) {
        SqlHelper helper = new SqlHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(helper.COLUMN_MOVIE_TITLE, this.movie_title);
        values.put(helper.COLUMN_NOTES, this.notes);
        values.put(helper.COLUMN_DATE, this.date);
        values.put(helper.COLUMN_CATEGORY_ID, this.category_id);
        values.put(helper.COLUMN_RATING, this.rating);

        database.insert(helper.TABLE_MOVIES, null, values);

        database.close();
        helper.close();
    }

    public void update(Context context, String movie_title, String notes, String date, int category_id, double rating) {
        SqlHelper helper = new SqlHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(helper.COLUMN_MOVIE_TITLE, movie_title);
        values.put(helper.COLUMN_NOTES, notes);
        values.put(helper.COLUMN_DATE, date);
        values.put(helper.COLUMN_CATEGORY_ID, category_id);
        values.put(helper.COLUMN_RATING, rating);

        database.update(SqlHelper.TABLE_MOVIES, values, "id = ?", new String[]{String.valueOf(this.movie_id)});

        database.close();
        helper.close();
    }

    public void delete(Context context) {
        SqlHelper helper = new SqlHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();

        database.delete(SqlHelper.TABLE_MOVIES, "id = ?", new String[]{String.valueOf(this.movie_id)});

        database.close();
        helper.close();
    }
}
