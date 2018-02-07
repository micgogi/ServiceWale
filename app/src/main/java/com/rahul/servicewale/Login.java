package com.rahul.servicewale;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Login extends AppCompatActivity {
    String myJSON;
    public static final String TAG_RESULTS = "result";

    private static final String TAG_Phone = "mobno";//here username and password we are getting from the server...
    private static final String TAG_password = "password";
    private static final String TAG_Email="email";
    private static final String TAG_Name="name";
    JSONArray peoples = null;
    ArrayList<HashMap<String, String>> personList;
    private EditText et_email;
    private EditText etpass;
    private Button btnlogin;
    private TextView link_login;
    String email,password,name,mobno;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_email = (EditText) findViewById(R.id.input_email);
        etpass = (EditText) findViewById(R.id.input_password);
        btnlogin = (Button) findViewById(R.id.btn_login);
        link_login = (TextView) findViewById(R.id.link_signup);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });


        link_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,SignUp.class);
                startActivity(intent);
            }
        });
    }






    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);
            if (et_email.getText().toString() != null && etpass.getText().toString() != null) {

                for (int i = 0; i < peoples.length(); i++) {
                    JSONObject c = peoples.getJSONObject(i);
                    email = c.getString("email");
                    password = c.getString("password");
                    name=c.getString("name");
                    mobno=c.getString("mobno");
                    id=c.getInt("id");

                    Log.d("jkl",email+password+name+mobno);

                    if (et_email.getText().toString().equals(email) && etpass.getText().toString().equals(password))
                    {
                        Intent k=new Intent(Login.this,MainActivity.class);
                        k.putExtra("name",name);
                        k.putExtra("email",email);
                        k.putExtra("mobno",mobno);
                        k.putExtra("id",id);
                        startActivity(k);
                    }



                }

            }else
            {
                Toast.makeText(Login.this,"Your user name is not correct",Toast.LENGTH_LONG).show();
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }}

    public void getData() {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost("http://www.antireservation.co.in/rahul/rahul_login.php");

                // Depends on your web service
                httppost.setHeader("Content-type", "application/json");

                InputStream inputStream = null;
                String result = null;
                try {
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();

                    inputStream = entity.getContent();
                    // json is UTF-8 by default
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (Exception e) {
                    // Oops
                } finally {
                    try {
                        if (inputStream != null) inputStream.close();
                    } catch (Exception squish) {
                    }
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result) {
                myJSON = result;
                showList();
            }

        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
    }
}
