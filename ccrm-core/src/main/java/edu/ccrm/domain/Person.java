package edu.ccrm.domain;
import java.time.LocalDate;
import java.util.Objects;
public abstract class Person {
    protected final int id; protected final String fullName; protected final String email; protected final LocalDate createdAt;
    protected Person(int id,String fullName,String email){ assert id>0; this.id=id; this.fullName=Objects.requireNonNull(fullName); this.email=email; this.createdAt=LocalDate.now(); }
    public abstract String getRole(); public int getId(){return id;} public String getFullName(){return fullName;} public String getEmail(){return email;} @Override public String toString(){ return "["+getRole()+"] "+id+" - "+fullName+" ("+email+")";}
}
