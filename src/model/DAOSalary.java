/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Project;
import entity.Salary;
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
public class DAOSalary {
    private DBConnection dbConn = null;
    private Connection conn = null;

    public DAOSalary(DBConnection dbconn) {
        this.dbConn = dbconn;
        this.conn = dbconn.getConn();
    }

    public Vector<Salary> getAllSa() {
        Vector<Salary> vector_sa = new Vector<Salary>();
        String sql = "select * from salary";
        ResultSet rs = dbConn.getData(sql);
        try {
            while (rs.next()) {
                String salary_id = rs.getString("salary_id");
                int salary_base = rs.getInt(2);
               
                Salary sa = new Salary(salary_id, salary_base);
                vector_sa.add(sa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSalary.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector_sa;
    }
    
    public int insertSalary(Salary sa) {
        int n = 1;
        String sql = "Insert into salary(salary_id, salary_base) "
                + " values(?,?)";
        try {
            // ? ~ parameter/filedName, index start 1
            PreparedStatement pre = conn.prepareStatement(sql);
            // set parameter
//            pre.setDataType(index,value);
//            DataType : dataType of field
//            index is index of ? 
//            value ~ parameter        
            pre.setString(1, sa.getSalary_id());
            pre.setInt(2, sa.getSalary_base());
            // run
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOSalary.class.getName()).log(Level.SEVERE, null, ex);
            n = 0;
        }
        return n;
    }
    public int deleteSalary(String id) {
        int check = 1;
        String sql = "exec pro_XoaSa" + "'" + id + "'";
        int n = 0;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            // run
            n = pre.executeUpdate();
            System.out.println("OK");
        }
        catch (SQLException ex) {
            Logger.getLogger(DAOProject.class.getName()).log(Level.SEVERE, null, ex);
            check = 0;
        }
        return check;
    }
     public int ChangeSalary(Salary pro) {
        int check = 1;
        String sql = "exec pro_SuaSalary" + "'" + pro.getSalary_id() + "',"  + pro.getSalary_base();
        int n = 0;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            // run
            n = pre.executeUpdate();
            System.out.println("OK");
        }
        catch (SQLException ex) {
            Logger.getLogger(DAOProject.class.getName()).log(Level.SEVERE, null, ex);
            check = 0;
        }
        return check;
    }   
}
