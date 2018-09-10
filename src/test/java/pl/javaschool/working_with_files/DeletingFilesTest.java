package pl.javaschool.working_with_files;

import org.junit.Test;

import java.io.IOException;
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
}
