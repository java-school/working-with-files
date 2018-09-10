package pl.javaschool.working_with_files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CheckFileOrFolder {
    private static String BASE_DIR = System.getProperty("user.dir") + "/temp";

    public static void main(String[] args) throws IOException {
        checkIfFileOrFolderExists();

        checkIfFileOrFolderDoesNotExist();

        checkIfFileIsRegularFile();

        checkIfFileIsReadable();

        checkIfFileIsWritable();

        checkIfFileIsExecutable();

        checkIfTheSameFile();
    }

    private static void checkIfFileOrFolderExists() {
        Path path = Paths.get(BASE_DIR);

        System.out.println(String.format("File or folder: [%s] exists: %s", path, Files.exists(path)));
    }

    private static void checkIfFileOrFolderDoesNotExist() {
        Path path = Paths.get(BASE_DIR + "/non_existing.txt");

        System.out.println(String.format("File or folder: [%s] does not exist: %s", path, Files.notExists(path)));
    }

    private static void checkIfFileIsRegularFile() {
        Path path = Paths.get(BASE_DIR);

        System.out.println(String.format("File or folder: [%s] is regular file (regular = not a directory): %s", path, Files.isRegularFile(path)));
    }

    private static void checkIfFileIsReadable() {
        Path path = Paths.get(BASE_DIR);

        System.out.println(String.format("File or folder: [%s] is readable: %s", path, Files.isReadable(path)));
    }

    private static void checkIfFileIsWritable() {
        Path path = Paths.get(BASE_DIR);

        System.out.println(String.format("File or folder: [%s] is writable: %s", path, Files.isWritable(path)));
    }

    private static void checkIfFileIsExecutable() {
        Path path = Paths.get(BASE_DIR);

        System.out.println(String.format("File or folder: [%s] is executable: %s", path, Files.isExecutable(path)));
    }

    private static void checkIfTheSameFile() throws IOException {
        Path firstPath = Paths.get(BASE_DIR);
        Path secondPath = Paths.get(BASE_DIR);

        System.out.println(String.format("File or folder: [%s] and file or folder: [%s] is the same: %s",
                firstPath,
                secondPath,
                Files.isSameFile(firstPath, secondPath)
        ));
    }
}
