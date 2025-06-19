package geometri.Benda3D;

import geometri.Benda2D.PersegiPanjang;
import geometri.TolakNilaiException;

/**
 * Merepresentasikan bangun ruang Prisma dengan alas Persegi Panjang (Balok).
 */
public class PrismaPersegiPanjang extends PersegiPanjang {

    // Dimensi dan hasil kalkulasi dibuat public agar konsisten
    public double tinggiPrisma;
    private double volume;
    private double luasPermukaan;

    public PrismaPersegiPanjang(double panjangAlas, double lebarAlas, double tinggiPrisma) throws TolakNilaiException {
        super(panjangAlas, lebarAlas);
        if (tinggiPrisma <= 0) {
            throw new TolakNilaiException("Tinggi prisma harus bernilai positif.");
        }
        this.tinggiPrisma = tinggiPrisma;
    }

    public double hitungVolume()  {
        this.volume = super.hitungLuas() * this.tinggiPrisma;
        return this.volume;
    }

    /**
     * Menghitung luas permukaan prisma (balok) berdasarkan state objek.
     * L = 2 * (pl + pt + lt)
     */
    public double hitungLuasPermukaan() {

        double luasAlasBalok = super.hitungLuas();
        double kelilingAlasBalok = super.hitungKeliling();
        double luasSelubung = kelilingAlasBalok * this.tinggiPrisma;
        this.luasPermukaan = (2 * luasAlasBalok) + luasSelubung;
        return this.luasPermukaan;
    }

    public double hitungVolume(double panjang, double lebar, double tinggiPrisma) throws TolakNilaiException {
        if (panjang <= 0 || lebar <= 0 || tinggiPrisma <= 0) {
            throw new TolakNilaiException("Panjang, lebar, dan tinggi prisma harus bernilai positif.");
        }
        this.volume = panjang * lebar * tinggiPrisma;
        return this.volume;
    }

    public double hitungLuasPermukaan(double panjang, double lebar, double tinggiPrisma) throws TolakNilaiException {
        if (panjang <= 0 || lebar <= 0 || tinggiPrisma <= 0) {
            throw new TolakNilaiException("Panjang, lebar, dan tinggi prisma harus bernilai positif.");
        }
        this.luasPermukaan = 2 * ((panjang * lebar) + (panjang * tinggiPrisma) + (lebar * tinggiPrisma));
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