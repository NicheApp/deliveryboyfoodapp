package com.mj.deliveryboyapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Firstpage  extends AppCompatActivity {
    public TextView youare,go,make,ser;
    public Button goonline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstpage);
        youare = findViewById(R.id.ur);
        go = findViewById(R.id.go);
        make = findViewById(R.id.make);
        ser = findViewById(R.id.ser);
        goonline = findViewById(R.id.goonline);
        goonline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fp=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(fp);
            }
        });
    }
}
