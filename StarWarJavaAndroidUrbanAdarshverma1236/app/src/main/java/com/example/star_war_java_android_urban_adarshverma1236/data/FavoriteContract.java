package com.example.star_war_java_android_urban_adarshverma1236.data;

import android.provider.BaseColumns;

public class FavoriteContract {
    public static final class FavoriteEntry implements BaseColumns {

        public static final String TABLE_NAME = "favorite";
        public static final String COLUMN_CHARACTERID = "movieid";
        public static final String COLUMN_NAME = "title";
        public static final String COLUMN_MASS = "mass";
        public static final String COLUMN_HAIR_COLOR = "haircolor";
        public static final String COLUMN_SKIN_COLOR = "skincolor";
        public static final String COLUMN_EYE_COLOR = "eyecolor";
        public static final String COLUMN_BIRTH_YEAR = "birthyear";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_HOMEWORLD = "homeworld";
        public static final String COLUMN_CREATED = "created";
        public static final String COLUMN_EDITED = "edited";
        public static final String COLUMN_URL = "url";
    }
}



