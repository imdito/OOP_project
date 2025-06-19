package geometri.Benda2D;

import geometri.TolakNilaiException;

public class JuringLingkaran extends Lingkaran implements Runnable{

    public double sudutBusurDerajat; // Dimensi spesifik juring

    public JuringLingkaran(double jariJari) throws TolakNilaiException {
        super(jariJari);
        if (jariJari <= 0) {
            throw new TolakNilaiException("Jari-jari harus bernilai positif.");
        }
        this.sudutBusurDerajat = sudutBusurDerajat; // 'this' diperlukan
    }

    @Override
    public double hitungLuas()   {
        // Menggunakan super.jariJari (atau cukup jariJari karena protected)
        // Menyimpan hasil ke field 'luas' yang diwarisi dari Lingkaran, tapi dengan nilai Juring

        luas = (sudutBusurDerajat / 360.0) * (Math.PI * jariJari * jariJari);
        return luas;
    }

    public double hitungLuas(double sudutBusurDerajat) throws TolakNilaiException {
        if (sudutBusurDerajat <= 0 || sudutBusurDerajat > 360) {
            throw new TolakNilaiException("Sudut busur harus bernilai positif dan tidak lebih dari 360 derajat.");
        }
     
        luas = (sudutBusurDerajat / 360.0) * (Math.PI * jariJari * jariJari);
        return luas;
    }

    @Override
    public double hitungKeliling() {
        double panjangBusur = (sudutBusurDerajat / 360.0) * (2 * Math.PI * jariJari);
        // Menyimpan hasil ke field 'keliling' yang diwarisi, tapi dengan nilai Juring
        keliling = panjangBusur + (2 * jariJari);
        return keliling;
    }

    public double hitungKeliling(double sudutBusurDerajat) throws TolakNilaiException {
        if (sudutBusurDerajat <= 0 || sudutBusurDerajat > 360) {
            throw new TolakNilaiException("Sudut busur harus bernilai positif dan tidak lebih dari 360 derajat.");
        }
        double panjangBusur = (sudutBusurDerajat / 360.0) * (2 * Math.PI * jariJari);
        keliling = panjangBusur + (2 * jariJari);
        return keliling;
    }

    @Override
    public void run() {
        System.out.println(String.format("[%s] Menghitung Juring Lingkaran dengan jari-jari %.2f dan sudut %.2f derajat.", Thread.currentThread().getName(), jariJari, sudutBusurDerajat));
        double l = hitungLuas();
        double k = hitungKeliling();
        System.out.println(String.format("[%s] >> Luas: %.2f, Keliling: %.2f.", Thread.currentThread().getName(), l, k));
    }

    public double getSudutBusurDerajat() {
        return sudutBusurDerajat;
    }
    
}