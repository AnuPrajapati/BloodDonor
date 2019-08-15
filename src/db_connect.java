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
    String DRIVER ="org.postgresql.Driver";
    String URL="jdbc:postgresql";
    String HOST="127.0.0.1";//ip address or we can use local host
    String PORT="5081";
    String DBNAME="DonorInfo_db";
    String USERNAME="postgres";
    String PASSWORD="anuprazapati";
    Connection c= null;
    Connection conn=null;
    public Connection connectDatabase(){
        try {
            Class.forName(DRIVER);
            String urlInfo=URL+"://"+HOST+":"+PORT+"/"+DBNAME;
            
            c =DriverManager.getConnection(urlInfo, USERNAME, PASSWORD);
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver Error:"+ex); //ex.toString()
        } catch (SQLException ex) {
             System.out.println("Connection Error:"+ex);
        }
        if(c!=null)
        System.out.println("DB connectes Succesffully");
        return c;
    }
    public void closeDBConnection(){
        if(this.c!=null)
        {
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(db_connect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void disconnectDatabase(){
        try {   
            c.close();
        } catch (SQLException ex) {
            System.out.println("Disconnect Error"+ex);
        }
    }
//    public static void main(String [] args){
//        db_connect con=new db_connect();
//        con.connectDatabase();
//    }
}

