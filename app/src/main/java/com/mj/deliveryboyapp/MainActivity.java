package com.mj.deliveryboyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RequestQueue mRequestQue;
    private String URL = "https://fcm.googleapis.com/fcm/send";
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
        Transition explodeAnimation = TransitionInflater.from(this).inflateTransition(R.transition.slide);
        explodeAnimation.setDuration(1000);
        getWindow().setEnterTransition(explodeAnimation);


         bottomNavigationView = findViewById(R.id.bottomNavigationView);
         frameLayout = findViewById(R.id.flFragment);
         date=findViewById(R.id.date);
         available=findViewById(R.id.available);
         aSwitch=findViewById(R.id.switch1);
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
                orderdetailsbackground orderdetailsbackground=new orderdetailsbackground(this);
                orderdetailsbackground.execute();
                setFragement(new Homefragment());

            }
bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                orderdetailsbackground orderdetailsbackground=new orderdetailsbackground(getApplication() );
                orderdetailsbackground.execute();
                setFragement(new Homefragment());
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
}