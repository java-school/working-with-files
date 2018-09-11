package pl.javaschool.working_with_files;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class DeletingFilesTest {
    private static String BASE_DIR = System.getProperty("user.dir") + "/temp";

    @Test
    public void givenExistingFile_shouldDelete() throws IOException {
        //given
        Path path = Paths.get(BASE_DIR + "/file.txt");
        assertThat(Files.exists(path)).isFalse();
        Files.createFile(path);
        assertThat(Files.exists(path)).isTrue();

        //when
        Files.delete(path);

        //then
        assertThat(Files.exists(path)).isFalse();
    }

    @Test
    public void givenNonExistingFile_whenDelete_shouldThrowException() {
        //given
        Path path = Paths.get(BASE_DIR + "/non_existing_file.txt");
        assertThat(Files.exists(path)).isFalse();

        //when
        Throwable throwable = catchThrowable(() -> Files.delete(path));

        //then
        assertThat(throwable).isInstanceOf(IOException.class);
    }

    @Test
    public void givenNonExistingFile_whenDeleteIfExists_shouldNotFail() throws IOException {
        //given
        Path path = Paths.get(BASE_DIR + "/non_existing_file.txt");
        assertThat(Files.exists(path)).isFalse();
        //when
        Files.deleteIfExists(path);
    }

    @Test
    public void givenExistingNonEmptyDirectory_whenDelete_shouldThrowException() throws IOException {
        //given
        Path dirPath = Files.createTempDirectory(Paths.get(BASE_DIR), null);
        assertThat(Files.exists(dirPath)).isTrue();

        Path filePath = dirPath.resolve("file.txt");
        Files.createFile(filePath);
        //when
        Throwable throwable = catchThrowable(() -> Files.delete(dirPath));
        //then
        assertThat(throwable).isInstanceOf(DirectoryNotEmptyException.class);
        assertThat(Files.exists(dirPath)).isTrue();
    }
}
