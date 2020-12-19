/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Total_salary;
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
public class DAOTotal_salary {
    DBConnection dbc = new DBConnection();
    Connection c = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public DAOTotal_salary(){
        c = dbc.getConn();
    }
    
    public List<Total_salary> getDataofTotalSA(){
        List<Total_salary> li = new ArrayList<>();
        String sql = "SELECT fn_totalaward.staff_id,month,year,totalaward,totalbase, project_status,\n"
                + "CASE project_status\n" 
                + "WHEN 0 THEN  totalbase + 0\n" 
                + "ELSE totalbase + totalaward\n" 
                + "END\n" 
                + "AS total\n"
                + "FROM dbo.fn_totalaward() INNER JOIN fn_totalbase() ON fn_totalbase.staff_id = fn_totalaward.staff_id";
        try {
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Total_salary t = new Total_salary();
                t.setStaff_id(rs.getString("staff_id"));
                t.setMonth(rs.getInt("month"));
                t.setYear(rs.getInt("year"));
                t.setTotalaward(rs.getInt("totalaward"));
                t.setTotalbase(rs.getInt("totalbase"));
                t.setProject_status(rs.getByte("project_status"));
                t.setTotal(rs.getInt("total"));
                li.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return li;
    }
    
    public List<Total_salary> findTotalSa(Object... objects){
        List<Total_salary> li = new ArrayList<>();
        String sql = "SELECT fn_totalaward.staff_id,month,year,totalaward,totalbase, project_status,\n"
                + "CASE project_status\n" 
                + "WHEN 0 THEN  totalbase + 0\n" 
                + "ELSE totalbase + totalaward\n" 
                + "END\n" 
                + "AS total\n"
                + "FROM dbo.fn_totalaward() INNER JOIN fn_totalbase() ON fn_totalbase.staff_id = fn_totalaward.staff_id "
                + "WHERE 1=1";
        for(int i=0; i<objects.length; i++){
            if(i==0){
                if(!((String) objects[i]).equalsIgnoreCase("")) sql += "AND fn_totalaward.staff_id like '%"+ (String) objects[i] +"%' ";
            }
            if(i==1){
                if(!((String) objects[i]).equalsIgnoreCase("")) sql += "AND month like '%"+ (String) objects[i] +"%' ";
            }
            if(i==2){
                if(!((String) objects[i]).equalsIgnoreCase("")) sql += "AND year like '%"+ (String) objects[i] +"%' ";
            }
            if(i==3){
                if(!((String) objects[i]).equalsIgnoreCase("")) sql += "AND totalaward like '%"+ (String) objects[i] +"%'";
            }
            if(i==4){
                if(!((String) objects[i]).equalsIgnoreCase("")) sql += "AND totalbase like '%"+ (String) objects[i] +"%'";
            }
            if(i==5){
                if(!((String) objects[i]).equalsIgnoreCase("")) sql += "AND project_status like '%"+ (String) objects[i] +"%'";
            }
        }
        try {
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Total_salary t = new Total_salary();
                t.setStaff_id(rs.getString("staff_id"));
                t.setMonth(rs.getInt("month"));
                t.setYear(rs.getInt("year"));
                t.setTotalaward(rs.getInt("totalaward"));
                t.setTotalbase(rs.getInt("totalbase"));
                t.setProject_status(rs.getByte("project_status"));
                t.setTotal(rs.getInt("total"));
                li.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail");
        }
        return li;
    }
}
