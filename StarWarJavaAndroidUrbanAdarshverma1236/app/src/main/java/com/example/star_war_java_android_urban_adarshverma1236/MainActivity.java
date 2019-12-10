package com.example.star_war_java_android_urban_adarshverma1236;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.star_war_java_android_urban_adarshverma1236.adapter.CharactersAdapter;
import com.example.star_war_java_android_urban_adarshverma1236.api.Client;
import com.example.star_war_java_android_urban_adarshverma1236.api.Service;
import com.example.star_war_java_android_urban_adarshverma1236.data.FavoriteDbHelper;
import com.example.star_war_java_android_urban_adarshverma1236.model.Character;
import com.example.star_war_java_android_urban_adarshverma1236.model.CharactersResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.core.view.MenuItemCompat.getActionView;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener, SearchView.OnQueryTextListener {

    private RecyclerView recyclerView;
    private CharactersAdapter mCharactersAdapter;
    private List<Character> mCharacterList;
    ProgressDialog pd;
    private SwipeRefreshLayout swipeContainer;
    private FavoriteDbHelper favoriteDbHelper;
    private AppCompatActivity activity = MainActivity.this;////p3
    public static final String LOG_TAG = CharactersAdapter.class.getName();
    private int FirstPageNumber = 1;
    private int LastpageNumber = 9;
    private int LoadMorePageNumber = 1;
    private int LoadLessPageNumber = 1;


    //9s
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    //9f
//10s
    public Activity getActivity() {
        Context context = this;
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;

    }

    //10f
//11s
    private void initViews() {

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

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
        // p3
        favoriteDbHelper = new FavoriteDbHelper(activity);//p3
        //adding
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.main_content);
        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initViews();
                Toast.makeText(MainActivity.this, "Characters Refreshed", Toast.LENGTH_SHORT).show();
            }
        });
        //loadJSON();
        checkSortOrder();
    }

    //11f
//13s
    // adding p3 favorite
    private void initViews2() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

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

    //13f
//2s
    // finish
    private void loadJSON() {/*--------------------------change*/

        try {
            if (BuildConfig.THE_STAR_WAR_API_TOKEN.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please obtain API Key Firstly", Toast.LENGTH_SHORT).show();
                pd.dismiss();
                return;
            }

            Client Client = new Client();
            Service apiService =
                    Client.getClient().create(Service.class);
            Call<CharactersResponse> call = apiService.getPeople(FirstPageNumber);
            call.enqueue(new Callback<CharactersResponse>() {
                @Override
                public void onResponse(Call<CharactersResponse> call, Response<CharactersResponse> response) {
                    List<Character> characters = response.body().getResults();
                    Collections.sort(characters, Character.BY_NAME_ALPHABETICAL);//**********************
                    recyclerView.setAdapter(new CharactersAdapter(getApplicationContext(), characters));
                    recyclerView.smoothScrollToPosition(0);
                    if (swipeContainer.isRefreshing()) {
                        swipeContainer.setRefreshing(false);
                    }
                    LoadMorePageNumber = 1;
                    LoadLessPageNumber = 1;
                    mCharacterList = characters;

                }

                @Override
                public void onFailure(Call<CharactersResponse> call, Throwable t) {

                    Log.d("Error", t.getMessage());
                    Toast.makeText(MainActivity.this, "Error Fetching Data! OR Internet Problem", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    //2f
//3f
    private void loadJSON1() {//--------------------------change2

        try {
            if (BuildConfig.THE_STAR_WAR_API_TOKEN.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please obtain API Key Firstly", Toast.LENGTH_SHORT).show();
                pd.dismiss();
                return;
            }

            Client Client = new Client();
            Service apiService =
                    Client.getClient().create(Service.class);//change another
            Call<CharactersResponse> call = apiService.getSpecies(FirstPageNumber);
            call.enqueue(new Callback<CharactersResponse>() {
                @Override
                public void onResponse(Call<CharactersResponse> call, Response<CharactersResponse> response) {
                    List<Character> characters = response.body().getResults();
                    recyclerView.setAdapter(new CharactersAdapter(getApplicationContext(), characters));
                    recyclerView.smoothScrollToPosition(0);
                    if (swipeContainer.isRefreshing()) {
                        swipeContainer.setRefreshing(false);
                    }

                    // pd.dismiss();
                }


                @Override
                public void onFailure(Call<CharactersResponse> call, Throwable t) {

                    Log.d("Error", t.getMessage());
                    Toast.makeText(MainActivity.this, "Error Fetching Data! OR Internet Problem", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    //3f
//4s
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem search = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) getActionView(search);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    //4f
//5s
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                /*-----------------------c6 settings --> setting*/
                Intent intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                /*-----------------------c6*/
                return true;
            case R.id.action_search:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //5f
//6s
    /*----------------------------change3*/
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        Log.d(LOG_TAG, "Preferences updated");
        checkSortOrder();
    }

    /*----------------------------change4*/
//6f
//7s
    public void checkSortOrder() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String sortOrder = preferences.getString(
                this.getString(R.string.pref_sort_order_key),
                this.getString(R.string.pref_people)
        );
        if (sortOrder.equals(this.getString(R.string.pref_people))) {
            Log.d(LOG_TAG, "Sorting by people");
            loadJSON();


        } else if (sortOrder.equals(this.getString(R.string.favorite))) {   //p3
            Log.d(LOG_TAG, "Sorting by favorite");
            initViews2();
        } else {
            Log.d(LOG_TAG, "Sorting by species");
            loadJSON1();
        }
    }

    //7f
//8s
    /*----------------------------change5*/
    @Override
    public void onResume() {
        super.onResume();
        if (mCharacterList.isEmpty()) {
            checkSortOrder();
        } else {

            checkSortOrder();
        }
    }

    //8sf
//12s
    // adding p3
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


    public void loadMore(View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String sortOrder = preferences.getString(
                this.getString(R.string.pref_sort_order_key),
                this.getString(R.string.pref_people)
        );
        if (sortOrder.equals(this.getString(R.string.pref_people))) {
            LoadMorePageNumber += 1;
            LoadLessPageNumber = LoadMorePageNumber;
            if (LoadMorePageNumber <= LastpageNumber) {
                try {
                    if (BuildConfig.THE_STAR_WAR_API_TOKEN.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Please obtain API Key Firstly", Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                        return;
                    }

                    Client Client = new Client();
                    final Service apiService =
                            Client.getClient().create(Service.class);
                    Call<CharactersResponse> call = apiService.getPeople(LoadMorePageNumber);
                    call.enqueue(new Callback<CharactersResponse>() {
                        @Override
                        public void onResponse(Call<CharactersResponse> call, Response<CharactersResponse> response) {
                            List<Character> characters = response.body().getResults();
                            Collections.sort(characters, Character.BY_NAME_ALPHABETICAL);
                            recyclerView.setAdapter(new CharactersAdapter(getApplicationContext(), characters));
                            recyclerView.smoothScrollToPosition(0);
                            if (swipeContainer.isRefreshing()) {
                                swipeContainer.setRefreshing(false);
                            }
                            Toast.makeText(getApplicationContext(), "Current Page: " + LoadMorePageNumber , Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<CharactersResponse> call, Throwable t) {

                            Log.d("Error", t.getMessage());
                            Toast.makeText(MainActivity.this, "Error Fetching Data! OR Internet Problem", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    Log.d("Error", e.getMessage());
                    Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "this is the last page ${LastPageNumber}", Toast.LENGTH_SHORT).show();
                return;
            }

        } else if (sortOrder.equals(this.getString(R.string.favorite))) {   //p3
            Log.d(LOG_TAG, "Scroll down");
            initViews2();
        } else {
            Log.d(LOG_TAG, "Sorting by species");
            LoadMorePageNumber += 1;
            LoadLessPageNumber = LoadMorePageNumber;
            if (LoadMorePageNumber <= LastpageNumber) {
                try {
                    if (BuildConfig.THE_STAR_WAR_API_TOKEN.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Please obtain API Key Firstly", Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                        return;
                    }

                    Client Client = new Client();
                    Service apiService =
                            Client.getClient().create(Service.class);//change another
                    Call<CharactersResponse> call = apiService.getSpecies(LoadMorePageNumber);
                    call.enqueue(new Callback<CharactersResponse>() {
                        @Override
                        public void onResponse(Call<CharactersResponse> call, Response<CharactersResponse> response) {
                            List<Character> characters = response.body().getResults();
                            recyclerView.setAdapter(new CharactersAdapter(getApplicationContext(), characters));
                            recyclerView.smoothScrollToPosition(0);
                            if (swipeContainer.isRefreshing()) {
                                swipeContainer.setRefreshing(false);
                            }

                            // pd.dismiss();
                        }


                        @Override
                        public void onFailure(Call<CharactersResponse> call, Throwable t) {

                            Log.d("Error", t.getMessage());
                            Toast.makeText(MainActivity.this, "Error Fetching Data! OR Internet Problem", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    Log.d("Error", e.getMessage());
                    Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void loadless(View view) {

        LoadLessPageNumber -= 1;
        LoadMorePageNumber -= 1;

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String sortOrder = preferences.getString(
                this.getString(R.string.pref_sort_order_key),
                this.getString(R.string.pref_people)
        );
        if (sortOrder.equals(this.getString(R.string.pref_people))) {
            if (LoadLessPageNumber >= FirstPageNumber) {
                try {
                    if (BuildConfig.THE_STAR_WAR_API_TOKEN.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Please obtain API Key Firstly", Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                        return;
                    }

                    Client Client = new Client();
                    Service apiService =
                            Client.getClient().create(Service.class);
                    Call<CharactersResponse> call = apiService.getPeople(LoadLessPageNumber);
                    call.enqueue(new Callback<CharactersResponse>() {
                        @Override
                        public void onResponse(Call<CharactersResponse> call, Response<CharactersResponse> response) {
                            List<Character> characters = response.body().getResults();
                            Collections.sort(characters, Character.BY_NAME_ALPHABETICAL);
                            recyclerView.setAdapter(new CharactersAdapter(getApplicationContext(), characters));
                            recyclerView.smoothScrollToPosition(0);
                            if (swipeContainer.isRefreshing()) {
                                swipeContainer.setRefreshing(false);
                            }
                            Toast.makeText(getApplicationContext(), "Current Page: " + LoadMorePageNumber + call, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<CharactersResponse> call, Throwable t) {

                            Log.d("Error", t.getMessage());
                            Toast.makeText(MainActivity.this, "Error Fetching Data! OR Internet Problem", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    Log.d("Error", e.getMessage());
                    Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "this is the last page ${LastPageNumber}", Toast.LENGTH_SHORT).show();
                return;
            }
        } else if (sortOrder.equals(this.getString(R.string.favorite))) {   //p3
            Log.d(LOG_TAG, "Sorting by favorite");
            initViews2();
        } else {
            Log.d(LOG_TAG, "Sorting by species");
            loadJSON1();
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

        return true;
    }


}

//12f
