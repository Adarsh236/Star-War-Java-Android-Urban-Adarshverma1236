package com.example.star_war_java_android_urban_adarshverma1236;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Movie;
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

    ImageView imageView;//-----------------------traler-

    private RecyclerView recyclerView;/////trailer
    private FavoriteDbHelper favoriteDbHelper;
    private Character favorite;
    private final AppCompatActivity activity = DetailActivity.this;

    private SQLiteDatabase mDb;

    Character mCharacter;
    String thumbnail, Bname, Bmass, Bhaircolor, Bskincolor, Beyecolor, Bbirthyear, Bgender, Bhomeworld, Bcreated, Bedited, Burl;
    String movie_id;//+++++++++++

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
        if (intentThatStartedThisActivity.hasExtra("movies")) {

            /*movie = getIntent().getParcelableExtra("movies");

            thumbnail = movie.getPosterPath();
            movieName = movie.getOriginalTitle();
            synopsis = movie.getOverview();
            rating = Double.toString(movie.getVoteAverage());
            dateOfRelease = movie.getReleaseDate();
            movie_id = movie.getId();
            *//*

                        String thumbnail = getIntent().getExtras().getString("poster_path");
                        String movieName = getIntent().getExtras().getString("original_title");
                        String synopsis = getIntent().getExtras().getString("overview");
                        String rating = getIntent().getExtras().getString("vote_average");
                        String dateOfRelease = getIntent().getExtras().getString("release_date");
            *//*


            // adding past 3
            String poster = "https://image.tmdb.org/t/p/w500" + thumbnail;


            Glide.with(this)
                    // .load(thumbnail)
                    .load(poster)//p3-----------------
                    .placeholder(R.drawable.load)
                    .into(imageView);

            nameOfMovie.setText(movieName);
            plotSynopsis.setText(synopsis);
            userRating.setText(rating);
            releaseDate.setText(dateOfRelease);*/
            mCharacter = new Character();
            mCharacter = getIntent().getParcelableExtra("movies");

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

            movie_id = mCharacter.getHeight();








            //++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            /*

                        String thumbnail = getIntent().getExtras().getString("poster_path");
                        String movieName = getIntent().getExtras().getString("original_title");
                        String synopsis = getIntent().getExtras().getString("overview");
                        String rating = getIntent().getExtras().getString("vote_average");
                        String dateOfRelease = getIntent().getExtras().getString("release_date");
            */


            // adding past 3
            //String poster = "https://image.tmdb.org/t/p/w500" + thumbnail;
            String poster ="https://mcdn.wallpapersafari.com/medium/96/57/UXFOuz.jpg";


            Glide.with(this)
                    // .load(thumbnail)
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

                  /*  SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                    materialFavoriteButton.setOnFavoriteChangeListener(////+++++++++++++p11
                            new MaterialFavoriteButton.OnFavoriteChangeListener(){
                                @Override
                                public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite){
                                    if (favorite){
                                        SharedPreferences.Editor editor = getSharedPreferences("com.example.starsample1.DetailActivity", MODE_PRIVATE).edit();
                                        editor.putBoolean("Favorite Added", true);
                                        editor.commit();
                                        saveFavorite();/////p3
                                        Snackbar.make(buttonView, "Added to Favorite",
                                                Snackbar.LENGTH_SHORT).show();
                                    }else{
                                        int movie_id = getIntent().getExtras().getInt("id");
                                        favoriteDbHelper = new FavoriteDbHelper(DetailActivity.this);
                                        favoriteDbHelper.deleteFavorite(movie_id);

                                        SharedPreferences.Editor editor = getSharedPreferences("com.example.starsample1.DetailActivity", MODE_PRIVATE).edit();
                                        editor.putBoolean("Favorite Removed", true);
                                        editor.commit();
                                        Snackbar.make(buttonView, "Removed from Favorite",
                                                Snackbar.LENGTH_SHORT).show();
                                    }

                                }
                            }
                    );*/
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
                                favoriteDbHelper.deleteFavorite(Integer.parseInt(movie_id));
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


        //initViews();// trailer
    }

    /* private void initCollapsingToolbar(){
         final CollapsingToolbarLayout collapsingToolbarLayout =
                 (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
         collapsingToolbarLayout.setTitle(" ");
         AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
         appBarLayout.setExpanded(true);

         appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener(){
             boolean isShow = false;
             int scrollRange = -1;

             @Override
             public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset){
                 if (scrollRange == -1){
                     scrollRange = appBarLayout.getTotalScrollRange();
                 }
                 if (scrollRange + verticalOffset == 0){
                     collapsingToolbarLayout.setTitle(getString(R.string.movie_details));
                     isShow = true;
                 }else if (isShow){
                     collapsingToolbarLayout.setTitle(" ");
                     isShow = false;
                 }
             }
         });
     }//------------------------------------*/
    public void saveFavorite() {
        favoriteDbHelper = new FavoriteDbHelper(activity);
        favorite = new Character();

                   /* int movie_id = getIntent().getExtras().getInt("id");
                    String rate = getIntent().getExtras().getString("vote_average");
                    String poster = getIntent().getExtras().getString("poster_path");
*/
        String rate = mCharacter.getMass();


        favorite.setHeight(movie_id);
        favorite.setName(Bname);
        favorite.setUrl(thumbnail);
        favorite.setMass(Bmass);
        favorite.setBirthyear(Bbirthyear);
                    /*favorite.setOriginalTitle(nameOfMovie.getText().toString().trim());
                    favorite.setPosterPath(poster);
                    favorite.setVoteAverage(Double.parseDouble(rate));
                    favorite.setOverview(plotSynopsis.getText().toString().trim());*/

        favoriteDbHelper.addFavorite(favorite);
    }

}
