package files;

import java.io.IOException;
import java.nio.file.*;
import java.util.logging.Logger;

public class Backup {
    private static final Logger logger = Logger.getLogger(Backup.class.getName());

    public static void backupFiles(String directoryPath) {
        Path sourceDir = Paths.get(directoryPath);
        Path backupDir = Paths.get(directoryPath, "backup");

        try {
            Files.createDirectories(backupDir);

            try (DirectoryStream<Path> stream = Files.newDirectoryStream(sourceDir)) {
                for (Path file : stream) {
                    if (Files.isRegularFile(file)) {
                        Path destFile = backupDir.resolve(file.getFileName());
                        Files.copy(file, destFile, StandardCopyOption.REPLACE_EXISTING);
                    }
                }
            }
        } catch (IOException e) {
            logger.severe("An exception occurred: " + e.getMessage());
            logger.severe("Stack trace: " + e);
        }
    }
}
