package com.mj.deliveryboyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
BottomNavigationView bottomNavigationView;
FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
bottomNavigationView = findViewById(R.id.bottomNavigationView);
frameLayout = findViewById(R.id.flFragment);
bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
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