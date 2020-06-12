package com.example.ginoamaury.ufpsradio.activity.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ginoamaury.ufpsradio.R;
import com.example.ginoamaury.ufpsradio.activity.ProgramacionData;

public class ListAdapter extends RecyclerView.Adapter {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_programacion, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ((ListViewHolder) viewHolder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return ProgramacionData.title.length;
    }
    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mItemText;
        private TextView nItemText;
        private ImageView mItemImage;

        public ListViewHolder(View itemView){
            super(itemView);
            nItemText = (TextView) itemView.findViewById(R.id.item_txt);
            mItemText = (TextView) itemView.findViewById(R.id.hora_txt);
            mItemImage = (ImageView) itemView.findViewById(R.id.item_img);
            itemView.setOnClickListener(this);
        }
        public void bindView (int position){
            nItemText.setText(ProgramacionData.title[position]);
            mItemText.setText(ProgramacionData.hora[position]);
            mItemImage.setImageResource(ProgramacionData.picture[position]);
        }

        public void onClick(View view){

        }
    }
}
