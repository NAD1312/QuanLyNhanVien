/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author HP
 */
public class DBConnection {
    //Connection: Quan ly ket noi den SQL Server va Database
    private Connection conn=null;
    // para: ServerName, port, DB =URL ; username; password
    public DBConnection(String URL,String userName,String pass){
        try {
            // load driver: multi driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // nạp driver
            // call connect
            conn=DriverManager.getConnection(URL,userName, pass); // khởi tạo biến connect
            System.out.println("connected");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public DBConnection() {
        this("jdbc:sqlserver://localhost:1433;databaseName=Quanlydiemdanhchamcong","phuong1","123456"); // chuỗi kết nối
    }

    public Connection getConn() {
        return conn;
    }
    public ResultSet getData(String sql){
        ResultSet rs=null;
        //ResultSet.TYPE_FORWARD_ONLY: default, con tro khong quay nguoc
        //TYPE_SCROLL_INSENSITIVE : no thread safe
        //TYPE_SCROLL_SENSITIVE: thread safe
        // ResultSet.CONCUR_READ_ONLY: default - no edit
         //ResultSet.CONCUR_UPDATABLE: edited
        try {
            Statement state=conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs=state.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public static void main(String[] args) {
        new DBConnection();
    }
    
    
    
    
}
