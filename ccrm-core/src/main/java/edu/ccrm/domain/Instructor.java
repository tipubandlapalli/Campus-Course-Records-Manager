package edu.ccrm.domain;
public class Instructor extends Person {
    private final String department;
    public Instructor(int id,String name,String email,String dept){ super(id,name,email); this.department=dept; }
    @Override public String getRole(){ return "INSTRUCTOR"; } public String getDepartment(){ return department; }
}
