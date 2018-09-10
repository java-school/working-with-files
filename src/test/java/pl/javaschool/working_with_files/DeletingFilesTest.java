package pl.javaschool.working_with_files;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DeletingFilesTest {
    private static String BASE_DIR = System.getProperty("user.dir") + "/temp";

    @Test
    public void givenExistingFile_shouldDelete() throws IOException {
        //given
        Path path = Paths.get(BASE_DIR + "/file.txt");
        assertFalse(Files.exists(path));
        Files.createFile(path);
        assertTrue(Files.exists(path));
        //when
        Files.delete(path);
        //then
        assertFalse(Files.exists(path));
    }

    @Test(expected = IOException.class)
    public void givenNonExistingFile_whenDelete_shouldThrowException() throws IOException {
        //given
        Path path = Paths.get(BASE_DIR + "/non_existing_file.txt");
        assertFalse(Files.exists(path));
        //when
        Files.delete(path);
    }

    @Test
    public void givenNonExistingFile_whenDeleteIfExists_shouldNotFail() throws IOException {
        //given
        Path path = Paths.get(BASE_DIR + "/non_existing_file.txt");
        assertFalse(Files.exists(path));
        //when
        Files.deleteIfExists(path);
    }

    @Test(expected = DirectoryNotEmptyException.class)
    public void givenExistingNonEmptyDirectory_whenDelete_shouldThrowException() throws IOException {
        //given
        Path dirPath = Files.createTempDirectory(Paths.get(BASE_DIR), null);
        assertTrue(Files.exists(dirPath));

        Path filePath = dirPath.resolve("file.txt");
        Files.createFile(filePath);
        //when
        Files.delete(dirPath); //this throws Exception
        //then
//      How to check if directory still exists?
    }
}
