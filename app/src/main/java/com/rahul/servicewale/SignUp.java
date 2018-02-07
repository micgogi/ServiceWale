package com.rahul.servicewale;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
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

public class SignUp extends AppCompatActivity {


    private EditText name,email,password,mobNo;
    String ename,eemail,epassword,emobno;
    private Button signup;
    private TextView linkLogin;
    JSONParser jsonParser;
    String  url="http://www.antireservation.co.in/rahul/rahul_reg1.php";
    ProgressDialog progressDialog;
    boolean valid=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name = (EditText) findViewById(R.id.input_name);
        email = (EditText) findViewById(R.id.input_emailup);
        password = (EditText) findViewById(R.id.input_passwordup);
        mobNo = (EditText) findViewById(R.id.input_mobup);
        signup = (Button) findViewById(R.id.btn_signUp);
        linkLogin = (TextView) findViewById(R.id.link_login);

        jsonParser = new JSONParser();
        progressDialog = new ProgressDialog(this);


        linkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });




        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkValidation();
                if(valid) {
                    new Jadu().execute();

                }
            }
        });






    }



    public void checkValidation(){



        ename = name.getText().toString();
        eemail = email.getText().toString();
        epassword = password.getText().toString();
        emobno = mobNo.getText().toString();




        if(ename.isEmpty()){
            Toast.makeText(this,"name cannot be empty plz enter your name",Toast.LENGTH_LONG).show();
            valid = false;
        }else if(eemail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(eemail).matches()){
            Toast.makeText(this,"Plz enter your email address in correct formate",Toast.LENGTH_LONG).show();
            valid = false;
        } else if(!(emobno.charAt(0)== '7' || emobno.charAt(0)=='8' || emobno.charAt(0)== '9')||emobno.length()<10||emobno.length()>10){
            Toast.makeText(this,"Mob no is in wrong formate",Toast.LENGTH_LONG).show();
            valid=false;

        }else if(epassword.length()<4&&epassword.length()>10){
            Toast.makeText(this,"password should be in the range of 4-10 char", Toast.LENGTH_LONG).show();
            valid=false;
        } else{
            valid=true;
        }

    }

    public class Jadu extends AsyncTask<String,String,String> {


        @Override
        protected String doInBackground(String... params) {
            List<NameValuePair> parms = new ArrayList<NameValuePair>();
            parms.add(new BasicNameValuePair("name",ename));
            parms.add(new BasicNameValuePair("email",eemail));
            parms.add(new BasicNameValuePair("password",epassword));
            parms.add(new BasicNameValuePair("mobileno",emobno));

            JSONObject jsonObject = jsonParser.makeHttpRequest(url,"POST",parms);

            Log.d("CREATE REPONSE", jsonObject.toString()+ename+eemail);

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

            progressDialog.dismiss();
            Toast.makeText(SignUp.this, "SuccesFully Regiesterd", Toast.LENGTH_LONG).show();


        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            progressDialog = new ProgressDialog(SignUp.this);

            progressDialog.setMessage("Loading");
            progressDialog.show();
        }


    }

}
