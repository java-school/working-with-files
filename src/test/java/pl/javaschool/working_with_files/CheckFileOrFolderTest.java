package pl.javaschool.working_with_files;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pl.javaschool.working_with_files.utils.TestUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckFileOrFolderTest {
    private static String BASE_DIR = System.getProperty("user.dir") + "/temp";
    private static final Path BASE_PATH = Paths.get(BASE_DIR);

    @Before
    public void setUp() throws Exception {
        Files.createDirectories(BASE_PATH);
    }

    @After
    public void tearDown() {
        TestUtils.deleteRecursively(BASE_PATH);
    }

    @Test
    public void givenExistingFile_whenCheckedIfExist_shouldReturnTrue() {
        //given
        Path path = Paths.get(BASE_DIR);
        //then
        assertThat(Files.exists(path)).isTrue();
    }

    @Test
    public void givenNonExistingFile_whenCheckedIfNotExist_shouldReturnTrue() {
        //given
        Path path = Paths.get(BASE_DIR + "/non_existing.txt");
        //then
        assertThat(Files.notExists(path)).isTrue();

    }

    @Test
    public void givenDirectory_whenCheckedIfIsRegularFile_shouldReturnFalse() {
        //given
        Path path = Paths.get(BASE_DIR);
        //then
        assertThat(Files.isRegularFile(path)).isFalse();
    }

    @Test
    public void givenExistingFile_whenCheckedIfIsReadable_shouldReturnTrue() {
        //given
        Path path = Paths.get(BASE_DIR);
        //then
        assertThat(Files.isReadable(path)).isTrue();
    }

    @Test
    public void givenExistingFile_whenCheckedIfIsWritable_shouldReturnTrue() {
        //given
        Path path = Paths.get(BASE_DIR);
        //then
        assertThat(Files.isWritable(path)).isTrue();
    }

    @Test
    public void givenExistingFile_whenCheckedIfIsExecutable_shouldReturnTrue() {
        //given
        Path path = Paths.get(BASE_DIR);
        //then
        assertThat(Files.isExecutable(path)).isTrue();
    }

    @Test
    public void givenSameFilePaths_whenCheckedIfSameFile_shouldReturnTrue() throws IOException {
        //given
        Path firstPath = Paths.get(BASE_DIR);
        Path secondPath = Paths.get(BASE_DIR);
        //then
        assertThat(Files.isSameFile(firstPath, secondPath)).isTrue();
    }
}