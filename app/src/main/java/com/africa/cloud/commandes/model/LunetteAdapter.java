package com.africa.cloud.commandes.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.africa.cloud.commandes.LunetteFragment;
import com.africa.cloud.commandes.LunetteTypeActivity;
import com.africa.cloud.commandes.R;
import com.africa.cloud.commandes.activity.ProduitDetailActivity;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.*;

public class LunetteAdapter extends RecyclerView.Adapter<LunetteAdapter.MyViewHolder> implements View.OnClickListener{


    Lunette lunette;
    private List<Lunette> lunetteList;
    public FragmentManager f_manager;
    //Lunette lunette;
    Context mContext;
    @Override
    public void onClick(View v) {


    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView types, description, prix;
        private ImageView image;
        Context mContext;
        List<Lunette> lunetteList = new ArrayList<>();




        public MyViewHolder(final View itemView, final List<Lunette> lunetteList, final Context mContext) {
            super(itemView);
            this.lunetteList = lunetteList;
            this.mContext = mContext;

            types = (TextView) itemView.findViewById(R.id.lunette_item_nom);
            description = (TextView) itemView.findViewById(R.id.lunette_item_description);
            prix = (TextView) itemView.findViewById(R.id.lunette_item_prix);
            image =(ImageView) itemView.findViewById(R.id.lunette_item_image);





            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position =getAdapterPosition();

                    Lunette lunette = lunetteList.get(position);
                  Intent details = new Intent(mContext, ProduitDetailActivity.class); //Initialisation de l'Intent pour la redirection vers l'activité contenant le fragment details

                   //*********************************************************************************************************
                   //envoi des données dans l'activité ProduitDetailActivité
                   //**********************************************************************************************************

                    details.putExtra("image", lunette.getPhoto()); //envoi de l'image
                    details.putExtra("types", lunette.getTypes() ); //envoi des types de produit
                    details.putExtra("description", lunette.getDescription()); //envoi de la description du produit
                    details.putExtra("prix",Integer.toString(lunette.getPrix() )); //envoi du prix


                    //************************************************************************************************************************************
                    //Toast de text pour la reccuperation des données qu'on tente d'envoyer que je vais juste commenté
                    //*************************************************************************************************************************************

                  //  Toast.makeText(mContext, ""+lunette.getTypes()+" "+lunette.getDescription()+" "+lunette.getPrix(), Toast.LENGTH_SHORT).show();


                    //*******************************************************************************************************************************************
                    //Lancement et le demarrage de l'activité ProduitDetailActivity avec les données envoyer qu'on reccupera au niveau de ProduitDetailActivity
                    //********************************************************************************************************************************************
                    mContext.startActivity(details);




                }
            });


        }


        public ImageView getImage() {
            return image;
        }

        public void setImage(ImageView image) {
            this.image = image;
        }
    }


    //communication et passage des données d'un fragment à un autre

    



    public LunetteAdapter(List<Lunette> lunetteList,Context mContext) {
        this.lunetteList = lunetteList;
        this.mContext = mContext;

    }




    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lunette_item, parent, false);


        return new MyViewHolder(itemView,lunetteList, mContext);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Lunette lunette = lunetteList.get(position);
        String nom =lunette.getTypes();
        String image = lunette.getPhoto();
        int prix = lunette.getPrix();

        holder.types.setText(lunette.getTypes());
        holder.description.setText(lunette.getDescription());
        holder.prix.setText(Integer.toString(lunette.getPrix())+" F-CFA");
        //holder.image.setImageResource(parseInt(lunetteList.get(position).getPhoto()));
        Glide.with(mContext).load(image).into(holder.image);




    }


    @Override
    public int getItemCount() {
        return lunetteList.size();
    }




}
