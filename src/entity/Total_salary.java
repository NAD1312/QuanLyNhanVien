/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

public class Total_salary {
    private String staff_id;
    private int month;
    private int year;
    private int totalaward;
    private int totalbase;
    private byte project_status;
    private int total;

    public Total_salary() {
    }

    public Total_salary(String staff_id, int month, int year, int totalaward, int totalbase, byte project_status, int total) {
        this.staff_id = staff_id;
        this.month = month;
        this.year = year;
        this.totalaward = totalaward;
        this.totalbase = totalbase;
        this.project_status = project_status;
        this.total = total;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTotalaward() {
        return totalaward;
    }

    public void setTotalaward(int totalaward) {
        this.totalaward = totalaward;
    }

    public int getTotalbase() {
        return totalbase;
    }

    public void setTotalbase(int totalbase) {
        this.totalbase = totalbase;
    }

    public byte getProject_status() {
        return project_status;
    }

    public void setProject_status(byte project_status) {
        this.project_status = project_status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
     
}
