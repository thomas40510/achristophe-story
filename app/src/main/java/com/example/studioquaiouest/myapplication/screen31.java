package com.example.studioquaiouest.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class screen31 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen31);
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
