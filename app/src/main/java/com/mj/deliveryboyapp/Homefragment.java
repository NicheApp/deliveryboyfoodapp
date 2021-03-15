package com.mj.deliveryboyapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import static com.mj.deliveryboyapp.MainActivity.orders;
public class Homefragment extends Fragment {
    public RecyclerView recyclerView;
    public HomeAdapter homeAdapter;
    public static TextView noorders;
    public  static ProgressBar homebar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homerecyclerview,container,false);

        recyclerView=view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
         noorders=view.findViewById(R.id.noorders);

        homeAdapter=new HomeAdapter(getContext(),orders);
        homeAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(homeAdapter);
        homeAdapter.notifyDataSetChanged();
//        homeAdapter.notify();



        recyclerView.setAdapter(homeAdapter);
        return view;
    }
}
