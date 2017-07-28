package com.example.studioquaiouest.myapplication;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Point;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.EventListener;
import java.util.Random;

import io.smooch.ui.ConversationActivity;

public class screen47 extends AppCompatActivity {

    int sizex, sizey, beginx, endx, endy;
    int n, a, b;
    int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen47);



        n = 0;

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        sizex = size.x;
        sizey = size.y;
        endy = sizey + 20;
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

    public void begin(View view){
        a = 0;
        startTimer();
    }

    Runnable runnable;
    final Handler handler = new Handler();
    public void startTimer(){

        final int delay = 500; //milliseconds


        handler.postDelayed(new Runnable(){
            public void run(){

                if (time != 7) {
                    genId();

                    if (a == b){
                        genId();
                    }else {
                        b = a;
                        time++;
                        setRandom(a,v);
                        String txtID = "imgShow"+a;
                        int resID = getResources().getIdentifier(txtID, "id", getPackageName());
                        findViewById(resID).setVisibility(View.VISIBLE);
                    } handler.postDelayed(this, delay);
                } else {
                    handler.removeCallbacks(this);
                    Verif();
                }
            }
        }, delay);

    }

    public void genId(){
        Random r = new Random();
        a = r.nextInt(4);
        a++;
    }
    int resID;
    View v;
    public void setRandom(int a, View v){
        Random r = new Random();
        beginx = r.nextInt(sizex);
        endx = r.nextInt(sizex);



        TranslateAnimation trans = new TranslateAnimation(beginx, endx, 0, endy);
        trans.setDuration(4000);

        String txtID = "imgShow"+a;
        resID = getResources().getIdentifier(txtID, "id", getPackageName());
        findViewById(resID).startAnimation(trans);
        v.startAnimation(trans);


        trans.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                findViewById(resID).setVisibility(View.INVISIBLE);
            }
        });
    }


    public void onTextClick(View view){
        int clickNbr = Integer.parseInt(view.getTag().toString());
        String txtID = "imgShow"+clickNbr;
        int clickID = getResources().getIdentifier(txtID, "id", getPackageName());
        findViewById(clickID).setVisibility(View.INVISIBLE);

        n++;
    }

    public void Verif(){
        Toast.makeText(getApplicationContext(), ""+n, Toast.LENGTH_SHORT).show();
    }

    public void gotoNext (View view){
        Intent intent = new Intent(this, screenshots.class);
        int i = 48;
        int s = 49;
        intent.putExtra("screenshot", i).putExtra("next", s);
        startActivity(intent);
    }
}
