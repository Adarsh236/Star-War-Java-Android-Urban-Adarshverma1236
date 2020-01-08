package com.example.star_war_java_android_urban_adarshverma1236;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.star_war_java_android_urban_adarshverma1236.MainActivityComponents.maGetAllInnerClasses;
import com.example.star_war_java_android_urban_adarshverma1236.data.FavoriteDbHelper;

public class MainActivity extends maGetAllInnerClasses {
    private final AppCompatActivity activity = MainActivity.this;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialViews1();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initialViews1() {
        InnerClassOfinitialViews();
        favoriteDbHelper = new FavoriteDbHelper(activity);
        swipeContainer = findViewById(R.id.main_content);
        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initialViews1();
                Toast.makeText(MainActivity.this, "Page is Refreshed", Toast.LENGTH_SHORT).show();
            }
        });
        checkSortOrder();
    }

    // adding favorite
    private void initialViews2() {
        InnerClassOfinitialViews();
        favoriteDbHelper = new FavoriteDbHelper(activity);
        getAllFavorite();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onResume() {
        super.onResume();
        if (mCharacterList.isEmpty()) {
            checkSortOrder();
        } else {
            checkSortOrder();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        Log.d(LOG_TAG, "Preferences updated");
        checkSortOrder();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void checkSortOrder() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String sortOrder = preferences.getString(
                this.getString(R.string.pref_sort_order_key),
                this.getString(R.string.pref_people));
        if (sortOrder.equals(this.getString(R.string.pref_people))) {
            Log.d(LOG_TAG, "Sorting by people");
            loadJSON();
        }
        if (sortOrder.equals(this.getString(R.string.favorite))) {
            Log.d(LOG_TAG, "Sorting by favorite");
            initialViews2();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void LoadUp(View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String sortOrder = preferences.getString(
                this.getString(R.string.pref_sort_order_key),
                this.getString(R.string.pref_people));
        if (sortOrder.equals(this.getString(R.string.pref_people))) {
            LoadUpPageOfCharacter();
        }
        if (sortOrder.equals(this.getString(R.string.favorite))) {
            Toast.makeText(getApplicationContext(), "Scroll down ", Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void LoadDown(View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String sortOrder = preferences.getString(
                this.getString(R.string.pref_sort_order_key),
                this.getString(R.string.pref_people));
        if (sortOrder.equals(this.getString(R.string.pref_people))) {
            LoadDownPageOfCharacter();
        }
        if (sortOrder.equals(this.getString(R.string.favorite))) {
            Toast.makeText(getApplicationContext(), "Scroll Up", Toast.LENGTH_SHORT).show();
            initialViews2();
        }
    }
}