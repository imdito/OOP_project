package geometri.Benda3D;

import geometri.Benda2D.LayangLayang;
import geometri.TolakNilaiException;

/**
 * Merepresentasikan bangun ruang Limas dengan alas berbentuk Layang-Layang.
 */
public class LimasLayangLayang extends LayangLayang {

    // Dimensi dan hasil kalkulasi dibuat public agar konsisten
    public double tinggiLimas;
    private double tinggiSisiTegakPendek;  // Tinggi sisi tegak pada sisi pendek
    private double tinggiSisiTegakPanjang; // Tinggi sisi tegak pada sisi panjang

    private double volume;
    private double luasSelubung;
    private double luasPermukaan;

    /**
     * Konstruktor lengkap untuk kalkulasi volume dan luas permukaan.
     */
    public LimasLayangLayang(double diagonal1Alas, double diagonal2Alas, double sisiPendekAlas, double sisiPanjangAlas,
                             double tinggiLimas, double tinggiSisiTegakPendek, double tinggiSisiTegakPanjang)   {

        super(diagonal1Alas, diagonal2Alas, sisiPendekAlas, sisiPanjangAlas);
        this.tinggiLimas = tinggiLimas;
        this.tinggiSisiTegakPendek = tinggiSisiTegakPendek;
        this.tinggiSisiTegakPanjang = tinggiSisiTegakPanjang;
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
     * Menghitung luas selubung limas berdasarkan state objek.
     */
    public double hitungLuasSelubung() throws TolakNilaiException {
        if (this.tinggiSisiTegakPendek <= 0 || this.tinggiSisiTegakPanjang <= 0) {
            throw new TolakNilaiException("Tinggi sisi tegak pendek dan panjang harus bernilai positif.");
        }
        // Menggunakan field public secara langsung
        double luasTegakPendek = 2 * (0.5 * this.sisiPendek * this.tinggiSisiTegakPendek);
        double luasTegakPanjang = 2 * (0.5 * this.sisiPanjang * this.tinggiSisiTegakPanjang);
        luasSelubung = luasTegakPendek + luasTegakPanjang;
        return this.luasSelubung;
    }

    /**
     * Menghitung luas permukaan total limas berdasarkan state objek.
     * L = Luas Alas + Luas Selubung
     */

    public double hitungLuasPermukaan() throws TolakNilaiException {
        if (this.tinggiLimas <= 0 || this.tinggiSisiTegakPendek <= 0 || this.tinggiSisiTegakPanjang <= 0) {
            throw new TolakNilaiException("Tinggi limas, tinggi sisi tegak pendek, dan tinggi sisi tegak panjang harus bernilai positif.");
        }
        double luasAlasLimas = super.hitungLuas();
        double luasSelubungLimas = this.hitungLuasSelubung();
        luasPermukaan = luasAlasLimas + luasSelubungLimas;
        return this.luasPermukaan;
    }

    // --- METODE OVERLOAD BARU (SESUAI KONSEP KELAS INDUK) ---

    public double hitungVolume(double diagonal1Alas, double diagonal2Alas, double tinggiLimas) throws TolakNilaiException {
        if (diagonal1Alas <= 0 || diagonal2Alas <= 0 || tinggiLimas <= 0) {
            throw new TolakNilaiException("Diagonal alas dan tinggi limas harus bernilai positif.");
        }

        double luasAlas = 0.5 * diagonal1Alas * diagonal2Alas;
        this.volume = (1.0 / 3.0) * luasAlas * tinggiLimas;
        return this.volume;
    }

    public double hitungLuasPermukaan(double diagonal1Alas, double diagonal2Alas, double sisiPendekAlas,
                                      double sisiPanjangAlas, double tinggiSisiTegakPendek, double tinggiSisiTegakPanjang) throws TolakNilaiException {
        if (diagonal1Alas <= 0 || diagonal2Alas <= 0 || sisiPendekAlas <= 0 || sisiPanjangAlas <= 0 ||
            tinggiSisiTegakPendek <= 0 || tinggiSisiTegakPanjang <= 0) {
            throw new TolakNilaiException("Diagonal alas, sisi pendek, sisi panjang, dan tinggi sisi tegak harus bernilai positif.");
        }
        double luasAlas = 0.5 * diagonal1Alas * diagonal2Alas;
        double luasTegakPendek = sisiPendekAlas * tinggiSisiTegakPendek; // 2 * (0.5 * sisi * ts)
        double luasTegakPanjang = sisiPanjangAlas * tinggiSisiTegakPanjang; // 2 * (0.5 * sisi * ts)

        this.luasPermukaan = luasAlas + luasTegakPendek + luasTegakPanjang;
        return this.luasPermukaan;
    }

    public double getTinggiLimas() {
        return tinggiLimas;
    }
    public double getVolume(){
        return volume;
    }
    public double getLuasPermukaan(){

        return luasPermukaan;
    }

}