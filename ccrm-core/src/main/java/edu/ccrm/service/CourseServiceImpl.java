package edu.ccrm.service;
import edu.ccrm.datastore.DataStore;
import edu.ccrm.domain.*;
import java.util.List;
import java.util.stream.Collectors;
public class CourseServiceImpl implements CourseService {
    private final DataStore store = DataStore.get();
    @Override public Course createCourse(Course course){ return store.addCourse(course); }
    @Override public List<Course> listCourses(){ return store.listCourses().stream().collect(Collectors.toList()); }
    @Override public List<Course> searchByInstructor(String instructor){ return store.listCourses().stream().filter(c->c.getInstructor().equalsIgnoreCase(instructor)).collect(Collectors.toList()); }
    @Override public List<Course> filterByDept(String dept){ return store.listCourses().stream().filter(c->c.getDepartment().equalsIgnoreCase(dept)).collect(Collectors.toList()); }
    @Override public void deactivateCourse(CourseCode code){ store.getCourse(code).ifPresent(Course::deactivate); }
}
