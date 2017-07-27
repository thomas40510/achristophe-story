package com.example.studioquaiouest.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class screen33 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen33);
    }

    int ansID;

    public void ans1 (View view){
        ansID = 1;
        Exec(view);
    }
    public void ans2 (View view){
        ansID = 2;
        Exec(view);
    }
    public void ans3 (View view){
        ansID = 3;
        Exec(view);
    }

    public void Exec (View view){

        if (ansID == 1 || ansID == 3){
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(300);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Perdu !")
                    .setMessage("Eh bah tu es pas prêt de bosser comme infiltré !")
                    .setPositiveButton("Je réessaie", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
            builder.show();
        } else gotoNext(view);
    }

    public void gotoNext (View view){
        Intent intent = new Intent(this, screenshots.class);
        int i = 16;
        int s = 35;
        intent.putExtra("screenshot", i).putExtra("next", s);
        startActivity(intent);
    }
}
