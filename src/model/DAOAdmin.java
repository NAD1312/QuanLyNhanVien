/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Admin;
import entity.Salary;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phant
 */
public class DAOAdmin {

    private DBConnection dbConn = null;
    private Connection conn = null;

    public DAOAdmin(DBConnection dbconn) {
        this.dbConn = dbconn;
        this.conn = dbconn.getConn();
    }

    public Vector<Admin> getAllAd() {
        Vector<Admin> vector_ad = new Vector<Admin>();
        String sql = "select * from admin";
        ResultSet rs = dbConn.getData(sql);
        try {
            while (rs.next()) {
                String admin_id = rs.getString("admin_id");
                String user_name = rs.getString(2);
                String user_pass = rs.getString(3);

                Admin ad = new Admin(admin_id, user_name, user_pass);
                vector_ad.add(ad);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector_ad;
    }
}
