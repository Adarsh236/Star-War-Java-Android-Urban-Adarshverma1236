package com.example.star_war_java_android_urban_adarshverma1236.DetailActivityComponenets;

import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.star_war_java_android_urban_adarshverma1236.R;
import com.example.star_war_java_android_urban_adarshverma1236.data.FavoriteDbHelper;
import com.example.star_war_java_android_urban_adarshverma1236.model.Character;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.Objects;

public abstract class dCreate extends dVariables {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void InnerClassOfonCreate() {
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
    }
}
