package com.example.footballstore2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText editUsername;
    EditText editPass;
    Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       editUsername = findViewById(R.id.etUsername);
       editPass = findViewById(R.id.etPassword);
       btLogin = findViewById(R.id.btnLogin);

        btLogin.setOnClickListener(v -> {
            String username= editUsername.getText().toString().trim();
            String pass = editPass.getText().toString().trim();
            if (username.isEmpty()){
                editUsername.setError("Username is required");
            }
            else {
                if (pass.isEmpty()){
                    editPass.setError("Password is required");
                }
                else {
                    Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                }
            }

        });

    }

}