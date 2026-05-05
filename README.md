# Aplikasi Input Data Siswa (Tugas UTS)

Aplikasi Android untuk manajemen data siswa yang dibangun menggunakan Java dan Android Studio. Projek ini dibuat untuk memenuhi syarat UTS mata kuliah Pemrograman Piranti Bergerak.

## 🚀 Fitur Utama
Aplikasi ini menggabungkan beberapa materi utama yang diajarkan di kelas:

1.  **Pengelolaan Data (SQLite)**:
    *   Melakukan CRUD (Create, Read, Delete) data siswa.
    *   Data yang disimpan meliputi: Nama, NIM, Nilai, dan Foto.
2.  **Tampilan Modern (RecyclerView)**:
    *   Menampilkan daftar siswa menggunakan `RecyclerView` dan `Adapter` kustom (Mengacu pada materi **PirantiBergerak3**).
    *   Setiap baris data ditampilkan menggunakan desain CardView yang rapi.
3.  **Manajemen Sesi (SharedPreferences)**:
    *   Sistem Login untuk mengamankan data (Mengacu pada materi **PirantiBergerak4**).
    *   Fitur *Keep Login* agar user tidak perlu login ulang saat membuka aplikasi.
    *   Fitur Logout untuk keluar dari sesi.
4.  **Fitur Media**:
    *   Dukungan pemilihan foto siswa langsung dari galeri ponsel.

## 🛠️ Teknologi yang Digunakan
*   **Bahasa**: Java
*   **Database**: SQLite
*   **Penyimpanan Sesi**: SharedPreferences
*   **UI Components**: RecyclerView, CardView, RelativeLayout, LinearLayout.

## 📸 Akun Login Percobaan
Gunakan akun berikut untuk masuk ke aplikasi:
*   **Admin**: `admin` / `admin123`
*   **Siswa**: `siswa` / `siswa123`

## 📂 Struktur Folder Penting
*   `MainActivity.java`: Halaman utama manajemen data siswa.
*   `LoginActivity.java`: Halaman gerbang login.
*   `DatabaseHelper.java`: Pengatur database SQLite.
*   `SiswaAdapter.java`: Pengatur tampilan list (RecyclerView).
*   `SharedPrefManager.java`: Pengatur sesi login.

---
*Dibuat untuk Tugas UTS Pemrograman Piranti Bergerak.*
