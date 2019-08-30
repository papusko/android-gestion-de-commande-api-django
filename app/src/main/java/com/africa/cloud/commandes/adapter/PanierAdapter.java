package com.africa.cloud.commandes.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.africa.cloud.commandes.R;
import com.africa.cloud.commandes.activity.ProduitDetailActivity;
import com.africa.cloud.commandes.model.Carte;
import com.africa.cloud.commandes.model.Lunette;
import com.africa.cloud.commandes.model.LunetteAdapter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class PanierAdapter extends RecyclerView.Adapter<PanierAdapter.MyViewHolder> implements View.OnClickListener {


    Carte carte;
    private List<Carte> carteList;
    //Lunette lunette;
    Context mContext;
    @Override
    public void onClick(View v) {


    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView types, description, prix;
        private ImageView image;
        Context mContext;
        List<Carte> carteList = new ArrayList<>();




        public MyViewHolder(final View itemView, final List<Carte> carte, final Context mContext) {
            super(itemView);
            this.carteList = carteList;
            this.mContext = mContext;

            types = (TextView) itemView.findViewById(R.id.panier_types);
            description = (TextView) itemView.findViewById(R.id.panier_description);
            prix = (TextView) itemView.findViewById(R.id.panier_prix);
            image =(ImageView) itemView.findViewById(R.id.panier_image);








        }


        public ImageView getImage() {
            return image;
        }

        public void setImage(ImageView image) {
            this.image = image;
        }
    }


    //communication et passage des données d'un fragment à un autre





    public PanierAdapter(List<Carte> carteList,Context mContext) {
        this.carteList = carteList;
        this.mContext = mContext;

    }




    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.panier_items, parent, false);


        return new MyViewHolder(itemView,carteList, mContext);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        Carte carte = carteList.get(position);
        String image = carte.getPhoto();


        holder.types.setText(carte.getTypes());
        holder.description.setText(carte.getDescription());
        holder.prix.setText(Integer.toString(carte.getPrix())+" F-CFA");
        //holder.image.setImageResource(parseInt(lunetteList.get(position).getPhoto()));
        Glide.with(mContext).load(image).into(holder.image);



    }


    @Override
    public int getItemCount() {
        return carteList.size();
    }




}
