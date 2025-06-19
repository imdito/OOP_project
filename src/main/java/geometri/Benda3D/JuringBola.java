package geometri.Benda3D;

import geometri.TolakNilaiException;

/**
 * Merepresentasikan Juring Bola (Sektor Bola).
 * Juring Bola dibentuk oleh sebuah tembereng bola dan kerucut yang puncaknya
 * berada di pusat bola dan alasnya berimpit dengan alas tembereng.
 */
public class JuringBola extends Bola {

    public double tinggiTemberengDasar;
    protected double jariJariAlasTembereng;

    public JuringBola(double jariJariBola, double tinggiTemberengDasar) {
        super(jariJariBola);
        this.tinggiTemberengDasar = tinggiTemberengDasar;
    }

    /**
     * Menghitung dan mengembalikan jari-jari alas tembereng yang membentuk juring.
     * Metode ini memiliki efek samping, yaitu menyimpan hasilnya ke field.
     */
    public double hitungJariJariAlasTembereng() throws IllegalArgumentException {
        if (this.tinggiTemberengDasar <= 0 || this.jariJari <= 0) {
            throw new IllegalArgumentException("Tinggi tembereng dan jari-jari harus bernilai positif.");
        }
        this.jariJariAlasTembereng = Math.sqrt(this.tinggiTemberengDasar * (2 * this.jariJari - this.tinggiTemberengDasar));
        return this.jariJariAlasTembereng;
    }

    /**
     * Menghitung volume juring bola berdasarkan state objek.
     * V = (2/3) * pi * R^2 * h
     */
    @Override
    public double hitungVolume() throws TolakNilaiException {
        if (this.tinggiTemberengDasar <= 0 || this.jariJari <= 0) {
            throw new TolakNilaiException("Tinggi tembereng dan jari-jari harus bernilai positif.");
        }
        this.volume = (2.0 / 3.0) * Math.PI * Math.pow(this.jariJari, 2) * this.tinggiTemberengDasar;
        return this.volume;
    }

    /**
     * Menghitung luas permukaan juring bola berdasarkan state objek.
     * L = Luas Tembereng + Luas Selimut Kerucut
     */
    @Override
    public double hitungLuasPermukaan() throws TolakNilaiException {
        if (this.tinggiTemberengDasar <= 0 || this.jariJari <= 0) {
            throw new TolakNilaiException("Tinggi tembereng dan jari-jari harus bernilai positif.");
        }
        double luasLengkungTembereng = 2 * Math.PI * this.jariJari * this.tinggiTemberengDasar;

        // Hitung luas selimut kerucut di dalamnya
        double luasSelimutKerucut = 0;
        if (this.tinggiTemberengDasar < (2 * this.jariJari)) { // Hanya ada kerucut jika bukan bola utuh
            // Pastikan jari-jari alas sudah dihitung
            if(this.jariJariAlasTembereng == 0) {
                this.hitungJariJariAlasTembereng();
            }
            luasSelimutKerucut = Math.PI * this.jariJariAlasTembereng * this.jariJari;
        }

        this.luasPermukaanBola = luasLengkungTembereng + luasSelimutKerucut;
        return this.luasPermukaanBola;
    }

    // --- METODE OVERLOAD BARU (SESUAI KONSEP BOLA) ---

    public double hitungVolume(double jariJariBola, double tinggiTemberengDasar) throws TolakNilaiException {
        if (jariJariBola <= 0 || tinggiTemberengDasar <= 0) {
            throw new TolakNilaiException("Jari-jari dan tinggi tembereng harus bernilai positif.");
        }
        this.volume = (2.0 / 3.0) * Math.PI * Math.pow(jariJariBola, 2) * tinggiTemberengDasar;
        return this.volume;
    }

    public double hitungLuasPermukaan(double jariJariBola, double tinggiTemberengDasar) throws TolakNilaiException {
        if (jariJariBola <= 0 || tinggiTemberengDasar <= 0) {
            throw new TolakNilaiException("Jari-jari dan tinggi tembereng harus bernilai positif.");
        }

        double luasLengkungTembereng = 2 * Math.PI * jariJariBola * tinggiTemberengDasar;

        double luasSelimutKerucut = 0;
        if (tinggiTemberengDasar < (2 * jariJariBola)) {
            double jariJariAlas = Math.sqrt(tinggiTemberengDasar * (2 * jariJariBola - tinggiTemberengDasar));
            luasSelimutKerucut = Math.PI * jariJariAlas * jariJariBola;
        }

        this.luasPermukaanBola = luasLengkungTembereng + luasSelimutKerucut;
        return this.luasPermukaanBola;
    }
}