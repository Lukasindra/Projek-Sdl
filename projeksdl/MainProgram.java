package com.mycompany.projeksdl;

import java.util.Scanner;

public class MainProgram {
    private static DataBantuan dataBantuan = new DataBantuan();
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan;
        do {
            System.out.println("\n=== Menu Program Bantuan Sosial ===");
            System.out.println("1. Tambah Data Penduduk");
            System.out.println("2. Hapus Data Penduduk (berdasarkan NIK)");
            System.out.println("3. Cari Data Penduduk (berdasarkan Nama)");
            System.out.println("4. Urutkan Data Penduduk (berdasarkan Nama)");
            System.out.println("5. Tampilkan Semua Data Penduduk");
            System.out.println("6. Tampilkan Penduduk yang Sudah Menerima Bantuan");
            System.out.println("7. Tampilkan Penduduk yang Belum Menerima Bantuan");
            System.out.println("8. Update Data Penduduk (Alamat dan Status Bantuan)");
            System.out.println("9. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = Integer.parseInt(input.nextLine());

            switch (pilihan) {
                case 1: tambahPenduduk(); break;
                case 2: hapusPenduduk(); break;
                case 3: cariPenduduk(); break;
                case 4: {
                    dataBantuan.sortByNama();
                    System.out.println("Data sudah diurutkan berdasarkan nama.");
                    break;
                }
                case 5: dataBantuan.tampilkanSemua(); break;
                case 6: dataBantuan.tampilkanSudahMenerima(); break;
                case 7: dataBantuan.tampilkanBelumMenerima(); break;
                case 8: updatePenduduk(); break;
                case 9: System.out.println("Keluar program."); break;
                default: System.out.println("Pilihan tidak valid!"); break;
            }
        } while (pilihan != 9);
    }

    private static void tambahPenduduk() {
        System.out.print("Masukkan NIK: ");
        String nik = input.nextLine();
        System.out.print("Masukkan Nama: ");
        String nama = input.nextLine();
        System.out.print("Masukkan Alamat: ");
        String alamat = input.nextLine();
        System.out.print("Sudah menerima bantuan (Ya/Tidak): ");
        String statusStr = input.nextLine().trim().toLowerCase();
        boolean sudahMenerima = statusStr.equals("ya") || statusStr.equals("sudah");
        Penduduk p = new Penduduk(nik, nama, alamat, sudahMenerima);
        if (dataBantuan.tambahPenduduk(p)) {
            System.out.println("Data berhasil ditambahkan.");
        }
    }

    private static void hapusPenduduk() {
        System.out.print("Masukkan NIK penduduk yang ingin dihapus: ");
        String nik = input.nextLine();
        if (dataBantuan.hapusPendudukByNIK(nik)) {
            System.out.println("Data berhasil dihapus.");
        } else {
            System.out.println("Data dengan NIK tersebut tidak ditemukan.");
        }
    }

    private static void cariPenduduk() {
        System.out.print("Masukkan nama penduduk yang dicari: ");
        String nama = input.nextLine();
        dataBantuan.sortByNama();
        int index = dataBantuan.binarySearchByNama(nama);
        if (index >= 0) {
            System.out.println("Data ditemukan:");
            System.out.println(dataBantuan.getPenduduk(index));
        } else {
            System.out.println("Data tidak ditemukan.");
        }
    }

    private static void updatePenduduk() {
        System.out.print("Masukkan NIK penduduk yang ingin diupdate: ");
        String nik = input.nextLine();
        System.out.print("Masukkan alamat baru: ");
        String alamatBaru = input.nextLine();
        System.out.print("Status bantuan baru (Ya/Tidak): ");
        String statusStr = input.nextLine().trim().toLowerCase();
        boolean sudahMenerimaBaru = statusStr.equals("ya") || statusStr.equals("sudah");
        boolean berhasil = dataBantuan.updatePendudukByNIK(nik, alamatBaru, sudahMenerimaBaru);
        if (berhasil) {
            System.out.println("Data berhasil diperbarui.");
        } else {
            System.out.println("Data dengan NIK tersebut tidak ditemukan.");
        }
    }
}
