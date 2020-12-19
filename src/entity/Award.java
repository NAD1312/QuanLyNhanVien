/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

public class Award extends Project {
    private String project_id;
    private String staff_id;
    private int salary_award;

    public Award() {
    }

    public Award(String project_id, String staff_id, int salary_award) {
        this.project_id = project_id;
        this.staff_id = staff_id;
        this.salary_award = salary_award;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public int getSalary_award() {
        return salary_award;
    }

    public void setSalary_award(int salary_award) {
        this.salary_award = salary_award;
    }

    
}
