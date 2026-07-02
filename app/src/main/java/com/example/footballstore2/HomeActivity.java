package com.example.footballstore2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;
    ImageView imgBarca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNav = findViewById(R.id.bottomNav);
        imgBarca = findViewById(R.id.imgBarca);

        imgBarca.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, activity_jersey_list.class);
            startActivity(intent);
        });

        bottomNav.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.nav_home) {

                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();

            } else if (id == R.id.nav_category) {

                Toast.makeText(this, "Category", Toast.LENGTH_SHORT).show();

            } else if (id == R.id.nav_cart) {

                Intent intent = new Intent(HomeActivity.this, CartActivity.class);
                startActivity(intent);

            } else if (id == R.id.nav_profile) {

                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
            }

            return true;
        });
    }
}
