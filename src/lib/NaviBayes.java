/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Subayu
 */
public class NaviBayes {
   String gender;
   String PIC;
   String IQ;
   String PE;
   int total;
   int totalno;
   int totalyes;
   int yxall;
   int nxall;
   int genderNo;
   int genderYes;
   int PICNo;
   int PICYes;
   int IQNo;
   int IQYes;
   int PENo;
   int PENYes;
   public void setGender(String gender){
       this.gender = gender;
   }
   public void setPIC(String PIC){
       this.PIC = PIC;
   }
   public void setIQ(String IQ){
       this.IQ = IQ;
   }
   public void setPE(String PE){
       this.PE = PE;
   }
   public String getGender(){
       return gender;
   }
   public String getPIC(){
       return PIC;
   }
   public String getIQ(){
       return IQ;
   }
   public String getPE(){
       return PE;
   }
   public Integer getGenderNo(){
       return genderNo;
   }
   public  Integer getGenderYes(){
       return genderYes;
   }
   public Integer getPICNo(){
       return PICNo;
   }
   public Integer getPICYes(){
       return PICYes;
   }
   public Integer getIQNo(){
       return IQNo;
   }
   public Integer getIQYes(){
       return IQYes;
   }
   public Integer getPENo(){
       return PENo;
   }
   public Integer getPENYEs(){
       return PENYes;
   }
   public Integer getTotal(){
       return total;
   }
   public Integer getTotalNo(){
       return totalno;
   }
   public Integer getTotalYes(){
       return totalyes;
   }
   public void hipotesa(){
       Connection konek = new koneksi().konek();
       genderNo = 0;
       genderYes = 0;
       PICNo = 0;
       PICYes = 0;
       IQNo = 0;
       IQYes = 0;
       PENo = 0;
       PENYes = 0;
       
       try {
           //Hipotesa Total
           total = 0;
           String querya = "SELECT id FROM resiko_bisnis;";
           Statement stma = konek.createStatement();
           ResultSet hasila = stma.executeQuery(querya);
           while (hasila.next()) {               
               total = total+1;
           }
           //Hipotesa Total No
           totalno = 0;
           String queryn = "SELECT collegplan FROM resiko_bisnis where collegplan = 'No';";
           Statement stmn = konek.createStatement();
           ResultSet hasiln = stmn.executeQuery(queryn);
           while (hasiln.next()) {               
               totalno = totalno+1;
           }
           //Hipotesa Total Yes
           totalyes = 0;
           String queryy = "SELECT collegplan FROM resiko_bisnis where collegplan = 'Yes';";
           Statement stmy = konek.createStatement();
           ResultSet hasily = stmy.executeQuery(queryy);
           while (hasily.next()) {               
               totalyes = totalyes+1;
           }
           //Hipotesa Gender No
           String query = "SELECT gender FROM resiko_bisnis where collegplan = 'No' and gender = '"+ gender +"' ;";
           Statement stm = konek.createStatement();
           ResultSet hasil = stm.executeQuery(query);
           while (hasil.next()) {               
               genderNo = genderNo + 1;
           }
           //Hipotesa Gender yes
           String query2 = "SELECT gender FROM resiko_bisnis where collegplan = 'Yes' and gender = '"+ gender +"' ;";
           Statement stm2 = konek.createStatement();
           ResultSet hasil2 = stm2.executeQuery(query2);
           while (hasil2.next()) {               
               genderYes = genderYes + 1;
           }
           //Hipotesa IQ No
           
           String query3 = "SELECT iq FROM resiko_bisnis where collegplan = 'No' and iq = '"+ IQ +"' ;";
           Statement stm3 = konek.createStatement();
           ResultSet hasil3 = stm3.executeQuery(query3);
           while (hasil3.next()) {               
               IQNo = IQNo + 1;
           }
           //Hipotesa IQ Yes
           String query4 = "SELECT iq FROM resiko_bisnis where collegplan = 'Yes' and iq = '"+ IQ +"' ;";
           Statement stm4 = konek.createStatement();
           ResultSet hasil4 = stm4.executeQuery(query4);
           while (hasil4.next()) {               
               IQYes = IQYes + 1;
           }
           //Hipotesa PIC No
           
           String query5 = "SELECT parenincome FROM resiko_bisnis where collegplan = 'No' and parenincome = '"+ PIC +"' ;";
           Statement stm5 = konek.createStatement();
           ResultSet hasil5 = stm5.executeQuery(query5);
           while (hasil5.next()) {               
               PICNo = PICNo + 1;
           }
           //Hipotesa PIC Yes
           String query6 = "SELECT parenincome FROM resiko_bisnis where collegplan = 'Yes' and parenincome = '"+ PIC +"' ;";
           Statement stm6 = konek.createStatement();
           ResultSet hasil6 = stm6.executeQuery(query6);
           while (hasil6.next()) {               
               PICYes = PICYes + 1;
           }
           //Hipotesa PE No
           String query7 = "SELECT parenincount FROM resiko_bisnis where collegplan = 'No' and parenincount = '"+ PE +"';";
           Statement stm7 = konek.createStatement();
           ResultSet hasil7 = stm7.executeQuery(query7);
           while (hasil7.next()) {               
               PENo = PENo + 1;
           }
           //Hipotesa PE Yes
           String query8 = "SELECT parenincount FROM resiko_bisnis where collegplan = 'Yes' and parenincount = '"+ PE +"';";
           Statement stm8 = konek.createStatement();
           ResultSet hasil8 = stm8.executeQuery(query8);
           while (hasil8.next()) {               
               PENYes = PENYes + 1;
           }
       } catch (SQLException e) {
           
       }
   }
   public void hitungNav(){
       
       
       
       
   }
}
