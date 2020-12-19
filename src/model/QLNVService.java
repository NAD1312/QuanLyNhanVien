/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Award;
import entity.Project;
import entity.Staff;
import entity.Total_salary;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Nam
 */
public class QLNVService {

    DAOStaff daos;
    DAOAward daoa;
    DAOProject daop;
    DAOTotal_salary daot;

    public QLNVService() {
        daos = new DAOStaff();
        daoa = new DAOAward();
        daop = new DAOProject();
        daot = new DAOTotal_salary();
    }

    public List<Staff> getDataofstaff() {
        return daos.getDataofstaff();
    }

    public boolean insert(Staff st) {
        return daos.insert(st);
    }

    public boolean delete(String id) {
        return daos.delete(id);
    }

    public boolean update(Staff st) {
        return daos.update(st);
    }

    public List<Staff> findStaff(Object... objects) {
        return daos.find(objects);
    }

    public List<Award> getDataofawards() {
        return daoa.getDataofawards();
    }

    public boolean insert(Award aw) {
        return daoa.insert(aw);
    }

    public boolean update(Award aw) {
        return daoa.update(aw);
    }

    public boolean delete(String project_id, String staff_id) {
        return daoa.delete(project_id, staff_id);
    }

    public List<Award> findAward(Object... objects) {
        return daoa.find(objects);
    }

    public Vector<Project> getAllPro() {
        return daop.getAllPro();
    }

    public List<Total_salary> getDataofTotalSA() {
        return daot.getDataofTotalSA();
    }

    public List<Total_salary> findTotalSa(Object... objects) {
        return daot.findTotalSa(objects);
    }
    
    
}
