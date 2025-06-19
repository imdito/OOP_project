package geometri.Benda2D;

import geometri.TolakNilaiException;

public class JuringLingkaran extends Lingkaran implements Runnable{

    public double sudutBusurDerajat; // Dimensi spesifik juring

    public JuringLingkaran(double jariJari){
        super(jariJari);
        this.sudutBusurDerajat = sudutBusurDerajat; // 'this' diperlukan
    }

    @Override
    public double hitungLuas() throws TolakNilaiException {
        // Menggunakan super.jariJari (atau cukup jariJari karena protected)
        // Menyimpan hasil ke field 'luas' yang diwarisi dari Lingkaran, tapi dengan nilai Juring
        if (sudutBusurDerajat <= 0 || sudutBusurDerajat > 360) {
            throw new TolakNilaiException("Sudut busur harus bernilai positif dan tidak lebih dari 360 derajat.");
        }
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

    public double hitungKeliling(double sudutBusurDerajat) {
        double panjangBusur = (sudutBusurDerajat / 360.0) * (2 * Math.PI * jariJari);
        keliling = panjangBusur + (2 * jariJari);
        return keliling;
    }

    @Override
    public void run() {
        System.out.println(String.format("[%s] Menghitung Juring Lingkaran dengan jari-jari %.2f dan sudut %.2f derajat.", Thread.currentThread().getName(), jariJari, sudutBusurDerajat));
        try {
            double l = hitungLuas();
            double k = hitungKeliling();
            System.out.println(String.format("[%s] >> Luas: %.2f, Keliling: %.2f.", Thread.currentThread().getName(), l, k));
        } catch (TolakNilaiException e) {
            System.err.println(String.format("[%s] GAGAL MENGHITUNG %s: %s", Thread.currentThread().getName(), getNamaBangun(), e.getMessage()));
        }
    }

    public double getSudutBusurDerajat() {
        return sudutBusurDerajat;
    }
    
}