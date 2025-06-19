package geometri.Benda2D;

import geometri.AbstractGeometriDasar;
import geometri.TolakNilaiException;

public class JajarGenjang extends AbstractGeometriDasar {

    public double alas; // Dimensi dasar tetap private
    public double tinggi;
    public double sisiMiring;
    public double luas;
    public double keliling;

    public JajarGenjang(double alas, double tinggi, double sisiMiring)   {
        super("Jajar Genjang");
        this.alas = alas;         // 'this' diperlukan
        this.tinggi = tinggi;     // 'this' diperlukan
        this.sisiMiring = sisiMiring; // 'this' diperlukan
    }

    @Override
    public double hitungLuas() throws TolakNilaiException {
        if (alas <= 0 || tinggi <= 0) {
            throw new TolakNilaiException("Alas dan tinggi harus bernilai positif.");
        }
        luas = alas * tinggi;
        return luas;
    }

    public double hitungLuas(double alas, double tinggi) throws TolakNilaiException {
        if (alas <= 0 || tinggi <= 0) {
            throw new TolakNilaiException("Alas dan tinggi harus bernilai positif.");
        }
        luas = alas * tinggi;
        return luas;
    }

    @Override
    public double hitungKeliling() throws TolakNilaiException {
        if (alas <= 0 || sisiMiring <= 0) {
            throw new TolakNilaiException("Alas dan sisi miring harus bernilai positif.");
        }
        keliling = 2 * (alas + sisiMiring);
        return keliling;
    }

    public double hitungKeliling(double alas, double sisiMiring) throws TolakNilaiException {
        if (alas <= 0 || sisiMiring <= 0) {
            throw new TolakNilaiException("Alas dan sisi miring harus bernilai positif.");
        }
        keliling = 2 * (alas + sisiMiring);
        return keliling;
    }

}