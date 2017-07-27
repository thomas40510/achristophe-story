package com.example.studioquaiouest.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import io.smooch.ui.ConversationActivity;

public class screen21 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen21);
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

    public View view;

    public void Verif (View view){
        EditText text = (EditText)findViewById(R.id.editText);
        float moyenne = Float.parseFloat(text.getText().toString());

        if (moyenne == 8.18f){
            findViewById(R.id.imgWrong).setVisibility(View.INVISIBLE);
            gotoNext(view);
        }
        else {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(300);
            findViewById(R.id.imgWrong).setVisibility(View.VISIBLE);
        }
    }
    public void gotoNext (View view){
        Intent intent = new Intent(this, resume.class);
        int r = 1;
        int i = 6;
        int s = 23;
        intent.putExtra("resumenbr", r)
                .putExtra("screenshot", i)
                .putExtra("next", s);
        startActivity(intent);
    }
}
