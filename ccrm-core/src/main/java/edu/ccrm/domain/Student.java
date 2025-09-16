package edu.ccrm.domain;
import java.util.*;
public class Student extends Person {
    private final String regNo; private final Set<String> enrolled = new HashSet<>(); private boolean active=true;
    public Student(int id,String regNo,String fullName,String email){ super(id, fullName, email); this.regNo=regNo; }
    @Override public String getRole(){ return "STUDENT"; } public String getRegNo(){ return regNo; } public Set<String> getEnrolled(){ return Collections.unmodifiableSet(enrolled); } public void enroll(String c){ enrolled.add(c);} public void unenroll(String c){ enrolled.remove(c);} public boolean isActive(){return active;} public void deactivate(){ this.active=false; } @Override public String toString(){ return super.toString()+" regNo="+regNo+" enrolled="+enrolled.size(); }
}
