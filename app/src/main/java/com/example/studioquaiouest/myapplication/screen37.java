package com.example.studioquaiouest.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class screen37 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen37);
    }

    float coeff, poids, taux;
    String coeffi;
    int nbreVerres;
    View view;


    public String onRadioButtonClicked(View View) {
        final EditText weight = (EditText) findViewById(R.id.weight);
        final TextView poids = (TextView) findViewById(R.id.poids);
        final Button valider2 = (Button) findViewById(R.id.valider2);
        TextView textView = (TextView) findViewById(R.id.textView);
        boolean checked = ((RadioButton) View).isChecked();
        switch (View.getId()) {
            case R.id.radioHomme:
                if (checked)
                    Toast.makeText(this, "Homme", Toast.LENGTH_SHORT).show();
                coeff = 0.7f;
                poids.setVisibility(View.VISIBLE);
                weight.setVisibility(View.VISIBLE);
                valider2.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                break;
            case R.id.radioFemme:
                if (checked)
                    Toast.makeText(this, "Femme", Toast.LENGTH_SHORT).show();
                coeff = 0.6f;
                poids.setVisibility(View.VISIBLE);
                weight.setVisibility(View.VISIBLE);
                valider2.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                break;
        }
        return (coeffi);
    }
    public String saisiePoids(View View){
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        final TextView verresBus = (TextView) findViewById(R.id.verresBus);
        final EditText nbreVerresBus = (EditText) findViewById(R.id.nbreVerresBus);
        final Button valider3 = (Button) findViewById(R.id.valider3);
        final EditText weight = (EditText) findViewById(R.id.weight);
        verresBus.setVisibility(View.VISIBLE);
        nbreVerresBus.setVisibility(View.VISIBLE);
        valider3.setVisibility(View.VISIBLE);
        String stringPoids = weight.getText().toString();
        poids = Float.parseFloat(stringPoids);
        return (stringPoids);
    }
    public void saisieVerres (View View){
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        final EditText nbreVerresBus = (EditText) findViewById(R.id.nbreVerresBus);
        nbreVerres = Integer.parseInt(nbreVerresBus.getText().toString());

        calculate();

    }

    public void calculate(){
        taux = (nbreVerres*10)/(poids*coeff);

        if (taux>=10){
            gotoNext(view);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Vraiment ?")
                    .setMessage("Eh bah t'es loin d'être Breton ! Va te bourrer la gueule et reviens après !")
                    .setPositiveButton("J'y vais tout de suite !", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });

            builder.show();
        }
    }

    public void gotoNext (View view){
        Intent intent = new Intent(this, resume.class);
        int i = 29;
        int s = 39;
        int r = 5;
        intent.putExtra("screenshot", i).putExtra("next", s).putExtra("resumenbr", r);
        startActivity(intent);
    }
}
