package pl.javaschool.working_with_files;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CheckFileOrFolder {
    private static String BASE_DIR = System.getProperty("user.dir") + "/temp";

    public static void main(String[] args) {
        checkIfFileOrFolderExists();

        checkIfFileOrFolderDoesNotExist();
    }

    private static void checkIfFileOrFolderExists() {
        Path path = Paths.get(BASE_DIR);

        System.out.println(String.format("File or folder: [%s] exists: %s", path, Files.exists(path)));
    }

    private static void checkIfFileOrFolderDoesNotExist() {
        Path path = Paths.get(BASE_DIR + "/non_existing.txt");

        System.out.println(String.format("File or folder: [%s] does not exist: %s", path, Files.notExists(path)));
    }
}
