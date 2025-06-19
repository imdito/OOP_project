package geometri.Benda3D;

import geometri.TolakNilaiException;

/**
 * Merepresentasikan bangun ruang Tembereng Bola (Segmen Bola).
 * Tembereng Bola adalah bagian dari Bola yang dipotong oleh sebuah bidang datar.
 */
public class TemberengBola extends Bola {

    // Dimensi dibuat public agar konsisten dengan superclass
    public double tinggiTembereng;
    private double volume; // Volume tembereng bola
    // Field untuk menyimpan hasil perhitungan perantara
    private double jariJariAlas;
    private double luasPermukaanLengkung;

    public TemberengBola(double jariJariBola, double tinggiTembereng) {
        super(jariJariBola);
        this.tinggiTembereng = tinggiTembereng;
    }

    /**
     * Menghitung dan mengembalikan jari-jari alas tembereng.
     * Metode ini diubah namanya dari get... menjadi hitung... untuk kejelasan.
     */
    public double hitungJariJariAlas() throws TolakNilaiException {
        if (tinggiTembereng <= 0 || jariJari <= 0) {
            throw new TolakNilaiException("Tinggi tembereng dan jari-jari bola harus bernilai positif.");
        }
        // Menggunakan field public secara langsung
        this.jariJariAlas = Math.sqrt(this.tinggiTembereng * (2 * this.jariJari - this.tinggiTembereng));
        return this.jariJariAlas;
    }

    /**
     * Menghitung volume tembereng bola berdasarkan state objek.
     * V = (1/3) * pi * h^2 * (3R - h)
     */
    @Override
    public double hitungVolume() throws TolakNilaiException {
        if (tinggiTembereng <= 0 || jariJari <= 0) {
            throw new TolakNilaiException("Tinggi tembereng dan jari-jari bola harus bernilai positif.");
        }
        // Menggunakan field 'volume' yang diwarisi
        volume = (1.0 / 3.0) * Math.PI * Math.pow(tinggiTembereng, 2) * (3 * jariJariAlas - tinggiTembereng);
        return volume;
    }

    /**
     * Menghitung luas permukaan lengkung (selimut) tembereng berdasarkan state objek.
     */
    public double hitungLuasPermukaanLengkung() throws TolakNilaiException {
        if (tinggiTembereng <= 0 || jariJari <= 0) {
            throw new TolakNilaiException("Tinggi tembereng dan jari-jari bola harus bernilai positif.");
        }
        luasPermukaanLengkung = 2 * Math.PI * jariJariAlas * tinggiTembereng;
        return luasPermukaanLengkung;
    }

    /**
     * Menghitung luas permukaan total tembereng bola berdasarkan state objek.
     * L = Luas Selimut Lengkung + Luas Alas
     */
    @Override
    public double hitungLuasPermukaan() throws TolakNilaiException {
        if (tinggiTembereng <= 0 || jariJari <= 0) {
            throw new TolakNilaiException("Tinggi tembereng dan jari-jari bola harus bernilai positif.");
        }
        double luasLengkung = this.hitungLuasPermukaanLengkung();

        double luasAlasTembereng = 0;
        // Tembereng adalah bola utuh jika tingginya sama dengan diameter
        if (tinggiTembereng < (2 * jariJariAlas)) {
            // Memanggil metode hitung untuk mendapatkan jari-jari alas
            double rAlas = this.hitungJariJariAlas();
            luasAlasTembereng = Math.PI * Math.pow(rAlas, 2);
        }

        // Menggunakan field 'luasPermukaanBola' yang diwarisi
        luasPermukaanBola = luasLengkung + luasAlasTembereng;
        return luasPermukaanBola;
    }

    // --- METODE OVERLOAD BARU (SESUAI KONSEP BOLA) ---

    public double hitungVolume(double jariJariBola, double tinggiTembereng) throws TolakNilaiException {
        if (jariJariBola <= 0 || tinggiTembereng <= 0) {
            throw new TolakNilaiException("Jari-jari bola dan tinggi tembereng harus bernilai positif.");
        }
        this.volume = (1.0 / 3.0) * Math.PI * Math.pow(tinggiTembereng, 2) * (3 * jariJariBola - tinggiTembereng);
        return this.volume;
    }

    public double hitungLuasPermukaan(double jariJariBola, double tinggiTembereng) throws TolakNilaiException {
        if (jariJariBola <= 0 || tinggiTembereng <= 0) {
            throw new TolakNilaiException("Jari-jari bola dan tinggi tembereng harus bernilai positif.");
        }
        double luasLengkung = 2 * Math.PI * jariJariBola * tinggiTembereng;

        double luasAlasTembereng = 0;
        if (tinggiTembereng < (2 * jariJariBola)) {
            double rAlas = Math.sqrt(tinggiTembereng * (2 * jariJariBola - tinggiTembereng));
            luasAlasTembereng = Math.PI * Math.pow(rAlas, 2);
        }

        this.luasPermukaanBola = luasLengkung + luasAlasTembereng;
        return this.luasPermukaanBola;
    }

    public double getTinggiTembereng() {
        return this.tinggiTembereng;
    }
    public double getVolume() {
        return volume;
    }

}