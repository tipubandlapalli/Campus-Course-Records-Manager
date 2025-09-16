package edu.ccrm.io;
import edu.ccrm.config.AppConfig;
import java.nio.file.Path;
public class AppDirs { public static Path getDataDir(){ return AppConfig.get().getDataDir(); } }
