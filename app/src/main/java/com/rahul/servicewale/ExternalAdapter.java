package com.rahul.servicewale;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;



/**
 * Created by rahul on 7/13/2017.
 */

public class ExternalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


        private static final int TYPE_IMAGE = 0;
        private static final int TYPE_GROUP = 1;
        private Context context;
    public String h1;
        Intent intent;
       int k=0;
        ArrayList<ExternalData> eventsList = new ArrayList<>();

    DBHelper dbHelper ;

        public class MyViewHolder extends RecyclerView.ViewHolder  {
            public TextView title;
            public TextView description;
            public TextView plus,minus,number;

            int i;
            ArrayList<ExternalData> eventsList = new ArrayList<>();
            Context context;
            public MyViewHolder(View view, final Context context, ArrayList<ExternalData> listData) {
                super(view);
                this.eventsList = listData;
                int position = getAdapterPosition();
                this.context = context;
                dbHelper = new DBHelper(context);
                title = (TextView) view.findViewById(R.id.title);
                description = (TextView) view.findViewById(R.id.des);
                plus=(TextView) view.findViewById(R.id.textView6);
                number=(TextView) view.findViewById(R.id.textView7);
                minus = (TextView) view.findViewById(R.id.textView8);
                ExternalData externalData = new ExternalData();

                plus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();

                        i=i+1;
                        if(i<=0){
                            i=0;
                            number.setText("0");
                        }else{
                            String s=""+i;
                            number.setText(s);
                        }
                       k=i;
                    dbHelper.updateContact(position+1,i);


                    }
                });

                minus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                         int position = getAdapterPosition();

                        i=i-1;
                        if(i<=0){
                            i=0;
                            number.setText("0");
                        }else{
                            String s=""+i;
                            number.setText(s);
                        }
                        k=i;
                        dbHelper.updateContact(position+1,i);
                    }
                });





            }


        }





        public ExternalAdapter(ArrayList<ExternalData> listData,Context context) {
            this.eventsList = listData;
            this.context = context;


        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

           View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.external_list,parent,false);
              v.setEnabled(false);
            return new MyViewHolder(v,context,eventsList);

        }





        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int viewType){






            MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
            ExternalData events = eventsList.get(viewType);
            myViewHolder.title.setText(events.getTitle());
            myViewHolder.description.setText(""+events.getDes());
            dbHelper.addItems(events.getTitle(),events.getDes(),k);








        }

        @Override
        public int getItemCount() {
            return eventsList.size();
        }
    }

