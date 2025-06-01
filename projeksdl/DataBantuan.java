package com.mycompany.projeksdl;

public class DataBantuan {
    private Penduduk[] daftarPenduduk = new Penduduk[100];
    private int jumlahPenduduk = 0;

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
        if (jumlahPenduduk >= daftarPenduduk.length) {
            System.out.println("Data penduduk sudah penuh!");
            return false;
        }
        // Cek duplikat NIK
        for (int i = 0; i < jumlahPenduduk; i++) {
            if (daftarPenduduk[i].getNik().equals(p.getNik())) {
                System.out.println("NIK sudah ada!");
                return false;
            }
        }
        daftarPenduduk[jumlahPenduduk++] = p;
        return true;
    }

    public boolean hapusPendudukByNIK(String nik) {
        for (int i = 0; i < jumlahPenduduk; i++) {
            if (daftarPenduduk[i].getNik().equals(nik)) {
                // Geser kiri
                for (int j = i; j < jumlahPenduduk - 1; j++) {
                    daftarPenduduk[j] = daftarPenduduk[j + 1];
                }
                daftarPenduduk[--jumlahPenduduk] = null;
                return true;
            }
        }
        return false;
    }

    public void sortByNama() {
        for (int i = 0; i < jumlahPenduduk - 1; i++) {
            for (int j = 0; j < jumlahPenduduk - i - 1; j++) {
                if (daftarPenduduk[j].compareTo(daftarPenduduk[j + 1]) > 0) {
                    Penduduk temp = daftarPenduduk[j];
                    daftarPenduduk[j] = daftarPenduduk[j + 1];
                    daftarPenduduk[j + 1] = temp;
                }
            }
        }
    }

    public int binarySearchByNama(String nama) {
        int low = 0, high = jumlahPenduduk - 1;
        nama = nama.toLowerCase();
        while (low <= high) {
            int mid = (low + high) / 2;
            String namaMid = daftarPenduduk[mid].getNama().toLowerCase();
            int cmp = namaMid.compareTo(nama);
            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public void tampilkanSemua() {
        if (jumlahPenduduk == 0) {
            System.out.println("Data kosong.");
            return;
        }
        for (int i = 0; i < jumlahPenduduk; i++) {
            System.out.println(daftarPenduduk[i]);
        }
    }

    public void tampilkanSudahMenerima() {
        boolean found = false;
        for (int i = 0; i < jumlahPenduduk; i++) {
            if (daftarPenduduk[i].isSudahMenerimaBantuan()) {
                System.out.println(daftarPenduduk[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Tidak ada penduduk yang sudah menerima bantuan.");
        }
    }

    public void tampilkanBelumMenerima() {
        boolean found = false;
        for (int i = 0; i < jumlahPenduduk; i++) {
            if (!daftarPenduduk[i].isSudahMenerimaBantuan()) {
                System.out.println(daftarPenduduk[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Tidak ada penduduk yang belum menerima bantuan.");
        }
    }

    // Method baru: update data penduduk berdasarkan NIK
    public boolean updatePendudukByNIK(String nik, String alamatBaru, boolean sudahMenerimaBaru) {
        for (int i = 0; i < jumlahPenduduk; i++) {
            if (daftarPenduduk[i].getNik().equals(nik)) {
                daftarPenduduk[i].setAlamat(alamatBaru);
                daftarPenduduk[i].setSudahMenerimaBantuan(sudahMenerimaBaru);
                return true;
            }
        }
        return false;
    }
}

