package com.example.studioquaiouest.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.VideoView;

import io.smooch.ui.ConversationActivity;

public class screen61 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen61);
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

    EditText codeTxt;

    public void Verif (View view){
        codeTxt = (EditText) findViewById(R.id.txtCode);
        int code = Integer.parseInt(codeTxt.getText().toString());

        VideoView vid = (VideoView) findViewById(R.id.vidWrong);

        if (code == 317986){
            vid.setVisibility(View.INVISIBLE);
            vid.stopPlayback();
            hideEmpty();
            gotoNext(view);
        } else {
            vid.setVisibility(View.VISIBLE);
            hideEmpty();
            Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.nogod);
            vid.setVideoURI(uri);
            vid.start();
        }
    }

    public void hideEmpty (){
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        codeTxt.setText("");
    }

    public void gotoNext (View view){
        Intent intent = new Intent(this, screenshots.class);
        int i = 60;
        int s = 63;
        intent.putExtra("screenshot", i).putExtra("next", s);
        startActivity(intent);
    }
}
