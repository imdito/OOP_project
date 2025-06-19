package geometri.Benda3D;

import geometri.Benda2D.LayangLayang;
import geometri.TolakNilaiException;

public class PrismaLayangLayang extends LayangLayang {

    // Dimensi dan hasil kalkulasi dibuat public agar konsisten
    public double tinggiPrisma;
    private double volume;
    private double luasPermukaan;

    public PrismaLayangLayang(double diagonal1Alas, double diagonal2Alas, double sisiPendekAlas,
                              double sisiPanjangAlas, double tinggiPrisma) {
        super(diagonal1Alas, diagonal2Alas, sisiPendekAlas, sisiPanjangAlas);
        this.tinggiPrisma = tinggiPrisma;
    }

    public double hitungVolume() throws TolakNilaiException {
        if (this.tinggiPrisma <= 0) {
            throw new TolakNilaiException("Tinggi prisma harus bernilai positif.");
        }
        this.volume = super.hitungLuas() * this.tinggiPrisma;
        return this.volume;
    }

    public double hitungLuasPermukaan() throws TolakNilaiException {
        if (this.tinggiPrisma <= 0) {
            throw new TolakNilaiException("Tinggi prisma harus bernilai positif.");
        }
        double luasAlasLL = super.hitungLuas();
        double kelilingAlasLL = super.hitungKeliling();
        double luasSelubung = kelilingAlasLL * this.tinggiPrisma;
        this.luasPermukaan = (2 * luasAlasLL) + luasSelubung;
        return this.luasPermukaan;
    }

    public double hitungVolume(double diagonal1Alas, double diagonal2Alas, double tinggiPrisma) throws TolakNilaiException {
        if (diagonal1Alas <= 0 || diagonal2Alas <= 0 || tinggiPrisma <= 0) {
            throw new TolakNilaiException("Diagonal alas dan tinggi prisma harus bernilai positif.");
        }
        double luasAlas = 0.5 * diagonal1Alas * diagonal2Alas;
        this.volume = luasAlas * tinggiPrisma;
        return this.volume;
    }

    public double hitungLuasPermukaan(double diagonal1Alas, double diagonal2Alas, double sisiPendekAlas,
                                      double sisiPanjangAlas, double tinggiPrisma) throws TolakNilaiException {
        if (diagonal1Alas <= 0 || diagonal2Alas <= 0 || sisiPendekAlas <= 0 || sisiPanjangAlas <= 0 || tinggiPrisma <= 0) {
            throw new TolakNilaiException("Diagonal alas, sisi pendek, sisi panjang, dan tinggi prisma harus bernilai positif.");
        }
        double luasAlas = 0.5 * diagonal1Alas * diagonal2Alas;
        double kelilingAlas = 2 * (sisiPendekAlas + sisiPanjangAlas);
        double luasSelubung = kelilingAlas * tinggiPrisma;
        this.luasPermukaan = (2 * luasAlas) + luasSelubung;
        return this.luasPermukaan;
    }

    public double getTinggiPrisma() {
        return tinggiPrisma;
    }
    public double getVolume() {
        return volume;
    }
    public double getLuasPermukaan() {
        return luasPermukaan;
    }
}