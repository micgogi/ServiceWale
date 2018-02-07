package com.rahul.servicewale;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ItemClickActivity extends AppCompatActivity  {

Button callUs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_click);
        callUs = (Button) findViewById(R.id.call_us);
        callUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:9670490926"));
                startActivity(intent);


            }
        });
        Intent intent = getIntent();
        ImageView imageView = (ImageView) findViewById(R.id.c_top_image);
        imageView.setImageResource(intent.getIntExtra("image",00));

    }
}
