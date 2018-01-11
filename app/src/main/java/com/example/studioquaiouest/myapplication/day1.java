package com.example.studioquaiouest.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import io.smooch.ui.ConversationActivity;

public class day1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day1);

        SharedPreferences prefs = getSharedPreferences(save.MY_PREFS, MODE_PRIVATE);
        int savedDay = prefs.getInt("savedDay", 0);

        if (savedDay < 1){
            SharedPreferences.Editor editor = getSharedPreferences(save.MY_PREFS, MODE_PRIVATE).edit();
            editor.putInt("savedDay", 1);
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
        inflater.inflate(R.menu.menu_basic, menu); //your file name
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

    public void gotoIntro (View view){
        Intent intro = new Intent(this, intro.class);
        gotoNext(view);
        startActivity(intro);
    }

    public void gotoNext (View view){
        Intent intent = new Intent(this, screen2.class);
        intent.putExtra("screenshot", 1);
        startActivity(intent);
    }
}
