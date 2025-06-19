package geometri.Benda2D;

import geometri.AbstractGeometriDasar;
import geometri.TolakNilaiException;

/**
 * Kelas yang merepresentasikan bangun datar Lingkaran.
 */
public class Lingkaran extends AbstractGeometriDasar implements Runnable{

    public double jariJari;
    public double luas;
    public double keliling;

    public Lingkaran(double jariJari) {
        super("Lingkaran");
        this.jariJari = jariJari;
    }

    @Override
    public double hitungLuas() throws TolakNilaiException {
        luas =  Math.PI * jariJari * jariJari; // 
        return luas;
    }

    public double hitungLuas(double jariJari) throws TolakNilaiException {
        if (jariJari <= 0) {
            throw new TolakNilaiException("Jari-jari harus bernilai positif.");
        }
        luas = Math.PI * jariJari * jariJari; //
        return luas;
    }

    @Override
    public double hitungKeliling() throws TolakNilaiException {
        if (jariJari <= 0) {
            throw new TolakNilaiException("Jari-jari harus bernilai positif.");
        }
        keliling =  2 * Math.PI * jariJari; // 
        return keliling;
    }

    public double hitungKeliling(double jariJari) throws TolakNilaiException {
        if (jariJari <= 0) {
            throw new TolakNilaiException("Jari-jari harus bernilai positif.");
        }
        keliling = 2 * Math.PI * jariJari; //
        return keliling;
    }

    @Override
    public void run() {
        System.out.println(String.format("[%s] Menghitung Lingkaran dengan jari-jari %.2f.", Thread.currentThread().getName(), jariJari));
        try {
            double l = hitungLuas();
            double k = hitungKeliling();
            System.out.println(String.format("[%s] >> Luas: %.2f, Keliling: %.2f.", Thread.currentThread().getName(), l, k));
        } catch (TolakNilaiException e) {
            System.err.println(String.format("[%s] GAGAL MENGHITUNG %s: %s", Thread.currentThread().getName(), getNamaBangun(), e.getMessage()));
        }
    }
}