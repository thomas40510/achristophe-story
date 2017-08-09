package com.example.studioquaiouest.myapplication;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import io.smooch.ui.ConversationActivity;

public class epilogue extends AppCompatActivity {

    public String uri;
    public int e;
    public View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_epilogue);
        setTitle("\"Le Spectacle\" - Épilogue");

        e = 63;
        setEpi();
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

    private Animation fadeout;
    private Animation fadein;

    public void setEpi (){

        if (e!=63) {
            fadein = AnimationUtils.loadAnimation(this, R.anim.fadein);
            fadein.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationStart(Animation animation) {
                }
                public void onAnimationRepeat(Animation animation) {
                }
                public void onAnimationEnd(Animation animation) {
                }
            });
            findViewById(R.id.imageView3).startAnimation(fadein);
        }


        uri = "@drawable/epi"+e;
        int ImageRessource = getResources().getIdentifier(uri, null, getPackageName());
        ImageView imageView = (ImageView) findViewById(R.id.imageView3);
        Drawable res = getResources().getDrawable(ImageRessource);

        imageView.setImageDrawable(res);
    }



    public void gotoNext (View view) {

        if (e!=75) {
            e++;

            fadeout = AnimationUtils.loadAnimation(this, R.anim.fadeout);
            fadeout.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationStart(Animation animation) {}
                public void onAnimationRepeat(Animation animation) {}
                public void onAnimationEnd(Animation animation) {
                    setEpi();
                }
            });

            findViewById(R.id.imageView3).startAnimation(fadeout);
        }
        else {
            //Intent intent = new Intent(this, veryLast.class);
            Intent intent = new Intent(this, screenlast.class);
            startActivity(intent);
        }
    }
    public void gotoPrevious (View view){

        if (e!=63) {
            e--;

            fadeout = AnimationUtils.loadAnimation(this, R.anim.fadeout);
            fadeout.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationStart(Animation animation) {}
                public void onAnimationRepeat(Animation animation) {}
                public void onAnimationEnd(Animation animation) {
                    setEpi();
                }
            });

            findViewById(R.id.imageView3).startAnimation(fadeout);
        }
        else {
            //Intent intent = new Intent(this, veryLast.class);
            Intent intent = new Intent(this, day6.class);
            startActivity(intent);
        }
    }
}
