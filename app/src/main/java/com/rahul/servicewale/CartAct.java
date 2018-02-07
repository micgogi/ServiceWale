package com.rahul.servicewale;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class CartAct extends AppCompatActivity {


    TextView t2, t3, sub_total, deliever, tl;
    String data = "";
    int total, subTotal, del;
    boolean valid = false;
    JSONParser jsonParser;
    String url = "http://www.antireservation.co.in/rahul/rahul_reg1.php";
    ProgressDialog progressDialog;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        t2 = (TextView) findViewById(R.id.t2);
        t3 = (TextView) findViewById(R.id.t3);
        sub_total = (TextView) findViewById(R.id.sub_total);
        deliever = (TextView) findViewById(R.id.delievery);
        tl = (TextView) findViewById(R.id.Total);
        confirm =(Button) findViewById(R.id.button);


        jsonParser = new JSONParser();
        progressDialog = new ProgressDialog(this);

        DBHelper dbHelper = new DBHelper(this);
        List<ExternalData> contacts = dbHelper.getAllContacts();

        for (ExternalData cn : contacts) {
            String log = "Id: " + cn.getId() + " ,Name: " + cn.getTitle() + " ,No   : " + cn.getDes() + " ,no: " + cn.getNoOfItems();
            if (cn.getNoOfItems() > 0) {
                data = data + "  " + cn.getTitle() + "     " + cn.getDes() + "         " + cn.getNoOfItems() + System.getProperty("line.separator");
                t2.setText(data);
                int temp = cn.getNoOfItems() * cn.getDes();
                total = total + temp;
                valid = true;
            }
            if (valid) {
                sub_total.setText("SubTotal:                   " + total);
                deliever.setText("Delievery:                    " + "20");
                tl.setText("Total:                          " + (total + 20));
            }

            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CartAct.this,CheckOut.class);
                    intent.putExtra("data",data);
                    total=total+20;
                     Log.d("total",String.valueOf(total));
                    intent.putExtra("total",total);
                    startActivity(intent);
                }
            });

            Log.d("Name: ", log);
        }
    }



}



