package com.example.movie_notes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Category {

    private int id;
    private String title;

    public Category() {
    }

    public Category(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public static ArrayList<Category> getAllCategories(Context context) {
        ArrayList<Category> categories = new ArrayList<>();
        SqlHelper helper = new SqlHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();

        Cursor cursor = database.query(SqlHelper.TABLE_CATEGORIES, SqlHelper.CATEGORIES_COLUMN,null, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            Category category = new Category();
            category.id = cursor.getInt(1);
            category.title = cursor.getString(1);
            categories.add(category);

            cursor.moveToNext();
        }

        database.close();
        helper.close();

        return categories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category(Context context, int id) {
        SqlHelper helper = new SqlHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();

        Cursor cursor = database.query(helper.TABLE_CATEGORIES, helper.CATEGORIES_COLUMN, "id=?", new String[]{String.valueOf(id)}, null, null, null);

        cursor.moveToFirst();

        this.id = id;
        this.title = cursor.getString(1);
    }

}


