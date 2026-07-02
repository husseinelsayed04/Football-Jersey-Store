package com.example.footballstore2;

import android.content.Context;
import android.content.Intent;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;

public class JerseyAdapter extends ArrayAdapter<Jersey> {

    Context context;
    ArrayList<Jersey> jerseys;

    public JerseyAdapter(Context context, ArrayList<Jersey> jerseys) {
        super(context, 0, jerseys);
        this.context = context;
        this.jerseys = jerseys;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.activity_jersey_item, parent, false);
        }

        ImageView img = convertView.findViewById(R.id.imgJersey);
        TextView name = convertView.findViewById(R.id.txtName);
        TextView price = convertView.findViewById(R.id.txtPrice);
        Button btn = convertView.findViewById(R.id.btnAdd);

        Jersey jersey = jerseys.get(position);

        img.setImageResource(jersey.getImage());
        name.setText(jersey.getName());
        price.setText(jersey.getPrice());

        btn.setOnClickListener(v -> {

            Intent intent = new Intent(context, ActivityDeteails.class);

            intent.putExtra("productName", jersey.getName());
            intent.putExtra("price", jersey.getPrice());
            intent.putExtra("image", jersey.getImage());
            intent.putExtra("description", jersey.getDescription());

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(intent);
        });

        return convertView;
    }
}
