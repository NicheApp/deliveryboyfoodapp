package com.mj.deliveryboyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    public Context mContext;
    Myhomedata[] myhomedata;
    Homefragment context;

    public HomeAdapter(Context context, Myhomedata[] myhomedata) {
        this.myhomedata = myhomedata;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.noticard,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Myhomedata myhomeDataList = myhomedata[position];
        holder.restadd.setText(myhomeDataList.getRestadd());
        holder.custadd.setText(myhomeDataList.getCustadd());
        holder.pickuptime.setText(myhomeDataList.getPickuptime());
        holder.delivtime.setText(myhomeDataList.getDelivtime());

    }

    @Override
    public int getItemCount() {
        return myhomedata.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView restaddimg;
        ImageView custaddimg;
        ImageView pickuptimeimage;
        ImageView delivtimeimage;
        ImageView line;
        TextView restaddtxt;
        TextView custaddtxt;
        TextView restadd;
        TextView custadd;
        TextView pickuptime;
        TextView delivtime;
        TextView delivtimetxt;
        TextView pickuptimetxt;
        TextView appx;
        Button accept;
        Button reject;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            restaddimg = itemView.findViewById(R.id.restaddimg);
            custaddimg = itemView.findViewById(R.id.custaddimg);
            pickuptimeimage = itemView.findViewById(R.id.pickupimage);
            delivtimeimage = itemView.findViewById(R.id.delivimage);
            line = itemView.findViewById(R.id.line);
            restaddtxt = itemView.findViewById(R.id.restaddtxt);
            custaddtxt = itemView.findViewById(R.id.custaddtxt);
            custadd = itemView.findViewById(R.id.datacustadd);
            restadd = itemView.findViewById(R.id.datarestadd);
            pickuptimetxt = itemView.findViewById(R.id.pickuptimetxt);
            delivtimetxt = itemView.findViewById(R.id.delivtimetxt);
            pickuptime = itemView.findViewById(R.id.pickuptime);
            delivtime = itemView.findViewById(R.id.delivtime);
            accept = itemView.findViewById(R.id.accept);
            reject = itemView.findViewById(R.id.reject);
        }
    }

}