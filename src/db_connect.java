/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hasil
 */
public class db_connect {

    String url = "jdbc:mysql://localhost:3306/donorinfo_db"; //MySQL URL followed by the database name
    String username = "root"; //MySQL username
    String password = "";

    Connection conn = null;

    public Connection connectDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver Error:" + ex); //ex.toString()
        } catch (SQLException ex) {
            System.out.println("Connection Error:" + ex);
        }
        if (conn != null) {
            System.out.println("DB connectes Succesffully");
        }
        return conn;
    }

    public void closeDBConnection() {
        if (this.conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(db_connect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void disconnectDatabase() {
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Disconnect Error" + ex);
        }
    }
//    public static void main(String [] args){
//        db_connect con=new db_connect();
//        con.connectDatabase();
//    }
}
