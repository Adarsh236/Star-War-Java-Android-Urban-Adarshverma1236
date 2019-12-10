package com.example.star_war_java_android_urban_adarshverma1236.data;

import android.provider.BaseColumns;

public class FavoriteContract {
    public static final class FavoriteEntry implements BaseColumns {

        public static final String TABLE_NAME = "favorite";
        public static final String COLUMN_MOVIEID = "movieid";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_USERRATING = "userrating";
        public static final String COLUMN_POSTER_PATH = "posterpath";
        public static final String COLUMN_PLOT_SYNOPSIS = "overview";
    }
}
    /*public static final class FavoriteEntry implements BaseColumns {

        public static final String TABLE_NAME = "favoritecharacter1";
        public static final String COLUMN_CHARACTERID = "characterid";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_CREATED = "created";
        public static final String COLUMN_EDITED = "edited";
        public static final String COLUMN_URL = "url";

    }*/

