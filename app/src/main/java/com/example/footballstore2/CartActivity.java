package com.example.footballstore2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class CartActivity extends AppCompatActivity {

    ListView listView;
    TextView totalTxt;
    Button btnCheckout;

    CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        listView = findViewById(R.id.listViewCart);
        totalTxt = findViewById(R.id.txtCartTotal);
        btnCheckout = findViewById(R.id.btnCheckout);

        adapter = new CartAdapter(this, CartData.items, this::updateTotal);
        listView.setAdapter(adapter);

        updateTotal();

        btnCheckout.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
            startActivity(intent);
        });
    }

    private void updateTotal() {
        double total = 0;

        for (CartItem item : CartData.items) {
            total += item.getPrice() * item.getQuantity();
        }

        totalTxt.setText("Total: $" + total);
    }
}
