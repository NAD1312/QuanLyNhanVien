/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

public class Project {
    private String project_id;
    private String project_name;

    public Project() {
    }
    private String project_start;
    private String project_finish;
    private byte project_status;

    public Project(String project_id, String project_name, String project_start, String project_finish, byte project_status) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.project_start = project_start;
        this.project_finish = project_finish;
        this.project_status = project_status;
    }

    
    
    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_start() {
        return project_start;
    }

    public void setProject_start(String project_start) {
        this.project_start = project_start;
    }

    public String getProject_finish() {
        return project_finish;
    }

    public void setProject_finish(String project_finish) {
        this.project_finish = project_finish;
    }

    public byte getProject_status() {
        return project_status;
    }

    public void setProject_status(byte project_status) {
        this.project_status = project_status;
    }
    
}
