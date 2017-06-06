package com.cds.gcmnotificationdemo;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gcm.GCMRegistrar;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MainActivity extends Activity {

    private static final String SENDER_ID = "800720455732";
    String regId = "";
    private GoogleCloudMessaging gcm;
    private EditText username;
    private Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //username = (EditText) findViewById(R.id.username);
       // signin = (Button) findViewById(R.id.signin);
        FirebaseMessaging.getInstance().subscribeToTopic("test");
        FirebaseInstanceId.getInstance().getToken();

        //getRegisterationID();

        //String message =getIntent().getExtras().getString("message")!=null?getIntent().getExtras().getString("message"):"";
        //((TextView)findViewById(R.id.text)).setText(message);

    }

    public void SendNotification(View view){
       final Thread thread = new Thread(new Runnable() {
           @Override
           public void run() {
               OkHttpClient client = new OkHttpClient();
               //RequestBody requestbody = new FormBody.Builder()
               //      .build();
                Utils utils = new Utils(MainActivity.this);
               Request request = new Request.Builder()
                       .url("http://freecs13.hostei.com/fcm/gcm_send.php?SenderToken="+utils.getdata("token"))
                       .build();

               try{
                   client.newCall(request).execute();
                   Log.d("response_notif","Success");
               }catch (Exception e){
                   e.printStackTrace();

                   Log.d("response_notif",e.toString());
               }
           }
       });

        thread.start();
    }
}
