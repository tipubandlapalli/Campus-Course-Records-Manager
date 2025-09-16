package edu.ccrm.io;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.ccrm.datastore.DataStore;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
public class ImportExportService {
    private final DataStore store = DataStore.get();
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public void loadOnStartup() {
        try {
            Path base = Paths.get("data");
            Path students = base.resolve("students.json");
            if(Files.exists(students)) try(Reader r=Files.newBufferedReader(students)){
                edu.ccrm.domain.Student[] arr=gson.fromJson(r, edu.ccrm.domain.Student[].class);
                if(arr!=null) for(var s:arr) store.addStudent(s.getRegNo(), s.getFullName(), s.getEmail());
            }
            Path courses = base.resolve("courses.json");
            if(Files.exists(courses)) try(Reader r=Files.newBufferedReader(courses)){
                edu.ccrm.domain.Course[] arr=gson.fromJson(r, edu.ccrm.domain.Course[].class);
                if(arr!=null) for(var c:arr) store.addCourse(c);
            }
        }catch(Exception e){ System.out.println("Startup load error: "+e.getMessage()); }
    }
    public void exportAll(Path outDir) throws IOException {
        if(!Files.exists(outDir)) Files.createDirectories(outDir);
        Path students = outDir.resolve("students.json");
        try(BufferedWriter bw = Files.newBufferedWriter(students, StandardCharsets.UTF_8)){
            gson.toJson(store.listStudents(), bw);
        }
        Path courses = outDir.resolve("courses.json");
        try(BufferedWriter bw = Files.newBufferedWriter(courses, StandardCharsets.UTF_8)){
            gson.toJson(store.listCourses(), bw);
        }
    }
    public void exportOnExit() { try { Path outDir = Paths.get("output"); exportAll(outDir); System.out.println("Data exported to output folder."); } catch(Exception e){ System.out.println("Exit export failed: "+e.getMessage()); } }
    public void importStudentsFromCsv(Path csv) throws IOException { if(!Files.exists(csv)) throw new FileNotFoundException(csv.toString()); try(java.util.stream.Stream<String> lines = Files.lines(csv)){ lines.skip(1).forEach(line->{ String[] cols=line.split(","); if(cols.length>=3) store.addStudent(cols[0].trim(), cols[1].trim(), cols[2].trim()); }); } }
    public Path exportAndBackup() throws IOException { Path base = AppDirs.getDataDir(); Path out = base.resolve("export"); exportAll(out); BackupService b=new BackupService(); return b.backupDirectory(out); }
}
