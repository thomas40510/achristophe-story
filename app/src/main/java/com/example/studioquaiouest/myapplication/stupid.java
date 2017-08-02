package com.example.studioquaiouest.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.games.achievement.Achievement;

public class stupid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stupid);

        SharedPreferences prefs = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE);
        achieveprefs.isUnlocked[1][2] = prefs.getBoolean("achieveSave12", false);

        if (!achieveprefs.isUnlocked[1][2]){
            SharedPreferences.Editor editor = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE).edit();
            editor.putBoolean("achieveSave12", true);
            editor.commit();
            Toast.makeText(this, "Achievement unlocked !", Toast.LENGTH_SHORT).show();
        }

    }
    public void dispnext (View view){
        Intent intent = new Intent(this, stupid2.class);
        startActivity(intent);
    }
}
