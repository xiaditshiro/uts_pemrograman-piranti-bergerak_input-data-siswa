package com.example.utspemrograman_piranti_bergerak;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin;
    private SharedPrefManager prefManager;

    // Data user statis sesuai contoh Projek 4
    private static final String[][] users = {
            {"admin", "admin123", "Administrator"},
            {"siswa", "siswa123", "Nama Siswa"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        prefManager = SharedPrefManager.getInstance(this);

        // Jika sudah login, langsung ke MainActivity
        if (prefManager.isLoggedIn()) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> processLogin());
    }

    private void processLogin() {
        String user = etUsername.getText().toString();
        String pass = etPassword.getText().toString();

        if (user.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Harap isi semua field", Toast.LENGTH_SHORT).show();
            return;
        }

        String displayName = null;
        for (String[] u : users) {
            if (u[0].equals(user) && u[1].equals(pass)) {
                displayName = u[2];
                break;
            }
        }

        if (displayName != null) {
            prefManager.saveLoginSession(user, displayName);
            Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Username atau Password salah!", Toast.LENGTH_SHORT).show();
        }
    }
}
