package com.example.star_war_java_android_urban_adarshverma1236.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.star_war_java_android_urban_adarshverma1236.model.Character;

import java.util.ArrayList;
import java.util.List;

public class FavoriteDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "favorite1.db";

    private static final int DATABASE_VERSION = 1;

    public static final String LOGTAG = "FAVORITE";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase db;

    public FavoriteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() {
        Log.i(LOGTAG, "Database Opened");
        db = dbhandler.getWritableDatabase();
    }

    public void close() {
        Log.i(LOGTAG, "Database Closed");
        dbhandler.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_FAVORITE_TABLE = "CREATE TABLE " + FavoriteContract.FavoriteEntry.TABLE_NAME + " (" +
                FavoriteContract.FavoriteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                FavoriteContract.FavoriteEntry.COLUMN_CHARACTERID + " TEXT NOT NULL, " +
                FavoriteContract.FavoriteEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                FavoriteContract.FavoriteEntry.COLUMN_MASS + " REAL, " +
                FavoriteContract.FavoriteEntry.COLUMN_HAIR_COLOR + " TEXT, " +
                FavoriteContract.FavoriteEntry.COLUMN_SKIN_COLOR + " TEXT, " +
                FavoriteContract.FavoriteEntry.COLUMN_EYE_COLOR + " TEXT, " +
                FavoriteContract.FavoriteEntry.COLUMN_BIRTH_YEAR + " TEXT, " +
                FavoriteContract.FavoriteEntry.COLUMN_GENDER + " TEXT, " +
                FavoriteContract.FavoriteEntry.COLUMN_HOMEWORLD + " TEXT, " +
                FavoriteContract.FavoriteEntry.COLUMN_CREATED + " TEXT, " +
                FavoriteContract.FavoriteEntry.COLUMN_EDITED + " TEXT, " +
                FavoriteContract.FavoriteEntry.COLUMN_URL + " TEXT " +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FavoriteContract.FavoriteEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addFavorite(Character character) {
        SQLiteDatabase db = this.getWritableDatabase();

        String[] str = character.getUrl().split("/");//split to get ID
        String  idmo = (str[str.length-1]);//split to get ID

        ContentValues values = new ContentValues();
        values.put(FavoriteContract.FavoriteEntry.COLUMN_CHARACTERID, idmo);//+++++++++++++++++++++++++++++
        values.put(FavoriteContract.FavoriteEntry.COLUMN_NAME, character.getName());
        values.put(FavoriteContract.FavoriteEntry.COLUMN_MASS, character.getMass());
        values.put(FavoriteContract.FavoriteEntry.COLUMN_HAIR_COLOR, character.getHaircolor());
        values.put(FavoriteContract.FavoriteEntry.COLUMN_SKIN_COLOR, character.getSkincolor());
        values.put(FavoriteContract.FavoriteEntry.COLUMN_EYE_COLOR, character.getEyecolor());
        values.put(FavoriteContract.FavoriteEntry.COLUMN_BIRTH_YEAR, character.getBirthyear());
        values.put(FavoriteContract.FavoriteEntry.COLUMN_GENDER, character.getGender());
        values.put(FavoriteContract.FavoriteEntry.COLUMN_HOMEWORLD, character.getHomeworld());
        values.put(FavoriteContract.FavoriteEntry.COLUMN_CREATED, character.getCreated());
        values.put(FavoriteContract.FavoriteEntry.COLUMN_EDITED, character.getEdited());
        values.put(FavoriteContract.FavoriteEntry.COLUMN_URL, character.getUrl());

        db.insert(FavoriteContract.FavoriteEntry.TABLE_NAME, null, values);
        db.close();
    }

    public void deleteFavorite(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(FavoriteContract.FavoriteEntry.TABLE_NAME, FavoriteContract.FavoriteEntry.COLUMN_CHARACTERID + "=" + id, null);
    }

    public List<Character> getAllFavorite() {
        String[] columns = {
                FavoriteContract.FavoriteEntry._ID,
                FavoriteContract.FavoriteEntry.COLUMN_CHARACTERID,
                FavoriteContract.FavoriteEntry.COLUMN_NAME,
                FavoriteContract.FavoriteEntry.COLUMN_MASS,
                FavoriteContract.FavoriteEntry.COLUMN_HAIR_COLOR,
                FavoriteContract.FavoriteEntry.COLUMN_SKIN_COLOR,
                FavoriteContract.FavoriteEntry.COLUMN_EYE_COLOR,
                FavoriteContract.FavoriteEntry.COLUMN_BIRTH_YEAR,
                FavoriteContract.FavoriteEntry.COLUMN_GENDER,
                FavoriteContract.FavoriteEntry.COLUMN_HOMEWORLD,
                FavoriteContract.FavoriteEntry.COLUMN_CREATED,
                FavoriteContract.FavoriteEntry.COLUMN_EDITED,
                FavoriteContract.FavoriteEntry.COLUMN_URL
        };
        String sortOrder =
                FavoriteContract.FavoriteEntry._ID + " ASC";
        List<Character> favoriteList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(FavoriteContract.FavoriteEntry.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                sortOrder);

        if (cursor.moveToFirst()) {
            do {
                Character character = new Character();
                character.setHeight(cursor.getString(cursor.getColumnIndex((FavoriteContract.FavoriteEntry.COLUMN_CHARACTERID))));//+++++++++++++++++++++++++++++
                character.setName(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMN_NAME)));
                character.setMass(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMN_MASS)));
                character.setHaircolor(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMN_HAIR_COLOR)));
                character.setSkincolor(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMN_SKIN_COLOR)));
                character.setEyecolor(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMN_EYE_COLOR)));
                character.setBirthyear(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMN_BIRTH_YEAR)));
                character.setGender(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMN_GENDER)));
                character.setHomeworld(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMN_HOMEWORLD)));
                character.setCreated(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMN_CREATED)));
                character.setEdited(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMN_EDITED)));
                character.setUrl(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMN_URL)));

                favoriteList.add(character);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return favoriteList;
    }
}
