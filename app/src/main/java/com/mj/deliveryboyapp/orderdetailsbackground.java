package com.mj.deliveryboyapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static com.mj.deliveryboyapp.MainActivity.orders;

public class orderdetailsbackground extends AsyncTask<String,Void,String> {
    Context context;

    public orderdetailsbackground(Context ctx)
    {
        context=ctx;


    }
    @Override
    protected String doInBackground(String... voids) {
       // String login_url= "http://192.168.43.221/retrieve1.php";
         String login_url= "http://192.168.43.201/food/orderdetails.php";
        if(true){
            try {
                URL url=new URL(login_url);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line=bufferedReader.readLine())!=null)
                {
                    result+=line;


                }

                bufferedReader.close();
                inputStream.close();

                return  result;
            }catch (Exception e)
            {

                return e.toString();
            }

        }
        return null;
    }
    @Override
    protected void onPreExecute() {
        // alertDialog=new AlertDialog.Builder(context).create();
        //alertDialog.setTitle("Login Status");

    }

    @Override
    protected void onPostExecute(String result) {
        try {
            JSONArray jsonArray = new JSONArray(result);

            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject obj=jsonArray.getJSONObject(i);
                orders.add(new orderdetailsmodelclass(obj.getString("orderid"),obj.getString("name"),obj.getString("mobile"), obj.getString("email"),obj.getString("address"),obj.getString("latitude"),
                    obj.getString("longitude"),obj.getString("restaurant"),
                    obj.getString("billingprice"),obj.getString("billingtype"),obj.getString("deliverytime"),
                       obj.getString("pickuptime"),obj.getInt("deliverying"),obj.getString("resadd"),obj.getString("resnum"),obj.getString("resid")));

            }
        }catch (Exception e){

            Toast.makeText(context,e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}

