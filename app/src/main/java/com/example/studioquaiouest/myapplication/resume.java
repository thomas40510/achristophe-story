package com.example.studioquaiouest.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class resume extends AppCompatActivity {

    public int i,s,r;
    public String[] resumeNbr;
    public View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);
        resumeNbr = getResources().getStringArray(R.array.resumes);
        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            r = extras.getInt("resumenbr");
            i = extras.getInt("screenshot");
            s = extras.getInt("next");
        }
        r--;
        setResume(view);

    }
    public void setResume (View view){

        TextView txtResume = (TextView) findViewById(R.id.txtResume);
        txtResume.setText(resumeNbr[r]);
    }
    public void gotoNext (View view) {

        if (s == 40) {
            r++;
            s++;
            setResume(view);
        } else {
            Intent intent = new Intent(this, screenshots.class);
            intent.putExtra("screenshot", i)
                    .putExtra("next", s);
            startActivity(intent);
        }
    }

}
