package edu.ccrm.domain;
import java.util.Objects;
public class Course {
    private final CourseCode code; private final String title; private final int credits; private final String instructor; private final Semester semester; private final String department; private boolean active;
    private Course(Builder b){ code=b.code; title=b.title; credits=b.credits; instructor=b.instructor; semester=b.semester; department=b.department; active=b.active; }
    public CourseCode getCode(){return code;} public String getTitle(){return title;} public int getCredits(){return credits;} public String getInstructor(){return instructor;} public Semester getSemester(){return semester;} public String getDepartment(){return department;} public boolean isActive(){return active;} public void deactivate(){this.active=false;}
    @Override public String toString(){ return code+" | "+title+" | "+credits+"cr | "+instructor+" | "+semester; }
    public static class Builder{ private CourseCode code; private String title; private int credits; private String instructor; private Semester semester; private String department; private boolean active=true; public Builder code(CourseCode c){this.code=c;return this;} public Builder title(String t){this.title=t;return this;} public Builder credits(int c){this.credits=c;return this;} public Builder instructor(String i){this.instructor=i;return this;} public Builder semester(Semester s){this.semester=s;return this;} public Builder department(String d){this.department=d;return this;} public Builder active(boolean a){this.active=a;return this;} public Course build(){ Objects.requireNonNull(code); Objects.requireNonNull(title); if(credits<=0) throw new IllegalArgumentException("Credits>0"); return new Course(this);} }
}
