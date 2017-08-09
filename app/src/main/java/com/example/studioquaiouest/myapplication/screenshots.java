package com.example.studioquaiouest.myapplication;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import io.smooch.ui.ConversationActivity;

public class screenshots extends AppCompatActivity {

    public int i;
    public String uri;
    //public String[] ButtonTxt = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screenshots);
        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            i = extras.getInt("screenshot");
        }
        if (i<10) {
            uri = "@drawable/screenshot0" + i;
        }else{
            uri = "@drawable/screenshot"+i;
        }

        int ImageRessource = getResources().getIdentifier(uri, null, getPackageName());
        ImageView imageView = (ImageView) findViewById(R.id.imageView5);
        Drawable res = getResources().getDrawable(ImageRessource);
        imageView.setImageDrawable(res);

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

    public int s;

    public void gotoNext (View view) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            s = extras.getInt("next");
        }

        switch (s) {

            case 35 : {
                Intent day2 = new Intent(this, day2.class);
                startActivity(day2);
                break;
            }

            case 39 : {
                Intent day3 = new Intent(this, day3.class);
                startActivity(day3);
                break;
            }

            case 45 : {
                Intent day4 = new Intent(this, day4.class);
                startActivity(day4);
                break;
            }

            case 55 : {
                Intent day5 = new Intent(this, day5.class);
                startActivity(day5);
                break;
            }
            case 666 : {
                Intent end = new Intent(this, day6.class);
                startActivity(end);
                break;
            }

            default : {
                String className = "com.example.studioquaiouest.myapplication.screen" + s;
                Class<?> c = null;
                try {
                    c = Class.forName(className);
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                Intent intent = new Intent(this, c);
                startActivity(intent);
            }
        }
    }
}
