package com.example.ginoamaury.ufpsradio.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ginoamaury.ufpsradio.R;

import com.example.ginoamaury.ufpsradio.activity.ui.PodcastAdapter;

public class PodcastFragment extends Fragment {
    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private PodcastFragment podcast;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_podcast, container, false);
        RecyclerView recy = (RecyclerView) view.findViewById(R.id.listRecyclerPodcast);
        PodcastAdapter lista = new PodcastAdapter();
        recy.setAdapter(lista);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getActivity());
        recy.setLayoutManager(layout);

        return view;
    }


}
