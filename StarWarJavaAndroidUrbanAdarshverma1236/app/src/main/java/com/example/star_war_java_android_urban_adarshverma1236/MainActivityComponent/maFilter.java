package com.example.star_war_java_android_urban_adarshverma1236.MainActivityComponent;

import android.util.Log;

import com.example.star_war_java_android_urban_adarshverma1236.model.Character;

import java.util.ArrayList;

public abstract class maFilter extends maGetAllFavorite {

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
