/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
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
        setLocationRelativeTo(null);
        setTitle("Tugas Bu Nafi - Kecerdasan Buatan - Hari Rabu");
        update.setEnabled(false);
        hapus.setEnabled(false);
        simpan.setEnabled(true);
        
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        hitung = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
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
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("menuutama"); // NOI18N
        setResizable(false);

        jLabel8.setText("Hitung Resiko Bisnis Dengan Metode Navy Bayes - Java");

        jLabel9.setText("Gender ");

        jLabel10.setText("Parent In Come");

        jLabel11.setText("IQ");

        jLabel12.setText("Paren Encouraged");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        hitung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/hitung.png"))); // NOI18N
        hitung.setText("Hitung");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(hitung)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel8)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9)
                                .addComponent(jLabel10)
                                .addComponent(jLabel11)
                                .addComponent(jLabel12))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jComboBox4, 0, 187, Short.MAX_VALUE)
                                .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(472, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hitung)
                .addContainerGap(138, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Menu Utama", jPanel1);

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(InputCP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(InputPE, 0, 156, Short.MAX_VALUE)
                            .addComponent(InputGender, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inputID)
                            .addComponent(InputIQ)
                            .addComponent(InputPIC)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(hapus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(update))
                            .addComponent(Keluar, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(reset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(simpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(inputID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(InputGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(InputPIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(InputIQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(InputPE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(InputCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(simpan)
                            .addComponent(update)
                            .addComponent(hapus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(reset)
                            .addComponent(Keluar))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Data Trainer", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 790, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 326, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Kelompok", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
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

                String sql="delete from mahasiswa where id = '" 
                        + inputID.getText()+"'";
                java.sql.PreparedStatement stmt=koneksi.prepareStatement(sql);
                stmt.executeUpdate();
                //bersihkan teks
                JOptionPane.showMessageDialog(null,"Data Berhasil dihapus");
                resik();

            }catch(Exception e){
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
    private javax.swing.JComboBox<String> InputCP;
    private javax.swing.JComboBox<String> InputGender;
    private javax.swing.JTextField InputIQ;
    private javax.swing.JComboBox<String> InputPE;
    private javax.swing.JTextField InputPIC;
    private javax.swing.JButton Keluar;
    private javax.swing.JButton hapus;
    private javax.swing.JButton hitung;
    private javax.swing.JTextField inputID;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton reset;
    private javax.swing.JButton simpan;
    private javax.swing.JTable tblBisnis;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
