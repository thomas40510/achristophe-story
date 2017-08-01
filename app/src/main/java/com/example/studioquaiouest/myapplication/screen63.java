package com.example.studioquaiouest.myapplication;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.VideoView;

import io.smooch.ui.ConversationActivity;

public class screen63 extends AppCompatActivity {

    VideoView vid;
    Uri vidLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen63);

        vid = (VideoView) findViewById(R.id.vidShow);
        vidLink = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.olive);
        vid.setVideoURI(vidLink);
        vid.setVisibility(View.VISIBLE);
        vid.start();
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

    EditText passTxt;

    public void Verif (View view){
        passTxt = (EditText) findViewById(R.id.passTxt);
        String password = passTxt.getText().toString().toLowerCase();

        if (password.equals("olive")){
            vid.stopPlayback();
            hideEmpty();
            gotoNext(view);
        } else {
            Uri wrong = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.flutterno);
            vid.setVideoURI(wrong);
            vid.start();
            hideEmpty();
            vid.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    vid.setVideoURI(vidLink);
                    vid.start();
                }
            });
        }
    }

    public void hideEmpty (){
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        passTxt.setText("");
    }


    public void gotoNext (View view){
        Intent intent = new Intent(this, screenshots.class);
        int i = 61;
        int s = 65;
        intent.putExtra("screenshot", i).putExtra("next", s);
        startActivity(intent);
    }
}
