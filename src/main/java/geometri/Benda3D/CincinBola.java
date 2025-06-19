package geometri.Benda3D;

import geometri.TolakNilaiException;

public class CincinBola extends Bola {

    // Dimensi dibuat public agar konsisten dengan `jariJari` pada superclass
    public double tinggiCincin;
    public double jariJariAlas1;
    public double jariJariAlas2;

    // Field untuk menyimpan luas permukaan lengkung (selimut)
    protected double luasPermukaanLengkung;

    // Field 'volume' dan 'luasPermukaanBola' diwarisi dari kelas Bola

    public CincinBola(double jariJariBola, double tinggiCincin, double jariJariAlas1, double jariJariAlas2) {
        super(jariJariBola);

        this.tinggiCincin = tinggiCincin;
        this.jariJariAlas1 = jariJariAlas1;
        this.jariJariAlas2 = jariJariAlas2;
    }

    /**
     * Menghitung volume cincin bola berdasarkan state objek.
     * Hasilnya disimpan di field 'volume' yang diwarisi.
     */
    @Override
    public double hitungVolume() throws TolakNilaiException {
        if (this.tinggiCincin <= 0 || this.jariJariAlas1 <= 0 || this.jariJariAlas2 <= 0) {
            throw new TolakNilaiException("Tinggi cincin dan jari-jari alas harus bernilai positif.");
        }
        // Menggunakan field yang diwarisi 'volume'
        this.volume = (1.0 / 6.0) * Math.PI * this.tinggiCincin * (3 * Math.pow(this.jariJariAlas1, 2) + 3 * Math.pow(this.jariJariAlas2, 2) + Math.pow(this.tinggiCincin, 2));
        return this.volume;
    }

    /**
     * Menghitung luas permukaan lengkung (selimut) cincin bola berdasarkan state objek.
     */
    public double hitungLuasPermukaanLengkung() {
        this.luasPermukaanLengkung = 2 * Math.PI * this.jariJari * this.tinggiCincin;
        return this.luasPermukaanLengkung;
    }

    /**
     * Menghitung luas permukaan total cincin bola berdasarkan state objek.
     * Hasilnya disimpan di field 'luasPermukaanBola' yang diwarisi.
     */
    @Override
    public double hitungLuasPermukaan() throws TolakNilaiException {
        if (this.tinggiCincin <= 0 || this.jariJariAlas1 <= 0 || this.jariJariAlas2 <= 0) {
            throw new TolakNilaiException("Tinggi cincin dan jari-jari alas harus bernilai positif.");
        }
        double luasLengkung = this.hitungLuasPermukaanLengkung();
        double luasAlas1 = Math.PI * Math.pow(this.jariJariAlas1, 2);
        double luasAlas2 = Math.PI * Math.pow(this.jariJariAlas2, 2);

        // Menggunakan field yang diwarisi 'luasPermukaanBola'
        this.luasPermukaanBola = luasLengkung + luasAlas1 + luasAlas2;
        return this.luasPermukaanBola;
    }

    // --- METODE OVERLOAD BARU (SESUAI KONSEP BOLA) ---

    public double hitungVolume(double tinggiCincin, double jariJariAlas1, double jariJariAlas2) throws TolakNilaiException {
        if (tinggiCincin <= 0 || jariJariAlas1 <= 0 || jariJariAlas2 <= 0) {
            throw new TolakNilaiException("Tinggi cincin dan jari-jari alas harus bernilai positif.");
        }

        this.volume = (1.0 / 6.0) * Math.PI * tinggiCincin * (3 * Math.pow(jariJariAlas1, 2) + 3 * Math.pow(jariJariAlas2, 2) + Math.pow(tinggiCincin, 2));
        return this.volume;
    }

    public double hitungLuasPermukaan(double jariJariBola, double tinggiCincin, double jariJariAlas1, double jariJariAlas2) throws TolakNilaiException {
        if (tinggiCincin <= 0 || jariJariAlas1 <= 0 || jariJariAlas2 <= 0) {
            throw new TolakNilaiException("Tinggi cincin dan jari-jari alas harus bernilai positif.");
        }

        double luasLengkung = 2 * Math.PI * jariJariBola * tinggiCincin;
        double luasAlas1 = Math.PI * Math.pow(jariJariAlas1, 2);
        double luasAlas2 = Math.PI * Math.pow(jariJariAlas2, 2);

        this.luasPermukaanBola = luasLengkung + luasAlas1 + luasAlas2;
        return this.luasPermukaanBola;
    }
}