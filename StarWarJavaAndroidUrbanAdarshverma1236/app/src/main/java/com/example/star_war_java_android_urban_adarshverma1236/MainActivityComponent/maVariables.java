package com.example.star_war_java_android_urban_adarshverma1236.MainActivityComponent;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.star_war_java_android_urban_adarshverma1236.adapter.CharactersAdapter;
import com.example.star_war_java_android_urban_adarshverma1236.data.FavoriteDbHelper;
import com.example.star_war_java_android_urban_adarshverma1236.model.Character;

import java.util.List;

public abstract class maVariables extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener, SearchView.OnQueryTextListener {

    protected RecyclerView recyclerView;
    protected CharactersAdapter mCharactersAdapter;
    protected List<Character> mCharacterList;
    protected SwipeRefreshLayout swipeContainer;
    protected FavoriteDbHelper favoriteDbHelper;
    public static final String LOG_TAG = CharactersAdapter.class.getName();
    protected int FirstPageNumber = 1;
    protected int LoadMorePageNumber = 1;
    protected int LoadLessPageNumber = 1;
    ProgressDialog pd;

    public Activity getActivity() {
        return this;
    }
}
