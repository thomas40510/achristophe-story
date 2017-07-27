package com.example.studioquaiouest.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import junit.framework.Test;

import org.w3c.dom.Text;

public class leaderboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        n = 0;
    }
    public int n;

    public void fetch (View view){
        n = n+1;
        fetch2(view);
    }

    public void fetch2 (View view){

            DatabaseReference mDatabase;
            mDatabase = FirebaseDatabase.getInstance().getReference();
            DatabaseReference myRef = mDatabase.child("Ranks");

            String child = Integer.toString(n);
            myRef.child(child + "/name").addListenerForSingleValueEvent(new ValueEventListener() {

                public void onDataChange(DataSnapshot dataSnapshot) {
                    String test1 = dataSnapshot.getValue(String.class);
                    String nameID = "name" + (n);
                    int resID = getResources().getIdentifier(nameID, "id", getPackageName());
                    TextView name = (TextView) findViewById(resID);
                    name.setText(test1);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                }
            });
            myRef.child(child + "/time").addListenerForSingleValueEvent(new ValueEventListener() {

                public void onDataChange(DataSnapshot dataSnapshot) {
                    String test2 = dataSnapshot.getValue(String.class);
                    String nameID = "time" + (n);
                    int resID = getResources().getIdentifier(nameID, "id", getPackageName());
                    TextView name = (TextView) findViewById(resID);
                    name.setText(test2);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                }
            });


        /**DatabaseReference mDatabase;

        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference myref = mDatabase.child("Test");
        myref.child("test1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String test = dataSnapshot.getValue(String.class);
                TextView testTxtView = (TextView) findViewById(R.id.testTxt);
                testTxtView.setText(test);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "fetch cancelled", Toast.LENGTH_SHORT).show();
            }
        });
     */
    }
}
