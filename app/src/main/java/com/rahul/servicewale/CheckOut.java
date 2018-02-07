package com.rahul.servicewale;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CheckOut extends AppCompatActivity {


    Intent intent;
    TextView orderDetails,totalCost;
    String total="",data="";
    EditText address;
    String eAddress;
    Button checkOut;
    JSONParser jsonParser;
    String  url="http://www.antireservation.co.in/rahul/service.php";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        intent=getIntent();


        jsonParser = new JSONParser();
        progressDialog = new ProgressDialog(this);
        orderDetails = (TextView) findViewById(R.id.order_deatils);
        totalCost =(TextView) findViewById(R.id.total_cost);
        address = (EditText) findViewById(R.id.address);
        checkOut = (Button) findViewById(R.id.checkout);
        data=intent.getStringExtra("data");
        orderDetails.setText(data);
        total=""+intent.getIntExtra("total",0);
         Log.d("total",total);
        totalCost.setText("Total Cost: "+total);


        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                eAddress = address.getText().toString();
                if(eAddress.isEmpty()){
                    Toast.makeText(CheckOut.this,"Plz Enter The Address",Toast.LENGTH_LONG).show();
                }else{

                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(CheckOut.this);
                    } else {
                        builder = new AlertDialog.Builder(CheckOut.this);
                    }
                    builder.setTitle("Confirm order ?")
                            .setMessage("Are you sure you want to confirm Your Order")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    new Jadu().execute();

                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();

                }
            }
        });

    }

    public class Jadu extends AsyncTask<String,String,String> {


        @Override
        protected String doInBackground(String... params) {
            List<NameValuePair> parms = new ArrayList<NameValuePair>();
            parms.add(new BasicNameValuePair("name","ram"));
            parms.add(new BasicNameValuePair("mob_no","123"));
            parms.add(new BasicNameValuePair("items",data));
            parms.add(new BasicNameValuePair("total_price",total));
            parms.add(new BasicNameValuePair("address",eAddress));

            JSONObject jsonObject = jsonParser.makeHttpRequest(url,"POST",parms);

            Log.d("CREATE REPONSE", jsonObject.toString()+data+eAddress);

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

            progressDialog.dismiss();
            Toast.makeText(CheckOut.this,"Your ordr is Succesfully Booked",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);


        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            progressDialog = new ProgressDialog(CheckOut.this);

            progressDialog.setMessage("Loading");
            progressDialog.show();
        }


    }
}
