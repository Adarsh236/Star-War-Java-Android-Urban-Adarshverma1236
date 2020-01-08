package com.example.star_war_java_android_urban_adarshverma1236.MainActivityComponents;

import android.content.Intent;
import android.view.MenuItem;

import androidx.appcompat.widget.SearchView;

import com.example.star_war_java_android_urban_adarshverma1236.R;
import com.example.star_war_java_android_urban_adarshverma1236.SettingActivity;

public abstract class maMenu extends maLoadJson {
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
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
}
