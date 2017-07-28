package com.example.studioquaiouest.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.smooch.ui.ConversationActivity;

import static java.lang.Math.toIntExact;

public class screen45 extends AppCompatActivity {

    int spamCount, time;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen45);

        setButton();
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


    public void setButton (){
        spamCount = 0;
        time = 0;

        final TextView spamTxt = (TextView)findViewById(R.id.txtCount);
            Button spam = (Button) findViewById(R.id.buttonSpam);
            spam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (time != 5) {

                        if (spamCount == 0) {
                            startTimer();
                            spamCount++;
                            spamTxt.setText("" + spamCount);
                        } else {
                            spamCount++;
                            spamTxt.setText("" + spamCount);
                        }

                    } else {
                        if (time == 5) {
                            Verif(view);
                        } Toast.makeText(getApplicationContext(), "end", Toast.LENGTH_SHORT).show();
                    }
                }


            });

    }

    private Runnable runnable;

    public void startTimer(){

        final Handler handler = new Handler();
        final int delay = 1000; //milliseconds
        final TextView txtTime = (TextView)findViewById(R.id.txtTps);

        handler.postDelayed(new Runnable(){
            public void run(){
                if (time != 5) {
                    time++;
                    int remain = 5-time;
                    txtTime.setText(""+remain);
                } else {
                    Button button = (Button) findViewById(R.id.buttonSpam);
                    button.setText("Vérifier");
                }
                handler.postDelayed(this, delay);
            }
        }, delay);

    }


    public void Verif(View view){
        if (spamCount >= 30){
            gotoNext(view);

        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("C'est lent...")
                    .setMessage("Espère pas passer à la suite avec une vitesse pareille ! On parle quand même de Christophe !")
                    .setPositiveButton("Ok je retente", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(), screen45.class);
                            startActivity(intent);
                        }
                    });
            builder.show();
        }
    }

    public void gotoNext (View view){
        Intent intent = new Intent(this, resume.class);
        int i = 47;
        int s = 47;
        int r = 9;
        intent.putExtra("screenshot", i).putExtra("next", s).putExtra("resumenbr", r);
        startActivity(intent);
    }
}
