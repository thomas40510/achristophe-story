package com.example.studioquaiouest.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import io.smooch.ui.ConversationActivity;

public class screen31 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen31);
    }

    /**
     * Actions for toolbar menu
     */
    @Override
    //load menu file//
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_basic, menu); //your file name
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

    int buttonID;
    int n = 1;

    public void wrong1 (View view){
        buttonID = 1;
        Exec(view);
    }
    public void wrong2 (View view){
        buttonID = 3;
        Exec(view);
    }
    public void wrong3 (View view){
        buttonID = 4;
        Exec(view);
    }
    public void true1 (View view){
        buttonID = 2;
        Exec(view);
    }

    public void Exec (View view){
        if (n == 2 && (buttonID == 1 || buttonID == 3 || buttonID == 4)){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("C'est faux !!")
                    .setMessage("Revois ta géographie, t'es franchement mauvais !")
                    .setPositiveButton("d'accord...", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
            builder.show();
        } else if (n == 1 && (buttonID == 1 || buttonID == 3 || buttonID == 4)){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Ça doit être une erreur...")
                    .setMessage("Tu as dû misclick, réessaie...")
                    .setPositiveButton("ça marche !", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
            builder.show();
            n++;
        } else gotoNext(view);
    }

    public void gotoNext (View view){
        Intent intent = new Intent(this, screenshots.class);
        int i = 15;
        int s = 33;
        intent.putExtra("screenshot", i).putExtra("next", s);
        startActivity(intent);
    }
}
