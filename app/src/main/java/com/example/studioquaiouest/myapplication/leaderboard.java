package com.example.studioquaiouest.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class leaderboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        setTitle("\"Le Spectacle\" - LeaderBoard");

        fetch(view);
    }

    View view;

    String[] rankName = new String[10];
    String[] txtName = new String[10];
    String[] rankTime = new String[10];
    String[] txtTime = new String[10];
    private int n;




    public void fetch(View view){

        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference myRef = mDatabase.child("ranks");
        Query queryRef = myRef.orderByChild("name");
        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, String> value = (Map<String, String>)dataSnapshot.getValue();
                rankName[Integer.parseInt(dataSnapshot.getKey())] = value.get("name");

                n = Integer.parseInt(dataSnapshot.getKey());

                Log.e("Inf0", rankName[1]+" "+rankName[2]);

                txtName[n] = "name" + n;
                int resId= getResources().getIdentifier(txtName[n], "id", getPackageName());
                ((TextView) findViewById(resId)).setText(rankName[n]);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        queryRef = myRef.orderByChild("date");
        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, String> value = (Map<String, String>)dataSnapshot.getValue();
                rankTime[Integer.parseInt(dataSnapshot.getKey())] = value.get("date");

                n = Integer.parseInt(dataSnapshot.getKey());

                Log.e("Inf1", rankTime[1]+" "+rankTime[2]);

                txtTime[n] = "time" + n;
                int resId= getResources().getIdentifier(txtTime[n], "id", getPackageName());
                ((TextView) findViewById(resId)).setText(rankTime[n]);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Toast.makeText(this, "Rangs récupérés", Toast.LENGTH_SHORT).show();
    }

    public void sendEmail(View view){

        Log.i("Send email", "");

        String[] TO = {"achristophe.story@gmail.com"};
        Intent intent = new Intent(this, leaderboard.class);
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Demande de rectification...");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "va mettre à jour ta base de données, j'ai fait un nouveau record !!");

        try {
            startActivity(intent);
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));

            finish();

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }

    }

    public void close(View view){
        finish();
    }

}
