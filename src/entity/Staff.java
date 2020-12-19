/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

public class Staff {
    private String staff_id;
    private String salary_id;
    private String staff_password;
    private String staff_fullname;
    private byte staff_age;
    private String staff_address;
    private String staff_sex;

    public Staff() {
    }

    public Staff(String staff_id, String salary_id, String staff_password, String staff_fullname, byte staff_age, String staff_address, String staff_sex) {
        this.staff_id = staff_id;
        this.salary_id = salary_id;
        this.staff_password = staff_password;
        this.staff_fullname = staff_fullname;
        this.staff_age = staff_age;
        this.staff_address = staff_address;
        this.staff_sex = staff_sex;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getSalary_id() {
        return salary_id;
    }

    public void setSalary_id(String salary_id) {
        this.salary_id = salary_id;
    }

    public String getStaff_password() {
        return staff_password;
    }

    public void setStaff_password(String staff_password) {
        this.staff_password = staff_password;
    }

    public String getStaff_fullname() {
        return staff_fullname;
    }

    public void setStaff_fullname(String staff_name) {
        this.staff_fullname = staff_name;
    }

    public byte getStaff_age() {
        return staff_age;
    }

    public void setStaff_age(byte staff_age) {
        this.staff_age = staff_age;
    }

    public String getStaff_address() {
        return staff_address;
    }

    public void setStaff_address(String staff_address) {
        this.staff_address = staff_address;
    }

    public String getStaff_sex() {
        return staff_sex;
    }

    public void setStaff_sex(String staff_sex) {
        this.staff_sex = staff_sex;
    }
    
}
