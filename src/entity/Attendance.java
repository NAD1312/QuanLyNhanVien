/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

public class Attendance {
    public String staff_id; 
    public String date; 
    public String time_in; 

    public Attendance(String staff_id, String date, String time_in) {
        this.staff_id = staff_id;
        this.date = date;
        this.time_in = time_in;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public String getDate() {
        return date;
    }

    public String getTime_in() {
        return time_in;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime_in(String time_in) {
        this.time_in = time_in;
    }
    
    
}
