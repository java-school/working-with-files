package pl.javaschool.working_with_files.utils;

import java.nio.file.Files;
import java.nio.file.Path;

public class TestUtils {

    public static void deleteRecursively(Path dirPath) {
        try {
            Files.walk(dirPath)
                    .map(Path::toFile)
                    .sorted((o1, o2) -> -o1.compareTo(o2))
                    .forEach(file -> file.delete());
        } catch (Exception e) {
            System.out.println(String.format("File does not exist: {%s}", e.getMessage()));
        }
    }
}
