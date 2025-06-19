package geometri.Benda3D;

import geometri.Benda2D.Trapesium;
import geometri.TolakNilaiException;

/**
 * Merepresentasikan bangun ruang Prisma dengan alas berbentuk Trapesium.
 */
public class PrismaTrapesium extends Trapesium {

    // Dimensi dan hasil kalkulasi dibuat public agar konsisten
    public double tinggiPrisma;
    private double volume;
    private double luasPermukaan;

    public PrismaTrapesium(double sisiAtasAlas, double sisiBawahAlas, double tinggiAlasTrapesium,
                           double sisiKiriAlas, double sisiKananAlas, double tinggiPrisma) {
        super(sisiAtasAlas, sisiBawahAlas, tinggiAlasTrapesium, sisiKiriAlas, sisiKananAlas);
        this.tinggiPrisma = tinggiPrisma;
    }

    /**
     * Menghitung volume prisma berdasarkan state objek.
     * V = Luas Alas * Tinggi Prisma
     */
    public double hitungVolume() throws TolakNilaiException {
        if (this.tinggiPrisma <= 0) {
            throw new TolakNilaiException("Tinggi prisma harus bernilai positif.");
        }
        this.volume = super.hitungLuas() * this.tinggiPrisma;
        return this.volume;
    }

    /**
     * Menghitung luas permukaan prisma berdasarkan state objek.
     * L = (2 * Luas Alas) + (Keliling Alas * Tinggi Prisma)
     */
    public double hitungLuasPermukaan() throws TolakNilaiException {
        if (this.tinggiPrisma <= 0) {
            throw new TolakNilaiException("Tinggi prisma harus bernilai positif.");
        }
        double luasAlasTrapesium = super.hitungLuas();
        double kelilingAlasTrapesium = super.hitungKeliling();
        double luasSelubung = kelilingAlasTrapesium * this.tinggiPrisma;
        this.luasPermukaan = (2 * luasAlasTrapesium) + luasSelubung;
        return this.luasPermukaan;
    }

    public double hitungVolume(double sisiAtasAlas, double sisiBawahAlas, double tinggiAlas, double tinggiPrisma) throws TolakNilaiException {
        if (tinggiPrisma <= 0) {
            throw new TolakNilaiException("Tinggi prisma harus bernilai positif.");
        }
        double luasAlas = 0.5 * (sisiAtasAlas + sisiBawahAlas) * tinggiAlas;
        this.volume = luasAlas * tinggiPrisma;
        return this.volume;
    }

    public double hitungLuasPermukaan(double sisiAtasAlas, double sisiBawahAlas, double tinggiAlas,
                                      double sisiKiriAlas, double sisiKananAlas, double tinggiPrisma) throws TolakNilaiException {
        if (tinggiPrisma <= 0) {
            throw new TolakNilaiException("Tinggi prisma harus bernilai positif.");
        }
        double luasAlas = 0.5 * (sisiAtasAlas + sisiBawahAlas) * tinggiAlas;
        double kelilingAlas = sisiAtasAlas + sisiBawahAlas + sisiKiriAlas + sisiKananAlas;
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