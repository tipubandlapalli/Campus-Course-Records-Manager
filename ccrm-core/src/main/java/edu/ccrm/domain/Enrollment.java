package edu.ccrm.domain;
import java.time.LocalDate;
public class Enrollment {
    private final int id; private final int studentId; private final CourseCode courseCode; private final LocalDate enrolledOn; private Grade grade; private LocalDate gradedOn;
    public Enrollment(int id,int studentId,CourseCode code){ this.id=id; this.studentId=studentId; this.courseCode=code; this.enrolledOn=LocalDate.now(); }
    public int getId(){return id;} public int getStudentId(){return studentId;} public CourseCode getCourseCode(){return courseCode;} public void setGrade(Grade g){ this.grade=g; this.gradedOn=LocalDate.now(); } @Override public String toString(){ return id+" | "+courseCode+" | student="+studentId+" | grade="+(grade==null?"N/A":grade); }
}
