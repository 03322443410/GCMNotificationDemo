package com.cds.gcmnotificationdemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by fazal on 2/6/2017.
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        //registerToken(token);
        Log.d("token",token);
        Utils utils = new Utils(this);
        utils.savedata("token",token);
    }

    private void registerToken(String token) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestbody = new FormBody.Builder()
                .add("token",token)
                .add("Name","")
                .build();

        Request request = new Request.Builder()
                .url("http://freecs13.hostei.com/fcm/register.php")
                .post(requestbody)
                .build();

        try{
            client.newCall(request).execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
