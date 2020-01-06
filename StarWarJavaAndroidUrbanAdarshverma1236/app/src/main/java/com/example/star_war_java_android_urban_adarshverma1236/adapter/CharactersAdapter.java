package com.example.star_war_java_android_urban_adarshverma1236.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.star_war_java_android_urban_adarshverma1236.DetailActivity;
import com.example.star_war_java_android_urban_adarshverma1236.R;
import com.example.star_war_java_android_urban_adarshverma1236.model.Character;

import java.util.ArrayList;
import java.util.List;

public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.MyViewHolder> {

    private Context mContext;
    private List<Character> mCharacterList;
    private String PictureURL1 = "https://images.fineartamerica.com/images/artworkimages/mediumlarge/2/stormtrooper-star-wars-storm-trooper-original-oil-painting-black-velvet-r073-ramirezramirez.jpg";

    public CharactersAdapter(Context mContext, List<Character> characterList) {
        this.mContext = mContext;
        this.mCharacterList = characterList;
    }

    @Override
    public CharactersAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.character_card, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CharactersAdapter.MyViewHolder viewHolder, int i) {
        viewHolder.name.setText(mCharacterList.get(i).getName());
        viewHolder.mass.setText(mCharacterList.get(i).getMass());

        String poster = PictureURL1;

        Glide.with(mContext)
                .load(poster)
                .placeholder(R.drawable.load)
                .into(viewHolder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return mCharacterList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, mass;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            mass = (TextView) view.findViewById(R.id.mass);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Character clickedDataItem = mCharacterList.get(pos);
                        Intent intent = new Intent(mContext, DetailActivity.class);
                        intent.putExtra("characters", clickedDataItem);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getName(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void setFilter(ArrayList<Character> newList) {
        mCharacterList = new ArrayList<>();
        mCharacterList.addAll(newList);
        notifyDataSetChanged();
    }
}
