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

public class trackbackground extends AsyncTask<String,Void,String> {
    Context context;

    public trackbackground(Context ctx)
    {
        context=ctx;


    }
    @Override
    protected String doInBackground(String... voids) {
        // String login_url= "http://192.168.43.221/retrieve1.php";
        String login_url= "http://192.168.43.201/food/livetrackupload.php";
        if(true){
            try {
                 String orderid= voids[0];
                 String lan=voids[1];
                 String lon=voids[2];
                 String dlid=voids[3];
                //  mobile=voids[1];
                URL url=new URL(login_url);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                 OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                 String post_data= URLEncoder.encode("orderid","UTF-8")+"="+URLEncoder.encode(orderid,"UTF-8")+"&"
                      +URLEncoder.encode("lat","UTF-8")+"="+URLEncoder.encode(lan,"UTF-8")+"&"
                         +URLEncoder.encode("lon","UTF-8")+"="+URLEncoder.encode(lan,"UTF-8")+"&"
                         +URLEncoder.encode("dlid","UTF-8")+"="+URLEncoder.encode(lan,"UTF-8");
                 bufferedWriter.write(post_data);
                 bufferedWriter.flush();
                 bufferedWriter.close();
                outputStream.close();
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

    }

    @Override
    protected void onPostExecute(String result) {

        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}

