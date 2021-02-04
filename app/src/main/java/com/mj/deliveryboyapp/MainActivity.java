package com.mj.deliveryboyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RequestQueue mRequestQue;
    private String URL = "https://fcm.googleapis.com/fcm/send";
    public  static List<orderdetailsmodelclass> orders;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_main);
        Transition explodeAnimation = TransitionInflater.from(this).inflateTransition(R.transition.slide);
        explodeAnimation.setDuration(1000);
        getWindow().setEnterTransition(explodeAnimation);
      textView=findViewById(R.id.txt);

        orders=new ArrayList<>();
        if (getIntent().hasExtra("category")){
            Intent intent = new Intent(MainActivity.this,ReceiveNotificationActivity.class);
            intent.putExtra("category",getIntent().getStringExtra("category"));
            intent.putExtra("brandId",getIntent().getStringExtra("brandId"));
            startActivity(intent);
        }
        Button button = findViewById(R.id.btn);
        mRequestQue = Volley.newRequestQueue(this);
        SharedPreferences prefs=this.getSharedPreferences("MyPref",MODE_PRIVATE);
        textView.setText(prefs.getString("available","0"));
        if(prefs.getString("available","no").equals("no"))
        {FirebaseMessaging.getInstance().subscribeToTopic("no");}
      else  if(prefs.getString("available","no").equals("yes"))
        FirebaseMessaging.getInstance().subscribeToTopic("yes");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("yes");
                  //  sendNotification();


            }
        });

    }



    private void sendNotification() {

        JSONObject json = new JSONObject();
        try {
            json.put("to","/topics/"+"news");
            JSONObject notificationObj = new JSONObject();
            notificationObj.put("title","");
            notificationObj.put("body","any body");

            JSONObject extraData = new JSONObject();
            extraData.put("brandId","puma");
            extraData.put("category","Shoes");

            json.put("notification",notificationObj);
            json.put("data",extraData);


            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL,
                    json,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Log.d("MUR-------------------", "onResponse: "+response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("MUR", "onError:------ "+error.networkResponse);
                }
            }
            ){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> header = new HashMap<>();
                    header.put("content-type","application/json");
                    header.put("authorization","key=AAAAT1XhxNM:APA91bE2ybeRH_7-qoXs-YTBeTuZiiuvBMA7EcN7PvFgLnEYUOAPlwD5aWZmGgDwb1QWuncdWki5KxuN1Qao3v_uTFB5ygDWBCksXbxM1LtN4fWVfaJSeauawbsGl8rNCfW-U0N7f8zH");
                    return header;
                }
            };
            mRequestQue.add(request);
        }
        catch (JSONException e)

        {
            Toast.makeText(this,"89898989"+e.toString(),Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
