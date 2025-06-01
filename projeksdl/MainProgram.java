package com.mycompany.projeksdl;

import java.util.Scanner;

public class MainProgram {

    private static Scanner input = new Scanner(System.in);
    private static DataBantuan dataBantuan = new DataBantuan();

    public static void main(String[] args) {
        preloadData();

        boolean running = true;
        while (running) {
            tampilkanMenu();
            System.out.print("Pilih menu (1-7): ");
            String pilihan = input.nextLine();

            switch (pilihan) {
                case "1":
                    tambahPenduduk();
                    break;
                case "2":
                    dataBantuan.tampilkanSemua();
                    hapusPenduduk();
                    break;
                case "3":
                    cariPenduduk();
                    break;
                case "4":
                    dataBantuan.sortByNama();
                    System.out.println("Data sudah diurutkan berdasarkan nama.");
                    break;
                case "5":
                    System.out.println("\n== Daftar Semua Penduduk ==");
                    dataBantuan.tampilkanSemua();
                    break;
                case "6":
                    System.out.println("\n== Penduduk yang Sudah Menerima Bantuan ==");
                    dataBantuan.tampilkanSudahMenerima();
                    System.out.println("\n== Penduduk yang Belum Menerima Bantuan ==");
                    dataBantuan.tampilkanBelumMenerima();
                    break;
                case "7":
                    editDataPenduduk();
                    break;

                case "8":
                    running = false;
                    System.out.println("Terima kasih telah menggunakan program ini.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid, coba lagi.");
            }
            System.out.println();
        }
    }

    private static void tampilkanMenu() {
        System.out.println("===== Program Data Bantuan Sosial =====");
        System.out.println("1. Tambah Penduduk");
        System.out.println("2. Hapus Penduduk berdasarkan NIK");
        System.out.println("3. Cari Penduduk berdasarkan Nama");
        System.out.println("4. Urutkan Data Penduduk berdasarkan Nama");
        System.out.println("5. Tampilkan Semua Data Penduduk");
        System.out.println("6. Tampilkan Penduduk Berdasarkan Status Bantuan");
        System.out.println("7. Edit Data Alamat / Status Bantuan Penduduk");
        System.out.println("8. Keluar");
    }

    private static void tambahPenduduk() {
        System.out.print("Masukkan NIK: ");
        String nik = input.nextLine();

        // Cek duplikat NIK
        for (int i = 0; i < dataBantuan.getJumlahPenduduk(); i++) {
            if (dataBantuan.getPenduduk(i).getNik().equals(nik)) {
                System.out.println("NIK sudah terdaftar, tidak bisa tambah data.");
                return;
            }
        }

        System.out.print("Masukkan Nama: ");
        String nama = input.nextLine();

        System.out.print("Masukkan Alamat: ");
        String alamat = input.nextLine();

        System.out.print("Sudah menerima bantuan? (Sudah/Belum): ");
        String status = input.nextLine().trim().toLowerCase();
        boolean sudahMenerima = status.equals("sudah") || status.equals("y");

        Penduduk p = new Penduduk(nik, nama, alamat, sudahMenerima);
        if (dataBantuan.tambahPenduduk(p)) {
            System.out.println("Penduduk berhasil ditambahkan.");
        } else {
            System.out.println("Data penuh, gagal menambahkan penduduk.");
        }
    }

    private static void hapusPenduduk() {
        System.out.print("Masukkan NIK penduduk yang ingin dihapus: ");
        String nik = input.nextLine();

        if (dataBantuan.hapusPendudukByNIK(nik)) {
            System.out.println("Penduduk dengan NIK " + nik + " berhasil dihapus.");
        } else {
            System.out.println("Penduduk dengan NIK " + nik + " tidak ditemukan.");
        }
    }

    private static void cariPenduduk() {
        System.out.print("Masukkan nama penduduk yang dicari: ");
        String namaCari = input.nextLine();

        dataBantuan.sortByNama(); // Pastikan data terurut
        int index = dataBantuan.binarySearchByNama(namaCari);

        if (index != -1) {
            System.out.println("Data penduduk ditemukan:");
            dataBantuan.cetakHeaderTabel();
            dataBantuan.getPenduduk(index).tampilkanData();
            dataBantuan.cetakFooterTabel();
        } else {
            System.out.println("Penduduk dengan nama \"" + namaCari + "\" tidak ditemukan.");
        }
    }

    private static void editDataPenduduk() {
        System.out.print("Masukkan NIK penduduk yang ingin diedit: ");
        String nik = input.nextLine();

        Penduduk target = null;
        for (int i = 0; i < dataBantuan.getJumlahPenduduk(); i++) {
            if (dataBantuan.getPenduduk(i).getNik().equals(nik)) {
                target = dataBantuan.getPenduduk(i);
                break;
            }
        }

        if (target == null) {
            System.out.println("Penduduk dengan NIK tersebut tidak ditemukan.");
            return;
        }

        System.out.println("Data saat ini:");
        dataBantuan.cetakHeaderTabel();
        target.tampilkanData();
        dataBantuan.cetakFooterTabel();

        System.out.println("Pilih data yang ingin diedit:");
        System.out.println("1. Alamat");
        System.out.println("2. Status Bantuan");
        System.out.println("3. Keduanya");
        System.out.print("Pilihan: ");
        String pilihan = input.nextLine();

        if (pilihan.equals("1") || pilihan.equals("3")) {
            System.out.print("Masukkan alamat baru: ");
            String alamatBaru = input.nextLine();
            target.setAlamat(alamatBaru);
        }

        if (pilihan.equals("2") || pilihan.equals("3")) {
            System.out.print("Status bantuan baru (Sudah/Belum): ");
            String status = input.nextLine().trim().toLowerCase();
            boolean sudahMenerima = status.equals("sudah") || status.equals("y");
            target.setSudahMenerimaBantuan(sudahMenerima);
        }

        System.out.println("Data berhasil diperbarui.");
    }

    private static void preloadData() {
        dataBantuan.tambahPenduduk(new Penduduk("1234567890123456", "Budi Santoso", "Jl. Merdeka No.1", true));
        dataBantuan.tambahPenduduk(new Penduduk("2345678901234567", "Siti Aminah", "Jl. Melati No.2", false));
        dataBantuan.tambahPenduduk(new Penduduk("3456789012345678", "Andi Wijaya", "Jl. Kenanga No.3", true));
        dataBantuan.tambahPenduduk(new Penduduk("4567890123456789", "Dewi Lestari", "Jl. Mawar No.4", false));
    }
}
