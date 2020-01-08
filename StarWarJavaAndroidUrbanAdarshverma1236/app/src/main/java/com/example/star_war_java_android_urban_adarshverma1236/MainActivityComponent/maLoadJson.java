package com.example.star_war_java_android_urban_adarshverma1236.MainActivityComponent;

import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.star_war_java_android_urban_adarshverma1236.BuildConfig;
import com.example.star_war_java_android_urban_adarshverma1236.adapter.CharactersAdapter;
import com.example.star_war_java_android_urban_adarshverma1236.api.Client;
import com.example.star_war_java_android_urban_adarshverma1236.api.Service;
import com.example.star_war_java_android_urban_adarshverma1236.model.Character;
import com.example.star_war_java_android_urban_adarshverma1236.model.CharacterComparator;
import com.example.star_war_java_android_urban_adarshverma1236.model.CharactersResponse;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class maLoadJson extends maVariables {
    // calling api
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void loadJSON() {

        try {
            if (BuildConfig.THE_STAR_WAR_API_TOKEN.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please obtain API Key Firstly", Toast.LENGTH_SHORT).show();
                pd.dismiss();
                return;
            }
            Client Client = new Client();
            Service apiService = Client.getClient().create(Service.class);
            Call<CharactersResponse> call = apiService.getPeople(FirstPageNumber);
            call.enqueue(new Callback<CharactersResponse>() {
                @Override
                public void onResponse(Call<CharactersResponse> call, Response<CharactersResponse> response) {
                    List<Character> characters = response.body().getResults();
                    Collections.sort(characters, CharacterComparator.BY_NAME_ALPHABETICAL);//implement comparator
                    recyclerView.setAdapter(new CharactersAdapter(getApplicationContext(), characters));
                    recyclerView.smoothScrollToPosition(0);
                    if (swipeContainer.isRefreshing()) {
                        swipeContainer.setRefreshing(false);
                    }
                    LoadMorePageNumber = 1;
                    LoadLessPageNumber = 1;
                    mCharacterList = characters;
                }

                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onFailure(Call<CharactersResponse> call, Throwable t) {

                    Log.d("Error", Objects.requireNonNull(t.getMessage()));
                    //Toast.makeText(MainActivity.this, "Error Fetching Data! OR Internet Problem", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Log.d("Error", Objects.requireNonNull(e.getMessage()));
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
