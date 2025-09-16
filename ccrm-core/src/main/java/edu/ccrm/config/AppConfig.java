package edu.ccrm.config;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
public final class AppConfig {
    private static final AppConfig INSTANCE = new AppConfig();
    private final Path dataDir;
    private final int maxCreditsPerSemester;
    private final Duration backupRetention;
    private AppConfig() {
        this.dataDir = Paths.get(System.getProperty("user.home"), "ccrm-data");
        this.maxCreditsPerSemester = 18;
        this.backupRetention = Duration.ofDays(30);
    }
    public static AppConfig get() { return INSTANCE; }
    public Path getDataDir() { return dataDir; }
    public int getMaxCreditsPerSemester() { return maxCreditsPerSemester; }
    public Duration getBackupRetention() { return backupRetention; }
    @Override public String toString(){ return "AppConfig[dataDir="+dataDir+"]"; }
}
