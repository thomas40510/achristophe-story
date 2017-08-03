package com.example.studioquaiouest.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import io.smooch.ui.ConversationActivity;

public class screen59 extends AppCompatActivity {

    VideoView vid;
    Uri vidLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen59);

        vid = (VideoView) findViewById(R.id.videoShow);
        vidLink = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.stromae);
        vid.setVideoURI(vidLink);
        vid.start();
        vid.canPause();

        vid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vid.pause();
            }
        });
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

    int w = 0;
    public void Verif (View view){
        String isTrue = view.getTag().toString();

        if (isTrue.equals("true")){
            vid.stopPlayback();
            gotoNext(view);
        } else {

            if (w==2){

                SharedPreferences prefs = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE);
                achieveprefs.isUnlocked[2][1] = prefs.getBoolean("achieveSave21", false);

                if (!achieveprefs.isUnlocked[2][1]){
                    SharedPreferences.Editor editor = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE).edit();
                    editor.putBoolean("achieveSave21", true);
                    editor.commit();
                    Toast.makeText(this, "Achievement unlocked !", Toast.LENGTH_SHORT).show();
                }
            } else {
                w++;
            }

            Uri wrong = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.rlynigga);
            vid.setVideoURI(wrong);
            vid.start();
            vid.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    vid.setVideoURI(vidLink);
                    vid.start();
                }
            });
        }
    }

    public void gotoNext (View view){
        Intent intent = new Intent(this, screenshots.class);
        int i = 59;
        int s = 61;
        intent.putExtra("screenshot", i).putExtra("next", s);
        startActivity(intent);
    }
}
