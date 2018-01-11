/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.io.File;
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
           JOptionPane.showMessageDialog(null, "Library Driver tidak ditemukan!\n" + ex,"Peringatan",JOptionPane.WARNING_MESSAGE);
       }
       
       //untuk koneksi ke database
       try{
           File temp = new File(".");
           String url="jdbc:sqlite:"+ temp.getAbsolutePath()+"\\db\\datatrainer.sqlite";
           koneksdb=DriverManager.getConnection(url);
           System.out.println("Log : Database Berhasil terkoneksi");
       }catch(SQLException se){
           System.out.println("Log : Gagal koneksi "+se);
           JOptionPane.showMessageDialog(null,"Gagal Teroneksi ke Database","Peringatan",JOptionPane.WARNING_MESSAGE);
       }
       return koneksdb;
    }
}
