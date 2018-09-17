package pl.javaschool.working_with_files.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class TestUtils {

    public static void deleteRecursively(Path dirPath) {
//        deleteRecursivelyJava8Style(dirPath);
//        deleteRecursivelyOldStyle(dirPath.toFile());
//        deleteRecursivelyJava7Style(dirPath);
        deleteRecursivelyApacheCommonsStyle(dirPath);
//        deleteRecursivelySpringUtilsStyle(dirPath);

    }

    private static void deleteRecursivelyJava8Style(Path dirPath) {
        try {
            Files.walk(dirPath)
                    .map(Path::toFile)
                    .sorted((o1, o2) -> -o1.compareTo(o2))
                    .forEach(file -> file.delete());
        } catch (Exception e) {
            System.out.println(String.format("File does not exist: {%s}", e.getMessage()));
        }
    }

    private static void deleteRecursivelyOldStyle(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteRecursivelyOldStyle(file);
            }
        }
        directoryToBeDeleted.delete();
    }

    private static void deleteRecursivelyJava7Style(Path dirPath) {
        try {
            Files.walkFileTree(dirPath,
                    new SimpleFileVisitor<Path>() {
                        @Override
                        public FileVisitResult postVisitDirectory(
                                Path dir, IOException exc) throws IOException {
                            Files.delete(dir);
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult visitFile(
                                Path file, BasicFileAttributes attrs)
                                throws IOException {
                            Files.delete(file);
                            return FileVisitResult.CONTINUE;
                        }
                    });
        } catch (IOException e) {
            System.out.println(String.format("File does not exist: {%s}", e.getMessage()));
        }
    }

    private static void deleteRecursivelyApacheCommonsStyle(Path dirPath) {
        try {
            FileUtils.deleteDirectory(dirPath.toFile());
        } catch (Exception e) {
            System.out.println(String.format("File does not exist: {%s}", e.getMessage()));
        }
    }

    private static void deleteRecursivelySpringUtilsStyle(Path dirPath) {
        try {
            FileSystemUtils.deleteRecursively(dirPath);
        } catch (IOException e) {
            System.out.println(String.format("File does not exist: {%s}", e.getMessage()));
        }
    }
}
