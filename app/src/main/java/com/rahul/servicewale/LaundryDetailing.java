package com.rahul.servicewale;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class LaundryDetailing extends AppCompatActivity {

    public String k;
    public static Context mcontext;
    private ArrayList<ExternalData> eventsList = new ArrayList<>();
    Button cnt;
    SQLiteDatabase database;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry_detailing);
        cnt = (Button) findViewById(R.id.cnt);

        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();
        database.delete(DBHelper.TABLE_ITEMS,null,null);



        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view1);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(LaundryDetailing.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        final ExternalAdapter adapter = new ExternalAdapter(eventsList, LaundryDetailing.this);
        recyclerView.setAdapter(adapter);
        prepareData();

        cnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LaundryDetailing.this, CartAct.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Leaving Page")
                .setMessage("Are you sure you want to leave this Page?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }


    public void prepareData() {

        ExternalData externalData = new ExternalData("T-Shirts", 10);
        eventsList.add(externalData);

        externalData = new ExternalData("T-Shirts", 10);
        eventsList.add(externalData);
        externalData = new ExternalData("Shirts   ", 10);
        eventsList.add(externalData);
        externalData = new ExternalData("Trouser", 15);
        eventsList.add(externalData);
        externalData = new ExternalData("T-Shirts", 10);
        eventsList.add(externalData);
        externalData = new ExternalData("T-Shirts", 10);
        eventsList.add(externalData);

    }

}
