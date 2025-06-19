package geometri.Benda2D;

import geometri.AbstractGeometriDasar;
import geometri.TolakNilaiException;

public class PersegiPanjang extends AbstractGeometriDasar {

    public double panjang; // Tetap private
    public double lebar;   // Tetap private
    public double luas;
    public double keliling;

    public PersegiPanjang(double panjang, double lebar)  {
        super("Persegi Panjang");
        this.panjang = panjang; // 'this' diperlukan
        this.lebar = lebar;     // 'this' diperlukan
    }

    @Override
    public double hitungLuas() throws TolakNilaiException {
        if (panjang <= 0 || lebar <= 0) {
            throw new TolakNilaiException("Panjang dan lebar harus bernilai positif.");
        }
        luas = panjang * lebar;
        return luas;
    }

    public double hitungLuas(double panjang, double lebar) throws TolakNilaiException {
        if (panjang <= 0 || lebar <= 0) {
            throw new TolakNilaiException("Panjang dan lebar harus bernilai positif.");
        }
        luas = panjang * lebar;
        return luas;
    }

    @Override
    public double hitungKeliling() throws TolakNilaiException {
        if (panjang <= 0 || lebar <= 0) {
            throw new TolakNilaiException("Panjang dan lebar harus bernilai positif.");
        }
        keliling = 2 * (panjang + lebar);
        return keliling;
    }

    public double hitungKeliling(double panjang, double lebar) throws TolakNilaiException {
    if (panjang <= 0 || lebar <= 0) {
            throw new TolakNilaiException("Panjang dan lebar harus bernilai positif.");
        }
        keliling = 2 * (panjang + lebar);
        return keliling;
    }

}