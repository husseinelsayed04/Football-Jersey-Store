package com.example.footballstore2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    TextView tvName;
    Button btnExplore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        tvName = findViewById(R.id.tvName);
        btnExplore = findViewById(R.id.btnExplore);

        String username = getIntent().getStringExtra("username");

        if (username != null && !username.isEmpty()) {
            tvName.setText(username + " \uD83D\uDC4B");
        }

        btnExplore.setOnClickListener(v -> {
            Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
