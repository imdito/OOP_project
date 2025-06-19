package geometri.Benda3D;

import geometri.TolakNilaiException;

/**
 * Merepresentasikan bangun ruang Kerucut Terpancung (Frustum).
 * Kelas ini mewarisi sifat-sifat dari Kerucut.
 */
public class KerucutTerpancung extends Kerucut {

    // Dimensi spesifik frustum, dibuat public agar konsisten
    public double jariJariAtas;
    public double tinggiFrustum;

    // Field 'garisPelukisKerucut', 'volume', dan 'luasPermukaanKerucut'
    // sudah diwarisi (inherited) dari kelas Kerucut.

    public KerucutTerpancung(double jariJariBawah, double jariJariAtas, double tinggiFrustum) {
        // Catatan: Panggilan super() ini akan mengisi field 'tinggi' di kelas Kerucut
        // dengan nilai 'tinggiFrustum', yang tidak digunakan di kelas ini.
        // Ini adalah konsekuensi dari struktur pewarisan yang ada.
        super(jariJariBawah, tinggiFrustum);
        this.jariJariAtas = jariJariAtas;
        this.tinggiFrustum = tinggiFrustum;
    }

    /**
     * Menghitung garis pelukis (s) dari frustum.
     * Metode ini diubah namanya dari get... menjadi hitung... untuk kejelasan.
     * Hasilnya disimpan di field 'garisPelukisKerucut' yang diwarisi.
     */
    public double hitungGarisPelukis() {
        double selisihJariJari = this.jariJari - this.jariJariAtas; // this.jariJari adalah jari-jari bawah
        // Menggunakan field 'garisPelukisKerucut' yang diwarisi
        this.garisPelukisKerucut = Math.sqrt(Math.pow(this.tinggiFrustum, 2) + Math.pow(selisihJariJari, 2));
        return this.garisPelukisKerucut;
    }

    /**
     * Menghitung volume kerucut terpancung berdasarkan state objek.
     * Rumus: V = (1/3) * pi * h * (R^2 + R*r + r^2)
     */
    @Override
    public double hitungVolume() throws TolakNilaiException {
        if (this.jariJari <= 0 || this.jariJariAtas <= 0 || this.tinggiFrustum <= 0) {
            throw new TolakNilaiException("Jari-jari bawah, jari-jari atas, dan tinggi frustum harus bernilai positif.");
        }
        double R = this.jariJari; // Jari-jari bawah
        double r = this.jariJariAtas;
        // Menggunakan field 'volume' yang diwarisi
        this.volume = (1.0 / 3.0) * Math.PI * this.tinggiFrustum * (R*R + R*r + r*r);
        return this.volume;
    }

    /**
     * Menghitung luas permukaan total kerucut terpancung berdasarkan state objek.
     * L = Luas Alas Bawah + Luas Alas Atas + Luas Selimut
     */
    @Override
    public double hitungLuasPermukaan() throws TolakNilaiException {
        if (this.jariJari <= 0 || this.jariJariAtas <= 0 || this.tinggiFrustum <= 0) {
            throw new TolakNilaiException("Jari-jari bawah, jari-jari atas, dan tinggi frustum harus bernilai positif.");
        }
        double R = this.jariJari; // Jari-jari bawah
        double r = this.jariJariAtas;

        double luasAlasBawah = Math.PI * R * R;
        double luasAlasAtas = Math.PI * r * r;

        // Memanggil metode hitung untuk mendapatkan garis pelukis
        double s = this.hitungGarisPelukis();
        double luasSelimutFrustum = Math.PI * (R + r) * s;

        // Menggunakan field 'luasPermukaanKerucut' yang diwarisi
        this.luasPermukaanKerucut = luasAlasBawah + luasAlasAtas + luasSelimutFrustum;
        return this.luasPermukaanKerucut;
    }

    // --- METODE OVERLOAD BARU (SESUAI KONSEP KELAS INDUK) ---

    public double hitungVolume(double jariJariBawah, double jariJariAtas, double tinggiFrustum) throws TolakNilaiException {
        if (jariJariBawah <= 0 || jariJariAtas <= 0 || tinggiFrustum <= 0) {
            throw new TolakNilaiException("Jari-jari bawah, jari-jari atas, dan tinggi frustum harus bernilai positif.");
        }
        this.volume = (1.0 / 3.0) * Math.PI * tinggiFrustum * (Math.pow(jariJariBawah, 2) + jariJariBawah * jariJariAtas + Math.pow(jariJariAtas, 2));
        return this.volume;
    }

    public double hitungLuasPermukaan(double jariJariBawah, double jariJariAtas, double tinggiFrustum)  throws TolakNilaiException {
        if (jariJariBawah <= 0 || jariJariAtas <= 0 || tinggiFrustum <= 0) {
            throw new TolakNilaiException("Jari-jari bawah, jari-jari atas, dan tinggi frustum harus bernilai positif.");
        }
        double luasAlasBawah = Math.PI * jariJariBawah * jariJariBawah;
        double luasAlasAtas = Math.PI * jariJariAtas * jariJariAtas;

        double selisihJariJari = jariJariBawah - jariJariAtas;
        double s = Math.sqrt(Math.pow(tinggiFrustum, 2) + Math.pow(selisihJariJari, 2));
        double luasSelimut = Math.PI * (jariJariBawah + jariJariAtas) * s;

        this.luasPermukaanKerucut = luasAlasBawah + luasAlasAtas + luasSelimut;
        return this.luasPermukaanKerucut;
    }
}