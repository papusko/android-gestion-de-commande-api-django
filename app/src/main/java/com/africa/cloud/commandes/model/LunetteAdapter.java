package com.africa.cloud.commandes.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.africa.cloud.commandes.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import static java.lang.Integer.*;

public class LunetteAdapter extends RecyclerView.Adapter<LunetteAdapter.MyViewHolder>{


    private List<Lunette> lunetteList;
    //Lunette lunette;
    Context mContext;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView types;
        private ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            types = (TextView) itemView.findViewById(R.id.lunette_item_nom);
            image =(ImageView) itemView.findViewById(R.id.lunette_item_image);


        }


        public ImageView getImage() {
            return image;
        }

        public void setImage(ImageView image) {
            this.image = image;
        }
    }



    public LunetteAdapter(List<Lunette> lunetteList,Context mContext) {
        this.lunetteList = lunetteList;
        this.mContext = mContext;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lunette_item, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Lunette lunette = lunetteList.get(position);
        String nom =lunette.getTypes();
        String image = lunette.getPhoto();

        holder.types.setText(lunette.getTypes());
        //holder.image.setImageResource(parseInt(lunetteList.get(position).getPhoto()));

        Picasso.with(mContext).load("http://192.168.1.211:8000/images/lunettes/lunette.jpg").into(holder.image);
    }


    @Override
    public int getItemCount() {
        return lunetteList.size();
    }


}
