package geometri;

/**
 * Interface untuk semua bangun geometri.
 * Menyediakan kontrak untuk menghitung luas dan keliling.
 */
public interface BangunGeometri {

    /**
     * Menghitung luas bangun geometri.
     * @return luas bangun geometri.
     */
    double hitungLuas() throws TolakNilaiException;

    /**
     * Menghitung keliling bangun geometri.
     *
     * @return
     */
    double hitungKeliling() throws TolakNilaiException;
}