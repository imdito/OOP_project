package geometri.Benda3D;

import geometri.Benda2D.BelahKetupat;
import geometri.TolakNilaiException;

/**
 * Merepresentasikan bangun ruang Limas dengan alas berbentuk Belah Ketupat.
 * Kelas ini mewarisi sifat-sifat dari BelahKetupat sebagai alasnya.
 */
public class LimasBelahKetupat extends BelahKetupat {

    // Dimensi dan hasil kalkulasi dibuat public agar konsisten dengan superclass
    public double tinggiLimas;
    public double volume;
    public double tinggiSisiTegak;
    public double luasSelubung;
    public double luasPermukaan;

    // Field 'luas' dan 'keliling' untuk alas diwarisi dari BelahKetupat.

    public LimasBelahKetupat(double diagonal1Alas, double diagonal2Alas, double sisiAlas, double tinggiLimas)   {
        super(diagonal1Alas, diagonal2Alas, sisiAlas);
        this.tinggiLimas = tinggiLimas;
    }

    /**
     * Menghitung volume limas berdasarkan state objek.
     * V = (1/3) * Luas Alas * Tinggi Limas
     */
    public double hitungVolume() throws TolakNilaiException {
        if (this.tinggiLimas <= 0) {
            throw new TolakNilaiException("Tinggi limas harus bernilai positif.");
        }
        // Memanggil hitungLuas() dari superclass untuk mendapatkan luas alas
        double luasAlas = super.hitungLuas();
        this.volume = (1.0 / 3.0) * luasAlas * this.tinggiLimas;
        return this.volume;
    }

    /**
     * Menghitung tinggi sisi tegak limas (apotema limas).
     */
    public double hitungTinggiSisiTegak() throws TolakNilaiException {
        if (this.tinggiLimas <= 0 || this.sisi <= 0 || this.diagonal1 <= 0 || this.diagonal2 <= 0) {
            throw new TolakNilaiException("Tinggi limas, sisi, dan diagonal harus bernilai positif.");
        }
        // Menggunakan field public secara langsung, bukan getter
        double apotemaAlas = (this.diagonal1 * this.diagonal2) / (4 * this.sisi);
        this.tinggiSisiTegak = Math.sqrt(Math.pow(this.tinggiLimas, 2) + Math.pow(apotemaAlas, 2));
        return this.tinggiSisiTegak;
    }

    /**
     * Menghitung luas selubung (total luas sisi tegak) limas.
     */
    public double hitungLuasSelubung() throws TolakNilaiException {
        if (this.tinggiLimas <= 0 || this.sisi <= 0 || this.diagonal1 <= 0 || this.diagonal2 <= 0) {
            throw new TolakNilaiException("Tinggi limas, sisi, dan diagonal harus bernilai positif.");
        }
        // Memanggil metode hitung untuk memastikan nilainya ter-update
        double tst = this.hitungTinggiSisiTegak();
        double luasSatuSisiTegak = 0.5 * this.sisi * tst;
        this.luasSelubung = 4 * luasSatuSisiTegak;
        return this.luasSelubung;
    }

    /**
     * Menghitung luas permukaan total limas.
     * L = Luas Alas + Luas Selubung
     */
    public double hitungLuasPermukaan() throws TolakNilaiException {
        if (this.tinggiLimas <= 0 || this.sisi <= 0 || this.diagonal1 <= 0 || this.diagonal2 <= 0) {
            throw new TolakNilaiException("Tinggi limas, sisi, dan diagonal harus bernilai positif.");
        }
        double luasAlas = super.hitungLuas();
        double luasSelubungLimas = this.hitungLuasSelubung();
        this.luasPermukaan = luasAlas + luasSelubungLimas;
        return this.luasPermukaan;
    }

    // --- METODE OVERLOAD BARU (SESUAI KONSEP KELAS INDUK) ---

    public double hitungVolume(double diagonal1Alas, double diagonal2Alas, double tinggiLimas) throws TolakNilaiException {
        if (diagonal1Alas <= 0 || diagonal2Alas <= 0 || tinggiLimas <= 0) {
            throw new TolakNilaiException("Diagonal alas dan tinggi limas harus bernilai positif.");
        }
        double luasAlas = (diagonal1Alas * diagonal2Alas) / 2.0;
        this.volume = (1.0 / 3.0) * luasAlas * tinggiLimas;
        return this.volume;
    }

    public double hitungLuasPermukaan(double diagonal1Alas, double diagonal2Alas, double sisiAlas, double tinggiLimas) throws TolakNilaiException {
        if (diagonal1Alas <= 0 || diagonal2Alas <= 0 || sisiAlas <= 0 || tinggiLimas <= 0) {
            throw new TolakNilaiException("Diagonal alas, sisi alas, dan tinggi limas harus bernilai positif.");
        }
        // Hitung luas alas
        double luasAlas = (diagonal1Alas * diagonal2Alas) / 2.0;

        // Hitung luas selubung
        double apotemaAlas = (diagonal1Alas * diagonal2Alas) / (4 * sisiAlas);
        double tinggiSisiTegakLimas = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(apotemaAlas, 2));
        double luasSelubungLimas = 2 * sisiAlas * tinggiSisiTegakLimas; // 4 * (0.5 * sisi * tst)

        this.luasPermukaan = luasAlas + luasSelubungLimas;
        return this.luasPermukaan;
    }
    
    public double getTinggiLimas() {
        return tinggiLimas;
    }
    public double getVolume() {
        return volume;
    }
    public double getLuasPermukaan(){
        return luasPermukaan;
    }
}