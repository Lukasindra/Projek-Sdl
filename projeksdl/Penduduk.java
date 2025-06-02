package com.mycompany.projeksdl;

public class Penduduk implements Comparable<Penduduk> {
    private final String noKK;
    private final String nama;
    private String alamat;
    private boolean sudahMenerimaBantuan;
    private String jenisBansos;

    public Penduduk(String noKK, String nama, String alamat, boolean sudahMenerimaBantuan, String jenisBansos) {
        this.noKK = noKK;
        this.nama = nama;
        this.alamat = alamat;
        this.sudahMenerimaBantuan = sudahMenerimaBantuan;
        this.jenisBansos = jenisBansos;
    }

    public String getNoKK() {
        return noKK;
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

    public void setSudahMenerimaBantuan(boolean status) {
        this.sudahMenerimaBantuan = status;
    }

    public String getJenisBansos() {
        return jenisBansos;
    }

    public void setJenisBansos(String jenisBansos) {
        this.jenisBansos = jenisBansos;
    }

    public void tampilkanData() {
        System.out.printf("| %-16s | %-20s | %-20s | %-6s | %-10s |\n",
                noKK, nama, alamat, (sudahMenerimaBantuan ? "Sudah" : "Belum"), jenisBansos);
    }

    @Override
    public int compareTo(Penduduk other) {
        return this.nama.compareToIgnoreCase(other.nama);
    }
}
