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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
//import NewView;

/**
 *
 * @author hasil
 */
public class db_Operation1 {

    public ResultSet viewAllAcceptorInformation() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        db_connect dbc = new db_connect();
        conn = dbc.connectDatabase();
        String SQL = "select * from acceptor";

        try {
            stmt = conn.createStatement();

            rs = stmt.executeQuery(SQL);
        } catch (SQLException ex) {
            System.out.println("Select error:" + ex);
        }
        return rs;
    }

    public void addAcceptorInformtaion(ArrayList data) throws ParseException {
        System.out.println("gfhkgk");
        Connection conn = null;
        db_connect dbc = new db_connect();
        PreparedStatement ps = null;

        conn = dbc.connectDatabase();
        String SQL = "Insert Into acceptor(first_name,last_name,phone,address,"
                + "bloodgroup,description)"
                + "values(?,?,?,?,?,?)";

        try {
            ps = conn.prepareStatement(SQL); // no preparedStatement...
            ps.setString(1, data.get(0).toString()); //ps.setInteger();
            ps.setString(2, data.get(1).toString());
            ps.setLong(3, Long.parseLong(data.get(2).toString())); //ps.setInteger();
            ps.setString(4, data.get(3).toString());
            ps.setString(5, data.get(4).toString());
            ps.setString(6, data.get(5).toString());

            ps.executeUpdate();
            System.out.println("Inserted Successfully");
        } catch (SQLException ex) {
            System.out.println("Add Acceptor SQL Error" + ex);
        }
    }

    public void removeDonor(String id) {
        System.out.println("a" + id + "a");
        String SQL = "DELETE FROM \"donor\" WHERE id=" + id + ";";
        db_connect dc = new db_connect();
        Connection conn = dc.connectDatabase();
        try {
            Statement s = conn.createStatement();
            System.out.println("hello");
            s.executeUpdate(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(db_Operation1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
