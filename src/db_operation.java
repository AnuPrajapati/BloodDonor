/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
//import NewView;

/**
 *
 * @author hasil
 */
public class db_operation {

    public ResultSet viewAllDonorInformation() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        db_connect dbc = new db_connect();
        conn = dbc.connectDatabase();
        String SQL = "select * from donor order by id asc";

        try {

            stmt = conn.createStatement();

            rs = stmt.executeQuery(SQL);
        } catch (SQLException ex) {
            System.out.println("Select error:" + ex);
        }
        return rs;
    }
  public ResultSet viewSingleDonorInformation(String id){
        db_connect dbcon = new db_connect();
        PreparedStatement ps = null;  //to execute the query
        ResultSet rs = null;
        Connection conn = dbcon.connectDatabase();
        String SQL = "select * from donor where id = ?";
        try {
            ps = conn.prepareStatement(SQL);
            ps.setInt(1, Integer.parseInt(id)); //start from ?(1) so 1
            rs = ps.executeQuery(); 
            
        } catch (SQLException ex) {
            Logger.getLogger(db_operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
  }
    public void addDonorInformtaion(ArrayList dataToSave) throws ParseException {
        Connection conn = null;
        db_connect dbc = new db_connect();

        PreparedStatement ps = null;
        conn = dbc.connectDatabase();
        String SQL = "insert into \"donor\"(first_name,last_name,address,phone_no,age,gender,blood_group,weight) values (?,?,?,?,?,?,?,?);";

        try {
            ps = conn.prepareStatement(SQL);
            ps.setString(1, dataToSave.get(0).toString());
            ps.setString(2, dataToSave.get(1).toString());
            ps.setString(3, dataToSave.get(2).toString());
            ps.setLong(4, Long.parseLong(dataToSave.get(3).toString()));
            ps.setInt(5, Integer.parseInt(dataToSave.get(4).toString()));
            ps.setString(6, dataToSave.get(5).toString());
            ps.setString(7, dataToSave.get(6).toString());
             ps.setInt(8, Integer.parseInt(dataToSave.get(7).toString()));
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Add Student SQL Error" + ex);
        }
    }

    public ResultSet searchDonorInformation(String bg) {
        db_connect dbcon = new db_connect();
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        conn = dbcon.connectDatabase();
        String SQL = "select * from donor where blood_group=?";
        try {
            ps = conn.prepareStatement(SQL);
            ps.setString(1, bg);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            //Logger.getLogger(NewView.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rs;

    }
  
   public void UpdateDatatoStudentTable(ArrayList data,int id) {
        db_connect dc = new db_connect();
        Connection conn = dc.connectDatabase();
         String SQL = "update donor set first_name=?,last_name=?,address=?,phone_no=?,"
                +"age=?,gender=?,blood_group=?,weight=? where id=?";
        try {
            
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, data.get(0).toString());
            
            ps.setString(2, data.get(1).toString());
            ps.setString(3, data.get(2).toString());
           ps.setLong(4, Long.parseLong(data.get(3).toString()));
            ps.setInt(5, Integer.parseInt(data.get(4).toString()));
            ps.setString(6, data.get(5).toString());
                      

               ps.setString(7, data.get(6).toString());
                            System.out.println("anudj");

            ps.setInt(8, Integer.parseInt(data.get(7).toString()));
            
            
           System.out.println("anuj");
            ps.setInt(9, id);
            System.out.println("anu");
            ps.executeUpdate();
            JOptionPane.showMessageDialog(new javax.swing.JDialog(), "Record Updated");
        } catch (SQLException ex) {
            System.out.println("Prepare Error::" + ex.toString());
        } 
        dc.closeDBConnection();
        }
        
     
    public ResultSet deleteAllDonorInformation(String k) {
        db_connect dbcon = new db_connect();
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        conn = dbcon.connectDatabase();
        String SQL = "delete * from donor where id=?";
        try {
            ps = conn.prepareStatement(SQL);
            ps.setString(1, k);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            //Logger.getLogger(NewView.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("adsa" + ex);
        }
        return null;
    }

    public void deleteCustomer(String id) {
        String SQL = "DELETE FROM \"donor\" WHERE id=?;";
        db_connect dc = new db_connect();
        dc.connectDatabase();
        PreparedStatement ps = null;
        try {
            ps = dc.conn.prepareStatement(SQL);
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(db_operation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
