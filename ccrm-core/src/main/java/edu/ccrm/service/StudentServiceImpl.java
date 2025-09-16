package edu.ccrm.service;
import edu.ccrm.datastore.DataStore;
import edu.ccrm.domain.*;
import edu.ccrm.exception.*;
import edu.ccrm.config.AppConfig;
import java.util.*;
public class StudentServiceImpl implements StudentService {
    private final DataStore store = DataStore.get();
    @Override public Student createStudent(String regNo,String name,String email){ return store.addStudent(regNo,name,email); }
    @Override public void deactivateStudent(int studentId){ store.getStudent(studentId).ifPresent(Student::deactivate); }
    @Override public void enroll(int studentId, CourseCode code) throws DuplicateEnrollmentException, MaxCreditLimitExceededException {
        Student s = store.getStudent(studentId).orElseThrow(()->new IllegalArgumentException("Student not found"));
        if(s.getEnrolled().contains(code.asCode())) throw new DuplicateEnrollmentException("Already enrolled");
        Course c = store.getCourse(code).orElseThrow(()->new IllegalArgumentException("Course not found"));
        int currentCredits = s.getEnrolled().stream().map(CourseCode::parse).map(cc->store.getCourse(cc).orElse(null)).filter(Objects::nonNull).mapToInt(Course::getCredits).sum();
        int allowed = AppConfig.get().getMaxCreditsPerSemester();
        if(currentCredits + c.getCredits() > allowed) throw new MaxCreditLimitExceededException("Exceeds max credits");
        store.addEnrollment(studentId, code);
        s.enroll(code.asCode());
    }
    @Override public void unenroll(int studentId, CourseCode code){ Student s=store.getStudent(studentId).orElseThrow(); s.unenroll(code.asCode()); }
    @Override public List<Student> listStudents(){ return new ArrayList<>(store.listStudents()); }
}
