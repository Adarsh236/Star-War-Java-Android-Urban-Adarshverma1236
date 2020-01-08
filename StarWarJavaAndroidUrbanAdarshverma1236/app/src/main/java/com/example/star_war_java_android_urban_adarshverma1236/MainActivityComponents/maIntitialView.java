package com.example.star_war_java_android_urban_adarshverma1236.MainActivityComponents;

import android.content.res.Configuration;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.star_war_java_android_urban_adarshverma1236.R;
import com.example.star_war_java_android_urban_adarshverma1236.adapter.CharactersAdapter;

import java.util.ArrayList;

public abstract class maIntitialView extends maFilter {

    protected void InnerClassOfinitialViews1() {
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
    }

    protected void InnerClassOfinitialViews2() {
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
    }
}
