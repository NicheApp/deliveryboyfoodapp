package com.mj.deliveryboyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.messaging.FirebaseMessaging;

public class Unavailable extends AppCompatActivity {
Button goonline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = this.getSharedPreferences("MyPref", MODE_PRIVATE);
        if (prefs.getString("available", "no").equals("yes")) {
            FirebaseMessaging.getInstance().subscribeToTopic("yes");
            startActivity(new Intent(this,MainActivity.class));
        }
        else{

        setContentView(R.layout.activity_unavailable);
            FirebaseMessaging.getInstance().subscribeToTopic("no");
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        goonline = findViewById(R.id.goonline);
        /*Pass it to startActivity() method as the second parameter*/
        goonline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("available", "yes");
                editor.commit();
                FirebaseMessaging.getInstance().subscribeToTopic("yes");
                startActivity(new Intent(getApplicationContext(), MainActivity.class), options.toBundle());
            }
        });
    }
    }
}