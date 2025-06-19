package geometri.Benda3D;

import geometri.Benda2D.Persegi;
import geometri.TolakNilaiException;

public class LimasPersegi extends Persegi {

    public double tinggiLimas;
    private double volume;
    private double luasPermukaanLimas;


    public LimasPersegi(double sisiAlas, double tinggiLimas) {
        super(sisiAlas);
        this.tinggiLimas = tinggiLimas;
    }

    public double hitungVolume() throws TolakNilaiException {
        if (tinggiLimas <= 0) {
            throw new TolakNilaiException("Tinggi limas harus bernilai positif.");
        }
        volume = (1.0 / 3.0) * super.hitungLuas() * tinggiLimas;
        return volume;
    }

    public double hitungVolume(double sisiPersegi, double tinggiLimas) throws TolakNilaiException {
        if (sisiPersegi <= 0 || tinggiLimas <= 0) {
            throw new TolakNilaiException("Sisi persegi dan tinggi limas harus bernilai positif.");
        }
        volume = (1.0 / 3.0) * super.hitungLuas(sisiPersegi) * tinggiLimas;
        return volume;
    }

    public double hitungLuasPermukaan() throws TolakNilaiException {
        if (tinggiLimas <= 0) {
            throw new TolakNilaiException("Tinggi limas harus bernilai positif.");
        }

        luasPermukaanLimas = super.hitungLuas() + (2 * super.sisi * tinggiLimas);
        return luasPermukaanLimas;
    }

    public double hitungLuasPermukaan(double sisiPersegi, double tinggiLimas) throws TolakNilaiException {
        if (sisiPersegi <= 0 || tinggiLimas <= 0) {
            throw new TolakNilaiException("Sisi persegi dan tinggi limas harus bernilai positif.");
        }
        luasPermukaanLimas = super.hitungLuas(sisiPersegi) + (2 * super.sisi * tinggiLimas);
        return luasPermukaanLimas;
    }

    public double getTinggiLimas() {
        return tinggiLimas;
    }
    public double getVolume() {
        return volume;
    }
    public double getLuasPermukaanLimas() {
        return luasPermukaanLimas;
    }

}