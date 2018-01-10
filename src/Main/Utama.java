/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import lib.NaviBayes;
import lib.koneksi;



/**
 *
 * @author Subayu
 */
public class Utama extends javax.swing.JFrame {
    private DefaultTableModel DftTabMode1;
    
    /**
     * Creates new form Utama
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Utama() {
        initComponents();
        tabelawal();
        ImageIcon icon = new ImageIcon("src/gambar/chip.png");
        setIconImage(icon.getImage());
        setLocationRelativeTo(null);
        setTitle("Tugas Bu Nafi - Kecerdasan Buatan Resiko Bisnis Metode Navy Bayes - Hari Rabu");
        update.setEnabled(false);
        hapus.setEnabled(false);
        simpan.setEnabled(true);
        ResetDepan.setEnabled(false);
        
       
    }
    public void hitung(){
        String gender = genderbox.getSelectedItem().toString();
        String pic = picbox.getSelectedItem().toString();
        String iq = iqbox.getSelectedItem().toString();
        String pe = pebox.getSelectedItem().toString();
        NaviBayes nv = new NaviBayes();
        nv.setGender(gender);
        nv.setIQ(iq);
        nv.setPE(pe);
        nv.setPIC(pic);
        nv.hipotesa();
        Hno.setText(String.valueOf(nv.getTotalNo()+"/"+nv.getTotal()));
        HYes.setText(String.valueOf(nv.getTotalYes()+"/"+nv.getTotal()));
        genderno.setText(String.valueOf(nv.getGenderNo()+"/"+nv.getTotalNo()));
        genderyes.setText(String.valueOf(nv.getGenderYes()+"/"+nv.getTotalYes()));
        picno.setText(String.valueOf(nv.getPICNo()+"/"+nv.getTotalNo()));
        picyes.setText(String.valueOf(nv.getPICYes()+"/"+nv.getTotalYes()));
        iqno.setText(String.valueOf(nv.getIQNo()+"/"+nv.getTotalNo()));
        iqyes.setText(String.valueOf(nv.getIQYes()+"/"+nv.getTotalYes()));
        peno.setText(String.valueOf(nv.getPENo()+"/"+nv.getTotalNo()));
        peyes.setText(String.valueOf(nv.getPENYEs()+"/"+nv.getTotalYes()));
        double total = nv.getTotal();
        double totalyes = nv.getTotalYes();
        double totalno = nv.getTotalNo();
        double genderno = nv.getGenderNo();
        double genderyes = nv.getGenderYes();
        double picno = nv.getPICNo();
        double picyes = nv.getPICYes();
        double iqno = nv.getIQNo();
        double iqyes = nv.getIQYes();
        double peno = nv.getPENo();
        double penyes = nv.getPENYEs();
        double yestot = (totalyes / total)*(genderyes / totalyes)*(picyes / totalyes)*(iqyes / totalyes)*(penyes / totalyes) ;
        double notot = (totalno / total)*(genderno / totalno)*(picno / totalno)*(iqno / totalno)*(peno / totalno) ;
        DecimalFormat df = new DecimalFormat("#0.000");
        String yes = df.format(yestot);
        String no = df.format(notot);
        hkno.setText(no);
        hkyes.setText(yes);
        if (yestot>notot) {
            hipotesa.setText("Membandingkan Nilai Hipotesa no dengan \nHipotesa Yes, hasilnya adalah besar hipotesa yes \nDengan Nilai "+yes+"\nSehingga Hipotesa Terakhir adalah Ya");
        }else if (notot>yestot) {
            hipotesa.setText("Membandingkan Nilai Hipotesa no dengan \nHipotesa Yes, hasilnya adalah besar hipotesa no \nDengan Nilai "+no+"\nSehingga Hipotesa Terakhir adalah No");
        }
    }
    public void resik(){
        inputID.setText("");
        InputPIC.setText("");
        InputIQ.setText("");
        InputPE.setSelectedIndex(0);
        InputGender.setSelectedIndex(0);
        InputCP.setSelectedIndex(0);
        
        //mengaktifkan tombol simpan
        simpan.setEnabled(true);
        update.setEnabled(false);
        hapus.setEnabled(false);
    }
    public void resikdepan(){
        genderbox.setSelectedIndex(0);
        genderno.setText("0");
        genderyes.setText("0");
        picbox.setSelectedIndex(0);
        picno.setText("0");
        picyes.setText("0");
        pebox.setSelectedIndex(0);
        peno.setText("0");
        peyes.setText("0");
        iqbox.setSelectedIndex(0);
        iqno.setText("0");
        iqyes.setText("0");
    }
    public void tabelawal(){
        Object[] Judul = {"Nomer","Gender","Parent In Come","IQ", "Paren Encouraged","Collage Plans"};
        DftTabMode1 = new DefaultTableModel(null, Judul);
        tblBisnis.setModel(DftTabMode1);
        
        //koneksi lib
        Connection konek = new koneksi().konek();
        
        //query ambil data
        try {
            String sql="Select * from resiko_bisnis order by id asc";
            Statement stm = konek.createStatement();
            ResultSet hasil = stm.executeQuery(sql);
            while (hasil.next()) {
                String id = hasil.getString("id");
                String gender = hasil.getString("gender");
                String pic = hasil.getString("parenincome");
                String iq = hasil.getString("iq");
                String pe = hasil.getString("parenincount");
                String cp = hasil.getString("collegplan");
                
                String[] dataField={id, gender, pic, iq, pe, cp};
                DftTabMode1.addRow(dataField);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Database Error"
                    + "\nDengan pesan error : " + e.getMessage());
        }
    }
    public void update(){
        Connection konek = new koneksi().konek();
        String sql = "update resiko_bisnis set gender= ?, parenincome = ?, iq = ?, parenincount = ?, collegplan = ? where id = '"+inputID.getText()+"'";
        PreparedStatement stm = null;
        try {
            stm = konek.prepareStatement(sql);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Database Error"
                    + "\nDengan pesan error : " + e.getMessage());
        }
        try {
            stm.setString(1, InputGender.getSelectedItem().toString());
            stm.setString(2, InputPIC.getText());
            stm.setString(3, InputIQ.getText());
            stm.setString(4, InputPE.getSelectedItem().toString());
            stm.setString(5, InputCP.getSelectedItem().toString());
            stm.executeUpdate();
            JOptionPane.showMessageDialog(null,"Data Berhasil diubah!");
            resik();
            tabelawal();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Query SQL Error"
                    + "\nDengan pesan error : " + e.getMessage());
        }
    }
    public void simpan(){
        if(InputGender.getSelectedItem().equals("")){
            JOptionPane.showMessageDialog(null, "Gender masih kosong!\n"
                    + "Silahkan diisi terlebih dahulu!");
            InputGender.requestFocus();
        }else if(InputPIC.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Parent In COme masih kosong!\n"
                    + "Silahkan diisi terlebih dahulu!");
            InputPIC.requestFocus();
        }else if(InputIQ.getText().equals("")){
            JOptionPane.showMessageDialog(null, "IQ masih kosong!\n"
                    + "Silahkan diisi terlebih dahulu!");
            InputIQ.requestFocus();
        }else if(InputPE.getSelectedItem().equals("")){
            JOptionPane.showMessageDialog(null, "Paren Encourage masih kosong!\n"
                    + "Silahkan diisi terlebih dahulu!");
            InputPE.requestFocus();
        }else if(InputCP.getSelectedItem().equals("")){
            JOptionPane.showMessageDialog(null, "College Plans masih kosong!\n"
                    + "Silahkan diisi terlebih dahulu!");
            InputCP.requestFocus();
        }else{
            try {
                Connection konek = new koneksi().konek();
                String sql = "insert into resiko_bisnis(id,gender,parenincome,iq,parenincount,collegplan)"
                        + " values(?,?,?,?,?,?)";
                java.sql.PreparedStatement stmt = konek.prepareStatement(sql);
                try {
                    stmt.setString(1, inputID.getText());
                    stmt.setString(2, InputGender.getSelectedItem().toString());
                    stmt.setString(3, InputPIC.getText());
                    stmt.setString(4, InputIQ.getText());
                    stmt.setString(5, InputPE.getSelectedItem().toString());
                    stmt.setString(6, InputCP.getSelectedItem().toString());
                    
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Data berhasil disimpan!");
                    resik();
                    tabelawal();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null,"Data Gagal disimpan!"
                    + "\nDengan pesan error : " + e.getMessage());
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Database Error"
                    + "\nDengan pesan error : " + e.getMessage());
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pagetabmenu = new javax.swing.JTabbedPane();
        pagemenu1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        genderbox = new javax.swing.JComboBox<>();
        picbox = new javax.swing.JComboBox<>();
        iqbox = new javax.swing.JComboBox<>();
        pebox = new javax.swing.JComboBox<>();
        hitung = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        genderno = new javax.swing.JLabel();
        picno = new javax.swing.JLabel();
        iqno = new javax.swing.JLabel();
        peno = new javax.swing.JLabel();
        genderyes = new javax.swing.JLabel();
        picyes = new javax.swing.JLabel();
        iqyes = new javax.swing.JLabel();
        peyes = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        Hno = new javax.swing.JLabel();
        HYes = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        hkno = new javax.swing.JLabel();
        hkyes = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        hipotesa = new javax.swing.JTextArea();
        jLabel31 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        ResetDepan = new javax.swing.JButton();
        pagemenu2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBisnis = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        inputID = new javax.swing.JTextField();
        InputGender = new javax.swing.JComboBox<>();
        InputIQ = new javax.swing.JTextField();
        InputPE = new javax.swing.JComboBox<>();
        InputCP = new javax.swing.JComboBox<>();
        InputPIC = new javax.swing.JTextField();
        simpan = new javax.swing.JButton();
        update = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        reset = new javax.swing.JButton();
        Keluar = new javax.swing.JButton();
        pagemenu3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("menuutama"); // NOI18N
        setResizable(false);

        jLabel8.setText("Hitung Resiko Bisnis Dengan Metode Navy Bayes - Java");

        jLabel9.setText("Gender ");

        jLabel10.setText("Parent In Come");

        jLabel11.setText("IQ");

        jLabel12.setText("Paren Encouraged");

        genderbox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));

        picbox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10000", "20000", "30000", "40000", "50000", "60000" }));

        iqbox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "60", "70", "80", "90", "100", "110", "120", "130" }));

        pebox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Not Encouraged", "Encouraged" }));

        hitung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/hitung.png"))); // NOI18N
        hitung.setText("Hitung");
        hitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hitungActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel23.setText("Nama");

        jLabel24.setText("Hipotesa No");

        jLabel25.setText("Hipotesa Yes");

        jLabel26.setText("Gender");

        jLabel27.setText("Parent In Come");

        jLabel28.setText("IQ");

        jLabel29.setText("Parent Encouraged");

        genderno.setText("0");

        picno.setText("0");

        iqno.setText("0");

        peno.setText("0");

        genderyes.setText("0");

        picyes.setText("0");

        iqyes.setText("0");

        peyes.setText("0");

        jLabel20.setText("Hipotesa");

        Hno.setText("0");

        HYes.setText("0");

        jLabel30.setText("Hasil Kali");

        hkno.setText("0");

        hkyes.setText("0");

        hipotesa.setColumns(20);
        hipotesa.setRows(5);
        jScrollPane2.setViewportView(hipotesa);

        jLabel31.setText("Hipotesa");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel27)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel29)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel30))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addComponent(genderno)
                                    .addComponent(picno)
                                    .addComponent(iqno)
                                    .addComponent(peno)
                                    .addComponent(Hno)
                                    .addComponent(hkno))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(hkyes)
                                    .addComponent(HYes)
                                    .addComponent(jLabel25)
                                    .addComponent(genderyes)
                                    .addComponent(picyes)
                                    .addComponent(iqyes)
                                    .addComponent(peyes)))
                            .addComponent(jLabel31))
                        .addGap(0, 96, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(Hno)
                    .addComponent(HYes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(genderno)
                    .addComponent(genderyes))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(picno)
                    .addComponent(picyes)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(iqno)
                        .addComponent(iqyes))
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(peno)
                    .addComponent(peyes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(hkno)
                    .addComponent(hkyes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        jLabel21.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
        jLabel21.setText("Kisaran");

        jLabel22.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
        jLabel22.setText("Kisaran");

        ResetDepan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/reset.png"))); // NOI18N
        ResetDepan.setText("Reset");
        ResetDepan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetDepanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pagemenu1Layout = new javax.swing.GroupLayout(pagemenu1);
        pagemenu1.setLayout(pagemenu1Layout);
        pagemenu1Layout.setHorizontalGroup(
            pagemenu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pagemenu1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pagemenu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pagemenu1Layout.createSequentialGroup()
                        .addComponent(ResetDepan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hitung))
                    .addGroup(pagemenu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel8)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pagemenu1Layout.createSequentialGroup()
                            .addGroup(pagemenu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9)
                                .addComponent(jLabel10)
                                .addComponent(jLabel11)
                                .addComponent(jLabel12))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pagemenu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(pebox, 0, 187, Short.MAX_VALUE)
                                .addComponent(iqbox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(picbox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(genderbox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pagemenu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addGap(28, 28, 28)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pagemenu1Layout.setVerticalGroup(
            pagemenu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pagemenu1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pagemenu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pagemenu1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pagemenu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(genderbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pagemenu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(picbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pagemenu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(iqbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pagemenu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(pebox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pagemenu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hitung)
                            .addComponent(ResetDepan))
                        .addGap(0, 139, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pagetabmenu.addTab("Menu Utama", pagemenu1);

        tblBisnis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblBisnis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBisnisMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBisnis);

        jLabel1.setText("Input Data Trainer");

        jLabel2.setText("ID");

        jLabel3.setText("Gender");

        jLabel4.setText("ParentIncome");

        jLabel5.setText("IQ");

        jLabel6.setText("Paren Enco");

        jLabel7.setText("CollegePlans");

        InputGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));

        InputPE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Not Encouraged", "Encouraged" }));

        InputCP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Yes" }));

        simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/simpan.png"))); // NOI18N
        simpan.setText("Simpan");
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });

        update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/update.png"))); // NOI18N
        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/hapus.png"))); // NOI18N
        hapus.setText("Hapus");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/reset.png"))); // NOI18N
        reset.setText("Reset");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        Keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/keluar.png"))); // NOI18N
        Keluar.setText("Keluar");
        Keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pagemenu2Layout = new javax.swing.GroupLayout(pagemenu2);
        pagemenu2.setLayout(pagemenu2Layout);
        pagemenu2Layout.setHorizontalGroup(
            pagemenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pagemenu2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pagemenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pagemenu2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pagemenu2Layout.createSequentialGroup()
                        .addGroup(pagemenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pagemenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(InputCP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(InputPE, 0, 156, Short.MAX_VALUE)
                            .addComponent(InputGender, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inputID)
                            .addComponent(InputIQ)
                            .addComponent(InputPIC)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pagemenu2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pagemenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pagemenu2Layout.createSequentialGroup()
                                .addComponent(hapus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(update))
                            .addComponent(Keluar, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pagemenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(reset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(simpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pagemenu2Layout.setVerticalGroup(
            pagemenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pagemenu2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pagemenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pagemenu2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pagemenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(inputID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pagemenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(InputGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pagemenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(InputPIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pagemenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(InputIQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pagemenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(InputPE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pagemenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(InputCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pagemenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(simpan)
                            .addComponent(update)
                            .addComponent(hapus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pagemenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(reset)
                            .addComponent(Keluar))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE))
                .addContainerGap())
        );

        pagetabmenu.addTab("Data Trainer", pagemenu2);

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/me.JPG"))); // NOI18N

        jLabel13.setText("Nama");

        jLabel15.setText("NIM");

        jLabel16.setText("Kelas");

        jLabel17.setText("Moch. Afif");

        jLabel18.setText("111210277");

        jLabel19.setText("TI V - A");

        jLabel33.setText("Nama");

        jLabel34.setText("NIM");

        jLabel35.setText("Kelas");

        jLabel36.setText("Maulidiyah K F");

        jLabel37.setText("111510038");

        jLabel38.setText("TI V - A");

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/ft2.png"))); // NOI18N

        javax.swing.GroupLayout pagemenu3Layout = new javax.swing.GroupLayout(pagemenu3);
        pagemenu3.setLayout(pagemenu3Layout);
        pagemenu3Layout.setHorizontalGroup(
            pagemenu3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pagemenu3Layout.createSequentialGroup()
                .addGroup(pagemenu3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pagemenu3Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jLabel14)
                        .addGap(189, 189, 189)
                        .addComponent(jLabel39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel32))
                    .addGroup(pagemenu3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pagemenu3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16))
                        .addGap(36, 36, 36)
                        .addGroup(pagemenu3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19))
                        .addGap(141, 141, 141)
                        .addGroup(pagemenu3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(jLabel34)
                            .addComponent(jLabel35))
                        .addGap(51, 51, 51)
                        .addGroup(pagemenu3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38)
                            .addComponent(jLabel37)
                            .addComponent(jLabel36))))
                .addContainerGap(329, Short.MAX_VALUE))
            .addComponent(jSeparator1)
        );
        pagemenu3Layout.setVerticalGroup(
            pagemenu3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pagemenu3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pagemenu3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel32)
                    .addComponent(jLabel39))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pagemenu3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel17)
                    .addComponent(jLabel33)
                    .addComponent(jLabel36))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pagemenu3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel18)
                    .addComponent(jLabel34)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pagemenu3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel19)
                    .addComponent(jLabel35)
                    .addComponent(jLabel38))
                .addContainerGap(145, Short.MAX_VALUE))
        );

        pagetabmenu.addTab("Kelompok", pagemenu3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pagetabmenu)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pagetabmenu)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        simpan();
    }//GEN-LAST:event_simpanActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        int ok=JOptionPane.showConfirmDialog(null,"Apakah Anda yakin?","Konfirmasi",JOptionPane.YES_NO_OPTION);
        if(ok==0){
            try{
                //panggil method koneksi
                java.sql.Connection koneksi = new koneksi().konek();

                String sql="delete from resiko_bisnis where id = '" 
                        + inputID.getText()+"'";
                java.sql.PreparedStatement stmt=koneksi.prepareStatement(sql);
                stmt.executeUpdate();
                //bersihkan teks
                JOptionPane.showMessageDialog(null,"Data Berhasil dihapus");
                resik();
                tabelawal();
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null,"Data Gagal dihapus!"
                    + "\nDengan pesan error : " + e.getMessage());
            }
        }
    }//GEN-LAST:event_hapusActionPerformed

    private void tblBisnisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBisnisMouseClicked
        simpan.setEnabled(false);
        update.setEnabled(true);
        hapus.setEnabled(true);
        //saat pilih record
        int baris = tblBisnis.getSelectedRow();
        String id = DftTabMode1.getValueAt(baris, 0).toString();
        String gender = DftTabMode1.getValueAt(baris , 1).toString();
        String pic = DftTabMode1.getValueAt(baris , 2).toString();
        String iq = DftTabMode1.getValueAt(baris , 3).toString();
        String pe = DftTabMode1.getValueAt(baris , 4).toString();
        String cp = DftTabMode1.getValueAt(baris, 5).toString();
        
        inputID.setText(id);
        InputPIC.setText(pic);
        InputIQ.setText(iq);
        
        //selected item oleh data gender
        String [] lk = {"male","female"};
        for (int i = 0; i < lk.length; i++) {
            if (gender.equalsIgnoreCase(lk[i])) {
                InputGender.setSelectedIndex(i);
            }
        }
        
        //selected item oleh data paren encouraged
        String [] parentE = {"Not Encouraged","Encouraged"};
        for (int i = 0; i < parentE.length; i++) {
            if (pe.equalsIgnoreCase(parentE[i])) {
                InputPE.setSelectedIndex(i);
            }
        }
        //selected item oleh data collage plan
        String [] colege = {"no","yes"};
        for (int i = 0; i < colege.length; i++) {
            if (cp.equalsIgnoreCase(colege[i])) {
                InputCP.setSelectedIndex(i);
            }
        }
    }//GEN-LAST:event_tblBisnisMouseClicked

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        update();
    }//GEN-LAST:event_updateActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        resik();
    }//GEN-LAST:event_resetActionPerformed

    private void KeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeluarActionPerformed
        System.exit(0);
    }//GEN-LAST:event_KeluarActionPerformed

    private void hitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hitungActionPerformed
        hitung();
        ResetDepan.setEnabled(true);
    }//GEN-LAST:event_hitungActionPerformed

    private void ResetDepanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetDepanActionPerformed
        resikdepan();
        ResetDepan.setEnabled(false);
    }//GEN-LAST:event_ResetDepanActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Utama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel HYes;
    private javax.swing.JLabel Hno;
    private javax.swing.JComboBox<String> InputCP;
    private javax.swing.JComboBox<String> InputGender;
    private javax.swing.JTextField InputIQ;
    private javax.swing.JComboBox<String> InputPE;
    private javax.swing.JTextField InputPIC;
    private javax.swing.JButton Keluar;
    private javax.swing.JButton ResetDepan;
    private javax.swing.JComboBox<String> genderbox;
    private javax.swing.JLabel genderno;
    private javax.swing.JLabel genderyes;
    private javax.swing.JButton hapus;
    private javax.swing.JTextArea hipotesa;
    private javax.swing.JButton hitung;
    private javax.swing.JLabel hkno;
    private javax.swing.JLabel hkyes;
    private javax.swing.JTextField inputID;
    private javax.swing.JComboBox<String> iqbox;
    private javax.swing.JLabel iqno;
    private javax.swing.JLabel iqyes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel pagemenu1;
    private javax.swing.JPanel pagemenu2;
    private javax.swing.JPanel pagemenu3;
    private javax.swing.JTabbedPane pagetabmenu;
    private javax.swing.JComboBox<String> pebox;
    private javax.swing.JLabel peno;
    private javax.swing.JLabel peyes;
    private javax.swing.JComboBox<String> picbox;
    private javax.swing.JLabel picno;
    private javax.swing.JLabel picyes;
    private javax.swing.JButton reset;
    private javax.swing.JButton simpan;
    private javax.swing.JTable tblBisnis;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables

}
