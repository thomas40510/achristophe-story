package com.example.studioquaiouest.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
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

public class screen41 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen41);
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

    EditText codeTxt;

    public void Verif (View view){
        codeTxt = (EditText) findViewById(R.id.txtCode);
        String codeSt = codeTxt.getText().toString();
        if (codeSt.isEmpty()) codeSt = "0";
        int code = Integer.parseInt(codeSt);

        final VideoView vid = (VideoView) findViewById(R.id.vidWrong);

        if (code == 430383){
            vid.setVisibility(View.INVISIBLE);
            vid.stopPlayback();
            hideEmpty();
            gotoNext(view);
        } else {
            vid.setVisibility(View.VISIBLE);
            hideEmpty();
            Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.nope);
            vid.setVideoURI(uri);
            vid.start();
            vid.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    vid.setVisibility(View.INVISIBLE);
                }
            });
        }
    }

    public void hideEmpty (){
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        codeTxt.setText("");
    }

    public void showHelp (View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("username : guest"+"\n"+"password : 2242")
                .setPositiveButton("c'est noté !", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.show();
    }

    public void gotoNext (View view){
        Intent intent = new Intent(this, resume.class);
        int i = 43;
        int s = 43;
        int r = 8;
        intent.putExtra("screenshot", i).putExtra("next", s).putExtra("resumenbr", r);
        startActivity(intent);
    }
}
