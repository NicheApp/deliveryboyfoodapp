package com.mj.deliveryboyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.developer.kalert.KAlertDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Orderfinaldetails extends AppCompatActivity {
TextView order,ammount,items,payment,dltime,username,add,num;
Button callcustomer,getdirections;
Button rescall,resdirections,pickedup,usercall,userdirections,delivered;
TextView resname,resadd,resnumber,useradd,usernum;
String nameuser,adduser,numuser,ammnt;
CardView orderdetailsres,orderdetailsuser,contactdetailsres,contactdetailuser;
    String lat,lon;
    String resloc ="Thane";
    String resnum,restadd;
    private RequestQueue mRequestQue;
    private String URL = "https://fcm.googleapis.com/fcm/send";

    Geocoder geocoder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resdetails);
        order=findViewById(R.id.orderid);
        items=findViewById(R.id.textView2);
        payment=findViewById(R.id.payment);
        dltime=findViewById(R.id.time);

        //restaurant details
        num=findViewById(R.id.resnumber);
        rescall=findViewById(R.id.rescall);
        resdirections=findViewById(R.id.resdirections);
        resname=findViewById(R.id.resname);
        resadd=findViewById(R.id.resadd);
        resnumber=findViewById(R.id.resnumber);
        pickedup=findViewById(R.id.orderpickedup);
        orderdetailsres=findViewById(R.id.orderdetailscardview);
        contactdetailsres=findViewById(R.id.contactdetailscardview);

        //user details
            username=findViewById(R.id.username);
            useradd=findViewById(R.id.useraddress);
            usernum=findViewById(R.id.usernumber);
            usercall=findViewById(R.id.usercall);
            userdirections=findViewById(R.id.userdirections);
            ammount=findViewById(R.id.price);
            delivered=findViewById(R.id.delivered);
            orderdetailsuser=findViewById(R.id.orderdetailscardviewuser);
            contactdetailuser=findViewById(R.id.contactdetailscardviewuser);



         Intent intent=getIntent();
        //resdetails
        restadd=intent.getStringExtra("resadd");
         resadd.setText(restadd);
         resnumber.setText(intent.getStringExtra("resnum"));
         resname.setText(intent.getStringExtra("resname"));
         order.setText("Order "+intent.getStringExtra("orderid"));

         //user details

        resnum=intent.getStringExtra("resnum");
        nameuser=intent.getStringExtra("name");
        adduser=intent.getStringExtra("add");
        numuser=intent.getStringExtra("mobile");
         lat=intent.getStringExtra("lat");
         lon=intent.getStringExtra("lon");
         ammnt=intent.getStringExtra("ammount");

        username.setText(nameuser);
        useradd.setText(adduser);
        usernum.setText(numuser);
        ammount.setText(ammnt);


        callcustomer=findViewById(R.id.rescall);
        getdirections=findViewById(R.id.resdirections);
        mRequestQue = Volley.newRequestQueue(this);
     rescall.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + resnum));
             startActivity(intent);
         }
     });
     pickedup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          sendNotification();
          orderdetailsres.setVisibility(View.INVISIBLE);
          contactdetailsres.setVisibility(View.INVISIBLE);
          pickedup.setVisibility(View.INVISIBLE);
          orderdetailsuser.setVisibility(View.VISIBLE);
          contactdetailuser.setVisibility(View.VISIBLE);
          delivered.setVisibility(View.VISIBLE);


            }
        });
         callcustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+resnum));//change the number
                startActivity(callIntent);
            }
        });
getdirections.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Uri gmmIntentUri = Uri.parse("google.navigation:q="+restadd);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        mapIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(mapIntent);
    }
});

delivered.setOnClickListener(new View.OnClickListener() {
    @SuppressLint("ResourceType")
    @Override
    public void onClick(View v) {

    }
});
userdirections.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        Uri gmmIntentUri = Uri.parse("google.navigation:q="+lat+","+lon);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        mapIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(mapIntent);
    }
});
usercall.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+numuser));//change the number
        startActivity(callIntent);
    }
});

    }
    private void sendNotification() {

        JSONObject json = new JSONObject();
        try {
            json.put("to","/topics/"+"yes");
            JSONObject notificationObj = new JSONObject();
            notificationObj.put("title"," Yipee!! Your have been Picked Up");
            notificationObj.put("body","Arriving soon");
            notificationObj.put("sound","tmobile_wav");

            JSONObject extraData = new JSONObject();
            extraData.put("brandId","puma");
            extraData.put("category","Shoes");

            json.put("notification",notificationObj);
            json.put("data",extraData);


            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL,
                    json,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Log.d("MUR-------------------", "onResponse: "+response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("MUR", "onError:------ "+error.networkResponse);
                }
            }
            ){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> header = new HashMap<>();
                    header.put("content-type","application/json");
                    header.put("authorization","key=AAAA5pOKzgA:APA91bFKK4m7gTjCakJ1a1d8gtnsZQ5zWUIaJHKPCD--1x4CEHYbcz_OgHQEQsPc3V5Q7dq_2PQI9TZ5OVytG4g_3piceapBrS6ZHyCwWhF9uriqp7ayXj7zyMXM7ndiuYB7d9Kx_0zp");
                    return header;
                }
            };
            mRequestQue.add(request);
        }
        catch (JSONException e)

        {
            Toast.makeText(this,"89898989"+e.toString(),Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}