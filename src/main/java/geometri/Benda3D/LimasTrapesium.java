package geometri.Benda3D;

import geometri.Benda2D.Trapesium;
import geometri.TolakNilaiException;

/**
 * Merepresentasikan bangun ruang Limas dengan alas berbentuk Trapesium.
 */
public class LimasTrapesium extends Trapesium {

    // Dimensi dan hasil kalkulasi dibuat public agar konsisten
    public double tinggiLimas;
    private double tinggiSisiTegakAtas;
    private double tinggiSisiTegakBawah;
    private double tinggiSisiTegakKiri;
    private double tinggiSisiTegakKanan;

    private double volume;
    private double luasSelubung;
    private double luasPermukaan;

    /**
     * Konstruktor lengkap untuk kalkulasi volume dan luas permukaan.
     */
    public LimasTrapesium(double sisiAtasAlas, double sisiBawahAlas, double tinggiAlas,
                          double sisiKiriAlas, double sisiKananAlas, double tinggiLimas,
                          double tsAtas, double tsBawah, double tsKiri, double tsKanan) throws TolakNilaiException {
        super(sisiAtasAlas, sisiBawahAlas, tinggiAlas, sisiKiriAlas, sisiKananAlas);
        if (sisiAtasAlas <= 0 || sisiBawahAlas <= 0 || tinggiAlas <= 0 ||
            sisiKiriAlas <= 0 || sisiKananAlas <= 0 || tinggiLimas <= 0 ||
            tsAtas <= 0 || tsBawah <= 0 || tsKiri <= 0 || tsKanan <= 0) {
            throw new TolakNilaiException("Semua dimensi harus bernilai positif.");
        }
        this.tinggiLimas = tinggiLimas;
        this.tinggiSisiTegakAtas = tsAtas;
        this.tinggiSisiTegakBawah = tsBawah;
        this.tinggiSisiTegakKiri = tsKiri;
        this.tinggiSisiTegakKanan = tsKanan;
    }

    /**
     * Menghitung volume limas berdasarkan state objek.
     */
    public double hitungVolume() {
        this.volume = (1.0 / 3.0) * super.hitungLuas() * this.tinggiLimas;
        return this.volume;
    }

    /**
     * Menghitung luas selubung limas berdasarkan state objek.
     */
    public double hitungLuasSelubung() {
        // Menggunakan field public secara langsung
        double luasTegakAtas = 0.5 * this.sisiAtas * this.tinggiSisiTegakAtas;
        double luasTegakBawah = 0.5 * this.sisiBawah * this.tinggiSisiTegakBawah;
        double luasTegakKiri = 0.5 * this.sisiKiri * this.tinggiSisiTegakKiri;
        double luasTegakKanan = 0.5 * this.sisiKanan * this.tinggiSisiTegakKanan;
        this.luasSelubung = luasTegakAtas + luasTegakBawah + luasTegakKiri + luasTegakKanan;
        return this.luasSelubung;
    }

    /**
     * Menghitung luas permukaan total limas berdasarkan state objek.
     */
    public double hitungLuasPermukaan() {
        double luasAlasLimas = super.hitungLuas();
        double luasSelubungLimas = this.hitungLuasSelubung();
        this.luasPermukaan = luasAlasLimas + luasSelubungLimas;
        return this.luasPermukaan;
    }

    // --- METODE OVERLOAD BARU (SESUAI KONSEP KELAS INDUK) ---

    public double hitungVolume(double sisiAtasAlas, double sisiBawahAlas, double tinggiAlas, double tinggiLimas) throws TolakNilaiException {
        if (sisiAtasAlas <= 0 || sisiBawahAlas <= 0 || tinggiAlas <= 0 || tinggiLimas <= 0) {
            throw new TolakNilaiException("Sisi atas, sisi bawah, tinggi alas, dan tinggi limas harus bernilai positif.");
        }
        double luasAlas = 0.5 * (sisiAtasAlas + sisiBawahAlas) * tinggiAlas;
        this.volume = (1.0 / 3.0) * luasAlas * tinggiLimas;
        return this.volume;
    }

    public double hitungLuasPermukaan(double sisiAtasAlas, double sisiBawahAlas, double tinggiAlas,
                                      double sisiKiriAlas, double sisiKananAlas, double tsAtas,
                                      double tsBawah, double tsKiri, double tsKanan) throws TolakNilaiException {
        if (sisiAtasAlas <= 0 || sisiBawahAlas <= 0 || tinggiAlas <= 0 ||
            sisiKiriAlas <= 0 || sisiKananAlas <= 0 || tsAtas <= 0 || tsBawah <= 0 ||
            tsKiri <= 0 || tsKanan <= 0) {
            throw new TolakNilaiException("Sisi atas, sisi bawah, tinggi alas, sisi kiri, sisi kanan, dan tinggi sisi tegak harus bernilai positif.");
        }
        // Validasi bisa ditambahkan di sini
        double luasAlas = 0.5 * (sisiAtasAlas + sisiBawahAlas) * tinggiAlas;
        double luasTegakAtas = 0.5 * sisiAtasAlas * tsAtas;
        double luasTegakBawah = 0.5 * sisiBawahAlas * tsBawah;
        double luasTegakKiri = 0.5 * sisiKiriAlas * tsKiri;
        double luasTegakKanan = 0.5 * sisiKananAlas * tsKanan;

        this.luasPermukaan = luasAlas + luasTegakAtas + luasTegakBawah + luasTegakKiri + luasTegakKanan;
        return this.luasPermukaan;
    }

    public double getTinggiLimas() {
        return tinggiLimas;
    }
    public double getVolume() {
        return volume;
    }
    public double getLuasPermukaan() {
        return luasPermukaan;
    }
}