package com.example.studioquaiouest.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

import io.smooch.ui.ConversationActivity;

public class screen15 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen15);
        save.savedClass = screen15.class;

        SharedPreferences.Editor editor = getSharedPreferences(save.MY_PREFS, MODE_PRIVATE).edit();
        editor.putString("savedClass", "com.example.studioquaiouest.myapplication.screen15");
        editor.commit();

        if (Easter.enable.equals("true") && Easter.easterday.equals("day2")){
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

    public void gotoScreen16 (View view){
        TextView paschecked = (TextView) findViewById(R.id.msgerreur);
        CheckBox checkbox = (CheckBox) findViewById(R.id.cgucheck);
        if (checkbox.isChecked()){
            paschecked.setVisibility(View.INVISIBLE);
            Intent intent = new Intent(this, screen2.class);
            int i = 8;
            intent.putExtra("screenshot", i);
            startActivity(intent);
        }
        else {
            paschecked.setVisibility(View.VISIBLE);
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(300);
        }
    }
}
