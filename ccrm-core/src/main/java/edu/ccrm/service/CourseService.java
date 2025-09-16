package edu.ccrm.service;
import edu.ccrm.domain.*;
import java.util.List;
public interface CourseService {
    Course createCourse(Course course);
    List<Course> listCourses();
    List<Course> searchByInstructor(String instructor);
    List<Course> filterByDept(String dept);
    void deactivateCourse(CourseCode code);
}
