package com.example.studioquaiouest.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.ThrowOnExtraProperties;

import io.smooch.ui.ConversationActivity;

public class intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences prefs = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE);
        achieveprefs.isUnlocked[0][4] = prefs.getBoolean("achieveSave04", false);

        if (!achieveprefs.isUnlocked[0][4]){
            SharedPreferences.Editor editor = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE).edit();
            editor.putBoolean("achieveSave04", true);
            editor.commit();
            Toast.makeText(this, "Achievement unlocked !", Toast.LENGTH_SHORT).show();
        }
    }

    public void goBack (View view){
        finish();
    }
}
