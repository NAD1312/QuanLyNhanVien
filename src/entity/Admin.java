/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


public class Admin {
    public String admin_id; 
    public String user_name; 
    public String user_pass;

    public Admin(String admin_id, String user_name, String user_pass) {
        this.admin_id = admin_id;
        this.user_name = user_name;
        this.user_pass = user_pass;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }
    
}
