package com.example.star_war_java_android_urban_adarshverma1236.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
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
    private List<Character> characterList;

    public CharactersAdapter(Context mContext, List<Character> characterList) {
        this.mContext = mContext;
        this.characterList = characterList;
    }

    @Override
    public CharactersAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.people_card, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CharactersAdapter.MyViewHolder viewHolder, int i) {
        viewHolder.title.setText(characterList.get(i).getName());
        viewHolder.userrating.setText(characterList.get(i).getMass());

        // adding past 3
        String poster =
                "https://images.fineartamerica.com/images/artworkimages/mediumlarge/2/stormtrooper-star-wars-storm-trooper-original-oil-painting-black-velvet-r073-ramirezramirez.jpg";
        //+ movieList.get(i).getPosterPath();
        // adding past 3
        Glide.with(mContext)
                // .load(movieList.get(i).getPosterPath())
                .load(poster)
                .placeholder(R.drawable.load)
                .into(viewHolder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, userrating;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            userrating = (TextView) view.findViewById(R.id.userrating);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            /*-------------------------------------------------output*/
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Character clickedDataItem = characterList.get(pos);
                        Intent intent = new Intent(mContext, DetailActivity.class);
                       /* intent.putExtra("original_title", movieList.get(pos).getOriginalTitle());
                        intent.putExtra("poster_path", movieList.get(pos).getPosterPath());
                        intent.putExtra("overview", movieList.get(pos).getOverview());
                        intent.putExtra("vote_average", Double.toString(movieList.get(pos).getVoteAverage()));
                        intent.putExtra("id", movieList.get(pos).getId());
                        intent.putExtra("release_date", movieList.get(pos).getReleaseDate());*/
                        intent.putExtra("movies", clickedDataItem);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getName(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }




    public void setFilter(ArrayList<Movie> newList){
        characterList = new ArrayList<>();
       // characterList.addAll(newList);
        notifyDataSetChanged();

    }


}

