package geometri.Benda2D;

import geometri.AbstractGeometriDasar;
import geometri.TolakNilaiException;

public class Trapesium extends AbstractGeometriDasar {

    public double sisiAtas; // Dimensi dasar tetap private
    public double sisiBawah;
    public double tinggi;
    public double sisiKiri;
    public double sisiKanan;
    public double luas;
    public double keliling;

    public Trapesium(double sisiAtas, double sisiBawah, double tinggi, double sisiKiri, double sisiKanan) throws TolakNilaiException {

        super("Trapesium");
        if (sisiAtas <= 0 || sisiBawah <= 0 || tinggi <= 0 || sisiKiri <= 0 || sisiKanan <= 0) {
            throw new TolakNilaiException("Sisi atas, bawah, tinggi, kiri, dan kanan harus bernilai positif.");
        }
        this.sisiAtas = sisiAtas;
        this.sisiBawah = sisiBawah;
        this.tinggi = tinggi;
        this.sisiKiri = sisiKiri;
        this.sisiKanan = sisiKanan;
    }

    @Override
    public double hitungLuas()  {
        luas = 0.5 * (sisiAtas + sisiBawah) * tinggi;
        return luas;
    }

    public double hitungLuas(double sisiAtas, double sisiBawah, double tinggi) throws TolakNilaiException {

        if (sisiAtas <= 0 || sisiBawah <= 0 || tinggi <= 0) {
            throw new TolakNilaiException("Sisi atas, bawah, dan tinggi harus bernilai positif.");
        }
        luas = 0.5 * (sisiAtas + sisiBawah) * tinggi;
        return luas;
    }

    @Override
    public double hitungKeliling()   {
        keliling = sisiAtas + sisiBawah + sisiKiri + sisiKanan;
        return keliling;
    }

    public double hitungKeliling(double sisiAtas, double sisiBawah, double sisiKiri, double sisiKanan) throws TolakNilaiException {
        if (sisiAtas <= 0 || sisiBawah <= 0 || sisiKiri <= 0 || sisiKanan <= 0) {
            throw new TolakNilaiException("Sisi atas, bawah, kiri, dan kanan harus bernilai positif.");
        }
        keliling = sisiAtas + sisiBawah + sisiKiri + sisiKanan;
        return keliling;
    }

}