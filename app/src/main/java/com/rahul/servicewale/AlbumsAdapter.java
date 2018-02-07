package com.rahul.servicewale;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;


public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder>  {

    private Context mContext;
    List<Album> albumList ;



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, count;
        public ImageView thumbnail, overflow;
        List<Album> albumList;
        Context context;

        public MyViewHolder(View view, final Context context, List<Album> albumList) {
            super(view);
            view.setOnClickListener(this);
            this.albumList = albumList;
            this.context = context;
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);


        }

        @Override
        public void onClick(View v) {
            final Intent intent;
            MainActivity m = (MainActivity) context;
            int position = getAdapterPosition();
            Album album = this.albumList.get(position);
            if(position==0){

                intent = new Intent(context,CarDeatailingActivity.class);


            }else if (position==5){

             intent = new Intent(context,LaundryDetailing.class);



            }else {


                 intent = new Intent(context, ItemClickActivity.class);
                intent.putExtra("image", album.getThumbnail());

            }
            this.context.startActivity(intent);


        }
        public  void showACtivity(ImageView thumbnail) {
            final Intent intent;
            int position =  getAdapterPosition();
                    Album album = this.albumList.get(position);
            if(position==0){
                intent = new Intent(context,CarDeatailingActivity.class);


            }else if (position==5){

                intent = new Intent(context,LaundryDetailing.class);



            }else{


             intent = new Intent(context,ItemClickActivity.class);
            intent.putExtra("image",album.getThumbnail());

            }
            this.context.startActivity(intent);
        }
    }


    public AlbumsAdapter(Context mContext, List<Album> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView,mContext,  albumList);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Album album = albumList.get(position);
        holder.title.setText(album.getName());
        holder.count.setText(album.getNumOfSongs() + "");

        // loading album cover using Glide library
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               holder.showACtivity(holder.thumbnail);

            }
        });




    }







    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
