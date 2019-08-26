package com.africa.cloud.commandes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.africa.cloud.commandes.model.Lunette;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class LunetteTypeActivity extends AppCompatActivity {

    List<Lunette> lunetteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunette_type);

  /*      Intent intent = getIntent();
        String types = intent.getStringExtra("types");
        String description = intent.getStringExtra("description");
        int prix = intent.getIntExtra("prix", 0);

        TextView type = (TextView) findViewById(R.id.lunette_item_nom);
        type.setText(types);

        TextView desc = (TextView) findViewById(R.id.lunette_item_description);
        desc.setText(description);

 //       TextView px = (TextView) findViewById(R.id.lunette_item_prix);
   //     px.setText(Integer.parseInt(String.valueOf(prix)));

*/

    }
}