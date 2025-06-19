package geometri;

import geometri.Benda2D.*;
import geometri.Benda3D.*;
import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
// >>> PERUBAHAN: Import yang mungkin dibutuhkan untuk demo di dalam GUI
import java.util.ArrayList;
import java.util.List;


public class GeometriGUI extends JFrame{

    private final GeometriDataManager dataManager;
    private final JPanel mainContentPanel; // Panel utama untuk menampilkan detail bangun

    public GeometriGUI() {
        setTitle("Aplikasi Geometri Interaktif");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 680); // Ukuran sedikit diperbesar
        setLocationRelativeTo(null);

        dataManager = GeometriDataManager.getInstance();
        mainContentPanel = new JPanel(new BorderLayout());
        add(mainContentPanel, BorderLayout.CENTER);

        createMenuBar();

        // Tampilkan panel default saat aplikasi pertama kali dibuka (misal: Lingkaran)
        showPanel(dataManager.getLingkaran(), this::createLingkaranDetailsPanel);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // --- Menu Lingkaran ---
        JMenu lingkaranMenu = new JMenu("Lingkaran & Turunannya");
        // ... (KODE MENU LINGKARAN TIDAK BERUBAH) ...
        JMenuItem lingkaranItem = new JMenuItem("Lingkaran (Dasar)");
        lingkaranItem.addActionListener(e -> showPanel(dataManager.getLingkaran(), this::createLingkaranDetailsPanel));
        lingkaranMenu.add(lingkaranItem);

        JMenuItem juringLingkaranItem = new JMenuItem("Juring Lingkaran");
        juringLingkaranItem.addActionListener(e -> showPanel(dataManager.getJuringLingkaran(), this::createJuringLingkaranDetailsPanel));
        lingkaranMenu.add(juringLingkaranItem);

        JMenuItem temberengLingkaranItem = new JMenuItem("Tembereng Lingkaran");
        temberengLingkaranItem.addActionListener(e -> showPanel(dataManager.getTemberengLingkaran(), this::createTemberengLingkaranDetailsPanel));
        lingkaranMenu.add(temberengLingkaranItem);

        lingkaranMenu.addSeparator();

        JMenuItem tabungItem = new JMenuItem("Tabung");
        tabungItem.addActionListener(e -> showPanel(dataManager.getTabung(), this::createTabungDetailsPanel));
        lingkaranMenu.add(tabungItem);

        JMenuItem kerucutItem = new JMenuItem("Kerucut");
        kerucutItem.addActionListener(e -> showPanel(dataManager.getKerucut(), this::createKerucutDetailsPanel));
        lingkaranMenu.add(kerucutItem);

        JMenuItem kerucutTerpancungItem = new JMenuItem("Kerucut Terpancung");
        kerucutTerpancungItem.addActionListener(e -> showPanel(dataManager.getKerucutTerpancung(), this::createKerucutTerpancungDetailsPanel));
        lingkaranMenu.add(kerucutTerpancungItem);

        lingkaranMenu.addSeparator();

        JMenu bolaSubMenu = new JMenu("Bola & Turunannya");
        JMenuItem bolaItem = new JMenuItem("Bola (Dasar)");
        bolaItem.addActionListener(e -> showPanel(dataManager.getBola(), this::createBolaDetailsPanel));
        bolaSubMenu.add(bolaItem);

        JMenuItem juringBolaItem = new JMenuItem("Juring Bola");
        juringBolaItem.addActionListener(e -> showPanel(dataManager.getJuringBola(), this::createJuringBolaDetailsPanel));
        bolaSubMenu.add(juringBolaItem);

        JMenuItem cincinBolaItem = new JMenuItem("Cincin Bola");
        cincinBolaItem.addActionListener(e -> showPanel(dataManager.getCincinBola(), this::createCincinBolaDetailsPanel));
        bolaSubMenu.add(cincinBolaItem);

        JMenuItem temberengBolaItem = new JMenuItem("Tembereng Bola");
        temberengBolaItem.addActionListener(e -> showPanel(dataManager.getTemberengBola(), this::createTemberengBolaDetailsPanel));
        bolaSubMenu.add(temberengBolaItem);

        lingkaranMenu.add(bolaSubMenu);
        menuBar.add(lingkaranMenu);

        // --- Menu Poligon ---
        JMenu poligonMenu = new JMenu("Poligon & Turunannya");
        // ... (KODE MENU POLIGON TIDAK BERUBAH) ...
        JMenu persegiMenu = new JMenu("Persegi");
        JMenuItem persegiItem = new JMenuItem("Persegi (Dasar)");
        persegiItem.addActionListener(e -> showPanel(dataManager.getPersegi(), this::createPersegiDetailsPanel));
        persegiMenu.add(persegiItem);
        JMenuItem limasPersegiItem = new JMenuItem("Limas Persegi");
        limasPersegiItem.addActionListener(e -> showPanel(dataManager.getLimasPersegi(), this::createLimasPersegiDetailsPanel));
        persegiMenu.add(limasPersegiItem);
        JMenuItem prismaPersegiItem = new JMenuItem("Prisma Persegi (Kubus)");
        prismaPersegiItem.addActionListener(e -> showPanel(dataManager.getPrismaPersegi(), this::createPrismaPersegiDetailsPanel));
        persegiMenu.add(prismaPersegiItem);
        poligonMenu.add(persegiMenu);

        JMenu persegiPanjangMenu = new JMenu("Persegi Panjang");
        JMenuItem persegiPanjangItem = new JMenuItem("Persegi Panjang (Dasar)");
        persegiPanjangItem.addActionListener(e -> showPanel(dataManager.getPersegiPanjang(), this::createPersegiPanjangDetailsPanel));
        persegiPanjangMenu.add(persegiPanjangItem);
        JMenuItem limasPersegiPanjangItem = new JMenuItem("Limas Persegi Panjang");
        limasPersegiPanjangItem.addActionListener(e -> showPanel(dataManager.getLimasPersegiPanjang(), this::createLimasPersegiPanjangDetailsPanel));
        persegiPanjangMenu.add(limasPersegiPanjangItem);
        JMenuItem prismaPersegiPanjangItem = new JMenuItem("Prisma Persegi Panjang (Balok)");
        prismaPersegiPanjangItem.addActionListener(e -> showPanel(dataManager.getPrismaPersegiPanjang(), this::createPrismaPersegiPanjangDetailsPanel));
        persegiPanjangMenu.add(prismaPersegiPanjangItem);
        poligonMenu.add(persegiPanjangMenu);

        JMenu segitigaMenu = new JMenu("Segitiga");
        JMenuItem segitigaItem = new JMenuItem("Segitiga (Dasar)");
        segitigaItem.addActionListener(e -> showPanel(dataManager.getSegitiga(), this::createSegitigaDetailsPanel));
        segitigaMenu.add(segitigaItem);
        JMenuItem limasSegitigaItem = new JMenuItem("Limas Segitiga");
        limasSegitigaItem.addActionListener(e -> showPanel(dataManager.getLimasSegitiga(), this::createLimasSegitigaDetailsPanel));
        segitigaMenu.add(limasSegitigaItem);
        JMenuItem prismaSegitigaItem = new JMenuItem("Prisma Segitiga");
        prismaSegitigaItem.addActionListener(e -> showPanel(dataManager.getPrismaSegitiga(), this::createPrismaSegitigaDetailsPanel));
        segitigaMenu.add(prismaSegitigaItem);
        poligonMenu.add(segitigaMenu);

        JMenu belahKetupatMenu = new JMenu("Belah Ketupat");
        JMenuItem belahKetupatItem = new JMenuItem("Belah Ketupat (Dasar)");
        belahKetupatItem.addActionListener(e -> showPanel(dataManager.getBelahKetupat(), this::createBelahKetupatDetailsPanel));
        belahKetupatMenu.add(belahKetupatItem);
        JMenuItem limasBelahKetupatItem = new JMenuItem("Limas Belah Ketupat");
        limasBelahKetupatItem.addActionListener(e -> showPanel(dataManager.getLimasBelahKetupat(), this::createLimasBelahKetupatDetailsPanel));
        belahKetupatMenu.add(limasBelahKetupatItem);
        JMenuItem prismaBelahKetupatItem = new JMenuItem("Prisma Belah Ketupat");
        prismaBelahKetupatItem.addActionListener(e -> showPanel(dataManager.getPrismaBelahKetupat(), this::createPrismaBelahKetupatDetailsPanel));
        belahKetupatMenu.add(prismaBelahKetupatItem);
        poligonMenu.add(belahKetupatMenu);

        JMenu jajarGenjangMenu = new JMenu("Jajar Genjang");
        JMenuItem jajarGenjangItem = new JMenuItem("Jajar Genjang (Dasar)");
        jajarGenjangItem.addActionListener(e -> showPanel(dataManager.getJajarGenjang(), this::createJajarGenjangDetailsPanel));
        jajarGenjangMenu.add(jajarGenjangItem);
        JMenuItem limasJajarGenjangItem = new JMenuItem("Limas Jajar Genjang");
        limasJajarGenjangItem.addActionListener(e -> showPanel(dataManager.getLimasJajarGenjang(), this::createLimasJajarGenjangDetailsPanel));
        jajarGenjangMenu.add(limasJajarGenjangItem);
        JMenuItem prismaJajarGenjangItem = new JMenuItem("Prisma Jajar Genjang");
        prismaJajarGenjangItem.addActionListener(e -> showPanel(dataManager.getPrismaJajarGenjang(), this::createPrismaJajarGenjangDetailsPanel));
        jajarGenjangMenu.add(prismaJajarGenjangItem);
        poligonMenu.add(jajarGenjangMenu);

        JMenu layangLayangMenu = new JMenu("Layang-Layang");
        JMenuItem layangLayangItem = new JMenuItem("Layang-Layang (Dasar)");
        layangLayangItem.addActionListener(e -> showPanel(dataManager.getLayangLayang(), this::createLayangLayangDetailsPanel));
        layangLayangMenu.add(layangLayangItem);
        JMenuItem limasLayangLayangItem = new JMenuItem("Limas Layang-Layang");
        limasLayangLayangItem.addActionListener(e -> showPanel(dataManager.getLimasLayangLayang(), this::createLimasLayangLayangDetailsPanel));
        layangLayangMenu.add(limasLayangLayangItem);
        JMenuItem prismaLayangLayangItem = new JMenuItem("Prisma Layang-Layang");
        prismaLayangLayangItem.addActionListener(e -> showPanel(dataManager.getPrismaLayangLayang(), this::createPrismaLayangLayangDetailsPanel));
        layangLayangMenu.add(prismaLayangLayangItem);
        poligonMenu.add(layangLayangMenu);

        JMenu trapesiumMenu = new JMenu("Trapesium");
        JMenuItem trapesiumItem = new JMenuItem("Trapesium (Dasar)");
        trapesiumItem.addActionListener(e -> showPanel(dataManager.getTrapesium(), this::createTrapesiumDetailsPanel));
        trapesiumMenu.add(trapesiumItem);
        JMenuItem limasTrapesiumItem = new JMenuItem("Limas Trapesium");
        limasTrapesiumItem.addActionListener(e -> showPanel(dataManager.getLimasTrapesium(), this::createLimasTrapesiumDetailsPanel));
        trapesiumMenu.add(limasTrapesiumItem);
        JMenuItem prismaTrapesiumItem = new JMenuItem("Prisma Trapesium");
        prismaTrapesiumItem.addActionListener(e -> showPanel(dataManager.getPrismaTrapesium(), this::createPrismaTrapesiumDetailsPanel));
        trapesiumMenu.add(prismaTrapesiumItem);
        poligonMenu.add(trapesiumMenu);

        menuBar.add(poligonMenu);

        // --- Menu Simulasi ---
        JMenu simulasiMenu = new JMenu("Simulasi");
        JMenuItem multithreadItem = new JMenuItem("Jalankan Perhitungan Multithread");
        multithreadItem.addActionListener(e -> showMultithreadingSimulation());
        simulasiMenu.add(multithreadItem);

        // >>> DITAMBAHKAN: Menu item baru untuk demo polimorfisme
        JMenuItem polymorphItem = new JMenuItem("Demo Polimorfisme");
        polymorphItem.addActionListener(e -> showPolymorphismDemo());
        simulasiMenu.add(polymorphItem);
        // <<< SELESAI DITAMBAHKAN

        menuBar.add(simulasiMenu);

        setJMenuBar(menuBar);
    }

    // >>> DITAMBAHKAN: Metode baru untuk menampilkan jendela demo polimorfisme
    private void showPolymorphismDemo() {
        // Membuat frame baru untuk output demo
        JFrame demoFrame = new JFrame("Demonstrasi Polimorfisme");
        JTextArea outputArea = new JTextArea(25, 70);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // Memanggil metode yang menghasilkan teks demo dan menampilkannya
        outputArea.setText(generatePolymorphismDemoText());

        JScrollPane scrollPane = new JScrollPane(outputArea);
        demoFrame.add(scrollPane);
        demoFrame.pack();
        demoFrame.setLocationRelativeTo(this); // Muncul di tengah aplikasi utama
        demoFrame.setVisible(true);
    }
    // <<< SELESAI DITAMBAHKAN

    // >>> DITAMBAHKAN: Metode baru yang berisi logika demo dan mengembalikan String
    private String generatePolymorphismDemoText() {
        StringBuilder sb = new StringBuilder();

        try {
            sb.append("=============================================\n");
            sb.append("===== MEMULAI DEMONSTRASI POLIMORFISME =====\n");
            sb.append("=============================================\n");

            // --- Contoh Polimorfisme 1: Satu Objek ---
            sb.append("\n--- DEMO POLIMORFISME PADA SATU OBJEK ---\n");

            JajarGenjang prismaSebagaiJajarGenjang = new PrismaJajarGenjang(10, 5, 7, 12);

            sb.append("Objek dibuat: JajarGenjang ref = new PrismaJajarGenjang(...)\n\n");
            sb.append("Nama Bangun (dari referensi): ").append(prismaSebagaiJajarGenjang.getNamaBangun()).append("\n");
            sb.append("Luas Alas: ").append(String.format("%.2f", prismaSebagaiJajarGenjang.hitungLuas())).append("\n");
            sb.append("Keliling Alas: ").append(String.format("%.2f", prismaSebagaiJajarGenjang.hitungKeliling())).append("\n");

            if (prismaSebagaiJajarGenjang instanceof PrismaJajarGenjang) {
                sb.append("\nObjek ini adalah sebuah PrismaJajarGenjang.\n");
                PrismaJajarGenjang prismaAsli = (PrismaJajarGenjang) prismaSebagaiJajarGenjang;
                sb.append("Volume Prisma (setelah casting): ").append(String.format("%.2f", prismaAsli.hitungVolume())).append("\n");
                sb.append("Luas Permukaan Prisma (setelah casting): ").append(String.format("%.2f", prismaAsli.hitungLuasPermukaan())).append("\n");
            }

            sb.append("\n\n--- DEMO POLIMORFISME DENGAN KOLEKSI (KEKUATAN SEBENARNYA) ---\n");

            List<JajarGenjang> daftarBangunBeralasJajarGenjang = new ArrayList<>();
            sb.append("\nMembuat List<JajarGenjang> dan menambahkan objek:\n");
            sb.append("- JajarGenjang\n");
            sb.append("- LimasJajarGenjang\n");
            sb.append("- PrismaJajarGenjang\n");

            daftarBangunBeralasJajarGenjang.add(new JajarGenjang(8, 4, 5));
            daftarBangunBeralasJajarGenjang.add(new LimasJajarGenjang(12, 6, 8, 15, 16, 17));
            daftarBangunBeralasJajarGenjang.add(new PrismaJajarGenjang(10, 5, 7, 12));

            for (JajarGenjang bangun : daftarBangunBeralasJajarGenjang) {
                sb.append("\n---------------------------------------------\n");
                sb.append("Memproses objek dari kelas: ").append(bangun.getClass().getSimpleName()).append("\n");
                sb.append("Luas Alasnya adalah: ").append(String.format("%.2f", bangun.hitungLuas())).append("\n");

                if (bangun instanceof LimasJajarGenjang) {
                    LimasJajarGenjang limas = (LimasJajarGenjang) bangun;
                    sb.append(">> Tipe Khusus: Limas, dengan Volume: ").append(String.format("%.2f", limas.hitungVolume())).append("\n");
                } else if (bangun instanceof PrismaJajarGenjang) {
                    PrismaJajarGenjang prisma = (PrismaJajarGenjang) bangun;
                    sb.append(">> Tipe Khusus: Prisma, dengan Volume: ").append(String.format("%.2f", prisma.hitungVolume())).append("\n");
                } else {
                    sb.append(">> Tipe Khusus: Bangun Datar Jajar Genjang (tidak punya volume).\n");
                }
            }

            sb.append("\n=============================================\n");
            sb.append("====== DEMO POLIMORFISME SELESAI ======\n");
            sb.append("=============================================\n");

        } catch (TolakNilaiException e) {
            sb.append("\n\nERROR: Terjadi error saat membuat objek untuk demo: ").append(e.getMessage());
        }

        return sb.toString();
    }
    // <<< SELESAI DITAMBAHKAN

    private void showMultithreadingSimulation() {
        // ... (KODE MULTITHREADING TIDAK BERUBAH) ...
        JFrame simulationFrame = new JFrame("Output Simulasi Multithreading");
        JTextArea outputArea = new JTextArea(25, 60);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        simulationFrame.add(scrollPane);
        simulationFrame.pack();
        simulationFrame.setLocationRelativeTo(this);
        simulationFrame.setVisible(true);

        JButton runButton = new JButton("Jalankan Ulang Simulasi");
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(runButton);
        simulationFrame.add(bottomPanel, BorderLayout.SOUTH);

        runButton.addActionListener(e -> {
            outputArea.setText("");
            runTasks(outputArea, runButton);
        });

        runTasks(outputArea, runButton);
    }

    private void runTasks(JTextArea outputArea, JButton runButton) {
        // ... (KODE MULTITHREADING TIDAK BERUBAH) ...
        runButton.setEnabled(false);
        runButton.setText("Menjalankan...");

        PrintStream originalOut = System.out;
        PrintStream originalErr = System.err;
        PrintStream customPrintStream = new PrintStream(new CustomOutputStream(outputArea));
        System.setOut(customPrintStream);
        System.setErr(customPrintStream);

        ExecutorService executor = Executors.newFixedThreadPool(5);

        try {
            Lingkaran task1 = new Lingkaran(10);
            Persegi task2 = new Persegi(15);
            JuringLingkaran task3 = new JuringLingkaran(20);
            task3.sudutBusurDerajat = 90;
            TemberengLingkaran task4 = new TemberengLingkaran(25, 120);
            Bola task5 = new Bola(30);

            executor.submit(task1);
            executor.submit(task2);
            executor.submit(task3);
            executor.submit(task4);
            executor.submit(task5);

        } catch (Exception e) {
            outputArea.append("Gagal membuat objek untuk simulasi: " + e.getMessage());
        }

        executor.shutdown();

        new Thread(() -> {
            try {
                executor.awaitTermination(30, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                System.setOut(originalOut);
                System.setErr(originalErr);

                SwingUtilities.invokeLater(() -> {
                    outputArea.append("\n--- SEMUA PROSES SELESAI ---\n");
                    runButton.setEnabled(true);
                    runButton.setText("Jalankan Ulang Simulasi");
                });
            }
        }).start();
    }

    public static class CustomOutputStream extends OutputStream {
        // ... (KODE KELAS INI TIDAK BERUBAH) ...
        private JTextArea textArea;

        public CustomOutputStream(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void write(int b) {
            SwingUtilities.invokeLater(() -> {
                textArea.append(String.valueOf((char) b));
                textArea.setCaretPosition(textArea.getDocument().getLength());
            });
        }
    }

    private void showPanel(Object obj, java.util.function.Consumer<JPanel> contentCreator) {
        // ... (KODE METHOD INI TIDAK BERUBAH) ...
        mainContentPanel.removeAll();

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        detailsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        contentCreator.accept(detailsPanel);

        JButton refreshButton = new JButton("Refresh Data");
        refreshButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        refreshButton.addActionListener(e -> showPanel(obj, contentCreator));

        mainContentPanel.add(detailsPanel, BorderLayout.CENTER);
        JPanel southButtonPanel = new JPanel();
        southButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        southButtonPanel.add(refreshButton);
        mainContentPanel.add(southButtonPanel, BorderLayout.SOUTH);

        mainContentPanel.revalidate();
        mainContentPanel.repaint();
    }

    private JLabel createAlignedLabel(String text) {
        // ... (KODE METHOD INI TIDAK BERUBAH) ...
        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JPanel createInputPanel(String labelText, JTextField textField, JButton button, JLabel statusLabel) {
        // ... (KODE METHOD INI TIDAK BERUBAH) ...
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        textField.setPreferredSize(new Dimension(100, 25));

        panel.add(new JLabel(labelText));
        panel.add(textField);
        if (button != null) {
            panel.add(button);
        }
        if (statusLabel != null) {
            panel.add(statusLabel);
        }
        return panel;
    }

    // --- Panel Creator Methods ---
    // ... (SEMUA KODE PANEL DETAILS TIDAK BERUBAH) ...
    private void createLingkaranDetailsPanel(JPanel panel) {
        Lingkaran lingkaran = dataManager.getLingkaran();

        panel.add(new JLabel("--- Lingkaran ---"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(createAlignedLabel("Jari-Jari (Saat Ini): " + String.format("%.2f", lingkaran.jariJari)));
        panel.add(createAlignedLabel("Luas (Saat Ini): " + String.format("%.2f", lingkaran.hitungLuas())));
        panel.add(createAlignedLabel("Keliling (Saat Ini): " + String.format("%.2f", lingkaran.hitungKeliling())));
        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        JTextField inputJariJari = new JTextField(String.valueOf(lingkaran.jariJari), 10);
        JButton btnUpdateJariJari = new JButton("Terapkan Jari-Jari Lingkaran");
        JLabel hasilUpdate = createAlignedLabel("Status: Menunggu input...");

        JPanel inputControlPanel = createInputPanel("Jari-Jari Baru Lingkaran:", inputJariJari, btnUpdateJariJari, null);
        panel.add(inputControlPanel);

        btnUpdateJariJari.addActionListener(e -> {
            try {
                double newJariJari = Double.parseDouble(inputJariJari.getText());
                dataManager.updateLingkaranChildrenJariJari(newJariJari);
                hasilUpdate.setText("Status: Jari-jari berhasil diperbarui.");
                showPanel(dataManager.getLingkaran(), this::createLingkaranDetailsPanel);
            } catch (Exception ex) {
                hasilUpdate.setText("Error: " + ex.getMessage());
            }
        });

        JTextField inputJariJariTemp = new JTextField(10);
        JButton btnHitungTemp = new JButton("Hitung Luas/Keliling (Jari-jari Sementara)");
        JLabel hasilTemp = createAlignedLabel("Hasil Sementara: ");

        JPanel tempInputPanel = createInputPanel("Jari-Jari Untuk Hitung Sementara:", inputJariJariTemp, btnHitungTemp, null);
        panel.add(tempInputPanel);
        panel.add(hasilTemp);

        btnHitungTemp.addActionListener(e -> {
            try {
                double tempJariJari = Double.parseDouble(inputJariJariTemp.getText());
                double luasTemp = lingkaran.hitungLuas(tempJariJari);
                double kelilingTemp = lingkaran.hitungKeliling(tempJariJari);
                hasilTemp.setText("Hasil Sementara: Luas=" + String.format("%.2f", luasTemp) +
                        ", Keliling=" + String.format("%.2f", kelilingTemp));
            } catch (TolakNilaiException | NumberFormatException ex) {
                hasilTemp.setText("Error: " + ex.getMessage());
            }
        });
    }

    private void createBolaDetailsPanel(JPanel panel) {
        Bola bola = dataManager.getBola();

        panel.add(new JLabel("--- Bola ---"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(createAlignedLabel("Jari-Jari (Saat Ini): " + String.format("%.2f", bola.jariJari)));
        panel.add(createAlignedLabel("Volume: " + String.format("%.2f", bola.hitungVolume())));
        panel.add(createAlignedLabel("Luas Permukaan: " + String.format("%.2f", bola.hitungLuasPermukaan())));
        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        JTextField inputJariJariBola = new JTextField(String.valueOf(bola.jariJari), 10);
        JButton btnUpdateJariJariBola = new JButton("Terapkan Jari-Jari");
        JLabel hasilUpdateBola = createAlignedLabel("Status: Menunggu input...");

        JPanel inputControlPanel = createInputPanel("Jari-Jari Baru:", inputJariJariBola, btnUpdateJariJariBola, null);
        panel.add(inputControlPanel);

        JCheckBox updateBolaChildrenCheckbox = new JCheckBox("Perbarui Semua Turunan Bola");
        updateBolaChildrenCheckbox.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(updateBolaChildrenCheckbox);
        panel.add(hasilUpdateBola);

        btnUpdateJariJariBola.addActionListener(e -> {
            try {
                double newJariJari = Double.parseDouble(inputJariJariBola.getText());
                if (updateBolaChildrenCheckbox.isSelected()) {
                    dataManager.updateBolaAndChildrenJariJari(newJariJari);
                    hasilUpdateBola.setText("Status: Jari-jari Bola & turunannya diperbarui.");
                } else {
                    bola.jariJari = newJariJari;
                    hasilUpdateBola.setText("Status: Jari-jari Bola ini saja diperbarui.");
                }
                showPanel(bola, this::createBolaDetailsPanel);
            } catch (Exception ex) {
                hasilUpdateBola.setText("Error: " + ex.getMessage());
            }
        });
    }

    private void createCincinBolaDetailsPanel(JPanel panel) {
        CincinBola cincinBola = dataManager.getCincinBola();
        panel.add(new JLabel("<html><h2>--- Cincin Bola ---</h2></html>"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));


        panel.add(createAlignedLabel("Jari-Jari Bola (Diwarisi): " + String.format("%.2f", cincinBola.jariJari)));
        panel.add(createAlignedLabel("Tinggi Cincin: " + String.format("%.2f", cincinBola.tinggiCincin)));
        panel.add(createAlignedLabel("Jari-Jari Alas 1: " + String.format("%.2f", cincinBola.jariJariAlas1)));
        panel.add(createAlignedLabel("Jari-Jari Alas 2: " + String.format("%.2f", cincinBola.jariJariAlas2)));
        panel.add(createAlignedLabel("Volume: " + String.format("%.2f", cincinBola.hitungVolume())));
        panel.add(createAlignedLabel("Luas Permukaan Lengkung: " + String.format("%.2f", cincinBola.hitungLuasPermukaanLengkung())));
        panel.add(createAlignedLabel("Luas Permukaan Total: " + String.format("%.2f", cincinBola.hitungLuasPermukaan())));

        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        panel.add(createAlignedLabel("Perbarui Dimensi Cincin Bola:"));
        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        JPanel inputGrid = new JPanel(new GridLayout(4, 2, 5, 5));
        inputGrid.setAlignmentX(Component.LEFT_ALIGNMENT);
        inputGrid.setMaximumSize(new Dimension(400, 120));

        JTextField inputJariJariBolaCincin = new JTextField(String.valueOf(cincinBola.jariJari), 10);
        JTextField inputTinggiCincin = new JTextField(String.valueOf(cincinBola.tinggiCincin), 10);
        JTextField inputJariJariAlas1 = new JTextField(String.valueOf(cincinBola.jariJariAlas1), 10);
        JTextField inputJariJariAlas2 = new JTextField(String.valueOf(cincinBola.jariJariAlas2), 10);

        inputGrid.add(new JLabel("Jari-Jari Bola (Utama):")); inputGrid.add(inputJariJariBolaCincin);
        inputGrid.add(new JLabel("Tinggi Cincin:")); inputGrid.add(inputTinggiCincin);
        inputGrid.add(new JLabel("Jari-Jari Alas 1:")); inputGrid.add(inputJariJariAlas1);
        inputGrid.add(new JLabel("Jari-Jari Alas 2:")); inputGrid.add(inputJariJariAlas2);
        panel.add(inputGrid);

        JButton btnUpdateCincinBola = new JButton("Perbarui Dimensi");
        btnUpdateCincinBola.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(btnUpdateCincinBola);

        JLabel statusCincinBola = createAlignedLabel("Status: ");
        panel.add(statusCincinBola);

        btnUpdateCincinBola.addActionListener(e -> {
            try {
                double newJRBola = Double.parseDouble(inputJariJariBolaCincin.getText());
                double newTC = Double.parseDouble(inputTinggiCincin.getText());
                double newJRA1 = Double.parseDouble(inputJariJariAlas1.getText());
                double newJRA2 = Double.parseDouble(inputJariJariAlas2.getText());

                dataManager.updateCincinBolaDimensions(newJRBola, newTC, newJRA1, newJRA2);
                showPanel(cincinBola, this::createCincinBolaDetailsPanel);
            } catch (Exception ex) {
                statusCincinBola.setText("Error: " + ex.getMessage());
            }
        });
    }

    private void createJuringBolaDetailsPanel(JPanel panel) {
        JuringBola juringBola = dataManager.getJuringBola();
        panel.add(new JLabel("<html><h2>--- Juring Bola ---</h2></html>"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(createAlignedLabel("Jari-Jari Bola (Diwarisi): " + String.format("%.2f", juringBola.jariJari)));
        panel.add(createAlignedLabel("Tinggi Tembereng Dasar: " + String.format("%.2f", juringBola.tinggiTemberengDasar)));
        panel.add(createAlignedLabel("Jari-Jari Alas Tembereng: " + String.format("%.2f", juringBola.hitungJariJariAlasTembereng())));
        panel.add(createAlignedLabel("Volume: " + String.format("%.2f", juringBola.hitungVolume())));
        panel.add(createAlignedLabel("Luas Permukaan: " + String.format("%.2f", juringBola.hitungLuasPermukaan())));

        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        JTextField inputTinggiTemberengDasar = new JTextField(String.valueOf(juringBola.tinggiTemberengDasar), 10);
        JButton btnUpdateJuringBola = new JButton("Perbarui Tinggi");
        JLabel statusJuringBola = createAlignedLabel("Status: ");

        JPanel inputControlPanel = createInputPanel("Tinggi Tembereng Dasar Baru:", inputTinggiTemberengDasar, btnUpdateJuringBola, null);
        panel.add(inputControlPanel);
        panel.add(statusJuringBola);

        btnUpdateJuringBola.addActionListener(e -> {
            try {
                double newTinggiTemberengDasar = Double.parseDouble(inputTinggiTemberengDasar.getText());
                dataManager.updateJuringBolaDimensions(juringBola.jariJari, newTinggiTemberengDasar);
                showPanel(juringBola, this::createJuringBolaDetailsPanel);
            } catch (Exception ex) {
                statusJuringBola.setText("Error: " + ex.getMessage());
            }
        });
    }

    private void createTemberengBolaDetailsPanel(JPanel panel) {
        TemberengBola temberengBola = dataManager.getTemberengBola();
        panel.add(new JLabel("<html><h2>--- Tembereng Bola ---</h2></html>"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(createAlignedLabel("Jari-Jari Bola (Diwarisi): " + String.format("%.2f", temberengBola.jariJari)));
        panel.add(createAlignedLabel("Tinggi Tembereng: " + String.format("%.2f", temberengBola.getTinggiTembereng())));
        panel.add(createAlignedLabel("Jari-Jari Alas: " + String.format("%.2f", temberengBola.hitungJariJariAlas())));
        panel.add(createAlignedLabel("Volume: " + String.format("%.2f", temberengBola.hitungVolume())));
        panel.add(createAlignedLabel("Luas Permukaan Lengkung: " + String.format("%.2f", temberengBola.hitungLuasPermukaanLengkung())));
        panel.add(createAlignedLabel("Luas Permukaan Total: " + String.format("%.2f", temberengBola.hitungLuasPermukaan())));

        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        JTextField inputTinggiTembereng = new JTextField(String.valueOf(temberengBola.getTinggiTembereng()), 10);
        JButton btnUpdateTemberengBola = new JButton("Perbarui Tinggi");
        JLabel statusTemberengBola = createAlignedLabel("Status: ");

        JPanel inputControlPanel = createInputPanel("Tinggi Tembereng Baru:", inputTinggiTembereng, btnUpdateTemberengBola, null);
        panel.add(inputControlPanel);
        panel.add(statusTemberengBola);

        btnUpdateTemberengBola.addActionListener(e -> {
            try {
                double newTinggiTembereng = Double.parseDouble(inputTinggiTembereng.getText());
                dataManager.updateTemberengBolaDimensions(temberengBola.jariJari, newTinggiTembereng);
                showPanel(temberengBola, this::createTemberengBolaDetailsPanel);
            } catch (Exception ex) {
                statusTemberengBola.setText("Error: " + ex.getMessage());
            }
        });
    }

    private void createJuringLingkaranDetailsPanel(JPanel panel) {
        JuringLingkaran juringLingkaran = dataManager.getJuringLingkaran();
        panel.add(new JLabel("<html><h2>--- Juring Lingkaran ---</h2></html>"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(createAlignedLabel("Jari-Jari (Diwarisi): " + String.format("%.2f", juringLingkaran.jariJari)));
        panel.add(createAlignedLabel("Sudut Busur (Derajat): " + String.format("%.2f", juringLingkaran.sudutBusurDerajat)));
        panel.add(createAlignedLabel("Luas: " + String.format("%.2f", juringLingkaran.hitungLuas())));
        panel.add(createAlignedLabel("Keliling: " + String.format("%.2f", juringLingkaran.hitungKeliling())));

        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        JTextField inputSudutBusur = new JTextField(String.valueOf(juringLingkaran.sudutBusurDerajat), 10);
        JButton btnUpdateSudut = new JButton("Perbarui Sudut Busur");
        JLabel statusSudut = createAlignedLabel("Status: ");

        JPanel inputControlPanel = createInputPanel("Sudut Busur Baru (Derajat):", inputSudutBusur, btnUpdateSudut, null);
        panel.add(inputControlPanel);
        panel.add(statusSudut);

        btnUpdateSudut.addActionListener(e -> {
            try {
                double newSudut = Double.parseDouble(inputSudutBusur.getText());
                if (newSudut <= 0 || newSudut >= 360) {
                    throw new IllegalArgumentException("Sudut harus > 0 dan < 360.");
                }
                juringLingkaran.sudutBusurDerajat = newSudut;
                showPanel(juringLingkaran, this::createJuringLingkaranDetailsPanel);
            } catch (Exception ex) {
                statusSudut.setText("Error: " + ex.getMessage());
            }
        });
    }

    private void createTemberengLingkaranDetailsPanel(JPanel panel) {
        TemberengLingkaran temberengLingkaran = dataManager.getTemberengLingkaran();
        panel.add(new JLabel("<html><h2>--- Tembereng Lingkaran ---</h2></html>"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(createAlignedLabel("Jari-Jari (Diwarisi): " + String.format("%.2f", temberengLingkaran.jariJari)));
        panel.add(createAlignedLabel("Sudut Pusat (Derajat): " + String.format("%.2f", temberengLingkaran.getSudutPusatDerajat())));
        panel.add(createAlignedLabel("Luas: " + String.format("%.2f", temberengLingkaran.hitungLuas())));
        panel.add(createAlignedLabel("Keliling: " + String.format("%.2f", temberengLingkaran.hitungKeliling())));

        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        JTextField inputSudutPusat = new JTextField(String.valueOf(temberengLingkaran.getSudutPusatDerajat()), 10);
        JButton btnUpdateSudut = new JButton("Perbarui Sudut Pusat");
        JLabel statusSudut = createAlignedLabel("Status: ");

        JPanel inputControlPanel = createInputPanel("Sudut Pusat Baru (Derajat):", inputSudutPusat, btnUpdateSudut, null);
        panel.add(inputControlPanel);
        panel.add(statusSudut);

        btnUpdateSudut.addActionListener(e -> {
            try {
                double newSudut = Double.parseDouble(inputSudutPusat.getText());
                if (newSudut <= 0 || newSudut >= 360) {
                    throw new IllegalArgumentException("Sudut pusat harus > 0 dan < 360 derajat.");
                }
                temberengLingkaran.sudutPusatDerajat = newSudut;
                showPanel(temberengLingkaran, this::createTemberengLingkaranDetailsPanel);
            } catch (Exception ex) {
                statusSudut.setText("Error: " + ex.getMessage());
            }
        });
    }

    private void createTabungDetailsPanel(JPanel panel) {
        Tabung tabung = dataManager.getTabung();
        panel.add(new JLabel("<html><h2>--- Tabung ---</h2></html>"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(createAlignedLabel("Jari-Jari (Saat Ini): " + String.format("%.2f", tabung.jariJari)));
        panel.add(createAlignedLabel("Tinggi: " + String.format("%.2f", tabung.getTinggi())));
        panel.add(createAlignedLabel("Volume: " + String.format("%.2f", tabung.hitungVolume())));
        panel.add(createAlignedLabel("Luas Permukaan: " + String.format("%.2f", tabung.hitungLuasPermukaan())));

        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        JTextField inputJariJari = new JTextField(String.valueOf(tabung.jariJari), 10);
        JTextField inputTinggi = new JTextField(String.valueOf(tabung.getTinggi()), 10);
        JButton btnUpdate = new JButton("Perbarui Dimensi");
        JLabel status = createAlignedLabel("Status: ");

        panel.add(createInputPanel("Jari-Jari Baru:", inputJariJari, null, null));
        panel.add(createInputPanel("Tinggi Baru:", inputTinggi, null, null));
        panel.add(btnUpdate);
        panel.add(status);

        btnUpdate.addActionListener(e -> {
            try {
                double newJariJari = Double.parseDouble(inputJariJari.getText());
                double newTinggi = Double.parseDouble(inputTinggi.getText());
                dataManager.updateTabungDimensions(newJariJari, newTinggi);
                showPanel(tabung, this::createTabungDetailsPanel);
            } catch (Exception ex) {
                status.setText("Error: " + ex.getMessage());
            }
        });
    }

    private void createKerucutDetailsPanel(JPanel panel) {
        Kerucut kerucut = dataManager.getKerucut();
        panel.add(new JLabel("<html><h2>--- Kerucut ---</h2></html>"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(createAlignedLabel("Jari-Jari (Diwarisi): " + String.format("%.2f", kerucut.jariJari)));
        panel.add(createAlignedLabel("Tinggi: " + String.format("%.2f", kerucut.tinggi)));
        panel.add(createAlignedLabel("Garis Pelukis: " + String.format("%.2f", kerucut.getGarisPelukis())));
        panel.add(createAlignedLabel("Volume: " + String.format("%.2f", kerucut.hitungVolume())));
        panel.add(createAlignedLabel("Luas Permukaan: " + String.format("%.2f", kerucut.hitungLuasPermukaan())));

        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        JTextField inputJariJari = new JTextField(String.valueOf(kerucut.jariJari), 10);
        JTextField inputTinggi = new JTextField(String.valueOf(kerucut.tinggi), 10);
        JButton btnUpdate = new JButton("Perbarui Dimensi");
        JLabel status = createAlignedLabel("Status: ");

        panel.add(createInputPanel("Jari-Jari Baru:", inputJariJari, null, null));
        panel.add(createInputPanel("Tinggi Baru:", inputTinggi, null, null));
        panel.add(btnUpdate);
        panel.add(status);

        btnUpdate.addActionListener(e -> {
            try {
                double newJariJari = Double.parseDouble(inputJariJari.getText());
                double newTinggi = Double.parseDouble(inputTinggi.getText());
                dataManager.updateKerucutDimensions(newJariJari, newTinggi);
                showPanel(kerucut, this::createKerucutDetailsPanel);
            } catch (Exception ex) {
                status.setText("Error: " + ex.getMessage());
            }
        });
    }

    private void createKerucutTerpancungDetailsPanel(JPanel panel) {
        KerucutTerpancung kt = dataManager.getKerucutTerpancung();
        panel.add(new JLabel("<html><h2>--- Kerucut Terpancung ---</h2></html>"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(createAlignedLabel("Jari-Jari Bawah: " + String.format("%.2f", kt.jariJari)));
        panel.add(createAlignedLabel("Jari-Jari Atas: " + String.format("%.2f", kt.jariJariAtas)));
        panel.add(createAlignedLabel("Tinggi Frustum: " + String.format("%.2f", kt.tinggiFrustum)));
        panel.add(createAlignedLabel("Garis Pelukis: " + String.format("%.2f", kt.hitungGarisPelukis())));
        panel.add(createAlignedLabel("Volume: " + String.format("%.2f", kt.hitungVolume())));
        panel.add(createAlignedLabel("Luas Permukaan: " + String.format("%.2f", kt.hitungLuasPermukaan())));

        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        JTextField inputJRB = new JTextField(String.valueOf(kt.jariJari), 10);
        JTextField inputJRA = new JTextField(String.valueOf(kt.jariJariAtas), 10);
        JTextField inputTF = new JTextField(String.valueOf(kt.tinggiFrustum), 10);
        JButton btnUpdate = new JButton("Perbarui Dimensi");
        JLabel status = createAlignedLabel("Status: ");

        panel.add(createInputPanel("Jari-Jari Bawah:", inputJRB, null, null));
        panel.add(createInputPanel("Jari-Jari Atas:", inputJRA, null, null));
        panel.add(createInputPanel("Tinggi Frustum:", inputTF, null, null));
        panel.add(btnUpdate);
        panel.add(status);

        btnUpdate.addActionListener(e -> {
            try {
                double newJRB = Double.parseDouble(inputJRB.getText());
                double newJRA = Double.parseDouble(inputJRA.getText());
                double newTF = Double.parseDouble(inputTF.getText());
                dataManager.updateKerucutTerpancungDimensions(newJRB, newJRA, newTF);
                showPanel(kt, this::createKerucutTerpancungDetailsPanel);
            } catch (Exception ex) {
                status.setText("Error: " + ex.getMessage());
            }
        });
    }

    private void createPersegiDetailsPanel(JPanel panel) {
        Persegi persegi = dataManager.getPersegi();

        panel.add(new JLabel("--- Persegi ---"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(createAlignedLabel("Sisi (Saat Ini): " + String.format("%.2f", persegi.sisi)));
        panel.add(createAlignedLabel("Luas (Saat Ini): " + String.format("%.2f", persegi.hitungLuas())));
        panel.add(createAlignedLabel("Keliling (Saat Ini): " + String.format("%.2f", persegi.hitungKeliling())));

        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        JTextField inputSisi = new JTextField(String.valueOf(persegi.sisi), 10);
        JButton btnUpdateSisi = new JButton("Terapkan Sisi");
        JLabel hasilUpdate = createAlignedLabel("Status: ");

        panel.add(createInputPanel("Sisi Baru:", inputSisi, btnUpdateSisi, null));
        panel.add(hasilUpdate);

        btnUpdateSisi.addActionListener(e -> {
            try {
                double newSisi = Double.parseDouble(inputSisi.getText());
                dataManager.updatePersegiSisi(newSisi);
                showPanel(persegi, this::createPersegiDetailsPanel);
            } catch (Exception ex) {
                hasilUpdate.setText("Error: " + ex.getMessage());
            }
        });
    }

    private void createLimasPersegiDetailsPanel(JPanel panel) {
        LimasPersegi limasPersegi = dataManager.getLimasPersegi();
        panel.add(new JLabel("<html><h2>--- Limas Persegi ---</h2></html>"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(createAlignedLabel("Sisi Alas (Diwarisi): " + String.format("%.2f", limasPersegi.sisi)));
        panel.add(createAlignedLabel("Tinggi Limas: " + String.format("%.2f", limasPersegi.getTinggiLimas())));
        panel.add(createAlignedLabel("Volume: " + String.format("%.2f", limasPersegi.hitungVolume())));
        panel.add(createAlignedLabel("Luas Permukaan: " + String.format("%.2f", limasPersegi.hitungLuasPermukaan())));

        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        JTextField inputTinggiLimas = new JTextField(String.valueOf(limasPersegi.getTinggiLimas()), 10);
        JButton btnUpdateTinggi = new JButton("Perbarui Tinggi Limas");
        JLabel status = createAlignedLabel("Status: ");

        panel.add(createInputPanel("Tinggi Limas Baru:", inputTinggiLimas, btnUpdateTinggi, null));
        panel.add(status);

        btnUpdateTinggi.addActionListener(e -> {
            try {
                double newTinggiLimas = Double.parseDouble(inputTinggiLimas.getText());
                dataManager.updateLimasPersegiTinggi(newTinggiLimas);
                showPanel(limasPersegi, this::createLimasPersegiDetailsPanel);
            } catch (Exception ex) {
                status.setText("Error: " + ex.getMessage());
            }
        });
    }

    private void createPrismaPersegiDetailsPanel(JPanel panel) {
        PrismaPersegi prismaPersegi = dataManager.getPrismaPersegi();
        panel.add(new JLabel("<html><h2>--- Prisma Persegi (Kubus) ---</h2></html>"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(createAlignedLabel("Sisi (Saat Ini): " + String.format("%.2f", prismaPersegi.sisi)));
        panel.add(createAlignedLabel("Volume: " + String.format("%.2f", prismaPersegi.hitungVolume())));
        panel.add(createAlignedLabel("Luas Permukaan: " + String.format("%.2f", prismaPersegi.hitungLuasPermukaan())));

        panel.add(Box.createRigidArea(new Dimension(0, 25)));
        panel.add(createAlignedLabel("<html><i>Untuk memperbarui sisi kubus, gunakan menu 'Persegi (Dasar)'.</i></html>"));
    }

    private void createPersegiPanjangDetailsPanel(JPanel panel) {
        PersegiPanjang pp = dataManager.getPersegiPanjang();
        panel.add(new JLabel("<html><h2>--- Persegi Panjang ---</h2></html>"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(createAlignedLabel("Panjang: " + String.format("%.2f", pp.panjang)));
        panel.add(createAlignedLabel("Lebar: " + String.format("%.2f", pp.lebar)));
        panel.add(createAlignedLabel("Luas: " + String.format("%.2f", pp.hitungLuas())));
        panel.add(createAlignedLabel("Keliling: " + String.format("%.2f", pp.hitungKeliling())));

        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        JTextField inputPanjang = new JTextField(String.valueOf(pp.panjang), 10);
        JTextField inputLebar = new JTextField(String.valueOf(pp.lebar), 10);
        JButton btnUpdate = new JButton("Perbarui Dimensi");
        JLabel status = createAlignedLabel("Status: ");

        panel.add(createInputPanel("Panjang Baru:", inputPanjang, null, null));
        panel.add(createInputPanel("Lebar Baru:", inputLebar, null, null));
        panel.add(btnUpdate);
        panel.add(status);

        btnUpdate.addActionListener(e -> {
            try {
                double newPanjang = Double.parseDouble(inputPanjang.getText());
                double newLebar = Double.parseDouble(inputLebar.getText());
                dataManager.updatePersegiPanjangDimensions(newPanjang, newLebar);
                showPanel(pp, this::createPersegiPanjangDetailsPanel);
            } catch (Exception ex) {
                status.setText("Error: " + ex.getMessage());
            }
        });
    }

    private void createLimasPersegiPanjangDetailsPanel(JPanel panel) {
        LimasPersegiPanjang lpp = dataManager.getLimasPersegiPanjang();
        panel.add(new JLabel("<html><h2>--- Limas Persegi Panjang ---</h2></html>"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(createAlignedLabel("Panjang Alas (Diwarisi): " + String.format("%.2f", lpp.panjang)));
        panel.add(createAlignedLabel("Lebar Alas (Diwarisi): " + String.format("%.2f", lpp.lebar)));
        panel.add(createAlignedLabel("Tinggi Limas: " + String.format("%.2f", lpp.tinggiLimas)));
        panel.add(createAlignedLabel("Volume: " + String.format("%.2f", lpp.hitungVolume())));
        panel.add(createAlignedLabel("Luas Permukaan: " + String.format("%.2f", lpp.hitungLuasPermukaan())));

        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        JTextField inputTinggiLimas = new JTextField(String.valueOf(lpp.tinggiLimas), 10);
        JButton btnUpdateTinggi = new JButton("Perbarui Tinggi Limas");
        JLabel status = createAlignedLabel("Status: ");

        panel.add(createInputPanel("Tinggi Limas Baru:", inputTinggiLimas, btnUpdateTinggi, null));
        panel.add(status);

        btnUpdateTinggi.addActionListener(e -> {
            try {
                double newTinggiLimas = Double.parseDouble(inputTinggiLimas.getText());
                dataManager.updateLimasPersegiPanjangTinggi(newTinggiLimas);
                showPanel(lpp, this::createLimasPersegiPanjangDetailsPanel);
            } catch (Exception ex) {
                status.setText("Error: " + ex.getMessage());
            }
        });
    }

    private void createPrismaPersegiPanjangDetailsPanel(JPanel panel) {
        PrismaPersegiPanjang ppp = dataManager.getPrismaPersegiPanjang();
        panel.add(new JLabel("<html><h2>--- Prisma Persegi Panjang (Balok) ---</h2></html>"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(createAlignedLabel("Panjang Alas: " + String.format("%.2f", ppp.panjang)));
        panel.add(createAlignedLabel("Lebar Alas: " + String.format("%.2f", ppp.lebar)));
        panel.add(createAlignedLabel("Tinggi Prisma: " + String.format("%.2f", ppp.tinggiPrisma)));
        panel.add(createAlignedLabel("Volume: " + String.format("%.2f", ppp.hitungVolume())));
        panel.add(createAlignedLabel("Luas Permukaan: " + String.format("%.2f", ppp.hitungLuasPermukaan())));

        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        JTextField inputTinggiPrisma = new JTextField(String.valueOf(ppp.tinggiPrisma), 10);
        JButton btnUpdateTinggi = new JButton("Perbarui Tinggi Prisma");
        JLabel status = createAlignedLabel("Status: ");

        panel.add(createInputPanel("Tinggi Prisma Baru:", inputTinggiPrisma, btnUpdateTinggi, null));
        panel.add(status);

        btnUpdateTinggi.addActionListener(e -> {
            try {
                double newTinggiPrisma = Double.parseDouble(inputTinggiPrisma.getText());
                dataManager.updatePrismaPersegiPanjangTinggi(newTinggiPrisma);
                showPanel(ppp, this::createPrismaPersegiPanjangDetailsPanel);
            } catch (Exception ex) {
                status.setText("Error: " + ex.getMessage());
            }
        });
    }

    private void createSegitigaDetailsPanel(JPanel panel) {
        Segitiga s = dataManager.getSegitiga();
        panel.add(new JLabel("<html><h2>--- Segitiga ---</h2></html>"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(createAlignedLabel("Alas (u/ Luas): " + String.format("%.2f", s.alasUntukLuas)));
        panel.add(createAlignedLabel("Tinggi (u/ Luas): " + String.format("%.2f", s.tinggiUntukLuas)));
        panel.add(createAlignedLabel("Sisi A: " + String.format("%.2f", s.sisiA)));
        panel.add(createAlignedLabel("Sisi B: " + String.format("%.2f", s.sisiB)));
        panel.add(createAlignedLabel("Sisi C: " + String.format("%.2f", s.sisiC)));
        panel.add(createAlignedLabel("Luas: " + String.format("%.2f", s.hitungLuas())));
        panel.add(createAlignedLabel("Keliling: " + String.format("%.2f", s.hitungKeliling())));

        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        JTextField inputAlas = new JTextField(String.valueOf(s.alasUntukLuas), 10);
        JTextField inputTinggiLuas = new JTextField(String.valueOf(s.tinggiUntukLuas), 10);
        JTextField inputSisiA = new JTextField(String.valueOf(s.sisiA), 10);
        JTextField inputSisiB = new JTextField(String.valueOf(s.sisiB), 10);
        JTextField inputSisiC = new JTextField(String.valueOf(s.sisiC), 10);
        JButton btnUpdate = new JButton("Perbarui Dimensi");
        JLabel status = createAlignedLabel("Status: ");

        panel.add(createInputPanel("Alas (Luas):", inputAlas, null, null));
        panel.add(createInputPanel("Tinggi (Luas):", inputTinggiLuas, null, null));
        panel.add(createInputPanel("Sisi A:", inputSisiA, null, null));
        panel.add(createInputPanel("Sisi B:", inputSisiB, null, null));
        panel.add(createInputPanel("Sisi C:", inputSisiC, null, null));
        panel.add(btnUpdate);
        panel.add(status);

        btnUpdate.addActionListener(e -> {
            try {
                double newAlas = Double.parseDouble(inputAlas.getText());
                double newTinggiLuas = Double.parseDouble(inputTinggiLuas.getText());
                double newSisiA = Double.parseDouble(inputSisiA.getText());
                double newSisiB = Double.parseDouble(inputSisiB.getText());
                double newSisiC = Double.parseDouble(inputSisiC.getText());
                dataManager.updateSegitigaDimensions(newAlas, newTinggiLuas, newSisiA, newSisiB, newSisiC);
                showPanel(s, this::createSegitigaDetailsPanel);
            } catch (Exception ex) {
                status.setText("Error: " + ex.getMessage());
            }
        });
    }

    private void createLimasSegitigaDetailsPanel(JPanel panel) {
        LimasSegitiga ls = dataManager.getLimasSegitiga();
        panel.add(new JLabel("<html><h2>--- Limas Segitiga ---</h2></html>"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(createAlignedLabel("Tinggi Limas: " + String.format("%.2f", ls.getTinggiLimas())));
        panel.add(createAlignedLabel("Volume: " + String.format("%.2f", ls.hitungVolume())));
        panel.add(createAlignedLabel("Luas Permukaan: " + String.format("%.2f", ls.hitungLuasPermukaan())));

        panel.add(Box.createRigidArea(new Dimension(0, 25)));
        panel.add(createAlignedLabel("<html><i>Dimensi alas diatur di 'Segitiga (Dasar)'.</i></html>"));
    }

    private void createPrismaSegitigaDetailsPanel(JPanel panel) {
        PrismaSegitiga ps = dataManager.getPrismaSegitiga();
        panel.add(new JLabel("<html><h2>--- Prisma Segitiga ---</h2></html>"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(createAlignedLabel("Tinggi Prisma: " + String.format("%.2f", ps.getTinggiPrisma())));
        panel.add(createAlignedLabel("Volume: " + String.format("%.2f", ps.hitungVolume())));
        panel.add(createAlignedLabel("Luas Permukaan: " + String.format("%.2f", ps.hitungLuasPermukaan())));

        panel.add(Box.createRigidArea(new Dimension(0, 25)));
        panel.add(createAlignedLabel("<html><i>Dimensi alas diatur di 'Segitiga (Dasar)'.</i></html>"));
    }
    private void createBelahKetupatDetailsPanel(JPanel panel) {
        BelahKetupat bk = dataManager.getBelahKetupat();

        panel.add(new JLabel("--- Belah Ketupat ---"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(createAlignedLabel("Diagonal 1: " + String.format("%.2f", bk.diagonal1)));
        panel.add(createAlignedLabel("Diagonal 2: " + String.format("%.2f", bk.diagonal2)));
        panel.add(createAlignedLabel("Sisi: " + String.format("%.2f", bk.sisi)));
        panel.add(createAlignedLabel("Luas: " + String.format("%.2f", bk.hitungLuas())));
        panel.add(createAlignedLabel("Keliling: " + String.format("%.2f", bk.hitungKeliling())));

        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        JTextField inputD1 = new JTextField(String.valueOf(bk.diagonal1), 10);
        JTextField inputD2 = new JTextField(String.valueOf(bk.diagonal2), 10);
        JTextField inputSisi = new JTextField(String.valueOf(bk.sisi), 10);
        JButton btnUpdate = new JButton("Perbarui Dimensi Belah Ketupat");
        JLabel status = createAlignedLabel("Status: ");

        panel.add(createInputPanel("Diagonal 1:", inputD1, null, null));
        panel.add(createInputPanel("Diagonal 2:", inputD2, null, null));
        panel.add(createInputPanel("Sisi:", inputSisi, null, null));
        panel.add(btnUpdate);
        panel.add(status);

        btnUpdate.addActionListener(e -> {
            try {
                double newD1 = Double.parseDouble(inputD1.getText());
                double newD2 = Double.parseDouble(inputD2.getText());
                double newSisi = Double.parseDouble(inputSisi.getText());
                dataManager.updateBelahKetupatDimensions(newD1, newD2, newSisi);
                status.setText("Status: Dimensi Belah Ketupat diperbarui.");
                showPanel(bk, this::createBelahKetupatDetailsPanel);
            } catch (Exception ex) {
                status.setText("Error: " + ex.getMessage());
            }
        });
    }

    private void createLimasBelahKetupatDetailsPanel(JPanel panel) {
        LimasBelahKetupat lbk = dataManager.getLimasBelahKetupat();
        panel.add(new JLabel("<html><h2>--- Limas Belah Ketupat ---</h2></html>"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(createAlignedLabel("Tinggi Limas: " + String.format("%.2f", lbk.tinggiLimas)));
        panel.add(createAlignedLabel("Volume: " + String.format("%.2f", lbk.hitungVolume())));
        panel.add(createAlignedLabel("Luas Permukaan: " + String.format("%.2f", lbk.hitungLuasPermukaan())));

        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        JTextField inputTinggiLimas = new JTextField(String.valueOf(lbk.tinggiLimas), 10);
        JButton btnUpdateTinggi = new JButton("Perbarui Tinggi Limas");
        JLabel status = createAlignedLabel("Status: ");

        panel.add(createInputPanel("Tinggi Limas Baru:", inputTinggiLimas, btnUpdateTinggi, null));
        panel.add(status);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(createAlignedLabel("<html><i>Dimensi alas (diagonal) diatur di 'Belah Ketupat (Dasar)'.</i></html>"));

        btnUpdateTinggi.addActionListener(e -> {
            try {
                double newTinggi = Double.parseDouble(inputTinggiLimas.getText());
                dataManager.updateLimasBelahKetupatTinggi(newTinggi);
                showPanel(lbk, this::createLimasBelahKetupatDetailsPanel);
            } catch (Exception ex) {
                status.setText("Error: " + ex.getMessage());
            }
        });
    }

    private void createPrismaBelahKetupatDetailsPanel(JPanel panel) {
        PrismaBelahKetupat pbk = dataManager.getPrismaBelahKetupat();
        panel.add(new JLabel("<html><h2>--- Prisma Belah Ketupat ---</h2></html>"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(createAlignedLabel("Tinggi Prisma: " + String.format("%.2f", pbk.tinggiPrisma)));
        panel.add(createAlignedLabel("Volume: " + String.format("%.2f", pbk.hitungVolume())));
        panel.add(createAlignedLabel("Luas Permukaan: " + String.format("%.2f", pbk.hitungLuasPermukaan())));

        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        JTextField inputTinggiPrisma = new JTextField(String.valueOf(pbk.tinggiPrisma), 10);
        JButton btnUpdateTinggi = new JButton("Perbarui Tinggi Prisma");
        JLabel status = createAlignedLabel("Status: ");

        panel.add(createInputPanel("Tinggi Prisma Baru:", inputTinggiPrisma, btnUpdateTinggi, null));
        panel.add(status);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(createAlignedLabel("<html><i>Dimensi alas (diagonal) diatur di 'Belah Ketupat (Dasar)'.</i></html>"));

        btnUpdateTinggi.addActionListener(e -> {
            try {
                double newTinggi = Double.parseDouble(inputTinggiPrisma.getText());
                dataManager.updatePrismaBelahKetupatTinggi(newTinggi);
                showPanel(pbk, this::createPrismaBelahKetupatDetailsPanel);
            } catch (Exception ex) {
                status.setText("Error: " + ex.getMessage());
            }
        });
    }

    private void createJajarGenjangDetailsPanel(JPanel panel) {
        JajarGenjang jg = dataManager.getJajarGenjang();
        panel.add(new JLabel("<html><h2>--- Jajar Genjang ---</h2></html>"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(createAlignedLabel("Alas: " + String.format("%.2f", jg.alas)));
        panel.add(createAlignedLabel("Tinggi: " + String.format("%.2f", jg.tinggi)));
        panel.add(createAlignedLabel("Sisi Miring: " + String.format("%.2f", jg.sisiMiring)));
        panel.add(createAlignedLabel("Luas: " + String.format("%.2f", jg.hitungLuas())));
        panel.add(createAlignedLabel("Keliling: " + String.format("%.2f", jg.hitungKeliling())));

        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        JTextField inputAlas = new JTextField(String.valueOf(jg.alas), 10);
        JTextField inputTinggi = new JTextField(String.valueOf(jg.tinggi), 10);
        JTextField inputSisiMiring = new JTextField(String.valueOf(jg.sisiMiring), 10);
        JButton btnUpdate = new JButton("Perbarui Dimensi");
        JLabel status = createAlignedLabel("Status: ");

        panel.add(createInputPanel("Alas Baru:", inputAlas, null, null));
        panel.add(createInputPanel("Tinggi Baru:", inputTinggi, null, null));
        panel.add(createInputPanel("Sisi Miring Baru:", inputSisiMiring, null, null));
        panel.add(btnUpdate);
        panel.add(status);

        btnUpdate.addActionListener(e -> {
            try {
                double newAlas = Double.parseDouble(inputAlas.getText());
                double newTinggi = Double.parseDouble(inputTinggi.getText());
                double newSisiMiring = Double.parseDouble(inputSisiMiring.getText());
                dataManager.updateJajarGenjangDimensions(newAlas, newTinggi, newSisiMiring);
                showPanel(jg, this::createJajarGenjangDetailsPanel);
            } catch (Exception ex) {
                status.setText("Error: " + ex.getMessage());
            }
        });
    }

    private void createLimasJajarGenjangDetailsPanel(JPanel panel) {
        LimasJajarGenjang ljg = dataManager.getLimasJajarGenjang();
        panel.add(new JLabel("<html><h2>--- Limas Jajar Genjang ---</h2></html>"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(createAlignedLabel("Tinggi Limas: " + String.format("%.2f", ljg.tinggiLimas)));
        panel.add(createAlignedLabel("Volume: " + String.format("%.2f", ljg.hitungVolume())));
        panel.add(createAlignedLabel("Luas Permukaan: " + String.format("%.2f", ljg.hitungLuasPermukaan())));

        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        JTextField inputTinggiLimas = new JTextField(String.valueOf(ljg.tinggiLimas), 10);
        JButton btnUpdateTinggi = new JButton("Perbarui Tinggi Limas");
        JLabel status = createAlignedLabel("Status: ");

        panel.add(createInputPanel("Tinggi Limas Baru:", inputTinggiLimas, btnUpdateTinggi, null));
        panel.add(status);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(createAlignedLabel("<html><i>Dimensi alas diatur di 'Jajar Genjang (Dasar)'.<br>Tinggi sisi tegak diasumsikan tetap dari konstruktor awal.</i></html>"));

        btnUpdateTinggi.addActionListener(e -> {
            try {
                double newTinggi = Double.parseDouble(inputTinggiLimas.getText());
                dataManager.updateLimasJajarGenjangTinggi(newTinggi);
                showPanel(ljg, this::createLimasJajarGenjangDetailsPanel);
            } catch (Exception ex) {
                status.setText("Error: " + ex.getMessage());
            }
        });
    }

    private void createPrismaJajarGenjangDetailsPanel(JPanel panel) {
        PrismaJajarGenjang pjg = dataManager.getPrismaJajarGenjang();
        panel.add(new JLabel("<html><h2>--- Prisma Jajar Genjang ---</h2></html>"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(createAlignedLabel("Tinggi Prisma: " + String.format("%.2f", pjg.tinggiPrisma)));
        panel.add(createAlignedLabel("Volume: " + String.format("%.2f", pjg.hitungVolume())));
        panel.add(createAlignedLabel("Luas Permukaan: " + String.format("%.2f", pjg.hitungLuasPermukaan())));

        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        JTextField inputTinggiPrisma = new JTextField(String.valueOf(pjg.tinggiPrisma), 10);
        JButton btnUpdateTinggi = new JButton("Perbarui Tinggi Prisma");
        JLabel status = createAlignedLabel("Status: ");

        panel.add(createInputPanel("Tinggi Prisma Baru:", inputTinggiPrisma, btnUpdateTinggi, null));
        panel.add(status);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(createAlignedLabel("<html><i>Dimensi alas diatur di 'Jajar Genjang (Dasar)'.</i></html>"));

        btnUpdateTinggi.addActionListener(e -> {
            try {
                double newTinggi = Double.parseDouble(inputTinggiPrisma.getText());
                dataManager.updatePrismaJajarGenjangTinggi(newTinggi);
                showPanel(pjg, this::createPrismaJajarGenjangDetailsPanel);
            } catch (Exception ex) {
                status.setText("Error: " + ex.getMessage());
            }
        });
    }

    private void createLayangLayangDetailsPanel(JPanel panel) {
        LayangLayang ll = dataManager.getLayangLayang();
        panel.add(new JLabel("<html><h2>--- Layang-Layang ---</h2></html>"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(createAlignedLabel("Diagonal 1: " + String.format("%.2f", ll.diagonal1)));
        panel.add(createAlignedLabel("Diagonal 2: " + String.format("%.2f", ll.diagonal2)));
        panel.add(createAlignedLabel("Sisi Pendek: " + String.format("%.2f", ll.sisiPendek)));
        panel.add(createAlignedLabel("Sisi Panjang: " + String.format("%.2f", ll.sisiPanjang)));
        panel.add(createAlignedLabel("Luas: " + String.format("%.2f", ll.hitungLuas())));
        panel.add(createAlignedLabel("Keliling: " + String.format("%.2f", ll.hitungKeliling())));

        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        JTextField inputD1 = new JTextField(String.valueOf(ll.diagonal1), 10);
        JTextField inputD2 = new JTextField(String.valueOf(ll.diagonal2), 10);
        JTextField inputSisiPendek = new JTextField(String.valueOf(ll.sisiPendek), 10);
        JTextField inputSisiPanjang = new JTextField(String.valueOf(ll.sisiPanjang), 10);
        JButton btnUpdate = new JButton("Perbarui Dimensi");
        JLabel status = createAlignedLabel("Status: ");

        panel.add(createInputPanel("Diagonal 1:", inputD1, null, null));
        panel.add(createInputPanel("Diagonal 2:", inputD2, null, null));
        panel.add(createInputPanel("Sisi Pendek:", inputSisiPendek, null, null));
        panel.add(createInputPanel("Sisi Panjang:", inputSisiPanjang, null, null));
        panel.add(btnUpdate);
        panel.add(status);

        btnUpdate.addActionListener(e -> {
            try {
                double newD1 = Double.parseDouble(inputD1.getText());
                double newD2 = Double.parseDouble(inputD2.getText());
                double newSisiPendek = Double.parseDouble(inputSisiPendek.getText());
                double newSisiPanjang = Double.parseDouble(inputSisiPanjang.getText());
                dataManager.updateLayangLayangDimensions(newD1, newD2, newSisiPendek, newSisiPanjang);
                showPanel(ll, this::createLayangLayangDetailsPanel);
            } catch (Exception ex) {
                status.setText("Error: " + ex.getMessage());
            }
        });
    }

    private void createLimasLayangLayangDetailsPanel(JPanel panel) {
        LimasLayangLayang lll = dataManager.getLimasLayangLayang();
        panel.add(new JLabel("<html><h2>--- Limas Layang-Layang ---</h2></html>"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(createAlignedLabel("Tinggi Limas: " + String.format("%.2f", lll.getTinggiLimas())));
        panel.add(createAlignedLabel("Volume: " + String.format("%.2f", lll.hitungVolume())));
        panel.add(createAlignedLabel("Luas Permukaan: " + String.format("%.2f", lll.hitungLuasPermukaan())));

        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        JTextField inputTinggiLimas = new JTextField(String.valueOf(lll.getTinggiLimas()), 10);
        JButton btnUpdateTinggi = new JButton("Perbarui Tinggi Limas");
        JLabel status = createAlignedLabel("Status: ");

        panel.add(createInputPanel("Tinggi Limas Baru:", inputTinggiLimas, btnUpdateTinggi, null));
        panel.add(status);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(createAlignedLabel("<html><i>Dimensi alas diatur di 'Layang-Layang (Dasar)'.<br>Tinggi sisi tegak diasumsikan tetap.</i></html>"));

        btnUpdateTinggi.addActionListener(e -> {
            try {
                double newTinggi = Double.parseDouble(inputTinggiLimas.getText());
                dataManager.updateLimasLayangLayangTinggi(newTinggi);
                showPanel(lll, this::createLimasLayangLayangDetailsPanel);
            } catch (Exception ex) {
                status.setText("Error: " + ex.getMessage());
            }
        });
    }

    private void createPrismaLayangLayangDetailsPanel(JPanel panel) {
        PrismaLayangLayang pll = dataManager.getPrismaLayangLayang();
        panel.add(new JLabel("<html><h2>--- Prisma Layang-Layang ---</h2></html>"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(createAlignedLabel("Tinggi Prisma: " + String.format("%.2f", pll.tinggiPrisma)));
        panel.add(createAlignedLabel("Volume: " + String.format("%.2f", pll.hitungVolume())));
        panel.add(createAlignedLabel("Luas Permukaan: " + String.format("%.2f", pll.hitungLuasPermukaan())));

        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        JTextField inputTinggiPrisma = new JTextField(String.valueOf(pll.tinggiPrisma), 10);
        JButton btnUpdateTinggi = new JButton("Perbarui Tinggi Prisma");
        JLabel status = createAlignedLabel("Status: ");

        panel.add(createInputPanel("Tinggi Prisma Baru:", inputTinggiPrisma, btnUpdateTinggi, null));
        panel.add(status);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(createAlignedLabel("<html><i>Dimensi alas diatur di 'Layang-Layang (Dasar)'.</i></html>"));

        btnUpdateTinggi.addActionListener(e -> {
            try {
                double newTinggi = Double.parseDouble(inputTinggiPrisma.getText());
                dataManager.updatePrismaLayangLayangTinggi(newTinggi);
                showPanel(pll, this::createPrismaLayangLayangDetailsPanel);
            } catch (Exception ex) {
                status.setText("Error: " + ex.getMessage());
            }
        });
    }

    private void createTrapesiumDetailsPanel(JPanel panel) {
        Trapesium t = dataManager.getTrapesium();
        panel.add(new JLabel("<html><h2>--- Trapesium ---</h2></html>"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(createAlignedLabel("Sisi Atas: " + String.format("%.2f", t.sisiAtas)));
        panel.add(createAlignedLabel("Sisi Bawah: " + String.format("%.2f", t.sisiBawah)));
        panel.add(createAlignedLabel("Tinggi: " + String.format("%.2f", t.tinggi)));
        panel.add(createAlignedLabel("Sisi Kiri: " + String.format("%.2f", t.sisiKiri)));
        panel.add(createAlignedLabel("Sisi Kanan: " + String.format("%.2f", t.sisiKanan)));
        panel.add(createAlignedLabel("Luas: " + String.format("%.2f", t.hitungLuas())));
        panel.add(createAlignedLabel("Keliling: " + String.format("%.2f", t.hitungKeliling())));

        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        JTextField inputSisiAtas = new JTextField(String.valueOf(t.sisiAtas), 10);
        JTextField inputSisiBawah = new JTextField(String.valueOf(t.sisiBawah), 10);
        JTextField inputTinggi = new JTextField(String.valueOf(t.tinggi), 10);
        JButton btnUpdate = new JButton("Perbarui Dimensi");
        JLabel status = createAlignedLabel("Status: ");

        panel.add(createInputPanel("Sisi Atas Baru:", inputSisiAtas, null, null));
        panel.add(createInputPanel("Sisi Bawah Baru:", inputSisiBawah, null, null));
        panel.add(createInputPanel("Tinggi Baru:", inputTinggi, null, null));
        panel.add(btnUpdate);
        panel.add(status);
        panel.add(createAlignedLabel("<html><i>Sisi kiri & kanan akan dihitung ulang<br>dengan asumsi Trapesium Sama Kaki.</i></html>"));

        btnUpdate.addActionListener(e -> {
            try {
                double newSisiAtas = Double.parseDouble(inputSisiAtas.getText());
                double newSisiBawah = Double.parseDouble(inputSisiBawah.getText());
                double newTinggi = Double.parseDouble(inputTinggi.getText());
                double horizontalDiff = Math.abs(newSisiBawah - newSisiAtas) / 2.0;
                double newSisiMiring = Math.sqrt(Math.pow(horizontalDiff, 2) + Math.pow(newTinggi, 2));

                dataManager.updateTrapesiumDimensions(newSisiAtas, newSisiBawah, newTinggi, newSisiMiring, newSisiMiring);
                showPanel(t, this::createTrapesiumDetailsPanel);
            } catch (Exception ex) {
                status.setText("Error: " + ex.getMessage());
            }
        });
    }

    private void createLimasTrapesiumDetailsPanel(JPanel panel) {
        LimasTrapesium lt = dataManager.getLimasTrapesium();
        panel.add(new JLabel("<html><h2>--- Limas Trapesium ---</h2></html>"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(createAlignedLabel("Tinggi Limas: " + String.format("%.2f", lt.getTinggiLimas())));
        panel.add(createAlignedLabel("Volume: " + String.format("%.2f", lt.hitungVolume())));
        panel.add(createAlignedLabel("Luas Permukaan: " + String.format("%.2f", lt.hitungLuasPermukaan())));

        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        JTextField inputTinggiLimas = new JTextField(String.valueOf(lt.getTinggiLimas()), 10);
        JButton btnUpdateTinggi = new JButton("Perbarui Tinggi Limas");
        JLabel status = createAlignedLabel("Status: ");

        panel.add(createInputPanel("Tinggi Limas Baru:", inputTinggiLimas, btnUpdateTinggi, null));
        panel.add(status);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(createAlignedLabel("<html><i>Dimensi alas diatur di 'Trapesium (Dasar)'.<br>Tinggi sisi tegak diasumsikan tetap.</i></html>"));

        btnUpdateTinggi.addActionListener(e -> {
            try {
                double newTinggi = Double.parseDouble(inputTinggiLimas.getText());
                dataManager.updateLimasTrapesiumTinggi(newTinggi);
                showPanel(lt, this::createLimasTrapesiumDetailsPanel);
            } catch (Exception ex) {
                status.setText("Error: " + ex.getMessage());
            }
        });
    }

    private void createPrismaTrapesiumDetailsPanel(JPanel panel) {
        PrismaTrapesium pt = dataManager.getPrismaTrapesium();
        panel.add(new JLabel("<html><h2>--- Prisma Trapesium ---</h2></html>"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(createAlignedLabel("Tinggi Prisma: " + String.format("%.2f", pt.getTinggiPrisma())));
        panel.add(createAlignedLabel("Volume: " + String.format("%.2f", pt.hitungVolume())));
        panel.add(createAlignedLabel("Luas Permukaan: " + String.format("%.2f", pt.hitungLuasPermukaan())));

        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        JTextField inputTinggiPrisma = new JTextField(String.valueOf(pt.getTinggiPrisma()), 10);
        JButton btnUpdateTinggi = new JButton("Perbarui Tinggi Prisma");
        JLabel status = createAlignedLabel("Status: ");

        panel.add(createInputPanel("Tinggi Prisma Baru:", inputTinggiPrisma, btnUpdateTinggi, null));
        panel.add(status);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(createAlignedLabel("<html><i>Dimensi alas diatur di 'Trapesium (Dasar)'.</i></html>"));

        btnUpdateTinggi.addActionListener(e -> {
            try {
                double newTinggi = Double.parseDouble(inputTinggiPrisma.getText());
                dataManager.updatePrismaTrapesiumTinggi(newTinggi);
                showPanel(pt, this::createPrismaTrapesiumDetailsPanel);
            } catch (Exception ex) {
                status.setText("Error: " + ex.getMessage());
            }
        });
    }

    // >>> DIUBAH: Menghapus pemanggilan demo dari main agar tidak jalan otomatis
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GeometriGUI().setVisible(true));
    }
}