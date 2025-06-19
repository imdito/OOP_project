package geometri.Benda2D;

import geometri.TolakNilaiException;

import java.lang.Math;

/**
 * Kelas yang merepresentasikan bangun datar Tembereng Lingkaran.
 * Kelas ini mewarisi sifat-sifat dari Lingkaran.
 */
public class TemberengLingkaran extends Lingkaran implements Runnable{

    public double sudutPusatDerajat;

    public TemberengLingkaran(double jariJari, double sudutPusatDerajat) throws TolakNilaiException {

        super(jariJari);
        if (jariJari <= 0) {
            throw new TolakNilaiException("Jari-jari harus bernilai positif.");
        }
        this.sudutPusatDerajat = sudutPusatDerajat;
    }

    @Override
    public double hitungLuas()  {

        double sudutRadian = Math.toRadians(this.sudutPusatDerajat);
        double luasJuring = 0.5 * this.jariJari * this.jariJari * sudutRadian;
        double luasSegitiga = 0.5 * this.jariJari * this.jariJari * Math.sin(sudutRadian);
        this.luas = luasJuring - luasSegitiga; // Menyimpan hasil ke field 'luas'
        return this.luas;
    }

    @Override
    public double hitungKeliling()   {

        double sudutRadian = Math.toRadians(this.sudutPusatDerajat);
        double panjangBusur = this.jariJari * sudutRadian;
        double panjangTaliBusur = 2 * this.jariJari * Math.sin(sudutRadian / 2.0);
        this.keliling = panjangBusur + panjangTaliBusur; // Menyimpan hasil ke field 'keliling'
        return this.keliling;
    }

    public double hitungLuas(double jariJari, double sudutPusatDerajat) throws TolakNilaiException {
        if (sudutPusatDerajat <= 0 || sudutPusatDerajat >= 360) {
            throw new TolakNilaiException("Sudut pusat harus lebih dari 0 dan kurang dari 360 derajat.");
        }
        double sudutRadian = Math.toRadians(sudutPusatDerajat);
        double luasJuring = 0.5 * jariJari * jariJari * sudutRadian;
        double luasSegitiga = 0.5 * jariJari * jariJari * Math.sin(sudutRadian);
        this.luas = luasJuring - luasSegitiga; // Mengupdate field 'luas'
        return this.luas;
    }
    public double hitungKeliling(double jariJari, double sudutPusatDerajat) throws TolakNilaiException {
        if (jariJari <= 0) {
            throw new IllegalArgumentException("Jari-jari harus bernilai positif.");
        }
        double sudutRadian = Math.toRadians(sudutPusatDerajat);
        double panjangBusur = jariJari * sudutRadian;
        double panjangTaliBusur = 2 * jariJari * Math.sin(sudutRadian / 2.0);
        this.keliling = panjangBusur + panjangTaliBusur; // Mengupdate field 'keliling'
        return this.keliling;
    }

    @Override
    public void run() {
        System.out.println(String.format("[%s] Menghitung Tembereng Lingkaran dengan jari-jari %.2f dan sudut pusat %.2f derajat.", Thread.currentThread().getName(), jariJari, sudutPusatDerajat));
        try {
            double l = hitungLuas();
            double k = hitungKeliling();
            System.out.println(String.format("[%s] >> Luas: %.2f, Keliling: %.2f.", Thread.currentThread().getName(), l, k));
        } catch (TolakNilaiException e) {
            System.err.println(String.format("[%s] GAGAL MENGHITUNG %s: %s", Thread.currentThread().getName(), getNamaBangun(), e.getMessage()));
        }
    }

    public double getSudutPusatDerajat() {
        return this.sudutPusatDerajat;
    }

}