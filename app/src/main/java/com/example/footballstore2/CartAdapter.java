package com.example.footballstore2;

import android.app.Activity;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;

public class CartAdapter extends ArrayAdapter<CartItem> {

    Activity context;
    ArrayList<CartItem> items;
    Runnable updateTotal;

    public CartAdapter(Activity context, ArrayList<CartItem> items, Runnable updateTotal) {
        super(context, R.layout.activity_cart_item, items);
        this.context = context;
        this.items = items;
        this.updateTotal = updateTotal;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = context.getLayoutInflater().inflate(R.layout.activity_cart_item, null, true);

        ImageView img = view.findViewById(R.id.imgItem);
        TextView name = view.findViewById(R.id.txtName);
        TextView size = view.findViewById(R.id.txtSize);
        TextView price = view.findViewById(R.id.txtPrice);
        TextView qty = view.findViewById(R.id.txtQty);
        Button plus = view.findViewById(R.id.btnPlus);
        Button minus = view.findViewById(R.id.btnMinus);

        CartItem item = items.get(position);

        img.setImageResource(item.getImage());
        name.setText(item.getName());
        size.setText("Size: " + item.getSize());
        price.setText("$" + item.getPrice());
        qty.setText(String.valueOf(item.getQuantity()));

        plus.setOnClickListener(v -> {
            item.setQuantity(item.getQuantity() + 1);
            qty.setText(String.valueOf(item.getQuantity()));
            updateTotal.run();
        });

        minus.setOnClickListener(v -> {
            if (item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
                qty.setText(String.valueOf(item.getQuantity()));
                updateTotal.run();
            }
        });

        return view;
    }
}
