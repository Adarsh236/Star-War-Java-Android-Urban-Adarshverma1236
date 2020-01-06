package com.example.star_war_java_android_urban_adarshverma1236;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.star_war_java_android_urban_adarshverma1236.adapter.CharactersAdapter;
import com.example.star_war_java_android_urban_adarshverma1236.api.Client;
import com.example.star_war_java_android_urban_adarshverma1236.api.Service;
import com.example.star_war_java_android_urban_adarshverma1236.data.FavoriteContract;
import com.example.star_war_java_android_urban_adarshverma1236.data.FavoriteDbHelper;
import com.example.star_war_java_android_urban_adarshverma1236.model.Character;
import com.example.star_war_java_android_urban_adarshverma1236.model.CharactersResponse;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.snackbar.Snackbar;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.star_war_java_android_urban_adarshverma1236.MainActivity.LOG_TAG;

public class DetailActivity extends AppCompatActivity {

    TextView Aname, Amass, Ahaircolor, Askincolor, Aeyecolor, Abirthyear, Agender, Ahomeworld, Acreated, Aedited, Aurl;
    ImageView imageView;

    //private RecyclerView recyclerView;
    private FavoriteDbHelper favoriteDbHelper;
    private Character favorite;
    private final AppCompatActivity activity = DetailActivity.this;
    private String PictureURL2 = "https://mcdn.wallpapersafari.com/medium/96/57/UXFOuz.jpg";

    private SQLiteDatabase mDb;

    Character mCharacter;
    String thumbnail, Bname, Bmass, Bhaircolor, Bskincolor, Beyecolor, Bbirthyear, Bgender, Bhomeworld, Bcreated, Bedited, Burl;
    String mcharacter_id;//+++++++++++

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //TODO
        FavoriteDbHelper dbHelper = new FavoriteDbHelper(this);
        mDb = dbHelper.getWritableDatabase();
        /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++p11*/

        imageView = (ImageView) findViewById(R.id.thumbnail_image_header);
        Aname = (TextView) findViewById(R.id.title);
        Amass = (TextView) findViewById(R.id.mass);
        Ahaircolor = (TextView) findViewById(R.id.haircolor);
        Askincolor = (TextView) findViewById(R.id.skincolor);
        Aeyecolor = (TextView) findViewById(R.id.eyecolor);
        Abirthyear = (TextView) findViewById(R.id.birthyear);
        Agender = (TextView) findViewById(R.id.gender);
        Ahomeworld = (TextView) findViewById(R.id.homeworld);
        Acreated = (TextView) findViewById(R.id.created);
        Aedited = (TextView) findViewById(R.id.edited);
        Aurl = (TextView) findViewById(R.id.url);


        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra("characters")) {

            mCharacter = new Character();
            mCharacter = getIntent().getParcelableExtra("characters");

            thumbnail = mCharacter.getUrl();
            Bname = mCharacter.getName();
            Bmass = mCharacter.getMass();
            Bhaircolor = mCharacter.getHaircolor();
            Bskincolor = mCharacter.getSkincolor();
            Beyecolor = mCharacter.getEyecolor();
            Bbirthyear = mCharacter.getBirthyear();
            Bgender = mCharacter.getGender();
            Bhomeworld = mCharacter.getHomeworld();
            Bcreated = mCharacter.getCreated();
            Bedited = mCharacter.getEdited();
            Burl = mCharacter.getUrl();
            String[] str = mCharacter.getUrl().split("/");//splitting for ID
            String idmo = (str[str.length - 1]);
            mcharacter_id = idmo;

            String poster = PictureURL2;

            Glide.with(this)
                    .load(poster)
                    .placeholder(R.drawable.load)
                    .into(imageView);

            Aname.setText(Bname);
            Amass.setText(Bmass);
            Ahaircolor.setText(Bhaircolor);
            Askincolor.setText(Bskincolor);
            Aeyecolor.setText(Beyecolor);
            Abirthyear.setText(Bbirthyear);
            Agender.setText(Bgender);
            Ahomeworld.setText(Bhomeworld);
            Acreated.setText(Bcreated);
            Aedited.setText(Bedited);
            Aurl.setText(Burl);

            //TODO
            ((CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar)).setTitle(Bname);

        } else {
            Toast.makeText(this, "No API Data", Toast.LENGTH_SHORT).show();
        }
        MaterialFavoriteButton materialFavoriteButton = (MaterialFavoriteButton) findViewById(R.id.favorite_button);
        try {
            if (Exists(Bname)) {
                materialFavoriteButton.setFavorite(true);
                materialFavoriteButton.setOnFavoriteChangeListener(
                        new MaterialFavoriteButton.OnFavoriteChangeListener() {
                            @Override
                            public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                                if (favorite == true) {
                                    saveFavorite();
                                    Snackbar.make(buttonView, "Added to Favorite",
                                            Snackbar.LENGTH_SHORT).show();
                                } else {
                                    favoriteDbHelper = new FavoriteDbHelper(DetailActivity.this);
                                    favoriteDbHelper.deleteFavorite(Integer.parseInt(mcharacter_id));
                                    Snackbar.make(buttonView, "Removed from Favorite",
                                            Snackbar.LENGTH_SHORT).show();
                                }
                            }
                        });


            } else {
                materialFavoriteButton.setOnFavoriteChangeListener(
                        new MaterialFavoriteButton.OnFavoriteChangeListener() {
                            @Override
                            public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                                if (favorite == true) {
                                    saveFavorite();
                                    Snackbar.make(buttonView, "Added to Favorite",
                                            Snackbar.LENGTH_SHORT).show();
                                } else {
                                    int mcharacter_id = getIntent().getExtras().getInt("id");
                                    favoriteDbHelper = new FavoriteDbHelper(DetailActivity.this);
                                    favoriteDbHelper.deleteFavorite(mcharacter_id);
                                    Snackbar.make(buttonView, "Removed from Favorite",
                                            Snackbar.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

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

    public void saveFavorite() {
        favoriteDbHelper = new FavoriteDbHelper(activity);
        favorite = new Character();
        String[] str = Burl.split("/");//Splitting for ID
        int idmo = Integer.parseInt(str[str.length - 1]);

        favorite.setHeight(mcharacter_id);
        Log.d(LOG_TAG, "id 1: " + mcharacter_id);
        Log.d(LOG_TAG, "id 2: " + idmo);
        Log.d(LOG_TAG, "id 3: " + Burl);

        favorite.setName(Bname);
        favorite.setMass(Bmass);
        favorite.setHaircolor(Bhaircolor);
        favorite.setSkincolor(Bskincolor);
        favorite.setEyecolor(Beyecolor);
        favorite.setBirthyear(Bbirthyear);
        favorite.setGender(Bgender);
        favorite.setHomeworld(Bhomeworld);
        favorite.setCreated(Bcreated);
        favorite.setEdited(Bedited);
        favorite.setUrl(thumbnail);

        favoriteDbHelper.addFavorite(favorite);
    }
}
