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
import android.widget.ImageView;

import java.util.Random;

import io.smooch.ui.ConversationActivity;

public class screen35 extends AppCompatActivity {

    public int n, r;
    public long begin, end;
    public ImageView square;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen35);
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

    public void show3 (View view) {
        n = 1; //initialise les variables
        r = 0;
        begin = System.currentTimeMillis(); //récupère l'heure du système
        show4(view);
    }

    public void show4(View view) {
        Random rand = new Random();
        findViewById(R.id.button2).setVisibility(View.INVISIBLE);
        findViewById(R.id.button).setVisibility(View.INVISIBLE);
        int i = rand.nextInt(7); //génère un entier aléatoire sur [0;7[
        if (i == 0 || i == r) { //effectue le test si le nombre i est égal à 0 ou r (r est la valeur précédente de i). Ainsi, l'application n'affiche jamais 2 fois le même carré
            show4(view);        //si i est égal à 0 ou r, relance la méthode
        }
        else {
            r = i;
            String squareID = "square" + i; //crée un String intermédiaire, à partir du i choisi (si i=1, le String prendra la valeur "square1")
            int resID = getResources().getIdentifier(squareID, "id", getPackageName()); //convertit le String "squareId" en nom de ressource (pour l'appel au xml)
            square = ((ImageView) findViewById(resID));
            square.setVisibility(View.VISIBLE);
            square.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (n == 5) {
                        findViewById(R.id.button2).setVisibility(View.VISIBLE);
                        findViewById(R.id.button).setVisibility(View.VISIBLE);
                        square.setVisibility(View.INVISIBLE);
                        end = System.currentTimeMillis(); // récupère le temps du système, en millisecondes

                        double finalTime = (double)((end-begin)/5)/1000;// calcule le temps moyen, à partir du temps initial du système, et du temps final. Divise par 5 pour avoir la moyenne pour 1 carré
                        if (finalTime <1){
                            gotoNext(view);
                        } else {
                            showLoose(view);
                        }

                    } else {
                        n = n + 1;
                        square.setVisibility(View.INVISIBLE);
                        show4(view);
                    }
                }
            });

        }
    }

    public void showLoose (View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tu appelles-ça rapide ?")
                .setMessage("Il faudrait peut-être arrêter de jouer en fermant les yeux !")
                .setPositiveButton("Je vais essayer !", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        builder.show();
    }

    public void showGoal (View view){
        android.app.AlertDialog.Builder builder2 = new android.app.AlertDialog.Builder(this);
        builder2.setTitle("Comment faire...")
                .setMessage("Il faut tout simplement appuyer le plus rapidement possible sur les carrés. Si tu es assez rapide, tu passes à l'écran suivant.")
                .setPositiveButton("Ça marche !", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        builder2.show();
    }

    public void gotoNext (View view){
        Intent intent = new Intent(this, resume.class);
        int i = 21;
        int s = 37;
        int r = 4;
        intent.putExtra("screenshot", i).putExtra("next", s).putExtra("resumenbr", r);
        startActivity(intent);
    }
}
