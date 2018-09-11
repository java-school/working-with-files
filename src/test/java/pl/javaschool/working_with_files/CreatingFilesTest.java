package pl.javaschool.working_with_files;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class CreatingFilesTest {
    private static String BASE_DIR = System.getProperty("user.dir") + "/temp";

    @Test
    public void givenNonExistingFilePath_shouldCreateFile() throws IOException {
        //given
        String fileName = "test_file.txt";
        Path path = Paths.get(BASE_DIR + "/" + fileName);
        assertThat(Files.exists(path)).isFalse();
        //when
        Files.createFile(path);
        //then
        assertThat(Files.exists(path)).isTrue();

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

        assertThat(Files.exists(path)).isFalse();

        //when
        Files.createDirectory(path);

        //then
        assertThat(Files.exists(path)).isTrue();
        assertThat(Files.isRegularFile(path)).isFalse();
        assertThat(Files.isDirectory(path)).isTrue();
        Files.deleteIfExists(path);
    }

    @Test
    public void givenNonExistingDirectoryPath_shouldCreateDirectoriesRecursively() throws IOException {
        //given
        String dirName = "test_dir";
        Path dirPath = Paths.get(BASE_DIR + "/" + dirName);
        Path subDirPath = dirPath.resolve("sub_directory");
        assertThat(Files.exists(dirPath)).isFalse();
        assertThat(Files.exists(subDirPath)).isFalse();
        //when
        Files.createDirectories(subDirPath);
        //then
        assertThat(Files.exists(dirPath)).isTrue();
        assertThat(Files.exists(subDirPath)).isTrue();
        deleteRecursively(dirPath);
    }

    @Test
    public void givenNoFilePath_shouldCreateTempFileInDefaultLocation() throws IOException {
        //when
        Path path = Files.createTempFile(null, null);
        //then
        assertThat(Files.exists(path)).isTrue();
    }

    @Test
    public void givenPath_shouldCreateTempFileInSpecifiedLocation() throws IOException {
        //given
        Path dirPath = Paths.get(BASE_DIR + "/");
        //when
        Path path = Files.createTempFile(dirPath, null, null);
        //then
        assertThat(Files.exists(path)).isTrue();
    }

    @Test
    public void givenPathPrefixAndSuffix_shouldCreateTempFile() throws IOException {
        //given
        String prefix = "file_";
        String suffix = ".txt";
        Path dirPath = Paths.get(BASE_DIR + "/");
        //when
        Path path = Files.createTempFile(dirPath, prefix, suffix);
        //then
        assertThat(Files.exists(path)).isTrue();
    }

    @Test
    public void givenPathAndPrefix_shouldCreateSpecificTempDir() throws IOException {
        //given
        String prefix = "temp_dir";
        Path dirPath = Paths.get(BASE_DIR + "/");
        //when
        Path path = Files.createTempDirectory(dirPath, prefix);
        //then
        assertThat(Files.exists(path)).isTrue();
    }

    private void deleteRecursively(Path dirPath) throws IOException {
        Files.walk(dirPath)
                .map(Path::toFile)
                .sorted((o1, o2) -> -o1.compareTo(o2))
                .forEach(File::delete);
    }
}