package com.example.studioquaiouest.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import io.smooch.ui.ConversationActivity;

public class screen25 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen25);
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

    public void isWrong(View view){
        findViewById(R.id.imgWrong).setVisibility(View.VISIBLE);
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(300);
    }

    public void isTrue (View view){
        findViewById(R.id.imgWrong).setVisibility(View.INVISIBLE);
        gotoNext(view);
    }

    public void gotoNext (View view){
        Intent intent = new Intent(this, resume.class);
        int r = 2;
        int i = 10;
        int s = 27;
        intent.putExtra("resumenbr", r)
                .putExtra("screenshot", i)
                .putExtra("next", s);
        startActivity(intent);
    }
}
