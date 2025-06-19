package geometri.Benda3D;

import geometri.Benda2D.Persegi;
import geometri.TolakNilaiException;

public class PrismaPersegi extends Persegi {


    private double volume;
    private double luasPermukaanPrisma;

    public PrismaPersegi(double sisiAlas) {
        super(sisiAlas);
    }

    public double hitungVolume() throws TolakNilaiException {
        if (super.sisi <= 0) {
            throw new TolakNilaiException("Sisi persegi harus bernilai positif.");
        }
        // super.hitungLuas() akan menghitung dan menyimpan luas alas di objek Persegi
        // dan mengembalikannya
        volume = super.hitungLuas() * super.sisi; // Menggunakan sisi sebagai tinggi prisma
        return volume;
    }

    public double hitungVolume(double sisiPersegi) throws  TolakNilaiException {
        if (sisiPersegi <= 0) {
            throw new TolakNilaiException("Sisi persegi harus bernilai positif.");
        }
        volume = super.hitungLuas(sisiPersegi) * sisiPersegi;
        return volume;
    }

    public double hitungLuasPermukaan() throws TolakNilaiException {
        if (super.sisi <= 0) {
            throw new TolakNilaiException("Sisi persegi harus bernilai positif.");
        }
        luasPermukaanPrisma = 6 * Math.sqrt(super.hitungLuas());
        return luasPermukaanPrisma;
    }

    public double hitungLuasPermukaan(double sisiPersegi) throws TolakNilaiException {
        if (sisiPersegi <= 0) {
            throw new TolakNilaiException("Sisi persegi harus bernilai positif.");
        }
        luasPermukaanPrisma = 6 * Math.sqrt(super.hitungLuas(sisiPersegi));
        return luasPermukaanPrisma;
    }

    public double getVolume() {
        return volume;
    }
    public double getLuasPermukaanPrisma() {
        return luasPermukaanPrisma;
    }
}