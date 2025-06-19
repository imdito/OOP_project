package geometri.Benda2D;

import geometri.AbstractGeometriDasar;
import geometri.TolakNilaiException;


public class BelahKetupat extends AbstractGeometriDasar {

    public double diagonal1; // Dimensi dasar tetap private
    public double diagonal2;
    public double sisi;
    public double luas;
    public double keliling;

    public BelahKetupat(double diagonal1, double diagonal2, double sisi) {
        super("Belah Ketupat");
        this.diagonal1 = diagonal1; // 'this' diperlukan
        this.diagonal2 = diagonal2; // 'this' diperlukan
        this.sisi = sisi;           // 'this' diperlukan
    }

    @Override
    public double hitungLuas() throws TolakNilaiException {
        if (diagonal1 <= 0 || diagonal2 <= 0) {
            throw new TolakNilaiException("Diagonal harus bernilai positif.");
        }
        luas = (diagonal1 * diagonal2) / 2.0;
        return luas;
    }

    public double hitungLuas(double diagonal1, double diagonal2) throws TolakNilaiException {
        if (diagonal1 <= 0 || diagonal2 <= 0) {
            throw new TolakNilaiException("Diagonal harus bernilai positif.");
        }
        luas = (diagonal1 * diagonal2) / 2.0;
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
}