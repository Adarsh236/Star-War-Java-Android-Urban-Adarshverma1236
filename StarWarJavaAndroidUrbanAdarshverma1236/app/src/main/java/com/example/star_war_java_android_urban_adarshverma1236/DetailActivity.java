package com.example.star_war_java_android_urban_adarshverma1236;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.star_war_java_android_urban_adarshverma1236.data.FavoriteContract;
import com.example.star_war_java_android_urban_adarshverma1236.data.FavoriteDbHelper;
import com.example.star_war_java_android_urban_adarshverma1236.model.Character;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import static com.example.star_war_java_android_urban_adarshverma1236.MainActivity.LOG_TAG;

public class DetailActivity extends AppCompatActivity {

    TextView Aname, Amass, Ahaircolor, Askincolor, Aeyecolor, Abirthyear, Agender, Ahomeworld, Acreated, Aedited, Aurl;
    ImageView imageView;

    private FavoriteDbHelper favoriteDbHelper;
    private final AppCompatActivity activity = DetailActivity.this;

    private SQLiteDatabase mDb;

    Character mCharacter;
    String thumbnail, Bname, Bmass, Bhaircolor, Bskincolor, Beyecolor, Bbirthyear, Bgender, Bhomeworld, Bcreated, Bedited, Burl;
    String mcharacter_id;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //TODO
        FavoriteDbHelper dbHelper = new FavoriteDbHelper(this);
        mDb = dbHelper.getWritableDatabase();

        imageView = findViewById(R.id.thumbnail_image_header);
        Aname = findViewById(R.id.title);
        Amass = findViewById(R.id.mass);
        Ahaircolor = findViewById(R.id.haircolor);
        Askincolor = findViewById(R.id.skincolor);
        Aeyecolor = findViewById(R.id.eyecolor);
        Abirthyear = findViewById(R.id.birthyear);
        Agender = findViewById(R.id.gender);
        Ahomeworld = findViewById(R.id.homeworld);
        Acreated = findViewById(R.id.created);
        Aedited = findViewById(R.id.edited);
        Aurl = findViewById(R.id.url);

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra("characters")) {

            mCharacter = new Character();
            mCharacter = getIntent().getParcelableExtra("characters");

            assert mCharacter != null;
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
            mcharacter_id = (str[str.length - 1]);

            String poster = "https://mcdn.wallpapersafari.com/medium/96/57/UXFOuz.jpg";
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
        MaterialFavoriteButton materialFavoriteButton = findViewById(R.id.favorite_button);
        try {
            if (Exists(Bname)) {
                materialFavoriteButton.setFavorite(true);
                materialFavoriteButton.setOnFavoriteChangeListener(
                        new MaterialFavoriteButton.OnFavoriteChangeListener() {
                            @Override
                            public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                                if (favorite) {
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
                            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                            @Override
                            public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                                if (favorite) {
                                    saveFavorite();
                                    Snackbar.make(buttonView, "Added to Favorite",
                                            Snackbar.LENGTH_SHORT).show();
                                } else {
                                    int mcharacter_id = Objects.requireNonNull(getIntent().getExtras()).getInt("id");
                                    favoriteDbHelper = new FavoriteDbHelper(DetailActivity.this);
                                    favoriteDbHelper.deleteFavorite(mcharacter_id);
                                    Snackbar.make(buttonView, "Removed from Favorite",
                                            Snackbar.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        } catch (Exception e) {
            Log.d("Error", Objects.requireNonNull(e.getMessage()));
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
        Character favorite = new Character();
        String[] str = Burl.split("/");//Splitting for ID
        int id = Integer.parseInt(str[str.length - 1]);

        favorite.setHeight(mcharacter_id);
        Log.d(LOG_TAG, "id 1: " + mcharacter_id);
        Log.d(LOG_TAG, "id 2: " + id);
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
