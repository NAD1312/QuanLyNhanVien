/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Staff;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nam
 */
public class DAOStaff {

    DBConnection dbcon = new DBConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection conn = null;

    //contructor
    public DAOStaff() {
        conn = dbcon.getConn();
    }

    //get list of staff 
    public List<Staff> getDataofstaff() {
        List<Staff> li = new ArrayList<>();
        String sql = "SELECT * FROM staff";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Staff st = new Staff();
                st.setStaff_id(rs.getString("staff_id"));
                st.setSalary_id(rs.getString("salary_id"));
                st.setStaff_password(rs.getString("staff_password"));
                st.setStaff_fullname(rs.getString("staff_fullname"));
                st.setStaff_age(rs.getByte("staff_age"));
                st.setStaff_address(rs.getString("staff_address"));
                st.setStaff_sex(rs.getString("staff_sex"));
                li.add(st);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return li;
    }

    //insert staff
    public boolean insert(Staff st) {
        String sql = "INSERT INTO staff VALUES(?,?,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, st.getStaff_id());
            ps.setString(2, st.getSalary_id());
            ps.setString(3, st.getStaff_password());
            ps.setString(4, st.getStaff_fullname());
            ps.setInt(5, st.getStaff_age());
            ps.setString(6, st.getStaff_address());
            ps.setString(7, st.getStaff_sex());
            int r = ps.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    //delete staff
    public boolean delete(String id) {
        String sql = "execute delstaff @staff_id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            int r = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(Staff st) {
//        String sql = "UPDATE staff SET salary_id=?, staff_password=?, ";
//        sql += "staff_fullname=?, staff_age=?, staff_address=?, staff_sex=? ";
//        sql += "WHERE staff_id=?";
        String sql = "UPDATE dbo.staff SET staff_id = ?, salary_id = ?, staff_password = ?, staff_fullname=?, staff_age = ?, staff_address=?, staff_sex = ? WHERE staff_id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, st.getStaff_id());
            ps.setString(2, st.getSalary_id());
            ps.setString(3, st.getStaff_password());
            ps.setString(4, st.getStaff_fullname());
            ps.setByte(5, st.getStaff_age());
            ps.setString(6, st.getStaff_address());
            ps.setString(7, st.getStaff_sex());
            ps.setString(8, st.getStaff_id());
            int r = ps.executeUpdate();
            System.out.println(r);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Staff> find(Object... objects) {
        List<Staff> li = new ArrayList<>();
        String sql = "SELECT * FROM staff WHERE 1=1 ";
        for(int i=0; i<objects.length; i++){
            if(i==0){
                if(!((String) objects[i]).equalsIgnoreCase("")) sql += "AND staff_id like '%"+ (String) objects[i] +"%' ";
            }
            if(i==1){
                if(!((String) objects[i]).equalsIgnoreCase("")) sql += "AND salary_id like '%"+ (String) objects[i] +"%' ";
            }
            if(i==2){
                if(!((String) objects[i]).equalsIgnoreCase("")) sql += "AND staff_password like '%"+ (String) objects[i] +"%' ";
            }
            if(i==3){
                if(!((String) objects[i]).equalsIgnoreCase("")) sql += "AND staff_fullname like '%"+ (String) objects[i] +"%' ";
            }
            if(i==4){
                if(!((String) objects[i]).equalsIgnoreCase("")) sql += "AND staff_age like '%"+ (String) objects[i] +"%' ";
            }
            if(i==5){
                if(!((String) objects[i]).equalsIgnoreCase("")) sql += "AND staff_address like '%"+ (String) objects[i] +"%' ";
            }
            if(i==6){
                if(!((String) objects[i]).equalsIgnoreCase("")) sql += "AND staff_sex like '%"+ (String) objects[i] +"%' ";
            }
        }
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Staff st = new Staff();
                st.setStaff_id(rs.getString("staff_id"));
                st.setSalary_id(rs.getString("salary_id"));
                st.setStaff_password(rs.getString("staff_password"));
                st.setStaff_fullname(rs.getString("staff_fullname"));
                st.setStaff_age(rs.getByte("staff_age"));
                st.setStaff_address(rs.getString("staff_address"));
                st.setStaff_sex(rs.getString("staff_sex"));
                li.add(st);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return li;
    }
}
