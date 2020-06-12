package com.example.ginoamaury.ufpsradio.activity.ui;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ginoamaury.ufpsradio.R;
import com.example.ginoamaury.ufpsradio.activity.PodcastData;


public class PodcastAdapter extends RecyclerView.Adapter {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_podcast, parent, false);
        return new PodcastAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ((PodcastAdapter.ListViewHolder) viewHolder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return PodcastData.title.length;
    }
    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mItemText;
        private ImageView mItemImage;
        private ImageButton playBu;
        private ImageButton likeBu;
        private ImageButton comBu;

        public ListViewHolder(View itemView){
            super(itemView);
            mItemText = (TextView) itemView.findViewById(R.id.item_txt2);
            mItemImage = (ImageView) itemView.findViewById(R.id.item_img2);
            playBu = (ImageButton) itemView.findViewById(R.id.imageButton2);
            likeBu = (ImageButton) itemView.findViewById(R.id.imageButton4);
            comBu = (ImageButton) itemView.findViewById(R.id.imageButton5);

            itemView.setOnClickListener(this);
        }
        public void bindView (int position){
            mItemText.setText(PodcastData.title[position]);
            mItemImage.setImageResource(PodcastData.picture[position]);
            playBu.setImageResource(PodcastData.play[position]);
            likeBu.setImageResource(PodcastData.like[position]);
            comBu.setImageResource(PodcastData.comentario[position]);
        }

        public void onClick(View view){

        }
    }
}
