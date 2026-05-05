package com.example.utspemrograman_piranti_bergerak;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private EditText etNama, etNim, etNilai;
    private Button btnSimpan, btnPilihFoto;
    private ImageView ivPreview;
    private RecyclerView rvSiswa;
    private DatabaseHelper dbHelper;
    private Uri selectedImageUri = null;

    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi UI
        etNama = findViewById(R.id.etNama);
        etNim = findViewById(R.id.etNim);
        etNilai = findViewById(R.id.etNilai);
        btnSimpan = findViewById(R.id.btnSimpan);
        btnPilihFoto = findViewById(R.id.btnPilihFoto);
        ivPreview = findViewById(R.id.ivPreview);
        rvSiswa = findViewById(R.id.rvSiswa);

        rvSiswa.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DatabaseHelper(this);

        refreshData();

        // Tombol Pilih Foto
        btnPilihFoto.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
        });

        // Tombol Simpan
        btnSimpan.setOnClickListener(v -> {
            String nama = etNama.getText().toString();
            String nim = etNim.getText().toString();
            String strNilai = etNilai.getText().toString();
            String fotoPath = (selectedImageUri != null) ? selectedImageUri.toString() : "";

            if (nama.isEmpty() || nim.isEmpty() || strNilai.isEmpty()) {
                Toast.makeText(MainActivity.this, "Semua data harus diisi!", Toast.LENGTH_SHORT).show();
                return;
            }

            int nilai = Integer.parseInt(strNilai);
            boolean isSuccess = dbHelper.insertData(nama, nim, nilai, fotoPath);

            if (isSuccess) {
                Toast.makeText(MainActivity.this, "Data berhasil disimpan!", Toast.LENGTH_SHORT).show();
                resetForm();
                refreshData();
            } else {
                Toast.makeText(MainActivity.this, "Gagal menyimpan data!", Toast.LENGTH_SHORT).show();
            }
        });

        // Tombol Logout (Menambahkan fungsi logout)
        findViewById(R.id.tvTitle).setOnClickListener(v -> {
            SharedPrefManager.getInstance(this).logout();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
        
        // Menampilkan sapaan user yang login
        String userName = SharedPrefManager.getInstance(this).getName();
        Toast.makeText(this, "Selamat Datang, " + userName, Toast.LENGTH_SHORT).show();
    }

    private void resetForm() {
        etNama.setText("");
        etNim.setText("");
        etNilai.setText("");
        ivPreview.setImageResource(android.R.drawable.ic_menu_camera);
        selectedImageUri = null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            ivPreview.setImageURI(selectedImageUri);
        }
    }

    private void refreshData() {
        ArrayList<HashMap<String, String>> dataSiswa = dbHelper.getAllSiswa();
        ArrayList<Siswa> listSiswa = new ArrayList<>();

        for (HashMap<String, String> map : dataSiswa) {
            listSiswa.add(new Siswa(
                    Integer.parseInt(map.get("id")),
                    map.get("nama"),
                    map.get("nim"),
                    Integer.parseInt(map.get("nilai")),
                    map.get("foto")
            ));
        }

        SiswaAdapter adapter = new SiswaAdapter(this, listSiswa, siswa -> {
            new android.app.AlertDialog.Builder(this)
                    .setTitle("Hapus Data")
                    .setMessage("Hapus data " + siswa.getNama() + "?")
                    .setPositiveButton("Ya", (dialog, which) -> {
                        dbHelper.deleteData(siswa.getId());
                        refreshData();
                    })
                    .setNegativeButton("Tidak", null)
                    .show();
        });

        rvSiswa.setAdapter(adapter);
    }
}