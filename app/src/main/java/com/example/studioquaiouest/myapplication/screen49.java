package com.example.studioquaiouest.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import io.smooch.ui.ConversationActivity;

public class screen49 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen49);
    }

    /**
     * Actions for toolbar menu
     */
    @Override
    //load menu file//
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_intro, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    //set on-click actions//
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case R.id.open_credits:
                Intent credits = new Intent(this, credits.class);
                startActivity(credits);
                return true;
            case R.id.open_achievements:
                Intent achieve = new Intent(this, achieveChoose.class);
                startActivity(achieve);
                return true;
            case R.id.open_Smooch:
                ConversationActivity.show(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    View view;
    int radioId;

    public void Verif (View view){

        RadioGroup group = (RadioGroup) findViewById(R.id.radioGroup2);
        radioId = group.getCheckedRadioButtonId();

        if (radioId == R.id.radio4){
            findViewById(R.id.txtWrong).setVisibility(View.INVISIBLE);
            RadioButton radio = (RadioButton) findViewById(radioId);
            radio.setChecked(false);
            gotoNext(view);
        } else {
            Wrong();
        }
    }

    int w = 0;

    public void Wrong(){
        if (w == 2){

            SharedPreferences prefs = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE);
            achieveprefs.isUnlocked[1][9] = prefs.getBoolean("achieveSave19", false);

            if (!achieveprefs.isUnlocked[1][9]){
                SharedPreferences.Editor editor = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE).edit();
                editor.putBoolean("achieveSave19", true);
                editor.commit();
                Toast.makeText(this, "Achievement unlocked !", Toast.LENGTH_SHORT).show();
            }
        } else {
            w++;
        }
        findViewById(R.id.txtWrong).setVisibility(View.VISIBLE);
        RadioButton radio = (RadioButton) findViewById(radioId);
        radio.setChecked(false);
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(300);
    }

    public void gotoNext (View view){
        Intent intent = new Intent(this, resume.class);
        int i = 51;
        int s = 51;
        int r = 10;
        intent.putExtra("screenshot", i).putExtra("next", s).putExtra("resumenbr", r);
        startActivity(intent);
    }
}
