package com.example.studioquaiouest.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class achieveD3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achieve_d3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setUnlock(view);
    }

    View view;

    public void setUnlock(View view){

        if (achieveprefs.isUnlocked[1][7]){
            findViewById(R.id.achieve17).setAlpha(1);
        }

    }

    public void close (View view){
        finish();
    }
}
