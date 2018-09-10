package pl.javaschool.working_with_files;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

public class CheckFileOrFolderTest {
    private static String BASE_DIR = System.getProperty("user.dir") + "/temp";

    @Test
    public void givenExistingFile_whenCheckedIfExist_shouldReturnTrue() {
        //given
        Path path = Paths.get(BASE_DIR);
        //then
        assertTrue(Files.exists(path));
    }
}