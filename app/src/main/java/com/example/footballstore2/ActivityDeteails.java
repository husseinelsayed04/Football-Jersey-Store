package com.example.footballstore2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityDeteails extends AppCompatActivity {

    ImageView img;
    TextView name, price, description, totalTxt;
    Button btn;
    RadioGroup group;
    CheckBox checkNameNumber, checkUCL, checkGift;

    double basePrice = 0;
    String productName;
    int productImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deteails);

        img = findViewById(R.id.imgDetails);
        name = findViewById(R.id.txtNameDetails);
        price = findViewById(R.id.txtPriceDetails);
        description = findViewById(R.id.txtDescription);
        totalTxt = findViewById(R.id.txtTotal);

        btn = findViewById(R.id.btnAddCart);
        group = findViewById(R.id.radioGroup);

        checkNameNumber = findViewById(R.id.checkNameNumber);
        checkUCL = findViewById(R.id.checkUCL);
        checkGift = findViewById(R.id.checkGift);

        productName = getIntent().getStringExtra("productName");
        String productPrice = getIntent().getStringExtra("price");
        String productDescription = getIntent().getStringExtra("description");
        productImage = getIntent().getIntExtra("image", 0);

        img.setImageResource(productImage);
        name.setText(productName);
        price.setText(productPrice);
        description.setText(productDescription);

        basePrice = Double.parseDouble(productPrice.replace("$", ""));

        Runnable calc = () -> {
            double total = basePrice;

            if (checkNameNumber.isChecked()) total += 15;
            if (checkUCL.isChecked()) total += 3;
            if (checkGift.isChecked()) total += 5;

            totalTxt.setText("Total: $" + total);
        };

        checkNameNumber.setOnCheckedChangeListener((b, v) -> calc.run());
        checkUCL.setOnCheckedChangeListener((b, v) -> calc.run());
        checkGift.setOnCheckedChangeListener((b, v) -> calc.run());

        calc.run();

        btn.setOnClickListener(v -> {

            int id = group.getCheckedRadioButtonId();

            if (id == -1) {
                Toast.makeText(this, "Select size", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton selected = findViewById(id);

            double total = basePrice;

            if (checkNameNumber.isChecked()) total += 15;
            if (checkUCL.isChecked()) total += 3;
            if (checkGift.isChecked()) total += 5;

            String size = selected.getText().toString();

            boolean found = false;

            for (CartItem item : CartData.items) {
                if (item.getName().equals(productName) && item.getSize().equals(size)) {
                    item.setQuantity(item.getQuantity() + 1);
                    found = true;
                    break;
                }
            }

            if (!found) {
                CartData.items.add(new CartItem(productName, total, productImage, size));
            }

            showCustomDialog(size, total);
        });
    }

    private void showCustomDialog(String size, double total) {

        View view = getLayoutInflater().inflate(R.layout.activity_dialog_added_to_cart, null);

        TextView txtDesc = view.findViewById(R.id.txtDialogDesc);
        Button btnViewCart = view.findViewById(R.id.btnViewCart);
        Button btnContinue = view.findViewById(R.id.btnContinue);

        txtDesc.setText(
                "Product: " + productName +
                        "\nSize: " + size +
                        "\nPrice: $" + total
        );

        android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create();

        btnViewCart.setOnClickListener(v -> {
            startActivity(new Intent(this, CartActivity.class));
            dialog.dismiss();
        });

        btnContinue.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }
}
