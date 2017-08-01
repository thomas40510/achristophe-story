package com.example.studioquaiouest.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.util.Log;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.Arrays;

import io.smooch.ui.ConversationActivity;

public class screen57 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen57);
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

    View view;


    int [][] isChecked = new int[][]{
            {0,0,0,0,0},
            {0,0,0,0,0},
            {0,0,0,0,0},
            {0,0,0,0,0},
            {0,0,0,0,0},
    };

    int [][] allCheck = new int[][]{
            {0,0,1,0,0},
            {1,0,0,0,0},
            {0,0,0,1,0},
            {0,0,0,0,1},
            {0,1,0,0,0},

    };

    VideoView vid;


    public void Verif (View view){
        int n = 1;
        int i;
        while (n < 6){
            for (i = 1; i < 6 ; i++) {
                String radioID = "r" + n + i;
                int resID = getResources().getIdentifier(radioID, "id", getPackageName());
                if (((RadioButton) findViewById(resID)).isChecked()) {
                    isChecked[(n-1)][(i-1)] = 1;
                } else {
                    isChecked[(n-1)][(i-1)] = 0;
                }
            }
            n++;
        }

        if (Arrays.deepEquals(isChecked, allCheck)) {

            vid.setVisibility(View.INVISIBLE);
            reset();
            gotoNext(view);
        } else {
            vid = (VideoView) findViewById(R.id.vidWrong);
            vid.setVisibility(View.VISIBLE);
            Uri vidLink = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.vaderno);
            vid.setVideoURI(vidLink);
            vid.start();
            reset();
            vid.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    vid.setVisibility(View.INVISIBLE);
                }
            });
        }
    }

    public void reset (){
        int l = 1;
        int k;
        while (l < 6) {
            for (k = 1; k < 6; k++) {
                String radioID = "r" + l + k;
                int resID = getResources().getIdentifier(radioID, "id", getPackageName());
                ((RadioButton) findViewById(resID)).setChecked(false);
            }
            l++;
        }
    }

    public void gotoNext (View view){
        Intent intent = new Intent(this, screenshots.class);
        int i = 58;
        int s = 59;
        intent.putExtra("screenshot", i).putExtra("next", s);
        startActivity(intent);
    }
}
