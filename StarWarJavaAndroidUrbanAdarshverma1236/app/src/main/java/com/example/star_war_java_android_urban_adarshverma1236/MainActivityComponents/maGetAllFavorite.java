package com.example.star_war_java_android_urban_adarshverma1236.MainActivityComponents;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

public abstract class maGetAllFavorite extends maMenu {
    @SuppressLint("StaticFieldLeak")
    protected void getAllFavorite() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                mCharacterList.clear();
                mCharacterList.addAll(favoriteDbHelper.getAllFavorite());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mCharactersAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
