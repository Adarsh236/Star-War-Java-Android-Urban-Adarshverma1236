package com.example.star_war_java_android_urban_adarshverma1236.DetailActivityComponents;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.star_war_java_android_urban_adarshverma1236.model.Character;

import static com.example.star_war_java_android_urban_adarshverma1236.MainActivityComponents.maVariables.LOG_TAG;

public abstract class dSaveFavorite extends dCreate {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void InnerClassOfsaveFavorite() {

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
