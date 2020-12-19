/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Admin;
import entity.Attendance;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phant
 */
public class DAOAttendance {

    private DBConnection dbConn = null;
    private Connection conn = null;

    public DAOAttendance(DBConnection dbconn) {
        this.dbConn = dbconn;
        this.conn = dbconn.getConn();
    }
    public Vector<Attendance> getAllAd() {
        Vector<Attendance> vector_at = new Vector<Attendance>();
        String sql = "select * from attendance";
        ResultSet rs = dbConn.getData(sql);
        try {
            while (rs.next()) {
                String staff_id = rs.getString("staff_id");
                String date = rs.getString(2);
                String time_in = rs.getString(3);

                Attendance ad = new Attendance(staff_id, date, time_in);
                vector_at.add(ad);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAttendance.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector_at;
    }
    public Vector<Attendance> getAttendanceOfOneDate(String year, String month, String day, int option) {
        Vector<Attendance> vector_at = new Vector<Attendance>();
        String sql = new String();
        if(option == 1){
            sql = "select * from attendance where date = " + "'" + year + "-" + month + "-" + day + "'";
        } else if(option == 2){
            sql = "select * from attendance where year(date) = " + "'" + year + "'" + " and " + "month(date) = " + "'" + month + "'";
        } else if (option == 3){
            sql = "select * from attendance where year(date) = " + "'" + year + "'";
        }
        
        ResultSet rs = dbConn.getData(sql);
        try {
            while (rs.next()) {
                String staff_id = rs.getString("staff_id");
                String date = rs.getString(2);
                String time_in = rs.getString(3);

                Attendance ad = new Attendance(staff_id, date, time_in);
                vector_at.add(ad);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAttendance.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector_at;
    }
    public Vector<Attendance> getAttendanceOfoneDateLate(String year, String month, String day, int option) {
        Vector<Attendance> vector_at = new Vector<Attendance>();
        String sql = new String();
        if(option == 1){
            sql = "select * from attendance where date = " + "'" + year + "-" + month + "-" + day + "'" + " and time_in > '08:00:00'";
        } else if(option == 2){
            sql = "select * from attendance where year(date) = " + "'" + year + "'" + " and " + "month(date) = " + "'" + month + "'" + " and time_in > '08:00:00'";
        } else if (option == 3){
            sql = "select * from attendance where year(date) = " + "'" + year + "'" +" and time_in > '08:00:00'";
        }
        ResultSet rs = dbConn.getData(sql);
        try {
            while (rs.next()) {
                String staff_id = rs.getString("staff_id");
                String date = rs.getString(2);
                String time_in = rs.getString(3);

                Attendance ad = new Attendance(staff_id, date, time_in);
                vector_at.add(ad);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAttendance.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector_at;
    }
    public int attendance(Attendance at){
        int n = 1;
        String sql = "Insert into attendance(staff_id, date, time_in) "
                + " values(?,?,?)";
        try {
            // ? ~ parameter/filedName, index start 1
            PreparedStatement pre = conn.prepareStatement(sql);
            // set parameter
//            pre.setDataType(index,value);
//            DataType : dataType of field
//            index is index of ? 
//            value ~ parameter        
            pre.setString(1, at.getStaff_id());
            pre.setString(2, at.getDate());
            pre.setString(3, at.getTime_in());
            // run
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOAttendance.class.getName()).log(Level.SEVERE, null, ex);
            n = 0;
        }
        return n ;
    }
}
