package com.mj.deliveryboyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Unavailable extends AppCompatActivity {
Button goonline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unavailable);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        goonline=findViewById(R.id.goonline);
        /*Pass it to startActivity() method as the second parameter*/
        goonline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("available","no");
                editor.commit();
                startActivity(new Intent(getApplicationContext(),MainActivity.class), options.toBundle());
            }
        });

    }
}