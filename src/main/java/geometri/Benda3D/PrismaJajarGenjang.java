package geometri.Benda3D;

import geometri.Benda2D.JajarGenjang;
import geometri.TolakNilaiException;

/**
 * Merepresentasikan bangun ruang Prisma dengan alas Jajar Genjang.
 */
public class PrismaJajarGenjang extends JajarGenjang {

    // Dimensi dan hasil kalkulasi dibuat public agar konsisten
    public double tinggiPrisma;
    private double volume;
    private double luasPermukaan;

    public PrismaJajarGenjang(double alasAlas, double tinggiAlas, double sisiMiringAlas, double tinggiPrisma) {
        super(alasAlas, tinggiAlas, sisiMiringAlas);
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
        double luasAlasJG = super.hitungLuas();
        double kelilingAlasJG = super.hitungKeliling();
        double luasSelubung = kelilingAlasJG * this.tinggiPrisma;
        this.luasPermukaan = (2 * luasAlasJG) + luasSelubung;
        return this.luasPermukaan;
    }

    public double hitungVolume(double alasAlas, double tinggiAlas, double tinggiPrisma) throws TolakNilaiException {
        if (alasAlas <= 0 || tinggiAlas <= 0 || tinggiPrisma <= 0) {
            throw new TolakNilaiException("Alas, tinggi alas, dan tinggi prisma harus bernilai positif.");
        }
        double luasAlas = alasAlas * tinggiAlas;
        this.volume = luasAlas * tinggiPrisma;
        return this.volume;
    }

    public double hitungLuasPermukaan(double alasAlas, double tinggiAlas, double sisiMiringAlas, double tinggiPrisma) throws TolakNilaiException {
        if (alasAlas <= 0 || tinggiAlas <= 0 || sisiMiringAlas <= 0 || tinggiPrisma <= 0) {
            throw new TolakNilaiException("Alas, tinggi alas, sisi miring, dan tinggi prisma harus bernilai positif.");
        }
        double luasAlas = alasAlas * tinggiAlas;
        double kelilingAlas = 2 * (alasAlas + sisiMiringAlas);
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