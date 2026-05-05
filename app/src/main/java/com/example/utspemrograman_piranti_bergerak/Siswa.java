package com.example.utspemrograman_piranti_bergerak;

public class Siswa {
    private int id;
    private String nama;
    private String nim;
    private int nilai;
    private String foto;

    public Siswa(int id, String nama, String nim, int nilai, String foto) {
        this.id = id;
        this.nama = nama;
        this.nim = nim;
        this.nilai = nilai;
        this.foto = foto;
    }

    public int getId() { return id; }
    public String getNama() { return nama; }
    public String getNim() { return nim; }
    public int getNilai() { return nilai; }
    public String getFoto() { return foto; }
}
