package com.example.studioquaiouest.myapplication;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;




public class MyFirebaseInstanceIdService extends com.google.firebase.iid.FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("INFO","Refreshed token: " + refreshedToken);


    }
}