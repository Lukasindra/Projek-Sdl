package com.mycompany.projeksdl;

public class Penduduk implements Comparable<Penduduk> {
    private String nik;
    private String nama;
    private String alamat;
    private boolean sudahMenerimaBantuan;

    public Penduduk(String nik, String nama, String alamat, boolean sudahMenerimaBantuan) {
        this.nik = nik;
        this.nama = nama;
        this.alamat = alamat;
        this.sudahMenerimaBantuan = sudahMenerimaBantuan;
    }

    public String getNik() {
        return nik;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public boolean isSudahMenerimaBantuan() {
        return sudahMenerimaBantuan;
    }

    public void setSudahMenerimaBantuan(boolean sudahMenerimaBantuan) {
        this.sudahMenerimaBantuan = sudahMenerimaBantuan;
    }

    @Override
    public int compareTo(Penduduk other) {
        return this.nama.compareToIgnoreCase(other.nama);
    }

    @Override
    public String toString() {
        return "NIK: " + nik + ", Nama: " + nama + ", Alamat: " + alamat +
                ", Sudah Menerima: " + (sudahMenerimaBantuan ? "Ya" : "Belum");
    }
}
