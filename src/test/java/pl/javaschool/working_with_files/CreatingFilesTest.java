package pl.javaschool.working_with_files;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CreatingFilesTest {
    private static String BASE_DIR = System.getProperty("user.dir") + "/temp";

    @Test
    public void givenNonExistingFilePath_shouldCreateFile() throws IOException {
        //given
        String fileName = "test_file.txt";
        Path path = Paths.get(BASE_DIR + "/" + fileName);
        assertFalse(Files.exists(path));
        //when
        Files.createFile(path);
        //then
        assertTrue(Files.exists(path));
        Files.deleteIfExists(path);
    }

    @Test
    public void givenNonExistingDirectoryPath_shouldCreateDirectory() throws IOException {
        //given
        String dirName = "test_dir";
        Path path = Paths.get(BASE_DIR + "/" + dirName);

//        If we would like to create directory in non existing directory the 'createDirectory()' method will throw Exception
//        In such case we should use 'createDirectories()' method - see next example

//        path = path.resolve("sub_directory"); //uncomment this to see Test failing

        assertFalse(Files.exists(path));
        //when
        Files.createDirectory(path);
        //then
        assertTrue(Files.exists(path));
        assertFalse(Files.isRegularFile(path));
        assertTrue(Files.isDirectory(path));
        Files.deleteIfExists(path);
    }

    @Test
    public void givenNonExistingDirectoryPath_shouldCreateDirectoriesRecursively() throws IOException {
        //given
        String dirName = "test_dir";
        Path dirPath = Paths.get(BASE_DIR + "/" + dirName);
        Path subDirPath = dirPath.resolve("sub_directory");
        assertFalse(Files.exists(dirPath));
        assertFalse(Files.exists(subDirPath));
        //when
        Files.createDirectories(subDirPath);
        //then
        assertTrue(Files.exists(dirPath));
        assertTrue(Files.exists(subDirPath));
        deleteRecursively(dirPath);
    }

    private void deleteRecursively(Path dirPath) throws IOException {
        Files.walk(dirPath)
                .map(Path::toFile)
                .sorted((o1, o2) -> -o1.compareTo(o2))
                .forEach(File::delete);
    }
}