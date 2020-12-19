/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

public class Salary {
    private String salary_id;
    private int salary_base;

    public String getSalary_id() {
        return salary_id;
    }

    public Salary(String salary_id, int salary_base) {
        this.salary_id = salary_id;
        this.salary_base = salary_base;
    }

    public void setSalary_id(String salary_id) {
        this.salary_id = salary_id;
    }

    public int getSalary_base() {
        return salary_base;
    }

    public void setSalary_base(int salary_base) {
        this.salary_base = salary_base;
    }
    
}
