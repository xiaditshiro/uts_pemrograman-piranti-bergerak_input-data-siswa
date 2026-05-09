package com.example.utspemrograman_piranti_bergerak;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SiswaDB";
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_SISWA = "siswa";
    private static final String COL_ID = "id";
    private static final String COL_NAMA = "nama";
    private static final String COL_NIM = "nim";
    private static final String COL_NILAI = "nilai";
    private static final String COL_FOTO = "foto";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_SISWA + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAMA + " TEXT, " +
                COL_NIM + " TEXT, " +
                COL_NILAI + " INTEGER, " +
                COL_FOTO + " TEXT)";
        db.execSQL(createTable);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SISWA);
        onCreate(db);
    }

    // Simpan Data
    public boolean insertData(String nama, String nim, int nilai, String foto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAMA, nama);
        values.put(COL_NIM, nim);
        values.put(COL_NILAI, nilai);
        values.put(COL_FOTO, foto);
        long result = db.insert(TABLE_SISWA, null, values);
        return result != -1;
    }

    // Ambil Semua Data
    public ArrayList<HashMap<String, String>> getAllSiswa() {
        ArrayList<HashMap<String, String>> listSiswa = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SISWA + " ORDER BY id DESC", null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(cursor.getColumnIndexOrThrow(COL_ID)));
                map.put("nama", cursor.getString(cursor.getColumnIndexOrThrow(COL_NAMA)));
                map.put("nim", cursor.getString(cursor.getColumnIndexOrThrow(COL_NIM)));
                map.put("nilai", cursor.getString(cursor.getColumnIndexOrThrow(COL_NILAI)));
                map.put("foto", cursor.getString(cursor.getColumnIndexOrThrow(COL_FOTO)));
                listSiswa.add(map);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return listSiswa;
    }


    // Hapus Data
    public void deleteData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SISWA, COL_ID + "=?", new String[]{String.valueOf(id)});
    }

    // Update Data
    public boolean updateData(int id, String nama, String nim, int nilai, String foto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAMA, nama);
        values.put(COL_NIM, nim);
        values.put(COL_NILAI, nilai);
        values.put(COL_FOTO, foto);
        int result = db.update(TABLE_SISWA, values, COL_ID + "=?", new String[]{String.valueOf(id)});
        return result > 0;
    }
}
