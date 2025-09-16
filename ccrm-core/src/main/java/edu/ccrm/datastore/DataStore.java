package edu.ccrm.datastore;
import edu.ccrm.domain.*;
import edu.ccrm.util.IdGenerator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.*;
import java.util.stream.Collectors;
public final class DataStore {
    private static final DataStore INSTANCE = new DataStore();
    private final ConcurrentHashMap<Integer, Student> students = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<CourseCode, Course> courses = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Integer, Enrollment> enrollments = new ConcurrentHashMap<>();
    private DataStore(){}
    public static DataStore get(){ return INSTANCE; }
    public Student addStudent(String regNo,String name,String email){ int id=IdGenerator.nextStudentId(); Student s=new Student(id,regNo,name,email); students.put(id,s); return s; }
    public Optional<Student> getStudent(int id){ return Optional.ofNullable(students.get(id)); }
    public Collection<Student> listStudents(){ return students.values(); }
    public Course addCourse(Course c){ courses.put(c.getCode(), c); return c; }
    public Optional<Course> getCourse(CourseCode code){ return Optional.ofNullable(courses.get(code)); }
    public Collection<Course> listCourses(){ return courses.values(); }
    public Enrollment addEnrollment(int studentId, CourseCode code){ int id=IdGenerator.nextEnrollmentId(); Enrollment e=new Enrollment(id, studentId, code); enrollments.put(id,e); return e; }
    public List<Enrollment> findEnrollmentsByStudent(int studentId){ return enrollments.values().stream().filter(e->e.getStudentId()==studentId).collect(Collectors.toList()); }
}
