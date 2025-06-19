package geometri.Benda2D;

import geometri.AbstractGeometriDasar;
import geometri.TolakNilaiException;

public class LayangLayang extends AbstractGeometriDasar {

    public double diagonal1; // Dimensi dasar tetap private
    public double diagonal2;
    public double sisiPendek;
    public double sisiPanjang;
    public double luas;
    public double keliling;

    public LayangLayang(double diagonal1, double diagonal2, double sisiPendek, double sisiPanjang){
        super("Layang-Layang");

        this.diagonal1 = diagonal1;     // 'this' diperlukan
        this.diagonal2 = diagonal2;     // 'this' diperlukan
        this.sisiPendek = sisiPendek;   // 'this' diperlukan
        this.sisiPanjang = sisiPanjang; // 'this' diperlukan
    }

    @Override
    public double hitungLuas() throws TolakNilaiException {
        if (diagonal1 <= 0 || diagonal2 <= 0) {
            throw new TolakNilaiException("Diagonal harus bernilai positif.");
        }
        luas = 0.5 * diagonal1 * diagonal2;
        return luas;
    }

    public double hitungLuas(double diagonal1, double diagonal2) throws TolakNilaiException {
        if (diagonal1 <= 0 || diagonal2 <= 0) {
            throw new TolakNilaiException("Diagonal harus bernilai positif.");
        }
        luas = 0.5 * diagonal1 * diagonal2;
        return luas;
    }

    @Override
    public double hitungKeliling() throws TolakNilaiException {
        if (sisiPendek <= 0 || sisiPanjang <= 0) {
            throw new TolakNilaiException("Sisi pendek dan sisi panjang harus bernilai positif.");
        }
        keliling = 2 * (sisiPendek + sisiPanjang);
        return keliling;
    }

    public double hitungKeliling(double sisiPendek, double sisiPanjang) throws TolakNilaiException {
        if (sisiPendek <= 0 || sisiPanjang <= 0) {
            throw new TolakNilaiException("Sisi pendek dan sisi panjang harus bernilai positif.");
        }
        keliling = 2 * (sisiPendek + sisiPanjang);
        return keliling;
    }


}