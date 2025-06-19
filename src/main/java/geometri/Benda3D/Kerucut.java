package geometri.Benda3D;

import geometri.Benda2D.Lingkaran;
import geometri.TolakNilaiException;

public class Kerucut extends Lingkaran {

    public double tinggi; // Dimensi dasar kerucut
    public double garisPelukisKerucut; // Menyimpan hasil perhitungan
    public double volume;
    public double luasPermukaanKerucut;

    public Kerucut(double jariJari, double tinggi) {
        super(jariJari);
        if (jariJari <= 0 || tinggi <= 0) {
            throw new IllegalArgumentException("Jari-jari dan tinggi harus lebih besar dari nol.");
        }
        this.tinggi = tinggi;
    }

    public double getGarisPelukis() {
        garisPelukisKerucut = Math.sqrt(Math.pow(super.jariJari, 2) + Math.pow(tinggi, 2));
        return garisPelukisKerucut;
    }

    public double hitungVolume() throws TolakNilaiException {
        if (tinggi <= 0) {
            throw new TolakNilaiException("Jari-jari dan tinggi harus bernilai positif.");
        }
        volume = (1.0 / 3.0) * super.hitungLuas() * tinggi;
        return volume;
    }

    public double hitungVolume(double jariJariKerucut, double tinggiKerucut) throws TolakNilaiException {
        if (jariJariKerucut <= 0 || tinggiKerucut <= 0) {
            throw new TolakNilaiException("Jari-jari dan tinggi harus bernilai positif.");
        }

        volume = (1.0 / 3.0) * super.hitungLuas(jariJariKerucut) * tinggiKerucut;
        return volume;
    }

    public double hitungLuasPermukaan() throws TolakNilaiException {
        if (tinggi <= 0) {
            throw new TolakNilaiException("Jari-jari dan tinggi harus bernilai positif.");
        }
        double luasAlasKerucut = super.hitungLuas();
        double s = getGarisPelukis();
        double luasSelimut = Math.PI * jariJari * s;
        luasPermukaanKerucut = luasAlasKerucut + luasSelimut;
        return luasPermukaanKerucut;
    }
    public double hitungLuasPermukaan(double jariJariKerucut, double tinggiKerucut) throws TolakNilaiException {
        if (jariJariKerucut <= 0 || tinggiKerucut <= 0) {
            throw new TolakNilaiException("Jari-jari dan tinggi harus bernilai positif.");
        }
        double luasAlasKerucut = super.hitungLuas(jariJariKerucut);
        double s = Math.sqrt(Math.pow(jariJariKerucut, 2) + Math.pow(tinggiKerucut, 2));
        double luasSelimut = Math.PI * jariJariKerucut * s;
        luasPermukaanKerucut = luasAlasKerucut + luasSelimut;
        return luasPermukaanKerucut;
    }

}