/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Award;
import entity.Project;
import entity.Staff;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nam
 */
public class DAOAward {

    DBConnection dbc = new DBConnection();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public DAOAward() {
        conn = dbc.getConn();
    }

    //get list of award 
    public List<Award> getDataofawards() {
        List<Award> li = new ArrayList<>();
        String sql = "SELECT * FROM Award";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Award aw = new Award();
                aw.setProject_id(rs.getString("project_id"));
                aw.setStaff_id(rs.getString("staff_id"));
                aw.setSalary_award(rs.getInt("salary_award"));
                li.add(aw);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return li;
    }

    //insert award
    public boolean insert(Award a) {
        String sql = "INSERT INTO award VALUES(?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, a.getProject_id());
            ps.setString(2, a.getStaff_id());
            ps.setInt(3, a.getSalary_award());
            int r = ps.executeUpdate();
            System.out.println(r);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //delete award by project_id and staff_id
    public boolean delete(String project_id, String staff_id) {
        String sql = "DELETE FROM award where project_id=? and staff_id=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, project_id);
            ps.setString(2, staff_id);
            int r = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(Award aw) {
        String sql = "UPDATE dbo.award SET salary_award = ? WHERE project_id =? and staff_id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, aw.getSalary_award());
            ps.setString(2, aw.getProject_id());
            ps.setString(3, aw.getStaff_id());
            int r = ps.executeUpdate();
            System.out.println(r);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Award> find(Object...objects) {
        List<Award> li = new ArrayList<Award>();

        String sql = "SELECT * FROM award WHERE 1=1 ";
            for (int i=0; i<objects.length; i++) {
                if(i==0){
                    if(!((String) objects[i]).equalsIgnoreCase("")) sql += "AND project_id like '%"+(String) objects[i]+"%' ";
                }else if(i==1){
                    if(!((String) objects[i]).equalsIgnoreCase("")) sql += "AND staff_id like '%"+(String) objects[i]+"%' ";
                }else if(i==2){
                    if(!((String) objects[i]).equalsIgnoreCase("")) sql += "AND salary_award like '%"+(String) objects[i]+"%' ";
                }
            }
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Award aw = new Award();
                aw.setProject_id(rs.getString("project_id"));
                aw.setStaff_id(rs.getString("staff_id"));
                aw.setSalary_award(rs.getInt("salary_award"));
                li.add(aw);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return li;
    }

}
