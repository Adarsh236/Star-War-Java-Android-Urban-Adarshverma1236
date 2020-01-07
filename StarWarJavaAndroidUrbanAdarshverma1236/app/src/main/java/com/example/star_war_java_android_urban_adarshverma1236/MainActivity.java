package com.example.star_war_java_android_urban_adarshverma1236;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.star_war_java_android_urban_adarshverma1236.adapter.CharactersAdapter;
import com.example.star_war_java_android_urban_adarshverma1236.api.Client;
import com.example.star_war_java_android_urban_adarshverma1236.api.Service;
import com.example.star_war_java_android_urban_adarshverma1236.data.FavoriteDbHelper;
import com.example.star_war_java_android_urban_adarshverma1236.model.Character;
import com.example.star_war_java_android_urban_adarshverma1236.model.CharacterComparator;
import com.example.star_war_java_android_urban_adarshverma1236.model.CharactersResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener, SearchView.OnQueryTextListener {

    private RecyclerView recyclerView;
    private CharactersAdapter mCharactersAdapter;
    private List<Character> mCharacterList;
    private SwipeRefreshLayout swipeContainer;
    private FavoriteDbHelper favoriteDbHelper;
    private AppCompatActivity activity = MainActivity.this;
    public static final String LOG_TAG = CharactersAdapter.class.getName();
    private int FirstPageNumber = 1;
    private int LoadMorePageNumber = 1;
    private int LoadLessPageNumber = 1;
    ProgressDialog pd;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    public Activity getActivity() {
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initViews() {
        recyclerView = findViewById(R.id.recycler_view);
        mCharacterList = new ArrayList<>();
        mCharactersAdapter = new CharactersAdapter(this, mCharacterList);

        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mCharactersAdapter);
        mCharactersAdapter.notifyDataSetChanged();

        favoriteDbHelper = new FavoriteDbHelper(activity);
        swipeContainer = findViewById(R.id.main_content);
        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initViews();
                Toast.makeText(MainActivity.this, "Page is Refreshed", Toast.LENGTH_SHORT).show();
            }
        });
        checkSortOrder();
    }

    // adding favorite
    private void initViews2() {
        recyclerView = findViewById(R.id.recycler_view);
        mCharacterList = new ArrayList<>();
        mCharactersAdapter = new CharactersAdapter(this, mCharacterList);

        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mCharactersAdapter);
        mCharactersAdapter.notifyDataSetChanged();
        favoriteDbHelper = new FavoriteDbHelper(activity);
        getAllFavorite();
    }

    // calling api
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void loadJSON() {

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
                    Toast.makeText(MainActivity.this, "Error Fetching Data! OR Internet Problem", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Log.d("Error", Objects.requireNonNull(e.getMessage()));
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem search = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) search.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                Intent intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_search:
                return true;
            default:
                return super.onOptionsItemSelected(item);
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
                this.getString(R.string.pref_people)
        );
        if (sortOrder.equals(this.getString(R.string.pref_people))) {
            Log.d(LOG_TAG, "Sorting by people");
            loadJSON();

        }
        if (sortOrder.equals(this.getString(R.string.favorite))) {
            Log.d(LOG_TAG, "Sorting by favorite");
            initViews2();
        }
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

    @SuppressLint("StaticFieldLeak")
    private void getAllFavorite() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                mCharacterList.clear();
                mCharacterList.addAll(favoriteDbHelper.getAllFavorite());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mCharactersAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void loadMore(View view) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String sortOrder = preferences.getString(
                this.getString(R.string.pref_sort_order_key),
                this.getString(R.string.pref_people)
        );
        if (sortOrder.equals(this.getString(R.string.pref_people))) {
            LoadMorePageNumber += 1;
            LoadLessPageNumber = LoadMorePageNumber;
            int LastPageNumber = 9;
            if (LoadMorePageNumber <= LastPageNumber) {
                try {
                    Client Client = new Client();
                    final Service apiService =
                            Client.getClient().create(Service.class);
                    Call<CharactersResponse> call = apiService.getPeople(LoadMorePageNumber);
                    call.enqueue(new Callback<CharactersResponse>() {
                        @Override
                        public void onResponse(Call<CharactersResponse> call, Response<CharactersResponse> response) {
                            List<Character> characters = response.body().getResults();
                            Collections.sort(characters, CharacterComparator.BY_NAME_ALPHABETICAL);
                            recyclerView.setAdapter(new CharactersAdapter(getApplicationContext(), characters));
                            recyclerView.smoothScrollToPosition(0);
                            if (swipeContainer.isRefreshing()) {
                                swipeContainer.setRefreshing(false);
                            }
                            mCharacterList = characters;
                            Toast.makeText(getApplicationContext(), "Characters: " + "Current Page: " + LoadMorePageNumber, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<CharactersResponse> call, Throwable t) {

                            Log.d("Error", Objects.requireNonNull(t.getMessage()));
                            Toast.makeText(MainActivity.this, "Error Fetching Data! OR Internet Problem", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    Log.d("Error", Objects.requireNonNull(e.getMessage()));
                    Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            } else {
                LoadMorePageNumber = LastPageNumber;
                LoadLessPageNumber = LastPageNumber;
                Toast.makeText(getApplicationContext(), "Characters: " + "this is the last page: " + LoadMorePageNumber, Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (sortOrder.equals(this.getString(R.string.favorite))) {
            Toast.makeText(getApplicationContext(), "Scroll down ", Toast.LENGTH_SHORT).show();
            initViews2();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void loadless(View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String sortOrder = preferences.getString(
                this.getString(R.string.pref_sort_order_key),
                this.getString(R.string.pref_people)
        );
        if (sortOrder.equals(this.getString(R.string.pref_people))) {
            LoadLessPageNumber -= 1;
            LoadMorePageNumber -= 1;
            if (LoadLessPageNumber >= FirstPageNumber) {
                try {
                    Client Client = new Client();
                    Service apiService =
                            Client.getClient().create(Service.class);
                    Call<CharactersResponse> call = apiService.getPeople(LoadLessPageNumber);
                    call.enqueue(new Callback<CharactersResponse>() {
                        @Override
                        public void onResponse(Call<CharactersResponse> call, Response<CharactersResponse> response) {
                            List<Character> characters = response.body().getResults();
                            Collections.sort(characters, CharacterComparator.BY_NAME_ALPHABETICAL);
                            recyclerView.setAdapter(new CharactersAdapter(getApplicationContext(), characters));
                            recyclerView.smoothScrollToPosition(0);
                            if (swipeContainer.isRefreshing()) {
                                swipeContainer.setRefreshing(false);
                            }
                            mCharacterList = characters;
                            Toast.makeText(getApplicationContext(), "Character: " + "Current Page: " + LoadMorePageNumber + call, Toast.LENGTH_SHORT).show();
                        }

                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        @Override
                        public void onFailure(Call<CharactersResponse> call, Throwable t) {

                            Log.d("Error", Objects.requireNonNull(t.getMessage()));
                            Toast.makeText(MainActivity.this, "Error Fetching Data! OR Internet Problem", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    Log.d("Error", Objects.requireNonNull(e.getMessage()));
                    Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            } else {
                LoadLessPageNumber = 1;
                LoadMorePageNumber = 1;
                Toast.makeText(getApplicationContext(), "Character: " + " this is the last page: " + LoadMorePageNumber, Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (sortOrder.equals(this.getString(R.string.favorite))) {
            Toast.makeText(getApplicationContext(), "Scroll Up", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.d(LOG_TAG, "NEWTEXT: " + newText);
        newText = newText.toLowerCase();
        ArrayList<Character> newList = new ArrayList<>();
        int i = 0;
        Log.d(LOG_TAG, "SIZE: " + mCharacterList.size());
        for (Character item : mCharacterList) {
            String name = item.getName().toLowerCase();
            Log.d(LOG_TAG, "NAME: " + name);
            if (name.startsWith(newText)) {
                newList.add(item);
                Log.d(LOG_TAG, "Sorting by favorite" + newList.get(i).getName());
                i++;
            }
        }
        Log.d(LOG_TAG, "ITEM sz: " + newList.size());
        Log.d(LOG_TAG, "ITEM sz: " + newList);
        // Toast.makeText(getApplicationContext(), newText + "show : " + newList, Toast.LENGTH_SHORT).show();
        //newList.forEach(T -> System.out.print(T + " ") );
        mCharactersAdapter.setFilter(newList);
        recyclerView.setAdapter(mCharactersAdapter);
        recyclerView.smoothScrollToPosition(0);
        Log.d(LOG_TAG, "Changed " + mCharactersAdapter);
        return true;
    }
}
