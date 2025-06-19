package geometri.Benda2D;

import geometri.AbstractGeometriDasar;
import geometri.TolakNilaiException;

public class Persegi extends AbstractGeometriDasar implements Runnable {

    public double sisi; // Tetap private untuk enkapsulasi dimensi dasar
    public double luas;
    public double keliling;

    public Persegi(double sisi) {
        super("Persegi");
        this.sisi = sisi; // 'this' diperlukan di sini karena parameter sama dengan nama field
    }

    @Override
    public double hitungLuas() throws TolakNilaiException {
        if (sisi <= 0) {
            throw new TolakNilaiException("Sisi harus bernilai positif.");
        }
        luas = sisi * sisi;
        return luas;
    }

    public double hitungLuas(double sisi) throws TolakNilaiException {
        if (sisi <= 0) {
            throw new TolakNilaiException("Sisi harus bernilai positif.");
        }
        luas = sisi * sisi;
        return luas;
    }

    @Override
    public double hitungKeliling() throws TolakNilaiException {
        if (sisi <= 0) {
            throw new TolakNilaiException("Sisi harus bernilai positif.");
        }
        keliling = 4 * sisi;
        return keliling;
    }

    public double hitungKeliling(double sisi) throws TolakNilaiException {
        if (sisi <= 0) {
            throw new TolakNilaiException("Sisi harus bernilai positif.");
        }
        keliling = 4 * sisi;
        return keliling;
    }

    @Override
    public void run() {
        System.out.println(String.format("[%s] Menghitung Persegi dengan sisi %.2f.", Thread.currentThread().getName(), sisi));
        try {
            double l = hitungLuas();
            double k = hitungKeliling();
            System.out.println(String.format("[%s] >> Luas: %.2f, Keliling: %.2f.", Thread.currentThread().getName(), l, k));
        } catch (TolakNilaiException e) {
            System.err.println(String.format("[%s] GAGAL MENGHITUNG %s: %s", Thread.currentThread().getName(), getNamaBangun(), e.getMessage()));
        }
    }
}