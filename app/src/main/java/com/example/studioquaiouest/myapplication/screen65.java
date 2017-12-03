package com.example.studioquaiouest.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import io.smooch.ui.ConversationActivity;

public class screen65 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen65);

        achieveprefs achprefs = new achieveprefs();
        achprefs.fetchUnlock(this);

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

    int n;

    public void Verif (final View view){
        n = 0;
        unlockCount(view);


        // previous condition, patched in v1.2
        // if (n >= 19){
        if (n>=15){
            findViewById(R.id.txtWrong).setVisibility(View.INVISIBLE);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Félicitations !")
                    .setMessage("Je crois que nous avons trouvé un nouvel expert... Vous pouvez passer à la suite.")
                    .setPositiveButton("C'est parti !", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            gotoNext(view);
                        }
                    });
            builder.show();
        } else {
            findViewById(R.id.txtWrong).setVisibility(View.VISIBLE);
            Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            v.vibrate(300);
        }

    }

    public void unlockCount(View view){

        for (int r = 0 ; r < 3 ; r++){
            for (int c = 0 ; c < 10 ; c++){
                if (achieveprefs.isUnlocked[r][c]){
                    n++;
                }
            }
        }
        ((TextView)findViewById(R.id.countTxt)).setText(""+n);
    }


    public void gotoNext (View view){
        Intent intent = new Intent(this, screenshots.class);
        int i = 62;
        int s = 666;
        intent.putExtra("screenshot", i).putExtra("next", s);
        startActivity(intent);
    }
}
