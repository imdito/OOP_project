package geometri.Benda3D;

import geometri.Benda2D.PersegiPanjang;
import geometri.TolakNilaiException;

/**
 * Merepresentasikan bangun ruang Limas dengan alas Persegi Panjang.
 * Diasumsikan sebagai limas tegak (right pyramid).
 */
public class LimasPersegiPanjang extends PersegiPanjang {

    // Dimensi dan hasil kalkulasi dibuat public agar konsisten
    public double tinggiLimas;
    public double volume;
    public double luasSelubung;
    public double luasPermukaan;

    // Field 'luas' dan 'keliling' untuk alas diwarisi dari PersegiPanjang.

    public LimasPersegiPanjang(double panjangAlas, double lebarAlas, double tinggiLimas) throws IllegalArgumentException{
        super(panjangAlas, lebarAlas);
        if (tinggiLimas <= 0) {
            throw new IllegalArgumentException("Tinggi limas harus bernilai positif.");
        }
        this.tinggiLimas = tinggiLimas;
    }

    /**
     * Menghitung volume limas berdasarkan state objek.
     */
    public double hitungVolume() throws TolakNilaiException {
        if (this.tinggiLimas <= 0) {
            throw new TolakNilaiException("Tinggi limas harus bernilai positif.");
        }
        // Memanggil hitungLuas() dari superclass untuk mendapatkan luas alas
        this.volume = (1.0 / 3.0) * super.hitungLuas() * this.tinggiLimas;
        return this.volume;
    }

    /**
     * Menghitung luas selubung (total luas sisi tegak) limas.
     */
    public double hitungLuasSelubung() throws TolakNilaiException {
        if (this.tinggiLimas <= 0 || this.panjang <= 0 || this.lebar <= 0) {
            throw new TolakNilaiException("Tinggi limas, panjang, dan lebar harus bernilai positif.");
        }

        double ts1 = Math.sqrt(Math.pow(this.tinggiLimas, 2) + Math.pow(this.lebar / 2.0, 2));
        double luasSisiTegak1 = 2 * (0.5 * this.panjang * ts1);

        double ts2 = Math.sqrt(Math.pow(this.tinggiLimas, 2) + Math.pow(this.panjang / 2.0, 2));
        double luasSisiTegak2 = 2 * (0.5 * this.lebar * ts2);

        this.luasSelubung = luasSisiTegak1 + luasSisiTegak2;
        return this.luasSelubung;
    }

    /**
     * Menghitung luas permukaan total limas.
     * L = Luas Alas + Luas Selubung
     */
    public double hitungLuasPermukaan() throws TolakNilaiException {
        if (this.tinggiLimas <= 0 || this.panjang <= 0 || this.lebar <= 0) {
            throw new TolakNilaiException("Tinggi limas, panjang, dan lebar harus bernilai positif.");
        }
        double luasAlasLimas = super.hitungLuas();
        double luasSelubungLimas = this.hitungLuasSelubung(); // Memanggil metode langsung
        this.luasPermukaan = luasAlasLimas + luasSelubungLimas;
        return this.luasPermukaan;
    }

    public double hitungVolume(double panjangAlas, double lebarAlas, double tinggiLimas) throws TolakNilaiException {
        if (panjangAlas <= 0 || lebarAlas <= 0 || tinggiLimas <= 0) {
            throw new TolakNilaiException("Panjang alas, lebar alas, dan tinggi limas harus bernilai positif.");
        }
        double luasAlas = panjangAlas * lebarAlas;
        this.volume = (1.0 / 3.0) * luasAlas * tinggiLimas;
        return this.volume;
    }

    public double hitungLuasPermukaan(double panjangAlas, double lebarAlas, double tinggiLimas) throws TolakNilaiException {
        if (panjangAlas <= 0 || lebarAlas <= 0 || tinggiLimas <= 0) {
            throw new TolakNilaiException("Panjang alas, lebar alas, dan tinggi limas harus bernilai positif.");
        }
        double luasAlas = panjangAlas * lebarAlas;
        double ts1 = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(lebarAlas / 2.0, 2));
        double luasTegak1 = panjangAlas * ts1;
        double ts2 = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(panjangAlas / 2.0, 2));
        double luasTegak2 = lebarAlas * ts2;
        double luasSelubungLimas = luasTegak1 + luasTegak2;

        this.luasPermukaan = luasAlas + luasSelubungLimas;
        return this.luasPermukaan;
    }

    public double getTinggiLimas() {
        return tinggiLimas;
    }
    public double getVolume() {
        return volume;
    }
}