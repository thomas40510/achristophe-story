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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import io.smooch.ui.ConversationActivity;

public class screen23 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen23);
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


    public View view;
    public int buttonID;
    public int resID, prevId;

    private Animation fadeout;
    private Animation fadein;

    public void wrong1 (View view){
        buttonID = 1;
        Exec(view);
    }
    public void wrong2 (View view){
        buttonID = 2;
        Exec(view);
    }
    public void wrong3 (View view){
        buttonID = 4;
        Exec(view);
    }
    public void true1 (View view){
        buttonID = 3;
        Exec(view);
    }

    public void Exec (View view){
        String ButtonName = "button"+buttonID;
        resID = getResources().getIdentifier(ButtonName, "id", getPackageName());
        final Button button = (Button)findViewById(resID);

        fadeout = AnimationUtils.loadAnimation(this, R.anim.fadeout);
        fadeout.setDuration(1000);
        fadeout.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {}
            public void onAnimationRepeat(Animation animation) {}
            public void onAnimationEnd(Animation animation) {
            }
        });

        fadein = AnimationUtils.loadAnimation(this, R.anim.fadein);
        fadein.setDuration(1000);
        fadein.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }
            public void onAnimationRepeat(Animation animation) {
            }
            public void onAnimationEnd(Animation animation) {
            }
        });

        if (prevId != resID) {
            switch (buttonID) {
                case 1:
                    button.startAnimation(fadeout);
                    button.setText("Ta mère");
                    button.startAnimation(fadein);
                    prevId = resID;
                    break;
                case 2:
                    button.startAnimation(fadeout);
                    button.setText("Mr Bénazet");
                    button.startAnimation(fadein);
                    findViewById(R.id.imgBenaz).setVisibility(View.VISIBLE);
                    prevId = resID;
                    break;
                case 3:
                    findViewById(R.id.imgBenaz).setVisibility(View.INVISIBLE);
                    gotoNext(view);
                    break;
                case 4:
                    button.startAnimation(fadeout);
                    button.setText("Ton chien");
                    button.startAnimation(fadein);
                    prevId = resID;
                    break;
            }
        } else {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(300);
        }
    }

    int w = 0;

    public void allHailBenaz (View view){
        if (w==2){
            SharedPreferences prefs = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE);
            achieveprefs.isUnlocked[1][4] = prefs.getBoolean("achieveSave14", false);

            if (!achieveprefs.isUnlocked[1][4]){
                SharedPreferences.Editor editor = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE).edit();
                editor.putBoolean("achieveSave14", true);
                editor.commit();
                Toast.makeText(this, "Achievement unlocked !", Toast.LENGTH_SHORT).show();
            }
        } else {
            w++;
        }
    }

    public void gotoNext (View view){
        Intent intent = new Intent(this, screenshots.class);
        int i = 7;
        int s = 25;
        intent.putExtra("screenshot", i)
                .putExtra("next", s);
        startActivity(intent);
    }
}
