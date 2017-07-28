package com.example.studioquaiouest.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

public class screen61 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen61);
    }

    public void Verif (View view){
        TextView codeTxt = (TextView) findViewById(R.id.txtCode);
        int code = Integer.parseInt(codeTxt.getText().toString());

        VideoView vid = (VideoView) findViewById(R.id.vidWrong);

        if (code == 317986){
            vid.setVisibility(View.INVISIBLE);
            vid.stopPlayback();
            gotoNext(view);
        } else {
            vid.setVisibility(View.VISIBLE);
            Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.nogod);
            vid.setVideoURI(uri);
            vid.start();
        }
    }

    public void gotoNext (View view){
        Intent intent = new Intent(this, screenshots.class);
        int i = 60;
        int s = 63;
        intent.putExtra("screenshot", i).putExtra("next", s);
        startActivity(intent);
    }
}
