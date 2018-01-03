/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Subayu
 */
public class koneksi {
    private Connection koneksdb;
    public Connection konek(){
        //untuk koneksi ke driver
       try{
           Class.forName("org.sqlite.JDBC");
           //JOptionPane.showMessageDialog(null, "berhasil load driver");
       }catch(ClassNotFoundException ex){
           JOptionPane.showMessageDialog(null, "Tidak ada Driver!\n" + ex);
       }
       
       //untuk koneksi ke database
       try{
           
           String url="jdbc:sqlite:C:\\Users\\Subayu\\Documents\\NetBeansProjects\\TugasAIRabu\\db\\datatrainer.sqlite";
           koneksdb=DriverManager.getConnection(url);
           System.out.println("Berhasil koneksi");
       }catch(SQLException se){
           System.out.println("Gagal koneksi "+se);
           JOptionPane.showMessageDialog(null,"Gagal Koneksi Database","Peringatan",JOptionPane.WARNING_MESSAGE);
       }
       return koneksdb;
    }
}
