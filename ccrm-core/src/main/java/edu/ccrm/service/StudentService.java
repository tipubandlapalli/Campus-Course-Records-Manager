package edu.ccrm.service;
import edu.ccrm.domain.Student;
import edu.ccrm.domain.CourseCode;
import edu.ccrm.exception.*;
import java.util.List;
public interface StudentService {
    Student createStudent(String regNo,String name,String email);
    void deactivateStudent(int studentId);
    void enroll(int studentId, CourseCode code) throws DuplicateEnrollmentException, MaxCreditLimitExceededException;
    void unenroll(int studentId, CourseCode code);
    List<Student> listStudents();
}
