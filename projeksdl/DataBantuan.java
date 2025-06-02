package com.mycompany.projeksdl;

public class DataBantuan {

    private Penduduk[] daftarPenduduk;
    private int jumlahPenduduk;

    public DataBantuan() {
        daftarPenduduk = new Penduduk[100];
        jumlahPenduduk = 0;
    }

    public int getJumlahPenduduk() {
        return jumlahPenduduk;
    }

    public Penduduk getPenduduk(int index) {
        if (index >= 0 && index < jumlahPenduduk) {
            return daftarPenduduk[index];
        }
        return null;
    }

    public boolean tambahPenduduk(Penduduk p) {
        if (jumlahPenduduk < daftarPenduduk.length) {
            daftarPenduduk[jumlahPenduduk] = p;
            jumlahPenduduk++;
            return true;
        }
        return false; // array penuh
    }

    public boolean hapusPendudukByNoKK(String noKK) {
        int index = -1;
        for (int i = 0; i < jumlahPenduduk; i++) {
            if (daftarPenduduk[i].getNoKK().equals(noKK)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return false;
        }

        for (int i = index; i < jumlahPenduduk - 1; i++) {
            daftarPenduduk[i] = daftarPenduduk[i + 1];
        }
        daftarPenduduk[jumlahPenduduk - 1] = null;
        jumlahPenduduk--;
        return true;
    }

    public void sortByNama() {
        for (int i = 0; i < jumlahPenduduk - 1; i++) {
            for (int j = 0; j < jumlahPenduduk - 1 - i; j++) {
                if (daftarPenduduk[j].compareTo(daftarPenduduk[j + 1]) > 0) {
                    Penduduk temp = daftarPenduduk[j];
                    daftarPenduduk[j] = daftarPenduduk[j + 1];
                    daftarPenduduk[j + 1] = temp;
                }
            }
        }
    }

    public int binarySearchByNama(String nama) {
        int left = 0, right = jumlahPenduduk - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int cmp = daftarPenduduk[mid].getNama().compareToIgnoreCase(nama);
            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public void tampilkanSemua() {
        cetakHeaderTabel();
        for (int i = 0; i < jumlahPenduduk; i++) {
            daftarPenduduk[i].tampilkanData();
        }
        cetakFooterTabel();
    }

    public void tampilkanSudahMenerima() {
        cetakHeaderTabel();
        boolean ada = false;
        for (int i = 0; i < jumlahPenduduk; i++) {
            if (daftarPenduduk[i].isSudahMenerimaBantuan()) {
                daftarPenduduk[i].tampilkanData();
                ada = true;
            }
        }
        if (!ada) {
            System.out.println("|           Tidak ada penduduk yang sudah menerima bantuan          |");
        }
        cetakFooterTabel();
    }

    public void tampilkanBelumMenerima() {
        cetakHeaderTabel();
        boolean ada = false;
        for (int i = 0; i < jumlahPenduduk; i++) {
            if (!daftarPenduduk[i].isSudahMenerimaBantuan()) {
                daftarPenduduk[i].tampilkanData();
                ada = true;
            }
        }
        if (!ada) {
            System.out.println("|          Semua penduduk sudah menerima bantuan                    |");
        }
        cetakFooterTabel();
    }

    public boolean editPendudukByNoKK(String noKK, String namaBaru, String alamatBaru, boolean statusBaru, String jenisBansosBaru) {
        for (int i = 0; i < jumlahPenduduk; i++) {
            if (daftarPenduduk[i].getNoKK().equals(noKK)) {
                daftarPenduduk[i] = new Penduduk(noKK, namaBaru, alamatBaru, statusBaru, jenisBansosBaru);
                return true;
            }
        }
        return false;
    }

    public void cetakHeaderTabel() {
        System.out.println("+------------------+----------------------+----------------------+--------+------------+");
        System.out.println("| No KK            | Nama                 | Alamat               | Status | Jenis Bansos |");
        System.out.println("+------------------+----------------------+----------------------+--------+------------+");
    }

    public void cetakFooterTabel() {
        System.out.println("+------------------+----------------------+----------------------+--------+------------+");
    }
}
