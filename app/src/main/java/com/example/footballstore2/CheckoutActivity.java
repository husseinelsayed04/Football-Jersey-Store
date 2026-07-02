package com.example.footballstore2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class CheckoutActivity extends AppCompatActivity {

    RadioGroup paymentGroup;
    Switch switchDelivery;
    Button btnPlaceOrder;

    ImageView img;
    TextView txtName, txtQty, txtTotal;

    double deliveryFee = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        paymentGroup = findViewById(R.id.paymentGroup);
        switchDelivery = findViewById(R.id.switchDelivery);
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);

        img = findViewById(R.id.imgCheckout);
        txtName = findViewById(R.id.txtCheckoutName);
        txtQty = findViewById(R.id.txtCheckoutQty);
        txtTotal = findViewById(R.id.txtCheckoutTotal);

        loadData();

        switchDelivery.setOnCheckedChangeListener((b, v) -> loadData());

        btnPlaceOrder.setOnClickListener(v -> showOrderDialog());
    }

    private void loadData() {

        if (CartData.items.isEmpty()) {
            txtName.setText("No items");
            txtQty.setText("");
            txtTotal.setText("");
            img.setImageDrawable(null);
            return;
        }

        double total = 0;

        for (CartItem i : CartData.items) {
            total += i.getPrice() * i.getQuantity();
        }

        if (switchDelivery.isChecked()) {
            total += deliveryFee;
        }

        CartItem item = CartData.items.get(CartData.items.size() - 1);

        img.setImageResource(item.getImage());
        txtName.setText(item.getName());
        txtQty.setText("Qty: " + item.getQuantity());
        txtTotal.setText("Total: $" + total);
    }

    private void showOrderDialog() {

        int id = paymentGroup.getCheckedRadioButtonId();

        if (id == -1) {
            Toast.makeText(this, "Select Payment Method", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton rb = findViewById(id);
        String payment = rb.getText().toString();

        String delivery;

        if (switchDelivery.isChecked()) {
            delivery = "Home Delivery";
        } else {
            delivery = "Pickup";
        }

        double total = 0;

        String summary = "";

        for (CartItem item : CartData.items) {

            double itemTotal = item.getPrice() * item.getQuantity();
            total += itemTotal;

            summary = summary
                    + item.getName()
                    + "\nQty: " + item.getQuantity()
                    + " | Size: " + item.getSize()
                    + "\n$" + itemTotal
                    + "\n\n";
        }

        if (switchDelivery.isChecked()) {
            total += deliveryFee;
        }

        summary = summary + "Payment: " + payment + "\n";
        summary = summary + "Delivery: " + delivery + "\n";
        summary = summary + "Total: $" + total;

        View view = getLayoutInflater().inflate(R.layout.activity_dialog_checkout_summary, null);

        TextView txtDesc = view.findViewById(R.id.txtDesc);
        Button btnConfirm = view.findViewById(R.id.btnConfirm);
        Button btnContinue = view.findViewById(R.id.btnContinue);

        txtDesc.setText(summary);

        android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create();

        dialog.show();

        btnConfirm.setOnClickListener(v -> {

            CartData.items.clear();

            Toast.makeText(this, "Order Placed Successfully", Toast.LENGTH_SHORT).show();

            dialog.dismiss();

            Intent intent = new Intent(CheckoutActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        btnContinue.setOnClickListener(v -> dialog.dismiss());
    }
}
