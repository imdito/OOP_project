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

    public Trapesium(double sisiAtas, double sisiBawah, double tinggi, double sisiKiri, double sisiKanan){
        super("Trapesium");
        this.sisiAtas = sisiAtas;     // 'this' diperlukan
        this.sisiBawah = sisiBawah;   // 'this' diperlukan
        this.tinggi = tinggi;         // 'this' diperlukan
        this.sisiKiri = sisiKiri;     // 'this' diperlukan
        this.sisiKanan = sisiKanan;   // 'this' diperlukan
    }

    @Override
    public double hitungLuas() throws TolakNilaiException {
        if (sisiAtas <= 0 || sisiBawah <= 0 || tinggi <= 0) {
            throw new TolakNilaiException("Sisi atas, bawah, dan tinggi harus bernilai positif.");
        }
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
    public double hitungKeliling() throws TolakNilaiException {
        if (sisiAtas <= 0 || sisiBawah <= 0 || sisiKiri <= 0 || sisiKanan <= 0) {
            throw new TolakNilaiException("Sisi atas, bawah, kiri, dan kanan harus bernilai positif.");
        }
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