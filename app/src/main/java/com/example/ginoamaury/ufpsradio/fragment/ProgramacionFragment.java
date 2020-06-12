package com.example.ginoamaury.ufpsradio.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.ginoamaury.ufpsradio.R;
import com.example.ginoamaury.ufpsradio.activity.ui.ListAdapter;

import java.util.List;

/**
 * author carlos22ivan
 * author gino
 */
public class ProgramacionFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_programacion, container, false);
        RecyclerView recy = (RecyclerView) view.findViewById(R.id.listRecycler);
        ListAdapter lista = new ListAdapter();
        recy.setAdapter(lista);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getActivity());
        recy.setLayoutManager(layout);

        return view;
    }
}
