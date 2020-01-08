package com.example.star_war_java_android_urban_adarshverma1236.DetailActivityComponenets;

import android.database.Cursor;

import com.example.star_war_java_android_urban_adarshverma1236.data.FavoriteContract;

public abstract class dCheckExitFavorites extends dSaveFavorite {
    public boolean Exists(String searchItem) {

        String[] projection = {
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

        String selection = FavoriteContract.FavoriteEntry.COLUMN_NAME + " =?";
        String[] selectionArgs = {searchItem};
        String limit = "1";

        Cursor cursor = mDb.query(FavoriteContract.FavoriteEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null, limit);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }
}
