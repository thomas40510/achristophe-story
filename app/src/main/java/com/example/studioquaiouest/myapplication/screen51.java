package com.example.studioquaiouest.myapplication;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import io.smooch.ui.ConversationActivity;

public class screen51 extends AppCompatActivity {

    int sizex, sizey, beginx, endx, endy;
    int n, a, b;
    int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen51);



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
        findViewById(R.id.bstart).setVisibility(View.INVISIBLE);
        findViewById(R.id.bHelp).setVisibility(View.INVISIBLE);
        startTimer();
    }

    Runnable runnable;
    final Handler handler = new Handler();
    public void startTimer(){

        final TextView showTime = (TextView)findViewById(R.id.showTime);

        final int delay = 750; //milliseconds


        handler.postDelayed(new Runnable(){
            public void run(){

                if (time != 10) {
                    int remainTime = 10-time;
                    genId();
                    showTime.setText(""+remainTime);

                    if (a == b){
                        genId();
                    }else {
                        b = a;
                        time++;
                        setRandom(a);
                        String txtID = "imgShow"+a;
                        int resID = getResources().getIdentifier(txtID, "id", getPackageName());
                        findViewById(resID).setVisibility(View.VISIBLE);
                    } handler.postDelayed(this, delay);
                } else {
                    showTime.setText("0");
                    findViewById(R.id.bstart).setVisibility(View.VISIBLE);
                    findViewById(R.id.bHelp).setVisibility(View.VISIBLE);
                    handler.removeCallbacks(this);
                    Verif();
                }
            }
        }, delay);

    }

    public void genId(){
        Random r = new Random();
        a = r.nextInt(6);
        a++;
    }
    int resID;
    View v;
    public void setRandom(int a){
        Random r = new Random();
        beginx = r.nextInt((sizex-20));
        endx = r.nextInt(sizex);


        String txtID = "imgShow"+a;
        resID = getResources().getIdentifier(txtID, "id", getPackageName());

        ObjectAnimator animeX = ObjectAnimator.ofFloat(findViewById(resID), "translationX", beginx, endx);
        ObjectAnimator animeY = ObjectAnimator.ofFloat(findViewById(resID), "translationY", 0, endy);
        animeX.setDuration(2500);
        animeY.setDuration(2500);

        //v = findViewById(resID);

        animeX.start();
        animeY.start();

    }


    public void onTextClick(View view){
        int clickNbr = Integer.parseInt(view.getTag().toString());
        String txtID = "imgShow"+clickNbr;
        int clickID = getResources().getIdentifier(txtID, "id", getPackageName());
        findViewById(clickID).setVisibility(View.INVISIBLE);

        n++;
    }

    View view;

    public void Verif(){
        int f = n;
        n = 0;
        time = 0;

        if (f >= 7){
            AlertDialog.Builder done = new AlertDialog.Builder(this);
            done.setTitle("Merci, merci, merci !!")
                    .setMessage("Vous avez encore une fois donné un coup de main non négligeable à notre équipe. Un grand merci à vous !")
                    .setPositiveButton("Y'a pas de quoi !", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            gotoNext(view);
                        }
                    });
            done.show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Plus vite soldat !")
                    .setMessage("Je connais des petits chinois payés 3centimes par jour qui feraient ça plus rapidement que toi !")
                    .setPositiveButton("Je retente !", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
            builder.show();
        }

    }

    public void showHelp (View view){
        AlertDialog.Builder help = new AlertDialog.Builder(this);
        help.setTitle("Kécécé kifofèr des jà ?")
                .setMessage("On est ici pour lutter contre le spam... Il faut tout simplement cliquer le plus rapidement possible sur les messages pour supprimer les spams.")
                .setPositiveButton("C Kompri !", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        help.show();
    }

    public void gotoNext (View view){
        Intent intent = new Intent(this, screenshots.class);
        int i = 52;
        int s = 53;
        intent.putExtra("screenshot", i).putExtra("next", s);
        startActivity(intent);
    }
}
