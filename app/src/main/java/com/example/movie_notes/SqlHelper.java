package com.example.movie_notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.security.PublicKey;
import java.sql.Date;

public class SqlHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "movie_notes";
    private static final int DATABASE_VERSION = 3;

    public static final String TABLE_MOVIES = "movies";
    public static final String  COLUMN_MOVIES_ID = "id";
    public static final String  COLUMN_MOVIE_TITLE = "title";
    public static final String  COLUMN_NOTES = "notes";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_CATEGORY_ID = "category_id";
    public static final String COLUMN_RATING = "rating";

    public static final String[] MOVIES_COLUMN = {COLUMN_MOVIES_ID, COLUMN_MOVIE_TITLE, COLUMN_NOTES, COLUMN_DATE, COLUMN_CATEGORY_ID, COLUMN_RATING};

    public static final String TABLE_CATEGORIES = "categories";
    public static final String COLUMN_CATEGORIES_ID = "id";
    public static final String COLUMN_CATEGORY_TITLE = "title";

    public static final String[] CATEGORIES_COLUMN = {COLUMN_CATEGORIES_ID, COLUMN_CATEGORY_TITLE};

    public SqlHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_MOVIES +" ("+COLUMN_MOVIES_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_MOVIE_TITLE +" TEXT NOT NULL, "
                + COLUMN_NOTES +" TEXT NOT NULL, "
                + COLUMN_DATE +" TEXT NOT NULL, "
                + COLUMN_CATEGORY_ID +" INTEGER NOT NULL, "
                + COLUMN_RATING +" DOUBLE NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_CATEGORIES +" ("+COLUMN_CATEGORIES_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CATEGORY_TITLE +" TEXT NOT NULL)");

        ContentValues values = new ContentValues();
        values.put(COLUMN_CATEGORIES_ID, 0);
        values.put(COLUMN_CATEGORY_TITLE, "ΔΡΑΣΗΣ");
        sqLiteDatabase.insert(TABLE_CATEGORIES, null, values);

        values = new ContentValues();
        values.put(COLUMN_CATEGORIES_ID, 1);
        values.put(COLUMN_CATEGORY_TITLE, "ΚΩΜΩΔΙΑ");
        sqLiteDatabase.insert(TABLE_CATEGORIES, null, values);

        values = new ContentValues();
        values.put(COLUMN_CATEGORIES_ID, 2);
        values.put(COLUMN_CATEGORY_TITLE, "ΦΑΝΤΑΣΙΑΣ");
        sqLiteDatabase.insert(TABLE_CATEGORIES, null, values);

        values = new ContentValues();
        values.put(COLUMN_CATEGORIES_ID, 3);
        values.put(COLUMN_CATEGORY_TITLE, "ΘΡΙΛΕΡ");
        sqLiteDatabase.insert(TABLE_CATEGORIES, null, values);

        values = new ContentValues();
        values.put(COLUMN_CATEGORIES_ID, 4);
        values.put(COLUMN_CATEGORY_TITLE, "ΔΡΑΜΑ");
        sqLiteDatabase.insert(TABLE_CATEGORIES, null, values);

        values = new ContentValues();
        values.put(COLUMN_CATEGORIES_ID, 5);
        values.put(COLUMN_CATEGORY_TITLE, "ΤΡΟΜΟΥ");
        sqLiteDatabase.insert(TABLE_CATEGORIES, null, values);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
