package pl.javaschool.working_with_files;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CheckFileOrFolder {
    private static String BASE_DIR = System.getProperty("user.dir") + "/temp";

    public static void main(String[] args) {
        checkIfFileOrFolderExists();
    }

    private static void checkIfFileOrFolderExists() {
        Path path = Paths.get(BASE_DIR);

        System.out.println("File or folder exists: " + Files.exists(path));
    }
}
