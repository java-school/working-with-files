package pl.javaschool.working_with_files;

import org.junit.Test;

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
}