package edu.ccrm.cli;
import edu.ccrm.service.*;
import edu.ccrm.service.StudentServiceImpl;
import edu.ccrm.domain.*;
import edu.ccrm.io.ImportExportService;
import edu.ccrm.exception.*;
import java.nio.file.Paths;
import java.util.Scanner;
public class CliMenu {
    private final StudentService studentService = new StudentServiceImpl();
    private final CourseService courseService = new CourseServiceImpl();
    private final ImportExportService ioService = new ImportExportService();
    public void start(){ ioService.loadOnStartup(); start(new Scanner(System.in), false); }
    public void start(Scanner sc, boolean demoMode){
        boolean running=true;
        while(running){
            System.out.println("\nMenu: 1-Students 2-Courses 3-Enrollment 4-IO 9-Demo 0-Exit");
            System.out.print("Choice: ");
            if(!sc.hasNextLine()) break;
            String line = sc.nextLine().trim();
            if(demoMode) System.out.println(">> " + line);
            int ch = line.isEmpty()? -1 : Integer.parseInt(line);
            switch(ch){
                case 1 -> manageStudents(sc,demoMode);
                case 2 -> manageCourses(sc,demoMode);
                case 3 -> manageEnrollment(sc,demoMode);
                case 4 -> ioMenu(sc,demoMode);
                case 9 -> new DemoRunner().runDemo();
                case 0 -> { ioService.exportOnExit(); running=false; }
                default -> System.out.println("Invalid choice.");
            }
        }
        System.out.println("Bye!");
    }
    private void manageStudents(Scanner sc, boolean demoMode){
        System.out.println("1-Add 2-List");
        String inp=sc.nextLine();
        if(demoMode) System.out.println(">> " + inp);
        int c=Integer.parseInt(inp);
        if(c==1){
            System.out.print("RegNo: "); String reg=sc.nextLine(); if(demoMode) System.out.println(">> "+reg);
            System.out.print("Name: "); String name=sc.nextLine(); if(demoMode) System.out.println(">> "+name);
            System.out.print("Email: "); String email=sc.nextLine(); if(demoMode) System.out.println(">> "+email);
            Student s=studentService.createStudent(reg,name,email); System.out.println("Created: "+s);
        } else { studentService.listStudents().forEach(System.out::println); }
    }
    private void manageCourses(Scanner sc, boolean demoMode){
        System.out.println("Courses: Add/List/Search by instructor");
        System.out.print("Action (add/list/search): "); String action=sc.nextLine(); if(demoMode) System.out.println(">> "+action);
        switch(action){
            case "add" -> {
                System.out.print("Course code (e.g., CS-101): "); CourseCode code=CourseCode.parse(sc.nextLine()); if(demoMode) System.out.println(">> "+code);
                System.out.print("Title: "); String title=sc.nextLine(); if(demoMode) System.out.println(">> "+title);
                System.out.print("Credits: "); int credits=Integer.parseInt(sc.nextLine()); if(demoMode) System.out.println(">> "+credits);
                System.out.print("Instructor: "); String instr=sc.nextLine(); if(demoMode) System.out.println(">> "+instr);
                System.out.print("Dept: "); String dept=sc.nextLine(); if(demoMode) System.out.println(">> "+dept);
                Course c=new Course.Builder().code(code).title(title).credits(credits).instructor(instr).department(dept).semester(Semester.FALL).build();
                courseService.createCourse(c); System.out.println("Course added: "+c);
            }
            case "list" -> courseService.listCourses().forEach(System.out::println);
            case "search" -> { System.out.print("Instructor name: "); String instr=sc.nextLine(); if(demoMode) System.out.println(">> "+instr); courseService.searchByInstructor(instr).forEach(System.out::println); }
            default -> System.out.println("Unknown action.");
        }
    }
    private void manageEnrollment(Scanner sc, boolean demoMode){
        System.out.print("StudentId: "); int sid=Integer.parseInt(sc.nextLine()); if(demoMode) System.out.println(">> "+sid);
        System.out.print("Course code (ex: CS-101): "); CourseCode cc=CourseCode.parse(sc.nextLine()); if(demoMode) System.out.println(">> "+cc);
        try{ studentService.enroll(sid,cc); System.out.println("Enrolled successfully."); }
        catch(Exception ex){ System.out.println("Enrollment failed: "+ex.getMessage()); }
    }
    private void ioMenu(Scanner sc, boolean demoMode){
        System.out.println("IO: 1-import CSV 2-export all 3-backup");
        String op=sc.nextLine(); if(demoMode) System.out.println(">> "+op);
        try{
            switch(Integer.parseInt(op)){
                case 1 -> ioService.importStudentsFromCsv(Paths.get("data","students.csv"));
                case 2 -> { ioService.exportAll(Paths.get("data","export")); System.out.println("Export completed."); }
                case 3 -> { var backup = ioService.exportAndBackup(); System.out.println("Backed up to: "+backup); }
            }
        }catch(Exception e){ System.out.println("IO error: "+e.getMessage()); }
    }
}
