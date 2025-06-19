package geometri.Benda3D;

import geometri.Benda2D.Lingkaran;
import geometri.TolakNilaiException;

public class Tabung extends Lingkaran {

    public double tinggi; // Dimensi dasar tabung, tetap private
    protected double volume;
    protected double luasPermukaanTabung;

    public Tabung(double jariJari, double tinggi) {
        super(jariJari);
        this.tinggi = tinggi;
    }

    public double hitungVolume() throws TolakNilaiException {
        if (super.jariJari <= 0 || tinggi <= 0) {
            throw new TolakNilaiException("Jari-jari dan tinggi harus bernilai positif.");
        }
        volume = super.hitungLuas() * tinggi;
        return volume;
    }

    public double hitungVolume(double jariJari, double tinggi) throws TolakNilaiException {
        if (jariJari <= 0 || tinggi <= 0) {
            throw new TolakNilaiException("Jari-jari dan tinggi harus bernilai positif.");
        }
        volume = super.hitungLuas(jariJari) * tinggi;
        return volume;
    }

    public double hitungLuasPermukaan() throws TolakNilaiException {
        if (super.jariJari <= 0 || tinggi <= 0) {
            throw new TolakNilaiException("Jari-jari dan tinggi harus bernilai positif.");
        }
        double luasAlasTabung = super.hitungLuas();
        double kelilingAlasTabung = super.hitungKeliling();
        double luasSelimut = kelilingAlasTabung * tinggi;
        luasPermukaanTabung = (2 * luasAlasTabung) + luasSelimut;
        return luasPermukaanTabung;
    }

    public double hitungLuasPermukaan(double jariJari, double tinggi) throws TolakNilaiException {
        if (jariJari <= 0 || tinggi <= 0) {
            throw new TolakNilaiException("Jari-jari dan tinggi harus bernilai positif.");
        }
        double luasAlasTabung = super.hitungLuas(jariJari);
        double kelilingAlasTabung = super.hitungKeliling(jariJari);
        double luasSelimut = kelilingAlasTabung * tinggi;
        luasPermukaanTabung = (2 * luasAlasTabung) + luasSelimut;
        return luasPermukaanTabung;
    }

    public double getTinggi() {
        return tinggi;
    }
    public double getVolume() {
        return volume;
    }
    public double getLuasPermukaanTabung() {
        return luasPermukaanTabung;
    }
}