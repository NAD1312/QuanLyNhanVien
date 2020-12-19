/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Project;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author phant
 */
public class DAOProject {

    private DBConnection dbConn = new DBConnection();
    private Connection conn = null;

    public DAOProject(DBConnection dbconn) {
        this.dbConn = dbconn;
        this.conn = dbconn.getConn();
    }
    public DAOProject() {

        this.conn = dbConn.getConn();
    }
    public Vector<Project> getAllPro() {
        Vector<Project> vector_pro = new Vector<Project>();
        String sql = "select * from project";
        ResultSet rs = dbConn.getData(sql);
        try {
            while (rs.next()) {
                String project_id = rs.getString("project_id");
                String project_name = rs.getString(2);
                String project_start = rs.getString(3);
                String project_finish = rs.getString(4);
                byte project_status = rs.getByte(5);
                Project pro = new Project(project_id, project_name, project_start, project_finish, project_status);
                vector_pro.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector_pro;
    }

    public int insertProject(Project pro) {
        int n = 1;
        String sql = "Insert into Project(project_id, project_name, project_start, project_finish, project_status) "
                + " values(?,?,?,?,?)";
        try {
            // ? ~ parameter/filedName, index start 1
            PreparedStatement pre = conn.prepareStatement(sql);
            // set parameter
//            pre.setDataType(index,value);
//            DataType : dataType of field
//            index is index of ? 
//            value ~ parameter        
            pre.setString(1, pro.getProject_id());
            pre.setString(2, pro.getProject_name());
            pre.setString(3, pro.getProject_start());
            pre.setString(4, pro.getProject_finish());

            pre.setByte(5, pro.getProject_status());
            // run
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOProject.class.getName()).log(Level.SEVERE, null, ex);
            n = 0;
        }
        return n;
    }

    public void deleteProject(String id) {
        String sql = "exec pro_XoaPro" + "'" + id + "'";
        int n = 0;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            // run
            n = pre.executeUpdate();
            System.out.println("OK");
        }
        catch (SQLException ex) {
            Logger.getLogger(DAOProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    public int ChangeProject(Project pro) {
        int check = 1;
        String sql = "exec pro_SuaDuAn" + "'" + pro.getProject_id() + "'," + "'" + pro.getProject_name() + "'," + "'" + pro.getProject_start()+ "'," + "'" + pro.getProject_finish()+ "'," + pro.getProject_status();
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
