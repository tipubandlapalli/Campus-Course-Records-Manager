package edu.ccrm.io;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
public class BackupService {
    public Path backupDirectory(Path sourceDir) throws IOException {
        Path backupRoot = AppDirs.getDataDir().resolve("backups"); if(!Files.exists(backupRoot)) Files.createDirectories(backupRoot);
        String ts = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
        Path dest = backupRoot.resolve("backup-"+ts);
        Files.walkFileTree(sourceDir, new SimpleFileVisitor<>(){
            @Override public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException { Path target = dest.resolve(sourceDir.relativize(dir)); Files.createDirectories(target); return FileVisitResult.CONTINUE; }
            @Override public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException { Path target = dest.resolve(sourceDir.relativize(file)); Files.copy(file, target, StandardCopyOption.COPY_ATTRIBUTES); return FileVisitResult.CONTINUE; }
        });
        return dest;
    }
}
