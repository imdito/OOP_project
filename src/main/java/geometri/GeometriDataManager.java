package geometri;

import geometri.Benda2D.*;
import geometri.Benda3D.*;

import java.util.ArrayList;
import java.util.List;

public class GeometriDataManager {
    private static GeometriDataManager instance;

    // Objek-objek Geometri yang akan dikelola
    private Lingkaran lingkaran;
    private Bola bola;
    private CincinBola cincinBola;
    private JuringBola juringBola;
    private TemberengBola temberengBola;
    private Kerucut kerucut;
    private Tabung tabung;
    private JuringLingkaran juringLingkaran;
    private TemberengLingkaran temberengLingkaran;

    private Persegi persegi;
    private LimasPersegi limasPersegi;
    private PrismaPersegi prismaPersegi; // Kubus
    private PersegiPanjang persegiPanjang;
    private LimasPersegiPanjang limasPersegiPanjang;
    private PrismaPersegiPanjang prismaPersegiPanjang; // Balok
    private Segitiga segitiga;
    private LimasSegitiga limasSegitiga;
    private PrismaSegitiga prismaSegitiga;
    private BelahKetupat belahKetupat;
    private LimasBelahKetupat limasBelahKetupat;
    private PrismaBelahKetupat prismaBelahKetupat;
    private JajarGenjang jajarGenjang;
    private LimasJajarGenjang limasJajarGenjang;
    private PrismaJajarGenjang prismaJajarGenjang;
    private LayangLayang layangLayang;
    private LimasLayangLayang limasLayangLayang;
    private PrismaLayangLayang prismaLayangLayang;
    private Trapesium trapesium;
    private LimasTrapesium limasTrapesium;
    private PrismaTrapesium prismaTrapesium;
    private KerucutTerpancung kerucutTerpancung;

    // List untuk objek yang jari-jarinya akan diupdate secara global oleh Lingkaran
    private List<Lingkaran> updatableFromLingkaran;
    // List untuk objek yang jari-jarinya akan diupdate secara global oleh Bola
    private List<Bola> updatableFromBola;


    private GeometriDataManager() {
        // Inisialisasi default awal
        double defaultJariJari2D = 7.0;
        double defaultJariJariBola = 5.0;
        double defaultSisiPersegi = 8.0;
        double defaultPanjangPersegiPanjang = 10.0;
        double defaultLebarPersegiPanjang = 6.0;
        double defaultAlasSegitiga = 10.0;
        double defaultTinggiSegitiga = 8.0;
        double defaultSisiSegitigaA = 10.0;
        double defaultSisiSegitigaB = 10.0;
        double defaultSisiSegitigaC = 10.0;
        double defaultDiagonal1BK = 12.0;
        double defaultDiagonal2BK = 8.0;
        double defaultSisiBK = Math.sqrt(Math.pow(defaultDiagonal1BK / 2.0, 2) + Math.pow(defaultDiagonal2BK / 2.0, 2)); // Dihitung
        double defaultAlasJG = 12.0;
        double defaultTinggiJG = 7.0;
        double defaultSisiMiringJG = 8.0;
        double defaultDiagonal1LL = 15.0;
        double defaultDiagonal2LL = 10.0;
        double defaultSisiPendekLL = Math.sqrt(Math.pow(defaultDiagonal1LL / 2.0, 2) + Math.pow(defaultDiagonal2LL * 0.4 / 2.0, 2)); // Contoh estimasi
        double defaultSisiPanjangLL = Math.sqrt(Math.pow(defaultDiagonal1LL / 2.0, 2) + Math.pow(defaultDiagonal2LL * 1.6 / 2.0, 2)); // Contoh estimasi

        double defaultSisiAtasTrapesium = 6.0;
        double defaultSisiBawahTrapesium = 10.0;
        double defaultTinggiTrapesium = 5.0;
        double horizontalDiffTrapesium = (defaultSisiBawahTrapesium - defaultSisiAtasTrapesium) / 2.0;
        double defaultSisiKiriTrapesium = Math.sqrt(Math.pow(horizontalDiffTrapesium, 2) + Math.pow(defaultTinggiTrapesium, 2));
        double defaultSisiKananTrapesium = defaultSisiKiriTrapesium; // Asumsi sama kaki

        double defaultTinggiLimas = 10.0;
        double defaultTinggiPrisma = 15.0;
        double defaultTinggiKerucut = 12.0;
        double defaultTinggiTabung = 20.0;
        double defaultTinggiTemberengBola = 2.0; // Harus < 2 * defaultJariJariBola
        double defaultTinggiJuringBola = 2.0;    // Harus < 2 * defaultJariJariBola

        // Inisialisasi objek-objek Lingkaran dan turunannya (non-Bola)
        try {
            lingkaran = new Lingkaran(defaultJariJari2D);
        } catch (TolakNilaiException e) {
            throw new RuntimeException(e);
        }
        try {
            juringLingkaran = new JuringLingkaran(defaultJariJari2D);
        } catch (TolakNilaiException e) {
            throw new RuntimeException(e);
        }
        juringLingkaran.sudutBusurDerajat = 90.0;
        try {
            temberengLingkaran = new TemberengLingkaran(defaultJariJari2D, 3.0); // Tinggi tembereng adalah sudut pusat pada konstruktor Anda
        } catch (TolakNilaiException e) {
            throw new RuntimeException(e);
        }
        try {
            kerucut = new Kerucut(defaultJariJari2D, defaultTinggiKerucut);
        } catch (TolakNilaiException e) {
            throw new RuntimeException(e);
        }
        try {
            tabung = new Tabung(defaultJariJari2D, defaultTinggiTabung);
        } catch (TolakNilaiException e) {
            throw new RuntimeException(e);
        }

        // Objek 3D yang berawal dari Bola
        try {
            bola = new Bola(defaultJariJariBola);
        } catch (TolakNilaiException e) {
            throw new RuntimeException(e);
        }
        try {
            cincinBola = new CincinBola(defaultJariJariBola, 10.0, 5.0, 10.0); // Jari-jari bola, tinggi cincin, jari-jari alas 1 & 2
        } catch (TolakNilaiException e) {
            throw new RuntimeException(e);
        }
        try {
            juringBola = new JuringBola(defaultJariJariBola, defaultTinggiJuringBola);
        } catch (TolakNilaiException e) {
            throw new RuntimeException(e);
        }
        try {
            temberengBola = new TemberengBola(defaultJariJariBola, defaultTinggiTemberengBola);
        } catch (TolakNilaiException e) {
            throw new RuntimeException(e);
        }

        // Inisialisasi objek-objek Persegi dan turunannya
        try {
            persegi = new Persegi(defaultSisiPersegi);
        } catch (TolakNilaiException e) {
            throw new RuntimeException(e);
        }
        try {
            limasPersegi = new LimasPersegi(defaultSisiPersegi, defaultTinggiLimas);
        } catch (TolakNilaiException e) {
            throw new RuntimeException(e);
        }
        try {
            prismaPersegi = new PrismaPersegi(defaultSisiPersegi); // Kubus (tinggi = sisi)
        } catch (TolakNilaiException e) {
            throw new RuntimeException(e);
        }

        // Inisialisasi objek-objek Persegi Panjang dan turunannya
        try {
            persegiPanjang = new PersegiPanjang(defaultPanjangPersegiPanjang, defaultLebarPersegiPanjang);
        } catch (TolakNilaiException e) {
            throw new RuntimeException(e);
        }
        try {
            limasPersegiPanjang = new LimasPersegiPanjang(defaultPanjangPersegiPanjang, defaultLebarPersegiPanjang, defaultTinggiLimas);
        } catch (TolakNilaiException e) {
            throw new RuntimeException(e);
        }
        try {
            prismaPersegiPanjang = new PrismaPersegiPanjang(defaultPanjangPersegiPanjang, defaultLebarPersegiPanjang, defaultTinggiPrisma);
        } catch (TolakNilaiException e) {
            throw new RuntimeException(e);
        }

        // Inisialisasi objek-objek Segitiga dan turunannya
        try {
            segitiga = new Segitiga(defaultAlasSegitiga, defaultTinggiSegitiga, defaultSisiSegitigaA, defaultSisiSegitigaB, defaultSisiSegitigaC);
        } catch (TolakNilaiException e) {
            throw new RuntimeException(e);
        }
        try {
            limasSegitiga = new LimasSegitiga(defaultAlasSegitiga, defaultTinggiSegitiga, defaultSisiSegitigaA, defaultSisiSegitigaB, defaultSisiSegitigaC, defaultTinggiLimas, 12.0, 12.0, 12.0); // Tinggi sisi tegak contoh
        } catch (TolakNilaiException e) {
            throw new RuntimeException(e);
        }
        try {
            prismaSegitiga = new PrismaSegitiga(defaultAlasSegitiga, defaultTinggiSegitiga, defaultSisiSegitigaA, defaultSisiSegitigaB, defaultSisiSegitigaC, defaultTinggiPrisma);
        } catch (TolakNilaiException e) {
            throw new RuntimeException(e);
        }

        // Inisialisasi objek-objek Belah Ketupat dan turunannya
        try {
            belahKetupat = new BelahKetupat(defaultDiagonal1BK, defaultDiagonal2BK, defaultSisiBK);
        } catch (TolakNilaiException e) {
            throw new RuntimeException(e);
        }
        try {
            limasBelahKetupat = new LimasBelahKetupat(defaultDiagonal1BK, defaultDiagonal2BK, defaultSisiBK, defaultTinggiLimas);
        } catch (TolakNilaiException e) {
            throw new RuntimeException(e);
        }
        try {
            prismaBelahKetupat = new PrismaBelahKetupat(defaultDiagonal1BK, defaultDiagonal2BK, defaultSisiBK, defaultTinggiPrisma);
        } catch (TolakNilaiException e) {
            throw new RuntimeException(e);
        }

        // Inisialisasi objek-objek Jajar Genjang dan turunannya
        try {
            jajarGenjang = new JajarGenjang(defaultAlasJG, defaultTinggiJG, defaultSisiMiringJG);
        } catch (TolakNilaiException e) {
            throw new RuntimeException(e);
        }
        try {
            limasJajarGenjang = new LimasJajarGenjang(defaultAlasJG, defaultTinggiJG, defaultSisiMiringJG, defaultTinggiLimas, 9.0, 9.0); // Tinggi sisi tegak contoh
        } catch (TolakNilaiException e) {
            throw new RuntimeException(e);
        }
        try {
            prismaJajarGenjang = new PrismaJajarGenjang(defaultAlasJG, defaultTinggiJG, defaultSisiMiringJG, defaultTinggiPrisma);
        } catch (TolakNilaiException e) {
            throw new RuntimeException(e);
        }

        // Inisialisasi objek-objek Layang-Layang dan turunannya
        try {
            layangLayang = new LayangLayang(defaultDiagonal1LL, defaultDiagonal2LL, defaultSisiPendekLL, defaultSisiPanjangLL);
        } catch (TolakNilaiException e) {
            throw new RuntimeException(e);
        }
        try {
            limasLayangLayang = new LimasLayangLayang(defaultDiagonal1LL, defaultDiagonal2LL, defaultSisiPendekLL, defaultSisiPanjangLL, defaultTinggiLimas, 10.0, 14.0); // Tinggi sisi tegak contoh
        } catch (TolakNilaiException e) {
            throw new RuntimeException(e);
        }
        try {
            prismaLayangLayang = new PrismaLayangLayang(defaultDiagonal1LL, defaultDiagonal2LL, defaultSisiPendekLL, defaultSisiPanjangLL, defaultTinggiPrisma);
        } catch (TolakNilaiException e) {
            throw new RuntimeException(e);
        }

        // Inisialisasi objek-objek Trapesium dan turunannya
        try {
            trapesium = new Trapesium(defaultSisiAtasTrapesium, defaultSisiBawahTrapesium, defaultTinggiTrapesium, defaultSisiKiriTrapesium, defaultSisiKananTrapesium);
        } catch (TolakNilaiException e) {
            throw new RuntimeException(e);
        }
        try {
            limasTrapesium = new LimasTrapesium(defaultSisiAtasTrapesium, defaultSisiBawahTrapesium, defaultTinggiTrapesium, defaultSisiKiriTrapesium, defaultSisiKananTrapesium, defaultTinggiLimas, 6.0, 11.0, 7.0, 7.0); // Tinggi sisi tegak contoh
        } catch (TolakNilaiException e) {
            throw new RuntimeException(e);
        }
        try {
            prismaTrapesium = new PrismaTrapesium(defaultSisiAtasTrapesium, defaultSisiBawahTrapesium, defaultTinggiTrapesium, defaultSisiKiriTrapesium, defaultSisiKananTrapesium, defaultTinggiPrisma);
        } catch (TolakNilaiException e) {
            throw new RuntimeException(e);
        }

        try {
            kerucutTerpancung = new KerucutTerpancung(10.0, 5.0, 8.0);
        } catch (TolakNilaiException e) {
            throw new RuntimeException(e);
        }

        // Inisialisasi daftar objek yang jari-jarinya akan diupdate secara global oleh Lingkaran (non-Bola)
        updatableFromLingkaran = new ArrayList<>();
        updatableFromLingkaran.add(lingkaran);
        updatableFromLingkaran.add(juringLingkaran);
        updatableFromLingkaran.add(temberengLingkaran);
        updatableFromLingkaran.add(kerucut);
        updatableFromLingkaran.add(tabung);

        // Inisialisasi daftar objek yang jari-jarinya akan diupdate secara global oleh Bola
        updatableFromBola = new ArrayList<>();
        updatableFromBola.add(bola);
        updatableFromBola.add(cincinBola);
        updatableFromBola.add(juringBola);
        updatableFromBola.add(temberengBola);
    }

    public static GeometriDataManager getInstance() {
        if (instance == null) {
            instance = new GeometriDataManager();
        }
        return instance;
    }

    // --- Getter untuk objek-objek geometri ---
    public Lingkaran getLingkaran() { return lingkaran; }
    public Bola getBola() { return bola; }
    public CincinBola getCincinBola() { return cincinBola; }
    public JuringBola getJuringBola() { return juringBola; }
    public TemberengBola getTemberengBola() { return temberengBola; }
    public Kerucut getKerucut() { return kerucut; }
    public Tabung getTabung() { return tabung; }
    public JuringLingkaran getJuringLingkaran() { return juringLingkaran; }
    public TemberengLingkaran getTemberengLingkaran() { return temberengLingkaran; }

    public Persegi getPersegi() { return persegi; }
    public LimasPersegi getLimasPersegi() { return limasPersegi; }
    public PrismaPersegi getPrismaPersegi() { return prismaPersegi; }
    public PersegiPanjang getPersegiPanjang() { return persegiPanjang; }
    public LimasPersegiPanjang getLimasPersegiPanjang() { return limasPersegiPanjang; }
    public PrismaPersegiPanjang getPrismaPersegiPanjang() { return prismaPersegiPanjang; }
    public Segitiga getSegitiga() { return segitiga; }
    public LimasSegitiga getLimasSegitiga() { return limasSegitiga; }
    public PrismaSegitiga getPrismaSegitiga() { return prismaSegitiga; }
    public BelahKetupat getBelahKetupat() { return belahKetupat; }
    public LimasBelahKetupat getLimasBelahKetupat() { return limasBelahKetupat; }
    public PrismaBelahKetupat getPrismaBelahKetupat() { return prismaBelahKetupat; }
    public JajarGenjang getJajarGenjang() { return jajarGenjang; }
    public LimasJajarGenjang getLimasJajarGenjang() { return limasJajarGenjang; }
    public PrismaJajarGenjang getPrismaJajarGenjang() { return prismaJajarGenjang; }
    public LayangLayang getLayangLayang() { return layangLayang; }
    public LimasLayangLayang getLimasLayangLayang() { return limasLayangLayang; }
    public PrismaLayangLayang getPrismaLayangLayang() { return prismaLayangLayang; }
    public Trapesium getTrapesium() { return trapesium; }
    public LimasTrapesium getLimasTrapesium() { return limasTrapesium; }
    public PrismaTrapesium getPrismaTrapesium() { return prismaTrapesium; }
    public KerucutTerpancung getKerucutTerpancung() { return kerucutTerpancung; }


    /**
     * Memperbarui jari-jari secara global untuk semua objek turunan Lingkaran (non-Bola).
     * @param newJariJari Nilai jari-jari baru.
     */
    public void updateLingkaranChildrenJariJari(double newJariJari) {
        if (newJariJari <= 0) {
            throw new IllegalArgumentException("Jari-jari harus bernilai positif.");
        }
        for (Lingkaran obj : updatableFromLingkaran) {
            obj.jariJari = newJariJari;
        }
    }

    /**
     * Memperbarui jari-jari secara global untuk Bola dan semua objek turunan Bola.
     * Tidak akan memengaruhi Lingkaran parent.
     * @param newJariJari Nilai jari-jari baru.
     */
    public void updateBolaAndChildrenJariJari(double newJariJari) {
        if (newJariJari <= 0) {
            throw new IllegalArgumentException("Jari-jari harus bernilai positif.");
        }
        for (Bola obj : updatableFromBola) {
            obj.jariJari = newJariJari;
            // Penting: Untuk JuringBola dan TemberengBola, tinggiTemberengDasar harus tetap <= 2 * newJariJari
            // Untuk CincinBola, jari-jari alasnya harus tetap <= newJariJari.
            // Di sini kita hanya update jariJari utama, validasi lebih lanjut saat update dimensi spesifik.
        }
    }

    public void updateCincinBolaDimensions(double newJariJariBola, double newTinggiCincin, double newJariJariAlas1, double newJariJariAlas2) {
        if (newJariJariBola <= 0 || newTinggiCincin <= 0 || newJariJariAlas1 < 0 || newJariJariAlas2 < 0) { // Alas bisa 0
            throw new IllegalArgumentException("Jari-jari bola dan tinggi cincin harus positif. Jari-jari alas tidak boleh negatif.");
        }
        if (newJariJariAlas1 > newJariJariBola || newJariJariAlas2 > newJariJariBola) {
            throw new IllegalArgumentException("Jari-jari alas tidak boleh lebih besar dari jari-jari bola utama.");
        }
        if (newTinggiCincin > (2 * newJariJariBola)) {
            throw new IllegalArgumentException("Tinggi cincin bola tidak boleh melebihi diameter bola utama.");
        }

        cincinBola.jariJari = newJariJariBola;
        cincinBola.tinggiCincin = newTinggiCincin;
        cincinBola.jariJariAlas1 = newJariJariAlas1;
        cincinBola.jariJariAlas2 = newJariJariAlas2;
    }

    // Metode update untuk Kerucut
    public void updateKerucutDimensions(double newJariJari, double newTinggi) {
        if (newJariJari <= 0 || newTinggi <= 0) {
            throw new IllegalArgumentException("Jari-jari dan tinggi kerucut harus positif.");
        }
        kerucut.jariJari = newJariJari;
        kerucut.tinggi = newTinggi;
    }

    // Metode update untuk Tabung
    public void updateTabungDimensions(double newJariJari, double newTinggi) {
        if (newJariJari <= 0 || newTinggi <= 0) {
            throw new IllegalArgumentException("Jari-jari dan tinggi tabung harus positif.");
        }
        tabung.jariJari = newJariJari;
        tabung.tinggi = newTinggi;
    }

    // Metode update untuk Juring Bola
    public void updateJuringBolaDimensions(double newJariJariBola, double newTinggiTemberengDasar) {
        if (newJariJariBola <= 0 || newTinggiTemberengDasar <= 0) {
            throw new IllegalArgumentException("Jari-jari bola dan tinggi tembereng dasar harus positif.");
        }
        if (newTinggiTemberengDasar > (2 * newJariJariBola)) {
            throw new IllegalArgumentException("Tinggi tembereng dasar tidak boleh melebihi diameter bola.");
        }
        juringBola.jariJari = newJariJariBola;
        juringBola.tinggiTemberengDasar = newTinggiTemberengDasar;
    }

    // Metode update untuk Tembereng Bola
    public void updateTemberengBolaDimensions(double newJariJariBola, double newTinggiTembereng) {
        if (newJariJariBola <= 0 || newTinggiTembereng <= 0) {
            throw new IllegalArgumentException("Jari-jari bola dan tinggi tembereng harus positif.");
        }
        if (newTinggiTembereng > (2 * newJariJariBola)) {
            throw new IllegalArgumentException("Tinggi tembereng tidak boleh melebihi diameter bola.");
        }
        temberengBola.jariJari = newJariJariBola;
        temberengBola.tinggiTembereng = newTinggiTembereng;
    }


    public void updatePersegiSisi(double newSisi) {
        if (newSisi <= 0) {
            throw new IllegalArgumentException("Sisi harus bernilai positif.");
        }
        persegi.sisi = newSisi;
        // Update anak-anak yang bergantung pada sisi alas
        limasPersegi.sisi = newSisi;
        prismaPersegi.sisi = newSisi;
    }

    // Update tinggi limas persegi
    public void updateLimasPersegiTinggi(double newTinggiLimas) {
        if (newTinggiLimas <= 0) {
            throw new IllegalArgumentException("Tinggi limas harus positif.");
        }
        limasPersegi.tinggiLimas = newTinggiLimas;
    }

    public void updatePersegiPanjangDimensions(double newPanjang, double newLebar) {
        if (newPanjang <= 0 || newLebar <= 0) {
            throw new IllegalArgumentException("Panjang dan lebar harus bernilai positif.");
        }
        persegiPanjang.panjang = newPanjang;
        persegiPanjang.lebar = newLebar;
        // Update turunannya
        limasPersegiPanjang.panjang = newPanjang;
        limasPersegiPanjang.lebar = newLebar;
        prismaPersegiPanjang.panjang = newPanjang;
        prismaPersegiPanjang.lebar = newLebar;
    }

    // Update tinggi limas persegi panjang
    public void updateLimasPersegiPanjangTinggi(double newTinggiLimas) {
        if (newTinggiLimas <= 0) {
            throw new IllegalArgumentException("Tinggi limas harus positif.");
        }
        limasPersegiPanjang.tinggiLimas = newTinggiLimas;
    }

    // Update tinggi prisma persegi panjang (balok)
    public void updatePrismaPersegiPanjangTinggi(double newTinggiPrisma) {
        if (newTinggiPrisma <= 0) {
            throw new IllegalArgumentException("Tinggi prisma harus positif.");
        }
        prismaPersegiPanjang.tinggiPrisma = newTinggiPrisma;
    }

    public void updateSegitigaDimensions(double newAlas, double newTinggiLuas, double newSisiA, double newSisiB, double newSisiC) {
        if (newAlas <= 0 || newTinggiLuas <= 0 || newSisiA <= 0 || newSisiB <= 0 || newSisiC <= 0) {
            throw new IllegalArgumentException("Semua dimensi segitiga harus positif.");
        }
        if (! (newSisiA + newSisiB > newSisiC &&
                newSisiA + newSisiC > newSisiB &&
                newSisiB + newSisiC > newSisiA) ) {
            throw new IllegalArgumentException("Sisi-sisi tidak membentuk segitiga yang valid.");
        }

        segitiga.alasUntukLuas = newAlas;
        segitiga.tinggiUntukLuas = newTinggiLuas;
        segitiga.sisiA = newSisiA;
        segitiga.sisiB = newSisiB;
        segitiga.sisiC = newSisiC;
        // Update Limas dan Prisma juga
        limasSegitiga.alasUntukLuas = newAlas;
        limasSegitiga.tinggiUntukLuas = newTinggiLuas;
        limasSegitiga.sisiA = newSisiA;
        limasSegitiga.sisiB = newSisiB;
        limasSegitiga.sisiC = newSisiC;
        prismaSegitiga.alasUntukLuas = newAlas;
        prismaSegitiga.tinggiUntukLuas = newTinggiLuas;
        prismaSegitiga.sisiA = newSisiA;
        prismaSegitiga.sisiB = newSisiB;
        prismaSegitiga.sisiC = newSisiC;
    }

    // Update tinggi limas segitiga
    public void updateLimasSegitigaTinggi(double newTinggiLimas) {
        if (newTinggiLimas <= 0) {
            throw new IllegalArgumentException("Tinggi limas harus positif.");
        }
        limasSegitiga.tinggiLimas = newTinggiLimas;
    }

    // Update tinggi prisma segitiga
    public void updatePrismaSegitigaTinggi(double newTinggiPrisma) {
        if (newTinggiPrisma <= 0) {
            throw new IllegalArgumentException("Tinggi prisma harus positif.");
        }
        prismaSegitiga.tinggiPrisma = newTinggiPrisma;
    }

    public void updateBelahKetupatDimensions(double newD1, double newD2, double newSisi) {
        if (newD1 <= 0 || newD2 <= 0 || newSisi <= 0) {
            throw new IllegalArgumentException("Diagonal dan sisi harus bernilai positif.");
        }
        // Validasi opsional: sisi harus konsisten dengan diagonal (sisi = sqrt((d1/2)^2 + (d2/2)^2))
        double calculatedSisi = Math.sqrt(Math.pow(newD1/2, 2) + Math.pow(newD2/2, 2));
        if (Math.abs(newSisi - calculatedSisi) > 0.01) { // Toleransi kecil untuk floating point
            // throw new IllegalArgumentException("Sisi tidak konsisten dengan diagonal yang diberikan.");
            // Atau, jika sisi selalu dihitung dari diagonal:
            // newSisi = calculatedSisi;
        }

        belahKetupat.diagonal1 = newD1;
        belahKetupat.diagonal2 = newD2;
        belahKetupat.sisi = newSisi;
        limasBelahKetupat.diagonal1 = newD1;
        limasBelahKetupat.diagonal2 = newD2;
        limasBelahKetupat.sisi = newSisi;
        prismaBelahKetupat.diagonal1 = newD1;
        prismaBelahKetupat.diagonal2 = newD2;
        prismaBelahKetupat.sisi = newSisi;
    }

    // Update tinggi limas belah ketupat
    public void updateLimasBelahKetupatTinggi(double newTinggiLimas) {
        if (newTinggiLimas <= 0) {
            throw new IllegalArgumentException("Tinggi limas harus positif.");
        }
        limasBelahKetupat.tinggiLimas = newTinggiLimas;
    }

    // Update tinggi prisma belah ketupat
    public void updatePrismaBelahKetupatTinggi(double newTinggiPrisma) {
        if (newTinggiPrisma <= 0) {
            throw new IllegalArgumentException("Tinggi prisma harus positif.");
        }
        prismaBelahKetupat.tinggiPrisma = newTinggiPrisma;
    }

    public void updateJajarGenjangDimensions(double newAlas, double newTinggi, double newSisiMiring) {
        if (newAlas <= 0 || newTinggi <= 0 || newSisiMiring <= 0) {
            throw new IllegalArgumentException("Alas, tinggi, dan sisi miring harus positif.");
        }
        if (newTinggi > newSisiMiring) {
            throw new IllegalArgumentException("Tinggi jajar genjang tidak boleh lebih besar dari sisi miring.");
        }

        jajarGenjang.alas = newAlas;
        jajarGenjang.tinggi = newTinggi;
        jajarGenjang.sisiMiring = newSisiMiring;
        limasJajarGenjang.alas = newAlas;
        limasJajarGenjang.tinggi = newTinggi;
        limasJajarGenjang.sisiMiring = newSisiMiring;
        prismaJajarGenjang.alas = newAlas;
        prismaJajarGenjang.tinggi = newTinggi;
        prismaJajarGenjang.sisiMiring = newSisiMiring;
    }

    // Update tinggi limas jajar genjang
    public void updateLimasJajarGenjangTinggi(double newTinggiLimas) {
        if (newTinggiLimas <= 0) {
            throw new IllegalArgumentException("Tinggi limas harus positif.");
        }
        limasJajarGenjang.tinggiLimas = newTinggiLimas;
    }

    // Update tinggi prisma jajar genjang
    public void updatePrismaJajarGenjangTinggi(double newTinggiPrisma) {
        if (newTinggiPrisma <= 0) {
            throw new IllegalArgumentException("Tinggi prisma harus positif.");
        }
        prismaJajarGenjang.tinggiPrisma = newTinggiPrisma;
    }

    public void updateLayangLayangDimensions(double newD1, double newD2, double newSisiPendek, double newSisiPanjang) {
        if (newD1 <= 0 || newD2 <= 0 || newSisiPendek <= 0 || newSisiPanjang <= 0) {
            throw new IllegalArgumentException("Semua dimensi harus positif.");
        }
        // Validasi layang-layang: sisi pendek/panjang harus konsisten dengan diagonal
        // Ini adalah validasi yang cukup kompleks karena ada 2 pasang sisi dan diagonal saling tegak lurus
        // Untuk demo, kita tidak akan melakukan validasi geometris yang ketat di sini, hanya positif.

        layangLayang.diagonal1 = newD1;
        layangLayang.diagonal2 = newD2;
        layangLayang.sisiPendek = newSisiPendek;
        layangLayang.sisiPanjang = newSisiPanjang;
        limasLayangLayang.diagonal1 = newD1;
        limasLayangLayang.diagonal2 = newD2;
        limasLayangLayang.sisiPendek = newSisiPendek;
        limasLayangLayang.sisiPanjang = newSisiPanjang;
        prismaLayangLayang.diagonal1 = newD1;
        prismaLayangLayang.diagonal2 = newD2;
        prismaLayangLayang.sisiPendek = newSisiPendek;
        prismaLayangLayang.sisiPanjang = newSisiPanjang;
    }

    // Update tinggi limas layang-layang
    public void updateLimasLayangLayangTinggi(double newTinggiLimas) {
        if (newTinggiLimas <= 0) {
            throw new IllegalArgumentException("Tinggi limas harus positif.");
        }
        limasLayangLayang.tinggiLimas = newTinggiLimas;
    }

    // Update tinggi prisma layang-layang
    public void updatePrismaLayangLayangTinggi(double newTinggiPrisma) {
        if (newTinggiPrisma <= 0) {
            throw new IllegalArgumentException("Tinggi prisma harus positif.");
        }
        prismaLayangLayang.tinggiPrisma = newTinggiPrisma;
    }

    public void updateTrapesiumDimensions(double newSisiAtas, double newSisiBawah, double newTinggi, double newSisiKiri, double newSisiKanan) {
        if (newSisiAtas <= 0 || newSisiBawah <= 0 || newTinggi <= 0 || newSisiKiri <= 0 || newSisiKanan <= 0) {
            throw new IllegalArgumentException("Semua dimensi harus positif.");
        }
        // Validasi trapesium (misal: sisi miring tidak boleh lebih pendek dari tinggi)
        if (newTinggi > newSisiKiri || newTinggi > newSisiKanan) {
            throw new IllegalArgumentException("Tinggi trapesium tidak boleh lebih besar dari sisi miringnya.");
        }

        trapesium.sisiAtas = newSisiAtas;
        trapesium.sisiBawah = newSisiBawah;
        trapesium.tinggi = newTinggi;
        trapesium.sisiKiri = newSisiKiri;
        trapesium.sisiKanan = newSisiKanan;
        limasTrapesium.sisiAtas = newSisiAtas;
        limasTrapesium.sisiBawah = newSisiBawah;
        limasTrapesium.tinggi = newTinggi;
        limasTrapesium.sisiKiri = newSisiKiri;
        limasTrapesium.sisiKanan = newSisiKanan;
        prismaTrapesium.sisiAtas = newSisiAtas;
        prismaTrapesium.sisiBawah = newSisiBawah;
        prismaTrapesium.tinggi = newTinggi;
        prismaTrapesium.sisiKiri = newSisiKiri;
        prismaTrapesium.sisiKanan = newSisiKanan;
    }

    // Update tinggi limas trapesium
    public void updateLimasTrapesiumTinggi(double newTinggiLimas) {
        if (newTinggiLimas <= 0) {
            throw new IllegalArgumentException("Tinggi limas harus positif.");
        }
        limasTrapesium.tinggiLimas = newTinggiLimas;
    }

    // Update tinggi prisma trapesium
    public void updatePrismaTrapesiumTinggi(double newTinggiPrisma) {
        if (newTinggiPrisma <= 0) {
            throw new IllegalArgumentException("Tinggi prisma harus positif.");
        }
        prismaTrapesium.tinggiPrisma = newTinggiPrisma;
    }

    // Metode update untuk Kerucut Terpancung
    public void updateKerucutTerpancungDimensions(double newJariJariBawah, double newJariJariAtas, double newTinggiFrustum) {
        if (newJariJariBawah <= 0 || newJariJariAtas < 0 || newTinggiFrustum <= 0) {
            throw new IllegalArgumentException("Jari-jari bawah dan tinggi harus positif. Jari-jari atas tidak boleh negatif.");
        }
        kerucutTerpancung.jariJari = newJariJariBawah; // jariJari diwarisi dari Kerucut untuk jari-jari bawah
        kerucutTerpancung.jariJariAtas = newJariJariAtas;
        kerucutTerpancung.tinggiFrustum = newTinggiFrustum; // tinggi diwarisi dari Kerucut, di sini kita gunakan tinggiFrustum
    }
}