package com.mj.deliveryboyapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Homefragment extends Fragment {
    public RecyclerView recyclerView;
    public HomeAdapter homeAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homerecyclerview,container,false);
        // Inflate the layout for this fragment
        recyclerView=view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Myhomedata[] myhomedata = new Myhomedata[]{
                new Myhomedata("Adarsh Super market, Bhakti nagar road, Adajan, Surat","A-58, Valam nagar Soc., Varachha, Surat","8:30","9:30"),
                new Myhomedata("Adarsh Super market, Bhakti nagar road, Adajan, Surat","A-58, Valam nagar Soc., Varachha, Surat","8:30","9:30")
        };

        homeAdapter=new HomeAdapter(getActivity(),myhomedata);
        recyclerView.setAdapter(homeAdapter);
        homeAdapter.notifyDataSetChanged();
        return view;
    }
}
