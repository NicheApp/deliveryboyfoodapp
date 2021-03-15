package com.mj.deliveryboyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.mj.deliveryboyapp.Homefragment.noorders;

public class MainActivity extends AppCompatActivity  {
    private static  final int REQUEST_LOCATION=1;
    private RequestQueue mRequestQue;
    private String URL = "https://fcm.googleapis.com/fcm/send";
    LocationManager locationManager;
    public String latitude,longitude;
    public  static List<orderdetailsmodelclass> orders=new ArrayList<>();
    Switch aSwitch;
    TextView available,date;
    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_main);
        //noorders=findViewById(R.i.noorders);

        Transition explodeAnimation = TransitionInflater.from(this).inflateTransition(R.transition.slide);
        explodeAnimation.setDuration(1000);
        getWindow().setEnterTransition(explodeAnimation);

         bottomNavigationView = findViewById(R.id.bottomNavigationView);
         frameLayout = findViewById(R.id.flFragment);
         date=findViewById(R.id.date);
         available=findViewById(R.id.available);
         aSwitch=findViewById(R.id.switch1);


        Calendar cal = Calendar.getInstance();
        SimpleDateFormat simpleformat = new SimpleDateFormat("dd/MM/yyyy");
         date.setText(simpleformat.format(cal.getTime())
         );
        ActivityCompat.requestPermissions(this,new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //Check gps is enable or not
        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            //Write Function To enable gps

            OnGPS();

        }
        else
        {
            //GPS is already On then

            getLocation();
        }

        if (getIntent().hasExtra("category")){
            Intent intent = new Intent(MainActivity.this,ReceiveNotificationActivity.class);
            intent.putExtra("category",getIntent().getStringExtra("category"));
            intent.putExtra("brandId",getIntent().getStringExtra("brandId"));
            startActivity(intent);
        }

        mRequestQue = Volley.newRequestQueue(this);
            SharedPreferences prefs=this.getSharedPreferences("MyPref",MODE_PRIVATE);
            if(prefs.getString("available","no").equals("no"))
            {FirebaseMessaging.getInstance().subscribeToTopic("no");}
            else  if(prefs.getString("available","no").equals("yes"))
                FirebaseMessaging.getInstance().subscribeToTopic("yes");

            aSwitch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(aSwitch.isChecked()==false) {
                        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("available", "no");
                        editor.commit();
                        startActivity(new Intent(MainActivity.this,Unavailable.class));
                    }
                }
            });

            if(savedInstanceState==null)
            {
                if(orders.isEmpty())
                {   // noorders.setVisibility(View.VISIBLE);
                    orderdetailsbackground orderdetailsbackground=new orderdetailsbackground(this);
                    orderdetailsbackground.execute();
                    setFragement(new Homefragment());
                }else {
                    setFragement(new Homefragment());
                }
            }
bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                if(orders.isEmpty())
                {   // noorders.setVisibility(View.VISIBLE);
                    orderdetailsbackground orderdetailsbackground=new orderdetailsbackground(getApplication());
                    orderdetailsbackground.execute();

                }
              else{
                setFragement(new Homefragment());}
                return true;
            case R.id.history:
                setFragement(new Historyfragment());
                return true;
            case R.id.account:
                setFragement(new Accountfragment());
                return true;
            default:
                return false;
        }

    }
});



    }

    private void setFragement(Fragment fragement) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flFragment,fragement);
        fragmentTransaction.commit();
    }

    private void getLocation() {

        //Check Permissions again

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this,

                Manifest.permission.ACCESS_COARSE_LOCATION) !=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
        else
        {
            Location LocationGps= locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location LocationNetwork=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location LocationPassive=locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (LocationGps !=null)
            {
                double lat=LocationGps.getLatitude();
                double longi=LocationGps.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("latitude", latitude);
                editor.putString("longitude", longitude);
                editor.commit();

            }
            else if (LocationNetwork !=null)
            {
                double lat=LocationNetwork.getLatitude();
                double longi=LocationNetwork.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("latitude", latitude);
                editor.putString("longitude", longitude);
                editor.commit();

            }
            else if (LocationPassive !=null)
            {
                double lat=LocationPassive.getLatitude();
                double longi=LocationPassive.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("latitude", latitude);
                editor.putString("longitude", longitude);
                editor.commit();

            }
            else
            {
                Toast.makeText(this, "Can't Get Your Location", Toast.LENGTH_SHORT).show();
            }

            //Thats All Run Your App
        }

    }
    private void OnGPS() {

        final AlertDialog.Builder builder= new AlertDialog.Builder(this);

        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                getLocation();
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        final AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }


}