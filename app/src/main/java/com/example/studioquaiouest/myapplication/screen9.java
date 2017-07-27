package com.example.studioquaiouest.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;

import io.smooch.ui.ConversationActivity;

public class screen9 extends AppCompatActivity {

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen9);
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        save.savedClass = screen9.class;

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
                Intent achieve = new Intent(this, achievements.class);
                startActivity(achieve);
                return true;
            case R.id.open_Smooch:
                ConversationActivity.show(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void gotoScreen10 (View view){
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();
        int currenth = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int currentm = new Time(System.currentTimeMillis()).getMinutes();

        if (hour == 22 && minute == 22){
            Intent intent = new Intent(this, screen2.class);
            int i = 5;
            intent.putExtra("screenshot", i);
            startActivity(intent);
        }
        else {
            i = i+1;
            if (i == 3) {
                if (hour == currenth && minute == currentm) {
                    SharedPreferences prefs = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE);
                    achieveprefs.Achieve[1] = prefs.getString("achieve1", "");

                    if (!achieveprefs.Achieve[1].equals("1")) {
                        SharedPreferences.Editor editor = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE).edit();
                        editor.putString("achieve1", "1");
                        editor.commit();
                        Toast.makeText(this, "Achievement unlocked !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
                TextView faux = (TextView) findViewById(R.id.txtFaux);
                faux.setVisibility(View.VISIBLE);
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(300);


        }
    }
}
