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
}