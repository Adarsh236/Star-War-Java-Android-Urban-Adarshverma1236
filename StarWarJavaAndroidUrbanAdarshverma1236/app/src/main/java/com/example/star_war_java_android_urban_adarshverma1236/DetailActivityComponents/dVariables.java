package com.example.star_war_java_android_urban_adarshverma1236.DetailActivityComponents;

import android.database.sqlite.SQLiteDatabase;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.star_war_java_android_urban_adarshverma1236.data.FavoriteDbHelper;
import com.example.star_war_java_android_urban_adarshverma1236.model.Character;

public abstract class dVariables extends AppCompatActivity {
    protected TextView Aname;
    protected TextView Amass;
    protected TextView Ahaircolor;
    protected TextView Askincolor;
    protected TextView Aeyecolor;
    protected TextView Abirthyear;
    protected TextView Agender;
    protected TextView Ahomeworld;
    protected TextView Acreated;
    protected TextView Aedited;
    protected TextView Aurl;
    protected ImageView imageView;

    protected FavoriteDbHelper favoriteDbHelper;

    protected SQLiteDatabase mDb;

    protected Character mCharacter;
    protected String thumbnail;
    protected String Bname;
    protected String Bmass;
    protected String Bhaircolor;
    protected String Bskincolor;
    protected String Beyecolor;
    protected String Bbirthyear;
    protected String Bgender;
    protected String Bhomeworld;
    protected String Bcreated;
    protected String Bedited;
    protected String Burl;
    protected String mcharacter_id;
}
