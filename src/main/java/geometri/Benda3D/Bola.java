package geometri.Benda3D;

import geometri.Benda2D.Lingkaran;
import geometri.TolakNilaiException;

public class Bola extends Lingkaran implements Runnable{

    public double volume;
    public double luasPermukaanBola;

    public Bola(double jariJari) throws TolakNilaiException{
        super(jariJari);
        if (jariJari <= 0) {
            throw new TolakNilaiException("Jari-jari harus bernilai positif.");
        }
    }
    public double hitungVolume() {
        volume = (4.0 / 3.0) * Math.PI * Math.pow(jariJari, 3);
        return volume;
    }

    public double hitungVolume(double jariJari) throws TolakNilaiException{
        if (jariJari <= 0) {
            throw new TolakNilaiException("Jari-jari harus bernilai positif.");
        }
        volume = (4.0 / 3.0) * Math.PI * Math.pow(jariJari, 3);
        return volume;
    }

    public double hitungLuasPermukaan() {
        luasPermukaanBola = 4 * Math.PI * Math.pow(super.jariJari, 2);
        return luasPermukaanBola;
    }

    public double hitungLuasPermukaan(double jariJari) throws TolakNilaiException{
        if (jariJari <= 0) {
            throw new TolakNilaiException("Jari-jari harus bernilai positif.");
        }
        luasPermukaanBola = 4 * Math.PI * Math.pow(jariJari, 2);
        return luasPermukaanBola;
    }

    @Override
    public void run() {
        System.out.println(String.format("[%s] Menghitung Bola dengan jari-jari %.2f.", Thread.currentThread().getName(), jariJari));
        double v = hitungVolume();
        double lp = hitungLuasPermukaan();
        System.out.println(String.format("[%s] >> Volume: %.2f, Luas Permukaan: %.2f.",
                Thread.currentThread().getName(), v, lp));
    }
}