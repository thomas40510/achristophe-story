package com.example.studioquaiouest.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import io.smooch.ui.ConversationActivity;

public class day2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day2);

        SharedPreferences prefs = getSharedPreferences(save.MY_PREFS, MODE_PRIVATE);
        int savedDay = prefs.getInt("savedDay", 0);

        int s = 2;

        if (savedDay < s || savedDay == 7){
            SharedPreferences.Editor editor = getSharedPreferences(save.MY_PREFS, MODE_PRIVATE).edit();
            editor.putInt("savedDay", s);
            editor.commit();

            Toast.makeText(this, "Progression Sauvegardée !", Toast.LENGTH_SHORT).show();
        }
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

    public void gotoNext (View view){
        Intent intent = new Intent(this, screen35.class);
        startActivity(intent);
    }
}
