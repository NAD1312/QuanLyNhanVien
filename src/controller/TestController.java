/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Project;
import java.util.List;
import java.util.Vector;
import model.DAOProject;
import model.DBConnection;


public class TestController {
    public static void main(String[] args) {
        DBConnection dbconn=new DBConnection();
        DAOProject daoPro = new DAOProject(dbconn); 
//        daoPro.getAllPro();
        Vector<Project> pro = new Vector<Project>();
        pro = daoPro.getAllPro();
        printData(pro);
    }
     public static void printData(Vector<Project> list) {
        // Show list through for-each
        for (Project item : list) {
            System.out.println(item.getProject_name());
        }
        System.out.println();
    }
}
