package com.mj.deliveryboyapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.kalert.KAlertDialog;

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

public class isalreadybooked extends AsyncTask<String,Void,String> {
    Context context;
    public int position;
    String ammount,items,payment,dltime,username,add,num,orderid,resadd,resnum,resid,resname,lat,lon;
    public isalreadybooked(Context ctx,int position)
    {
        context=ctx;
        this.position=position;


    }
    @Override
    protected String doInBackground(String... voids) {
        // String login_url= "http://192.168.43.221/retrieve1.php";
        String login_url= "http://192.168.43.201/food/isalreadyaccepted.php";
        if(true){
            try {
                orderid= voids[0];
               String deliveryboyname =voids[1];
                ammount=voids[2];
                payment=voids[3];
                dltime=voids[4];
                username=voids[5];
                add=voids[6];
                num=voids[7];
                resadd=voids[8];
                resnum=voids[9];
                resid=voids[10];
                resname=voids[11];
                lat=voids[12];
                lon=voids[13];



                //  mobile=voids[1];
                URL url=new URL(login_url);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                 OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                 String post_data= URLEncoder.encode("orderid","UTF-8")+"="+URLEncoder.encode(orderid,"UTF-8")
                         +"&"+URLEncoder.encode("dbname","UTF-8")+"="+URLEncoder.encode(deliveryboyname,"UTF-8");
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
        // alertDialog=new AlertDialog.Builder(context).create();
        //alertDialog.setTitle("Login Status");

    }

    @Override
    protected void onPostExecute(String result) {

       if(result.equals("1"))
        {
            new KAlertDialog(context, KAlertDialog.WARNING_TYPE)
                    .setTitleText("Sorry !")
                    .setContentText("This order is taken by someone!")
                    .setConfirmText("Remove it!")
                    .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                        @Override
                        public void onClick(KAlertDialog sDialog) {
                            //
                            sDialog.dismissWithAnimation();
                             orders.remove(position);

                        }
                    })
                    .show();

        }
        else
        {
            Intent intent =new Intent(context,Orderfinaldetails.class);
            intent.putExtra("orderid",orderid);
            intent.putExtra("resname",resname);
            intent.putExtra("resadd",resadd);
            intent.putExtra("resnum",resnum);
            intent.putExtra("resid",resid);
            intent.putExtra("type",payment);
            intent.putExtra("dltime",dltime);

            intent.putExtra("name",username);
            intent.putExtra("add",add);
            intent.putExtra("mobile",num);
            intent.putExtra("lat",lat);
            intent.putExtra("lon",lon);
            intent.putExtra("ammount",ammount);

            context.startActivity(intent);


        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}

