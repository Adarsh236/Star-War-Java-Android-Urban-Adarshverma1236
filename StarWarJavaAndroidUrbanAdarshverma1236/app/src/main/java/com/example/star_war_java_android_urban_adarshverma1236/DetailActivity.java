package com.example.star_war_java_android_urban_adarshverma1236;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.star_war_java_android_urban_adarshverma1236.data.FavoriteContract;
import com.example.star_war_java_android_urban_adarshverma1236.data.FavoriteDbHelper;
import com.example.star_war_java_android_urban_adarshverma1236.model.Character;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.snackbar.Snackbar;

public class DetailActivity extends AppCompatActivity {

    TextView Aname, Amass, Ahaircolor, Askincolor, Aeyecolor, Abirthyear, Agender, Ahomeworld, Acreated, Aedited, Aurl;
    ImageView imageView;

    private RecyclerView recyclerView;
    private FavoriteDbHelper favoriteDbHelper;
    private Character favorite;
    private final AppCompatActivity activity = DetailActivity.this;

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

        //initCollapsingToolbar();
        /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++p11*/
        //TODO
        FavoriteDbHelper dbHelper = new FavoriteDbHelper(this);
        mDb = dbHelper.getWritableDatabase();
        /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++p11*/

        imageView = (ImageView) findViewById(R.id.thumbnail_image_header);
        Aname = (TextView) findViewById(R.id.title);
        Amass = (TextView) findViewById(R.id.plotsynopsis);
        Ahaircolor = (TextView) findViewById(R.id.userrating);
        Askincolor = (TextView) findViewById(R.id.releasedate);
        Aeyecolor = (TextView) findViewById(R.id.eyecolor);
        Abirthyear = (TextView) findViewById(R.id.birthyear);
        Agender = (TextView) findViewById(R.id.gender);
        Ahomeworld = (TextView) findViewById(R.id.homeworld);
        Acreated = (TextView) findViewById(R.id.created);
        Aedited = (TextView) findViewById(R.id.edited);
        Aurl = (TextView) findViewById(R.id.url);


        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra("characters")) {//************fp3

            mCharacter = new Character();
            mCharacter = getIntent().getParcelableExtra("characters");//***********fp3

            thumbnail = mCharacter.getUrl();//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
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

            mcharacter_id = mCharacter.getHeight();

            String poster = "https://mcdn.wallpapersafari.com/medium/96/57/UXFOuz.jpg";


            Glide.with(this)
                    .load(poster)//p3-----------------
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
                   /* MaterialFavoriteButton materialFavoriteButtonNice =
                            (MaterialFavoriteButton) findViewById(R.id.favorite_button);*///--------------p11
        MaterialFavoriteButton materialFavoriteButton = (MaterialFavoriteButton) findViewById(R.id.favorite_button);////+++++++++++++p11
        //++++++++++++++++++++++++++++++++++++++++++++p11
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
                                int movie_id = getIntent().getExtras().getInt("id");
                                favoriteDbHelper = new FavoriteDbHelper(DetailActivity.this);
                                favoriteDbHelper.deleteFavorite(movie_id);
                                Snackbar.make(buttonView, "Removed from Favorite",
                                        Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    });


        }

        //initViews();

    }

    public boolean Exists(String searchItem) {

        String[] projection = {
                FavoriteContract.FavoriteEntry._ID,
                FavoriteContract.FavoriteEntry.COLUMN_MOVIEID,
                FavoriteContract.FavoriteEntry.COLUMN_TITLE,
                FavoriteContract.FavoriteEntry.COLUMN_USERRATING,
                FavoriteContract.FavoriteEntry.COLUMN_POSTER_PATH,
                FavoriteContract.FavoriteEntry.COLUMN_PLOT_SYNOPSIS

        };
        String selection = FavoriteContract.FavoriteEntry.COLUMN_TITLE + " =?";
        String[] selectionArgs = {searchItem};
        String limit = "1";

        Cursor cursor = mDb.query(FavoriteContract.FavoriteEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null, limit);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
        ///////////////////p++++++++++++++++++++++p11
        //initViews();
    }

    public void saveFavorite() {
        favoriteDbHelper = new FavoriteDbHelper(activity);
        favorite = new Character();

        String rate = mCharacter.getMass();

        favorite.setHeight(mcharacter_id);
        favorite.setName(Bname);
        favorite.setUrl(thumbnail);
        favorite.setMass(Bmass);
        favorite.setBirthyear(Bbirthyear);

        favoriteDbHelper.addFavorite(favorite);
    }

}
