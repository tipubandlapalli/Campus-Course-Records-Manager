package edu.ccrm.util;
import java.util.concurrent.atomic.AtomicInteger;
public final class IdGenerator {
    private static final AtomicInteger STUDENT = new AtomicInteger(1000);
    private static final AtomicInteger COURSE = new AtomicInteger(200);
    private static final AtomicInteger ENROLL = new AtomicInteger(10000);
    public static int nextStudentId(){ return STUDENT.getAndIncrement(); }
    public static int nextCourseId(){ return COURSE.getAndIncrement(); }
    public static int nextEnrollmentId(){ return ENROLL.getAndIncrement(); }
}
